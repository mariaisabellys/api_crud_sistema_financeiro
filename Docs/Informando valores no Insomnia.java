//cadastrando uma nova categoria
{
  "nome" : "Imposto"
}

//cadastrando uma nova pessoa
{
  "nome" : "Zé Maria",
  "endereco" : {
       "logradouro" : "Rua das Silvas",
	   "numero" : "25",
	   "bairro" : "Valentina",
	   "cep" : "58028-30",
	   "cidade" : "Joao Pessoa",
	   "estado" : "Paraiba"
  }, 
   ativo : "true"  
}

//cadastrando um Lancamento
{
  "descricao" : "Faculdade",
  "dataVencimento" : "2024-11-10",
  "valor" : 500,
  "tipo" : "DESPESA"
  "categoria" : {
    "codigo" : 5
  },
  "pessoa" : {
   "codigo" : 1
   }
}