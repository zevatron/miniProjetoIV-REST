```
Instituto Federal de Educação, Ciência e Tecnologia da Paraíba
Unidade Acadêmica de Informática
Curso de Tecnologia em Sistemas para a Internet
Disciplina: Programação Distribuída
Professor: Edemberg Rocha
Semestre: 2018.
Entrega/Defesa: 31/07/
```
# Mini-Projeto IV
API REST para Gerenciador de Filmes

**Descrição**

Uma provedora de filmes, via _streaming_ , deseja desenvolver um serviço _web_ para
gerenciamento de seus filmes. Sobre cada filme deverá ser mantidas informações sobre
título, diretor, estúdio, gênero e ano de lançamento, em uma base de dados.
Desenvolva uma aplicação RESTful, utilizando as APIs JAX-RS e JPA, para gerenciar tais
filmes. As serviços a serem oferecidos são:

## Criar filme
* Verbo HTTP: POST.
* Os dados de um filme deverão ser captados de um formulário HTML e enviados
ao servidor.
* O filme deverá ser persistido em um banco de dados.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que operação foi realizada com sucesso e retornar o filme criado em formato JSON ou;
  * Status do HTTP informando que houve um conflito de recurso (o filme já existe, caso o título já exista).

## Atualizar filme
* Verbo HTTP: PUT.
* Atualizar apenas o título de um filme.
* O id do filme, a ter o título atualizado, e o novo título do filme deverão ser passados parametrizados na URL da requisição.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso,retornando o filme atualizado, em um JSON ou;
  * Status do HTTP informando que o recurso não foi encontrado.

## Remover filme
* Verbo HTTP: DELETE.
* O id do filme a ser removido deverá ser passado parametrizado na URL da
requisição.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso e retornar, no corpo da mensagem, o filme removido no formato XML ou;
  * Status do HTTP informando que o recurso não foi encontrado.

## Recuperar um filme pelo ID
* Verbo HTTP: GET.
* O ID do filme desejado deverá ser passado via parâmetro _query_.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso, retornando o filme atualizado, em um JSON ou XML (a ser escolhido pelo cliente do serviço) ou;
  * Status do HTTP informando que o recurso não foi encontrado.

## Recuperar um filme pelo título
* Verbo HTTP: GET.
* O título do filme desejado deverá ser passado parametrizado na URL.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso e
retornar, no corpo da mensagem, a representação do filme em HTML ou;
  * Status do HTTP informando que o recurso não foi encontrado.

## Recuperar filmes por diretor
* Verbo HTTP: GET.
* O diretor desejado deverá ser passado parametrizado na URL.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso e
retornar, no corpo da mensagem, a representação do filme em HTML ou;
  * Status do HTTP informando que o recurso não foi encontrado.

## Recuperar filmes por gênero
* Verbo HTTP: GET.
* O gênero desejado deverá ser passado parametrizado na URL.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
  * Status do HTTP para informar que a operação foi realizada com sucesso e
retornar, no corpo da mensagem, a representação do filme em HTML ou;
 * Status do HTTP informando que o recurso não foi encontrado.

## Recuperar filmes por ano de lançamento
* Verbo HTTP: GET.
* O ano desejado deverá ser passado parametrizado na URL.
* Utilize as mensagens de status do HTTP como resposta ao cliente.
 * Status do HTTP para informar que a operação foi realizada com sucesso e retornar, no corpo da mensagem, a representação do filme em HTML ou;
 * Status do HTTP informando que o recurso não foi encontrado.

Desenvolva uma aplicação cliente, com uma interface _web_ , para consumir o serviço REST
descrito acima.

**Equipe:** máximo 2 alunos


