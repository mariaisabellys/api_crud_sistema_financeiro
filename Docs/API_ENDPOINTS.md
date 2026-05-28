# Endpoints da API - Projeto Financeiro

Este documento descreve os endpoints REST disponíveis no sistema.

## 1. Categorias (`/categorias`)

- `GET /categorias`: Lista todas as categorias.
- `GET /categorias/{codigo}`: Busca uma categoria por código.
- `POST /categorias`: Cadastra uma nova categoria.
- `DELETE /categorias/{codigo}`: Exclui uma categoria.

## 2. Pessoas (`/pessoas`)

- `GET /pessoas`: Lista todas as pessoas (com paginação).
- `GET /pessoas/{codigo}`: Busca uma pessoa por código.
- `POST /pessoas`: Cadastra uma nova pessoa.
- `PUT /pessoas/{codigo}`: Atualiza os dados de uma pessoa.
- `PUT /pessoas/{codigo}/ativo`: Ativa ou desativa uma pessoa.
- `DELETE /pessoas/{codigo}`: Exclui uma pessoa.

## 3. Lançamentos (`/lancamentos`)

- `GET /lancamentos`: Lista os lançamentos (com paginação e filtros).
- `GET /lancamentos/{codigo}`: Busca um lançamento por código.
- `POST /lancamentos`: Cadastra um novo lançamento.
- `PUT /lancamentos/{codigo}`: Atualiza um lançamento.
- `DELETE /lancamentos/{codigo}`: Exclui um lançamento.
