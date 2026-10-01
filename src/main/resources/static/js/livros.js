const formLivro = document.getElementById("formLivro");
const btnSalvarLivro = document.getElementById("btnSalvarLivro");

btnSalvarLivro.addEventListener("click", async function () {

    const id = document.getElementById("livroId").value;

    const autorIds = Array.from(
        document.getElementById("autorIds").selectedOptions
    ).map(option => Number(option.value));

    const livro = {
        titulo: document.getElementById("titulo").value,
        isbn: document.getElementById("isbn").value,
        anoPublicacao: Number(
            document.getElementById("anoPublicacao").value
        ),
        categoria: document.getElementById("categoria").value,
        editora: {
            id: Number(
                document.getElementById("editoraId").value
            )
        },
        autores: autorIds.map(id => ({
            id: id
        }))
    };

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
    } else {
        alert("Não foi possível salvar o livro.");
    }

});


async function editarLivro(id) {

    const resposta = await fetch(`/api/livros/id?id=${id}`);

    if (!resposta.ok) {
        alert("Não foi possível carregar o livro.");
        return;
    }

    const livro = await resposta.json();

    document.getElementById("livroId").value = livro.id;
    document.getElementById("titulo").value = livro.titulo || "";
    document.getElementById("isbn").value = livro.isbn || "";
    document.getElementById("anoPublicacao").value =
        livro.anoPublicacao || "";
    document.getElementById("categoria").value =
        livro.categoria || "";

    document.getElementById("editoraId").value =
        livro.editora ? livro.editora.id : "";

    const selectAutores = document.getElementById("autorIds");

    Array.from(selectAutores.options).forEach(option => {

        option.selected = livro.autores?.some(
            autor => Number(autor.id) === Number(option.value)
        );

    });

    btnSalvarLivro.innerHTML =
        '<i class="bi bi-check-lg"></i> Atualizar livro';

    new bootstrap.Modal(
        document.getElementById("modalLivro")
    ).show();

}


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
