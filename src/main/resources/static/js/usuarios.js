const formUsuario = document.getElementById("formUsuario");


// CADASTRAR OU EDITAR

formUsuario.addEventListener("submit", async function(event) {

    event.preventDefault();

    const id = document.getElementById("usuarioId").value;

    const usuario = {

        nome: document.getElementById("nome").value,
        cpf: document.getElementById("cpf").value,
        telefone: document.getElementById("telefone").value,
        email: document.getElementById("email").value,
        senha: document.getElementById("senha").value,
        ativo: true,
        tipo: document.getElementById("tipo").value

    };


    try {

        const resposta = await fetch(

            id
                ? `/api/usuarios/atualizar?id=${id}`
                : "/api/usuarios",

            {

                method: id ? "PUT" : "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(usuario)

            }

        );


        if (resposta.ok) {

            window.location.href = "/usuarios";

        } else {

            const erro = await resposta.text();

            console.error("Erro:", erro);

            alert("Não foi possível salvar o usuário.");

        }

    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao conectar com o servidor.");

    }

});


// EDITAR USUÁRIO

async function editarUsuario(id) {

    try {

        const resposta = await fetch(
            `/api/usuarios/id?id=${id}`
        );


        if (!resposta.ok) {

            alert("Não foi possível carregar o usuário.");

            return;

        }


        const usuario = await resposta.json();


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

        document.getElementById("senha").value =
            "";

        document.getElementById("tipo").value =
            usuario.tipo || "";


        new bootstrap.Modal(
            document.getElementById("modalUsuario")
        ).show();


    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao carregar o usuário.");

    }

}


// EXCLUIR USUÁRIO

async function excluirUsuario(id) {

    if (!confirm("Deseja realmente excluir este usuário?")) {

        return;

    }


    try {

        const resposta = await fetch(
            `/api/usuarios/deletarId?id=${id}`,
            {
                method: "DELETE"
            }
        );


        if (resposta.ok) {

            window.location.href = "/usuarios";

        } else {

            const erro = await resposta.text();

            console.error("Erro:", erro);

            alert("Não foi possível excluir o usuário.");

        }

    } catch (erro) {

        console.error("Erro:", erro);

        alert("Erro ao conectar com o servidor.");

    }

}


// LIMPAR FORMULÁRIO AO CLICAR EM "NOVO USUÁRIO"

document.getElementById("btnNovoUsuario")
    .addEventListener("click", function() {

        formUsuario.reset();

        document.getElementById("usuarioId").value = "";

    });