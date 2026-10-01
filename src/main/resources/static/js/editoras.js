const formEditora = document.getElementById("formEditora");


// ======================================================
// LIMPAR ERROS
// ======================================================

function limparErros() {

    document.querySelectorAll(".erro-validacao").forEach(elemento => {
        elemento.remove();
    });

    document.querySelectorAll(".campo-erro").forEach(elemento => {
        elemento.classList.remove("campo-erro");
    });

}


// ======================================================
// MOSTRAR ERRO NO CAMPO
// ======================================================

function mostrarErro(campoId, mensagem) {

    const campo = document.getElementById(campoId);

    if (!campo) {
        return;
    }

    campo.classList.add("campo-erro");

    const erro = document.createElement("div");

    erro.className = "erro-validacao";
    erro.textContent = mensagem;

    campo.parentNode.appendChild(erro);

}


// ======================================================
// TRATAR ERROS DO BACKEND
// ======================================================

function mostrarErrosBackend(erros) {

    limparErros();

    if (!erros) {
        return;
    }


    // Caso o backend retorne um objeto

    if (typeof erros === "object" && !Array.isArray(erros)) {

        if (erros.nome) {
            mostrarErro("nome", erros.nome);
        }

        if (erros.cidade) {
            mostrarErro("cidade", erros.cidade);
        }

        if (erros.email) {
            mostrarErro("email", erros.email);
        }

        return;
    }


    // Caso o Spring retorne uma lista de erros

    if (Array.isArray(erros)) {

        erros.forEach(erro => {

            const campo = erro.field || erro.campo;

            const mensagem =
                erro.defaultMessage || erro.message;

            if (!campo || !mensagem) {
                return;
            }


            const campos = {

                nome: "nome",

                cidade: "cidade",

                email: "email"

            };


            if (campos[campo]) {

                mostrarErro(
                    campos[campo],
                    mensagem
                );

            }

        });

    }

}


// ======================================================
// SALVAR / ATUALIZAR EDITORA
// ======================================================

formEditora.addEventListener("submit", async function(event) {

    event.preventDefault();

    limparErros();


    const id =
        document.getElementById("editoraId").value;


    const editora = {

        nome:
        document.getElementById("nome").value,

        cidade:
        document.getElementById("cidade").value,

        email:
        document.getElementById("email").value

    };


    try {

        const resposta = await fetch(

            id
                ? `/api/editoras/atualizar?id=${id}`
                : "/api/editoras",

            {
                method: id ? "PUT" : "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(editora)
            }

        );


        if (resposta.ok) {

            window.location.href = "/editoras";

            return;
        }


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
// EDITAR EDITORA
// ======================================================

async function editarEditora(id) {

    try {

        const resposta = await fetch(
            `/api/editoras/id?id=${id}`
        );


        if (!resposta.ok) {

            alert("Não foi possível carregar a editora.");

            return;

        }


        const editora = await resposta.json();


        document.getElementById("editoraId").value =
            editora.id;

        document.getElementById("nome").value =
            editora.nome || "";

        document.getElementById("cidade").value =
            editora.cidade || "";

        document.getElementById("email").value =
            editora.email || "";


        limparErros();


        new bootstrap.Modal(
            document.getElementById("modalEditora")
        ).show();

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar a editora.");
    }

}


// ======================================================
// EXCLUIR EDITORA
// ======================================================

async function excluirEditora(id) {

    if (!confirm("Deseja realmente excluir esta editora?")) {
        return;
    }

    try {
        const resposta = await fetch(
            `/api/editoras/deletarId?id=${id}`,
            {
                method: "DELETE"
            }
        );

        if (resposta.ok) {

            window.location.href = "/editoras";
        } else {

            alert("Não foi possível excluir a editora.");
        }
    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar com o servidor.");
    }
}