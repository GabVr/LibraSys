const formUsuario =
    document.getElementById("formUsuario");


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
            erro.remove();
        });
}


// ================================
// MOSTRAR ERRO NO CAMPO
// ================================

function mostrarErro(campoId, mensagem) {

    const campo =
        document.getElementById(campoId);

    if (!campo) {
        return;
    }

    campo.classList.add("campo-erro");

    const erro =
        document.createElement("div");

    erro.className = "erro-validacao";

    erro.textContent = mensagem;

    campo.parentNode.appendChild(erro);
}


// ================================
// MOSTRAR ERROS DO BACKEND
// ================================

function mostrarErrosBackend(erros) {

    limparErros();


    // Caso o backend retorne:
    // { campo: "mensagem" }

    if (
        erros &&
        typeof erros === "object" &&
        !Array.isArray(erros)
    ) {

        Object.entries(erros).forEach(
            ([campo, mensagem]) => {

                mostrarErro(
                    campo,
                    mensagem
                );

            }
        );

        return;
    }


    // Caso o backend retorne uma lista
    // de erros do Bean Validation

    if (Array.isArray(erros)) {

        erros.forEach(erro => {

            const campo =
                erro.field;

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
// CADASTRAR OU EDITAR
// ================================

formUsuario.addEventListener(
    "submit",
    async function(event) {

        event.preventDefault();

        limparErros();


        const id =
            document.getElementById("usuarioId").value;


        const usuario = {

            nome:
                document.getElementById("nome").value.trim(),

            cpf:
                document.getElementById("cpf").value.trim(),

            telefone:
                document.getElementById("telefone").value.trim(),

            email:
                document.getElementById("email").value.trim(),

            senha:
            document.getElementById("senha").value,

            ativo: true,

            tipo:
            document.getElementById("tipo").value

        };


        try {

            const resposta =
                await fetch(

                    id
                        ? `/api/usuarios/atualizar?id=${id}`
                        : "/api/usuarios",

                    {

                        method:
                            id ? "PUT" : "POST",

                        headers: {
                            "Content-Type":
                                "application/json"
                        },

                        body:
                            JSON.stringify(usuario)

                    }

                );


            // ================================
            // SUCESSO
            // ================================

            if (resposta.ok) {

                window.location.href =
                    "/usuarios";

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
                    "Não foi possível salvar o usuário."
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
// EDITAR USUÁRIO
// ================================

async function editarUsuario(id) {

    try {

        const resposta =
            await fetch(
                `/api/usuarios/id?id=${id}`
            );


        if (!resposta.ok) {

            alert(
                "Não foi possível carregar o usuário."
            );

            return;

        }


        const usuario =
            await resposta.json();


        document.getElementById("usuarioId").value =
            usuario.id;


        document.getElementById("nome").value =
            usuario.nome || "";


        document.getElementById("cpf").value =
            usuario.cpf || "";


        document.getElementById("telefone").value =
            usuario.telefone || "";


        document.getElementById("email").value =
            usuario.email || "";


        // Não carregar a senha existente
        document.getElementById("senha").value =
            "";


        document.getElementById("tipo").value =
            usuario.tipo || "";


        limparErros();


        new bootstrap.Modal(
            document.getElementById("modalUsuario")
        ).show();


    } catch (erro) {

        console.error(
            "Erro:",
            erro
        );

        alert(
            "Erro ao carregar o usuário."
        );

    }

}


// ================================
// EXCLUIR USUÁRIO
// ================================

async function excluirUsuario(id) {

    if (
        !confirm(
            "Deseja realmente excluir este usuário?"
        )
    ) {

        return;

    }


    try {

        const resposta =
            await fetch(
                `/api/usuarios/deletarId?id=${id}`,
                {
                    method: "DELETE"
                }
            );


        if (resposta.ok) {

            window.location.href =
                "/usuarios";

        } else {

            const erro =
                await resposta.text();

            console.error(
                "Erro:",
                erro
            );

            alert(
                "Não foi possível excluir o usuário."
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


// ================================
// NOVO USUÁRIO
// ================================

document
    .getElementById("btnNovoUsuario")
    .addEventListener(
        "click",
        function() {

            formUsuario.reset();

            document.getElementById(
                "usuarioId"
            ).value = "";

            limparErros();

        }
    );