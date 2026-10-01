const formEmprestimo =
    document.getElementById("formEmprestimo");


// NOVO EMPRÉSTIMO

document
    .getElementById("btnNovoEmprestimo")
    .addEventListener("click", function () {

        formEmprestimo.reset();

        document.getElementById("emprestimoId").value = "";

        document.getElementById("textoBotaoEmprestimo")
            .textContent = "Registrar empréstimo";

    });


// CADASTRAR / EDITAR

formEmprestimo.addEventListener("submit", async function (event) {

    event.preventDefault();


    const id =
        document.getElementById("emprestimoId").value;


    const usuarioId =
        document.getElementById("usuarioId").value;


    const exemplarId =
        document.getElementById("exemplarId").value;


    const dataPrevistaDevolucao =
        document.getElementById("dataPrevistaDevolucao").value;


    const emprestimo = {

        usuario: {
            id: Number(usuarioId)
        },

        exemplar: {
            id: Number(exemplarId)
        },

        dataPrevistaDevolucao:
        dataPrevistaDevolucao

    };


    try {

        let resposta;


        // NOVO EMPRÉSTIMO

        if (!id) {

            resposta = await fetch(
                "/api/emprestimos",
                {

                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(emprestimo)

                }
            );

        }


        // EDITAR EMPRÉSTIMO

        else {

            resposta = await fetch(
                `/api/emprestimos/atualizar?id=${id}`,
                {

                    method: "PUT",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(emprestimo)

                }
            );

        }


        if (resposta.ok) {

            window.location.href = "/emprestimos";

        } else {

            const erro = await resposta.text();

            console.error("Erro:", erro);

            alert(
                id
                    ? "Não foi possível atualizar o empréstimo."
                    : "Não foi possível registrar o empréstimo."
            );

        }

    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao conectar com o servidor.");

    }

});


// EDITAR EMPRÉSTIMO

async function editarEmprestimo(id) {

    try {

        const resposta = await fetch(
            `/api/emprestimos/id?idEmprestimo=${id}`
        );


        if (!resposta.ok) {

            alert("Não foi possível carregar o empréstimo.");

            return;

        }


        const emprestimo =
            await resposta.json();


        document.getElementById("emprestimoId").value =
            emprestimo.id;


        document.getElementById("usuarioId").value =
            emprestimo.usuario.id;


        document.getElementById("exemplarId").value =
            emprestimo.exemplar.id;


        document.getElementById("dataPrevistaDevolucao").value =
            emprestimo.dataPrevistaDevolucao || "";


        document.getElementById("textoBotaoEmprestimo")
            .textContent = "Atualizar empréstimo";


        new bootstrap.Modal(
            document.getElementById("modalEmprestimo")
        ).show();


    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao carregar o empréstimo.");

    }

}


// EXCLUIR EMPRÉSTIMO

async function excluirEmprestimo(id) {

    if (!confirm(
        "Deseja realmente excluir este empréstimo?"
    )) {

        return;

    }


    try {

        const resposta = await fetch(
            `/api/emprestimos/deletarId?id=${id}`,
            {
                method: "DELETE"
            }
        );


        if (resposta.ok) {

            window.location.href = "/emprestimos";

        } else {

            const erro = await resposta.text();

            console.error("Erro:", erro);

            alert("Não foi possível excluir o empréstimo.");

        }

    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao conectar com o servidor.");

    }

}


// ABRIR DEVOLUÇÃO

// ABRIR DEVOLUÇÃO

function abrirDevolucao(id) {

    document.getElementById("devolucaoEmprestimoId").value = id;

    document.querySelector(
        '#formDevolucao input[name="dataDevolucao"]'
    ).value = new Date().toISOString().split("T")[0];

    const modalElement = document.getElementById("modalDevolucao");

    const modal = bootstrap.Modal.getOrCreateInstance(modalElement);

    modal.show();
}


// CONFIRMAR DEVOLUÇÃO

document
    .getElementById("formDevolucao")
    .addEventListener("submit", async function (event) {

        event.preventDefault();

        const id =
            document.getElementById("devolucaoEmprestimoId").value;

        const dataDevolucao =
            document.querySelector(
                '#formDevolucao input[name="dataDevolucao"]'
            ).value;

        try {

            const resposta = await fetch(
                `/api/emprestimos/devolver?id=${id}&dataDevolucao=${dataDevolucao}`,
                {
                    method: "POST"
                }
            );

            if (resposta.ok) {

                window.location.href = "/emprestimos";

            } else {

                const erro = await resposta.text();

                console.error("Erro:", erro);

                alert("Não foi possível registrar a devolução.");
            }

        } catch (erro) {

            console.error("Erro:", erro);

            alert("Erro ao conectar com o servidor.");
        }

    });