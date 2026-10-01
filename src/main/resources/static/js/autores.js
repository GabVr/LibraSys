const formAutor = document.getElementById("formAutor");

formAutor.addEventListener("submit", async function(event) {

    event.preventDefault();

    const id = document.getElementById("autorId").value;

    const autor = {

        nome: document.getElementById("nome").value,
        nacionalidade: document.getElementById("nacionalidade").value,
        dataNascimento: document.getElementById("dataNascimento").value

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

        } else {

            alert("Não foi possível salvar o autor.");

        }

    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar com o servidor.");

    }

});


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

        document.getElementById("autorId").value = autor.id;

        document.getElementById("nome").value =
            autor.nome || "";

        document.getElementById("nacionalidade").value =
            autor.nacionalidade || "";

        document.getElementById("dataNascimento").value =
            autor.dataNascimento || "";

        new bootstrap.Modal(
            document.getElementById("modalAutor")
        ).show();

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar o autor.");

    }

}


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
