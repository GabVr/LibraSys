const toggleSenha = document.getElementById("toggleSenha");
const senha = document.getElementById("senha");

toggleSenha.addEventListener("click", function () {

    const tipo =
        senha.getAttribute("type") === "password"
            ? "text"
            : "password";

    senha.setAttribute("type", tipo);

    const icon = toggleSenha.querySelector("i");

    if (tipo === "text") {

        icon.classList.remove("bi-eye");
        icon.classList.add("bi-eye-slash");

    } else {

        icon.classList.remove("bi-eye-slash");
        icon.classList.add("bi-eye");

    }

});
