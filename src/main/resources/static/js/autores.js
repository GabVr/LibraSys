const formAutor = document.getElementById("formAutor");


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


function mostrarErro(campoId, mensagem) {

    const campo =
        document.getElementById(campoId);

    const erro =
        document.getElementById(`erro-${campoId}`);


    if (!campo || !erro) {
        console.error(
            "Campo de erro não encontrado:",
            campoId
        );
        return;
    }


    campo.classList.add("campo-erro");

    erro.textContent = mensagem;
}


function mostrarErrosBackend(erros) {

    limparErros();

    console.log("Erros recebidos:", erros);


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


    if (Array.isArray(erros)) {

        erros.forEach(erro => {

            if (erro.field && erro.defaultMessage) {

                mostrarErro(
                    erro.field,
                    erro.defaultMessage
                );

            }

        });

    }
}

// ======================================================
// SALVAR / ATUALIZAR
// ======================================================

formAutor.addEventListener("submit", async function(event) {

    event.preventDefault();

    limparErros();


    const id =
        document.getElementById("autorId").value;


    const autor = {

        nome:
        document.getElementById("nome").value,

        nacionalidade:
        document.getElementById("nacionalidade").value,

        dataNascimento:
            document.getElementById("dataNascimento").value === ""
                ? null
                : document.getElementById("dataNascimento").value

    };


    try {

        const resposta = await fetch(

            id
                ? `/api/autores/atualizar?idAutor=${id}`
                : "/api/autores",

            {
                method: id ? "PUT" : "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(autor)
            }

        );


        if (resposta.ok) {

            window.location.href = "/autores";

            return;
        }


        // Tenta pegar as mensagens do backend

        let erros;

        try {

            erros = await resposta.json();

        } catch {

            erros = null;

        }


        mostrarErrosBackend(erros);


    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar com o servidor.");

    }

});


// ======================================================
// EDITAR AUTOR
// ======================================================

async function editarAutor(id) {

    try {

        const resposta = await fetch(
            `/api/autores/id?idAutor=${id}`
        );


        if (!resposta.ok) {

            alert("Não foi possível carregar o autor.");

            return;

        }


        const autor = await resposta.json();


        document.getElementById("autorId").value =
            autor.id;

        document.getElementById("nome").value =
            autor.nome || "";

        document.getElementById("nacionalidade").value =
            autor.nacionalidade || "";

        document.getElementById("dataNascimento").value =
            autor.dataNascimento || "";


        limparErros();


        new bootstrap.Modal(
            document.getElementById("modalAutor")
        ).show();


    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar o autor.");

    }

}


// ======================================================
// EXCLUIR AUTOR
// ======================================================

async function excluirAutor(id) {

    if (!confirm("Deseja realmente excluir este autor?")) {
        return;
    }


    try {

        const resposta = await fetch(
            `/api/autores/deletarId?idAutor=${id}`,
            {
                method: "DELETE"
            }
        );


        if (resposta.ok) {

            window.location.href = "/autores";

        } else {

            alert("Não foi possível excluir o autor.");

        }


    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar com o servidor.");

    }

}