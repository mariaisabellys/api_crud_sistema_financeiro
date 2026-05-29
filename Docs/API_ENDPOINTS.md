# Endpoints da API - Projeto Financeiro

A API base reside em `http://localhost:8080`. Todos os endpoints de listagem suportam paginação e ordenação via query parameters (ex: `?size=5&page=0&sort=nome,asc`).

## 1. Categorias (`/categorias`)

### Listar Categorias (Paginado)
- **URL**: `/categorias` | **Método**: `GET`
- **Resposta**: `200 OK`. Retorna apenas categorias ativas.

### Detalhar Categoria
- **URL**: `/categorias/{codigo}` | **Método**: `GET`
- **Resposta**: `200 OK`.

### Cadastrar Categoria
- **URL**: `/categorias` | **Método**: `POST`
- **Body**: `{ "nome": "Lazer" }`
- **Sucesso**: `201 Created` com header `Location`.

### Atualizar Categoria
- **URL**: `/categorias` | **Método**: `PUT`
- **Body**: `{ "codigo": 1, "nome": "Novo Nome" }`
- **Sucesso**: `200 OK`.

### Excluir Categoria (Safe Delete)
- **URL**: `/categorias/{codigo}` | **Método**: `DELETE`
- **Sucesso**: `204 No Content`. Inativa a categoria (`ativo = false`).

## 2. Pessoas (`/pessoas`)

### Listar Pessoas (Paginado)
- **URL**: `/pessoas` | **Método**: `GET`
- **Resposta**: `200 OK`. Retorna apenas pessoas ativas.

### Detalhar Pessoa
- **URL**: `/pessoas/{codigo}` | **Método**: `GET`
- **Sucesso**: `200 OK`.
- **Erro**: `404 Not Found`.

### Cadastrar Pessoa
- **URL**: `/pessoas` | **Método**: `POST`
- **Body**: `{ "nome": "Maria", "ativo": true, "endereco": { ... } }`
- **Sucesso**: `201 Created`.

### Atualizar Pessoa
- **URL**: `/pessoas` | **Método**: `PUT`
- **Body**: `{ "codigo": 1, "nome": "Nome Atualizado", "ativo": true, "endereco": { ... } }`
- **Sucesso**: `200 OK`.

### Excluir Pessoa (Safe Delete)
- **URL**: `/pessoas/{codigo}` | **Método**: `DELETE`
- **Sucesso**: `204 No Content`. Inativa a pessoa (`ativo = false`).

## 3. Lançamentos (`/lancamentos`)

### Listar Lançamentos (Paginado)
- **URL**: `/lancamentos` | **Método**: `GET`
- **Resposta**: `200 OK`. Retorna apenas lançamentos ativos.

### Detalhar Lançamento
- **URL**: `/lancamentos/{codigo}` | **Método**: `GET`
- **Sucesso**: `200 OK`.

### Cadastrar Lançamento
- **URL**: `/lancamentos` | **Método**: `POST`
- **Body**: `{ "descricao": "...", "valor": 100.0, "codigoCategoria": 1, "codigoPessoa": 1, ... }`
- **Sucesso**: `201 Created`.
- **Regra**: Pessoa deve estar ativa.

### Atualizar Lançamento
- **URL**: `/lancamentos` | **Método**: `PUT`
- **Body**: `{ "codigo": 1, "descricao": "Novo texto", "valor": 150.0, "codigoCategoria": 2, ... }`
- **Sucesso**: `200 OK`.

### Excluir Lançamento (Safe Delete)
- **URL**: `/lancamentos/{codigo}` | **Método**: `DELETE`
- **Sucesso**: `204 No Content`. Inativa o lançamento (`ativo = false`).
