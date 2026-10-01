document.getElementById("formEditora").addEventListener("submit", async function(event) {

    event.preventDefault();

    const id = document.getElementById("editoraId").value;

    const editora = {

        nome: document.getElementById("nome").value,
        cidade: document.getElementById("cidade").value,
        email: document.getElementById("email").value

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

        } else {

            alert("Não foi possível salvar a editora.");

        }

    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar com o servidor.");

    }

});


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

        document.getElementById("editoraId").value = editora.id;

        document.getElementById("nome").value =
            editora.nome || "";

        document.getElementById("cidade").value =
            editora.cidade || "";

        document.getElementById("email").value =
            editora.email || "";

        new bootstrap.Modal(
            document.getElementById("modalEditora")
        ).show();

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar a editora.");

    }

}


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