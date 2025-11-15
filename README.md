# 📚 Library API

Uma API RESTful completa para gerenciamento de biblioteca, construída com **Spring Boot** e focada em design limpo e segurança robusta usando **Spring Security**.

## 💻 Tecnologias Utilizadas

* **Linguagem:** Java
* **Framework:** Spring Boot 3
* **Segurança:** Spring Security
* **Banco de Dados:** PostegresSql
* **Mapeamento:** JPA / Hibernate e MapStruct

## 📂 Estrutura do Projeto

A estrutura de pacotes segue o padrão **MVC (Model-View-Controller)** e separa as responsabilidades de forma clara, facilitando a manutenção e escalabilidade.

| Pacote | Responsabilidade |
| :--- | :--- |
| `controllers` | Lógica de requisição e resposta (endpoints). |
| `services` | Regras de negócio da aplicação. |
| `repositories` | Acesso e manipulação de dados. |
| `models` | Entidades de banco de dados (JPA). |
| `dto` | Data Transfer Objects (objetos para entrada/saída de dados). |
| `security` | Configurações do Spring Security e lógica de autenticação. |
| `exceptions` | Classes para tratamento de erros personalizados. |
| `validators` | Lógica de validação de dados de entrada. |

## 🔑 Regras de Acesso e Segurança (Spring Security)

A API utiliza dois perfis de acesso (`Role`) para controlar as permissões nas rotas: **USER** e **ADMIN**.

### 👤 Perfil USER

O perfil `USER` é o mais básico e possui permissões limitadas para consulta.

| Rota | Método HTTP | Permissão |
| :--- | :--- | :--- |
| `/livros` | `GET` | **Permitido** (Consultar) |
| `/livros` | `POST`, `PUT`, `DELETE` | **Negado** |
| `/autores` | `GET` | **Permitido** (Consultar) |
| `/autores` | `POST`, `PUT`, `DELETE` | **Negado** |
| `/users` | Qualquer | **Negado** |

### 👑 Perfil ADMIN

O perfil `ADMIN` possui acesso total para gerenciamento de livros e autores, além de ser o único capaz de **cadastrar novos usuários**.

| Rota | Método HTTP | Permissão |
| :--- | :--- | :--- |
| `/livros` | Qualquer | **Permitido** (CRUD completo) |
| `/autores` | Qualquer | **Permitido** (CRUD completo) |
| `/users` | `POST` (Cadastro) | **Permitido** |
| `/users` | `GET`, `PUT`, `DELETE` | **Permitido** (CRUD completo) |

> **Nota:** O cadastro de novos usuários já é feito com a atribuição do seu respectivo perfil (`USER` ou `ADMIN`).

## 🚀 Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone 
    ```
2.  **Configuração:**
    * Verifique e ajuste as configurações de banco de dados no arquivo `src/main/resources/application.yaml`.
3.  **Execução:**
    * Abra o projeto na sua IDE (IntelliJ IDEA, VS Code, etc.).
    * Execute a classe principal `Application.java` (localizada em `br.dev.luizmachado.LibraryApi`).
4.  **Acesso:**
    * A API estará acessível em `http://localhost:[PORTA_CONFIGURADA]`.

## 🛠️ Contribuição

Sinta-se à vontade para fazer sugestões, relatar bugs ou contribuir com melhorias!

---