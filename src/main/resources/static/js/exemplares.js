const formExemplar =
    document.getElementById("formExemplar");


// ================================
// LIMPAR ERROS
// ================================

function limparErros() {

    document.querySelectorAll(".campo-erro")
        .forEach(campo => {
            campo.classList.remove("campo-erro");
        });

    document.querySelectorAll(".erro-validacao")
        .forEach(erro => {
            erro.textContent = "";
        });
}


// ================================
// MOSTRAR ERRO NO CAMPO
// ================================

function mostrarErro(campoId, mensagem) {

    const campo =
        document.getElementById(campoId);

    const erro =
        document.getElementById(`erro-${campoId}`);


    if (!campo || !erro) {
        return;
    }


    campo.classList.add("campo-erro");

    erro.textContent = mensagem;
}


// ================================
// MOSTRAR ERROS DO BACKEND
// ================================

function mostrarErrosBackend(erros) {

    limparErros();


    // Backend retornando:
    // { campo: "mensagem" }

    if (
        erros &&
        typeof erros === "object" &&
        !Array.isArray(erros)
    ) {

        Object.entries(erros).forEach(
            ([campo, mensagem]) => {

                mostrarErro(campo, mensagem);

            }
        );

        return;
    }


    // Backend retornando lista
    // de erros do Bean Validation

    if (Array.isArray(erros)) {

        erros.forEach(erro => {

            const campo = erro.field;

            const mensagem =
                erro.defaultMessage;

            if (campo && mensagem) {

                mostrarErro(
                    campo,
                    mensagem
                );

            }

        });

    }
}


// ================================
// NOVO EXEMPLAR
// ================================

document
    .getElementById("btnNovoExemplar")
    .addEventListener("click", function () {

        formExemplar.reset();

        document.getElementById("exemplarId").value = "";

        document.getElementById("status").value =
            "DISPONIVEL";

        limparErros();

        document.getElementById("tituloModalExemplar")
            .textContent = "Novo exemplar";

        document.getElementById("textoBotaoExemplar")
            .textContent = "Cadastrar exemplar";

    });


// ================================
// CADASTRAR / EDITAR
// ================================

formExemplar.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();

        limparErros();


        const id =
            document.getElementById("exemplarId").value;


        const livroId =
            document.getElementById("livroId").value;


        const codigoPatrimonio =
            document
                .getElementById("codigoPatrimonio")
                .value
                .trim();


        const status =
            document.getElementById("status").value;


        // ================================
        // OBJETO DO EXEMPLAR
        // ================================

        const exemplar = {

            livro: livroId
                ? {
                    id: Number(livroId)
                }
                : null,

            codigoPatrimonio:
                codigoPatrimonio || null,

            status:
                status || null

        };


        console.log(
            "Exemplar enviado:",
            exemplar
        );


        try {

            let resposta;


            // ================================
            // NOVO EXEMPLAR
            // ================================

            if (!id) {

                resposta = await fetch(
                    "/api/exemplares",
                    {

                        method: "POST",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        body:
                            JSON.stringify(exemplar)

                    }
                );

            }


                // ================================
                // EDITAR EXEMPLAR
            // ================================

            else {

                resposta = await fetch(
                    `/api/exemplares/atualizar?id=${id}`,
                    {

                        method: "PUT",

                        headers: {
                            "Content-Type": "application/json"
                        },

                        body:
                            JSON.stringify(exemplar)

                    }
                );

            }


            // ================================
            // SUCESSO
            // ================================

            if (resposta.ok) {

                window.location.href =
                    "/exemplares";

                return;

            }


            // ================================
            // ERRO
            // ================================

            const erroTexto =
                await resposta.text();


            console.error(
                "Erro:",
                erroTexto
            );


            try {

                const erros =
                    JSON.parse(erroTexto);

                mostrarErrosBackend(erros);

            } catch {

                alert(
                    "Não foi possível salvar o exemplar."
                );

            }


        } catch (erro) {

            console.error(
                "Erro:",
                erro
            );

            alert(
                "Erro ao conectar com o servidor."
            );

        }

    }
);


// ================================
// EDITAR EXEMPLAR
// ================================

async function editarExemplar(id) {

    try {

        const resposta =
            await fetch(
                `/api/exemplares/id?id=${id}`
            );


        if (!resposta.ok) {

            alert(
                "Não foi possível carregar o exemplar."
            );

            return;

        }


        const exemplar =
            await resposta.json();


        document.getElementById("exemplarId").value =
            exemplar.id;


        document.getElementById("livroId").value =
            exemplar.livro?.id || "";


        document.getElementById("codigoPatrimonio").value =
            exemplar.codigoPatrimonio || "";


        // CARREGAR STATUS

        document.getElementById("status").value =
            exemplar.status || "DISPONIVEL";


        limparErros();


        document.getElementById("tituloModalExemplar")
            .textContent = "Editar exemplar";


        document.getElementById("textoBotaoExemplar")
            .textContent = "Atualizar exemplar";


        const modal =
            bootstrap.Modal.getOrCreateInstance(
                document.getElementById("modalExemplar")
            );


        modal.show();


    } catch (erro) {

        console.error(
            "Erro:",
            erro
        );

        alert(
            "Erro ao carregar o exemplar."
        );

    }

}


// ================================
// EXCLUIR EXEMPLAR
// ================================

async function excluirExemplar(id) {

    if (
        !confirm(
            "Deseja realmente excluir este exemplar?"
        )
    ) {

        return;

    }


    try {

        const resposta =
            await fetch(
                `/api/exemplares/deletarId?id=${id}`,
                {
                    method: "DELETE"
                }
            );


        if (resposta.ok) {

            window.location.href =
                "/exemplares";

        } else {

            const erro =
                await resposta.text();

            console.error(
                "Erro:",
                erro
            );

            alert(
                "Não foi possível excluir o exemplar."
            );

        }


    } catch (erro) {

        console.error(
            "Erro:",
            erro
        );

        alert(
            "Erro ao conectar com o servidor."
        );
    }
}