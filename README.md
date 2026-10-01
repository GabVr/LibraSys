# 📚 LibraSys

Sistema de gerenciamento de biblioteca desenvolvido como projeto acadêmico, com o objetivo de facilitar o controle de livros, autores, editoras, exemplares, usuários e empréstimos.

O sistema permite gerenciar o acervo da biblioteca, controlar a disponibilidade dos exemplares, registrar empréstimos e devoluções e controlar o acesso às funcionalidades de acordo com o tipo de usuário.

---

## 🛠️ Tecnologias utilizadas

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Data JPA**
- **Spring Security**
- **Hibernate**
- **MySQL 8**
- **Thymeleaf**
- **HTML, CSS e JavaScript**
- **Bootstrap 5**
- **Maven**
- **Docker e Docker Compose**

---

## 📋 Principais funcionalidades

- 📖 Cadastro, edição e exclusão de livros
- ✍️ Gerenciamento de autores
- 🏢 Gerenciamento de editoras
- 📦 Controle de exemplares e seus status
- 👥 Cadastro e gerenciamento de usuários
- 🔄 Registro de empréstimos
- ↩️ Registro de devoluções
- 📊 Controle de empréstimos ativos, devolvidos e atrasados
- 🔐 Login e controle de acesso por tipo de usuário
- 🔌 API REST para operações do sistema

---

## 🔐 Controle de acesso

O sistema utiliza Spring Security para autenticação e autorização.

- ADMIN: acesso às funcionalidades administrativas do sistema.
- USUARIO: acesso às funcionalidades destinadas ao usuário comum.


As senhas são armazenadas utilizando BCrypt.

--- 

## 🗄️ Estrutura do sistema

O projeto utiliza uma arquitetura dividida em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
MySQL
```
---
## Principais entidades:

```text
1. Livro
2. Autor
3. Editora
4. Exemplar
5. Usuario
6. Emprestimo
```
---
Um livro pode possuir vários autores e vários exemplares. Os empréstimos relacionam usuários e exemplares, permitindo acompanhar todo o processo de retirada e devolução.

## 🚀 Como executar

### Pré-requisitos

- **Docker**
- **Docker Compose**

Não é necessário instalar Java, Maven ou MySQL separadamente.

### 1. Clonar o projeto

```bash
git clone <url-do-repositorio>
cd LibraSys
```
### 2. Executar

```bash
docker compose up --build
```

Para executar em segundo plano:

```bash
docker compose up --build -d
```
---
## Acesse:

http://localhost:8080

---
### 4. Encerrar
```bash
docker compose down
```

##### Para resetar o banco de dados:

```bash
docker compose down -v
```
⚠️ down -v remove os dados persistidos do banco.

## 🧪 Testando a API com Postman

O LibraSys possui uma API REST que pode ser testada utilizando o **Postman**.

Com a aplicação executando em `http://localhost:8080`, alguns exemplos:

### 📚 Livros

**Listar livros**
```http
GET http://localhost:8080/api/livros
```
Buscar livro por ID
```http
GET http://localhost:8080/api/livros/id?id=1
```

Cadastrar livro
```http
POST http://localhost:8080/api/livros
```

Um exemplo de JSON 

```
{
  "titulo": "Dom Casmurro",
  "isbn": "9788535910663",
  "anoPublicacao": 1899,
  "categoria": "Literatura",
  "editora": {
    "id": 1
  },
  "autores": [
    {
      "id": 1
    }
  ]
}
```

### ⚠️ Observação

aqui para funcionar é necessário ter uma autor cadastrado

## Resumo de alguns caminhos para testar no postman

### ✍️ Autores
```http
GET    /api/autores
GET    /api/autores/id?idAutor=1
GET    /api/autores/nome?nomeAutor=Machado
POST   /api/autores
PUT    /api/autores/atualizar?idAutor=1
DELETE /api/autores/deletarId?idAutor=1
```

### 🏢 Editoras
```http
GET    /api/editoras
GET    /api/editoras/id?id=1
POST   /api/editoras
PUT    /api/editoras/atualizar?id=1
DELETE /api/editoras/deletarId?id=1
```

### 👤 Usuários

```http
GET    /api/usuarios
GET    /api/usuarios/id?id=1
POST   /api/usuarios
PUT    /api/usuarios/atualizar?id=1
DELETE /api/usuarios/deletarId?id=1
```

### 📦 Exemplares

```http
GET    /api/exemplares
GET    /api/exemplares/id?id=1
POST   /api/exemplares
PUT    /api/exemplares/atualizar?id=1
DELETE /api/exemplares/deletarId?id=1
```

### 🔄 Empréstimos

```http
GET    /api/emprestimos
GET    /api/emprestimos/id?idEmprestimo=1
GET    /api/emprestimos/situacao?situacao=ATIVO
POST   /api/emprestimos
PUT    /api/emprestimos/atualizar?id=1
DELETE /api/emprestimos/deletarId?id=1
DELETE /api/emprestimos/deletarStatus?situacao=ATIVO
```

💡 Para POST e PUT, utilize Body → raw → JSON no Postman.

---

## 🎓 Projeto acadêmico

Projeto desenvolvido para fins acadêmicos no curso de Análise e Desenvolvimento de Sistemas — IFPE.

Versão: 0.0.1-SNAPSHOT
Status: Em desenvolvimento

