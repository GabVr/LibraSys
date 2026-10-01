const formLivro = document.getElementById("formLivro");
const btnSalvarLivro = document.getElementById("btnSalvarLivro");


// ======================================================
// LIMPAR MENSAGENS DE VALIDAÇÃO
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
// MOSTRAR ERRO EM UM CAMPO
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
// INTERPRETAR ERROS DO SPRING VALIDATION
// ======================================================

function mostrarErrosBackend(erros) {

    limparErros();

    if (!erros) {
        return;
    }

    /*
     * Caso o backend retorne:
     *
     * {
     *   "titulo": "Você deve informar o título do livro",
     *   "isbn": "Você deve informar o isbn"
     * }
     */

    if (typeof erros === "object" && !Array.isArray(erros)) {

        if (erros.titulo) {
            mostrarErro("titulo", erros.titulo);
        }

        if (erros.isbn) {
            mostrarErro("isbn", erros.isbn);
        }

        if (erros.categoria) {
            mostrarErro("categoria", erros.categoria);
        }

        if (erros.anoPublicacao) {
            mostrarErro("anoPublicacao", erros.anoPublicacao);
        }

        if (erros.editora) {
            mostrarErro("editoraId", erros.editora);
        }

        return;
    }

    /*
     * Caso o backend retorne uma lista de erros.
     */

    if (Array.isArray(erros)) {

        erros.forEach(erro => {

            const campo = erro.field || erro.campo;
            const mensagem = erro.defaultMessage || erro.message;

            if (!campo || !mensagem) {
                return;
            }

            const campos = {
                titulo: "titulo",
                isbn: "isbn",
                categoria: "categoria",
                anoPublicacao: "anoPublicacao",
                editora: "editoraId"
            };

            if (campos[campo]) {
                mostrarErro(campos[campo], mensagem);
            }

        });

    }

}


// ======================================================
// SALVAR / ATUALIZAR LIVRO
// ======================================================

btnSalvarLivro.addEventListener("click", async function () {

    limparErros();

    const id = document.getElementById("livroId").value;

    const autorIds = Array.from(
        document.getElementById("autorIds").selectedOptions
    ).map(option => Number(option.value));


    const ano = document.getElementById("anoPublicacao").value;

    const editoraId = document.getElementById("editoraId").value;


    const livro = {

        titulo: document.getElementById("titulo").value,

        isbn: document.getElementById("isbn").value,

        /*
         * Se estiver vazio, enviamos null.
         * Isso permite que o @NotNull do backend funcione.
         */
        anoPublicacao: ano === "" ? null : Number(ano),

        categoria: document.getElementById("categoria").value,

        editora: editoraId === ""
            ? null
            : {
                id: Number(editoraId)
            },

        autores: autorIds.map(id => ({
            id: id
        }))

    };


    try {

        const resposta = await fetch(
            id
                ? `/api/livros/atualizar?id=${id}`
                : "/api/livros",
            {
                method: id ? "PUT" : "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(livro)
            }
        );


        if (resposta.ok) {

            window.location.href = "/livros";

            return;
        }


        // Tenta obter os erros retornados pelo backend

        let erros;

        try {
            erros = await resposta.json();
        } catch {
            erros = null;
        }


        mostrarErrosBackend(erros);


    } catch (erro) {

        console.error(erro);

        alert("Não foi possível conectar com o servidor.");

    }

});


// ======================================================
// EDITAR LIVRO
// ======================================================

async function editarLivro(id) {

    const resposta = await fetch(`/api/livros/id?id=${id}`);


    if (!resposta.ok) {

        alert("Não foi possível carregar o livro.");

        return;
    }


    const livro = await resposta.json();


    document.getElementById("livroId").value =
        livro.id;

    document.getElementById("titulo").value =
        livro.titulo || "";

    document.getElementById("isbn").value =
        livro.isbn || "";

    document.getElementById("anoPublicacao").value =
        livro.anoPublicacao || "";

    document.getElementById("categoria").value =
        livro.categoria || "";


    document.getElementById("editoraId").value =
        livro.editora
            ? livro.editora.id
            : "";


    const selectAutores =
        document.getElementById("autorIds");


    Array.from(selectAutores.options).forEach(option => {

        option.selected =
            livro.autores?.some(
                autor =>
                    Number(autor.id) ===
                    Number(option.value)
            );

    });


    btnSalvarLivro.innerHTML =
        '<i class="bi bi-check-lg"></i> Atualizar livro';


    limparErros();


    new bootstrap.Modal(
        document.getElementById("modalLivro")
    ).show();

}


// ======================================================
// EXCLUIR LIVRO
// ======================================================

async function excluirLivro(id) {

    if (!confirm("Tem certeza que deseja excluir este livro?")) {
        return;
    }


    const resposta = await fetch(
        `/api/livros/deletarId?id=${id}`,
        {
            method: "DELETE"
        }
    );


    if (resposta.ok) {

        window.location.href = "/livros";

    } else {

        alert("Não foi possível excluir o livro.");

    }

}