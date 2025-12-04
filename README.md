# 🛠️ Gerenciamento de Oficina Mecânica (API)

Bem-vindo ao projeto **Gerenciamento de Oficina Mecânica**! Esta é uma API RESTful profissional desenvolvida com **Spring Boot** para facilitar a administração e organização das atividades diárias de uma oficina mecânica.

## 📋 Sobre o Projeto

O sistema oferece uma solução robusta para o controle de **clientes**, **funcionários** e **veículos**. Diferente da versão anterior baseada em console, esta versão utiliza uma arquitetura moderna de microsserviços/API, com persistência em banco de dados e documentação automática.

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as melhores práticas e tecnologias do mercado:

- **[Java 17](https://www.oracle.com/java/)**: Linguagem base do projeto.
- **[Spring Boot 3](https://spring.io/projects/spring-boot)**: Framework principal para desenvolvimento rápido e configuração simplificada.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa)**: Para persistência de dados e abstração de repositórios.
- **[H2 Database](https://www.h2database.com/)**: Banco de dados em memória para desenvolvimento e testes rápidos.
- **[Lombok](https://projectlombok.org/)**: Para redução de código boilerplate (Getters, Setters, Construtores).
- **[Maven](https://maven.apache.org/)**: Gerenciamento de dependências e build.
- **[Swagger / OpenAPI](https://swagger.io/)**: Documentação viva e interativa da API.

## 📂 Estrutura do Projeto

A arquitetura segue o padrão de camadas (Layered Architecture) para garantir desacoplamento e manutenibilidade:

```
src/main/java/com/oficina/gerenciamento/
├── controller/   # Camada de API (REST Controllers)
├── service/      # Regras de Negócio (Services)
├── repository/   # Acesso a Dados (JPA Repositories)
├── entity/       # Modelo de Dados (JPA Entities)
├── dto/          # Objetos de Transferência de Dados (DTOs)
├── enums/        # Enumerações
└── GerenciamentoApplication.java # Classe Principal
```

## ✨ Funcionalidades (Endpoints)

A API fornece endpoints para gerenciamento completo (CRUD):

- **Clientes**: Cadastro, listagem, atualização e remoção.
- **Funcionários**: Gestão de equipe, cargos e salários.
- **Veículos**: Controle de carros e motos, associados aos clientes.

## ⚙️ Como Executar

### Pré-requisitos

- **Java JDK 17** ou superior instalado.
- **Maven** (opcional, pois o projeto inclui o wrapper `mvnw`).

### Passos

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/gerenciamento-mecanica.git
   cd gerenciamento-mecanica/gerenciamento
   ```

2. **Execute a aplicação**:
   Utilize o Maven Wrapper incluído para garantir a versão correta das dependências:
   
   **Windows:**
   ```powershell
   .\mvnw spring-boot:run
   ```
   
   **Linux/Mac:**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse a Documentação da API**:
   Após iniciar, acesse o Swagger UI para testar os endpoints:
   👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

4. **Console do Banco de Dados (H2)**:
   Para inspecionar o banco de dados diretamente:
   👉 [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - **JDBC URL**: `jdbc:h2:mem:testdb` (ou conforme configurado no `application.properties`)
   - **User**: `sa`
   - **Password**: (vazio ou conforme configurado)

## 👥 Autores

Este projeto foi desenvolvido por:

- **João Vitor Lucini**

---
*Projeto atualizado para demonstrar conhecimentos em desenvolvimento Web Back-end com Spring Boot e Arquitetura de Software.*
