# Sistema de Controle Financeiro - API REST

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.1-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)

Este projeto é uma API REST para controle financeiro pessoal ou empresarial, desenvolvida como parte da disciplina de **Back-End Avançado I**. A aplicação permite gerenciar categorias, pessoas e lançamentos (receitas e despesas) com foco em boas práticas de desenvolvimento e maturidade REST.

## 🚀 Funcionalidades Principais

- **Gestão de Categorias**: Cadastro, listagem, atualização e exclusão lógica.
- **Gestão de Pessoas**: Controle de cadastros com endereços e status ativo/inativo.
- **Controle de Lançamentos**: Registro de receitas e despesas vinculadas a pessoas e categorias.
- **Validações de Negócio**: Impedimento de lançamentos para pessoas inativas.
- **Exclusão Lógica (Soft Delete)**: Inativação de registros em vez de remoção física para manter o histórico.
- **Tratamento de Erros**: Retornos padronizados para erros de validação e recursos não encontrados.
- **Paginação e Ordenação**: Endpoints de listagem eficientes para grandes volumes de dados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java 21
- **Framework**: Spring Boot 3.4.1
- **Persistência**: Spring Data JPA / Hibernate
- **Banco de Dados**: MySQL 8.0
- **Migrações**: Flyway
- **Produtividade**: Lombok
- **Documentação da API**: Markdown detalhado em `/Docs`

## 📂 Estrutura do Projeto

O projeto está dividido em duas partes principais:
- `sistema.financeiro/`: Contém todo o código fonte da aplicação Spring Boot.
- `Docs/`: Documentação técnica detalhada, diagramas e guias de uso.

## 📖 Como Começar

### Pré-requisitos
- JDK 21 ou superior.
- Maven 3.6+.
- MySQL Server ativo.

### Configuração do Banco de Dados

Para maior segurança, o projeto utiliza variáveis de ambiente para gerenciar as credenciais do banco de dados. Você pode configurá-las no seu sistema ou diretamente na sua IDE (IntelliJ, VS Code, etc).

1.  Crie um banco de dados chamado `sistema_financeiro_api` no seu MySQL.
2.  Configure as seguintes variáveis de ambiente:
    -   `DB_URL`: URL de conexão (Ex: `jdbc:mysql://localhost:3306/sistema_financeiro_api`)
    -   `DB_USER`: Seu usuário do MySQL (Ex: `root`)
    -   `DB_PASSWORD`: Sua senha do MySQL

*Caso as variáveis não sejam informadas, o Spring Boot tentará usar os valores padrão definidos em `application.properties` (localhost, root, sem senha).*

### Executando a Aplicação
```bash
cd sistema.financeiro
./mvnw spring-boot:run
```

## 📄 Documentação Detalhada

Para entender a fundo o funcionamento da API, consulte a nossa **[Central de Documentação em /Docs](Docs/README.md)**. Lá você encontrará:
- [Guia de Endpoints](Docs/API_ENDPOINTS.md)
- [Modelo de Dados](Docs/RELATIONAL_MODEL.md)
- [Arquitetura](Docs/ARCHITECTURE.md)
- [Guia de Testes (Insomnia)](Docs/GUIA_TESTES_INSOMNIA.md)

---
*Desenvolvido por Maria Isabelly Silva Santos - P3 Sistemas para Internet (UNIESP)*
