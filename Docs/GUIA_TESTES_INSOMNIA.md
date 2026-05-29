# Guia de Testes no Insomnia: Sistema Financeiro (CRUD Completo)

Este guia atualizado cobre os novos endpoints de atualização e as regras de Safe Delete.

---

## 1. Categorias (`/categorias`)

### Cadastrar
- `POST /categorias` | Body: `{ "nome": "Saúde" }` -> `201 Created`.

### Atualizar
- `PUT /categorias` | Body: `{ "codigo": 1, "nome": "Saúde e Bem Estar" }` -> `200 OK`.

### Excluir (Inativar)
- `DELETE /categorias/1` -> `204 No Content`.

---

## 2. Pessoas (`/pessoas`)

### Cadastrar
- `POST /pessoas` | Body completo com endereço -> `201 Created`.

### Atualizar
- `PUT /pessoas` | Body: `{ "codigo": 1, "nome": "Novo Nome" }` -> `200 OK`.

### Listar (Verificar Safe Delete)
- `GET /pessoas` -> Deve retornar apenas as que estão com `ativo: true`.

---

## 3. Lançamentos (`/lancamentos`)

### Cadastrar
- `POST /lancamentos` | Body com `codigoCategoria` e `codigoPessoa`.
- **Teste de Regra**: Tente cadastrar para uma pessoa que foi inativada. -> Deve retornar `400 Bad Request`.

### Atualizar
- `PUT /lancamentos` | Body: `{ "codigo": 1, "valor": 500.0, "codigoCategoria": 2 }` -> `200 OK`.

### Listar (Paginado)
- `GET /lancamentos?size=2&page=0&sort=valor,desc` -> `200 OK`.

---

## 4. Validações e Erros

### Teste de 404
- `GET /pessoas/999999` -> `404 Not Found` formatado pelo `TratadorDeErros`.

### Teste de 400 (Bean Validation)
- `POST /categorias` | Body: `{ "nome": "" }` -> `400 Bad Request` com detalhamento do erro.
