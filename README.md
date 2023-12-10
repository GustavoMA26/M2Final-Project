# DevinPharmacy Gestão de Medicamentos

O DevinPharmacy é uma API REST focada na gestão de farmácias, medicamentos e estoques. Gerencia a entrada, saída, cadastro e listagem dos mesmos.

## Features:

- [x] Cadastro de Farmácia
- [x] Cadastro de Medicamento
- [x] Cadastro de Estoque
- [x] Transferência de Estoques entre Farmácias
- [x] Listagem de Farmácias, Medicamento e Estoques

## Tecnologias utilizadas:
* Java 17
* Spring Boot 3.2.0
* Maven 4.0.0
* SGDB PostgreSQL

## Pré-Requisitos:
* _Para rodar o projeto é necessário a instalação do [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (ou versão superior)_
* _Além disto, recomenda-se uma IDE para trabalhar com o código como [Itellij](https://www.jetbrains.com/pt-br/idea/download/) ou outro_

## Como executar o projeto:

**1. Faça o clone do projeto**
```shell
git clone <https://github.com/GustavoMA26/M2Final-Project.git>
```

**2. Acesse a pasta do projeto no terminal/cmd:**
```shell
cd M2Final-Project
```

**3. Após acessar a raiz do projeto, para remover todos os arquivos gerados pelo último build e compilar o código do projeto, insira:**

```shell
mvn clean compile
```

**4. Execute a operação:**

```shell
mvn spring-boot:run
```

## Endpoints da API:

* `POST /api/inicializacao`: Insere dados iniciais nas tabelas da base de dados.
* `GET /api/farmacias`: Retorna uma lista de todas as farmácias cadastradas.
* `GET /api/farmacias/{cnpj}`: Retorna uma farmácia específica pelo CNPJ.
* `GET /api/estoque/{cnpj}`Retorna os detalhes de estoque de uma farmácia específica pelo CNPJ.
* `GET /api/medicamentos`: Retorna uma lista de todos os medicamentos cadastrados.
* `POST /api/farmacias`: Cria uma nova farmácia.
* `POST /api/medicamentos`: Cria um novo medicamento.
* `POST /api/estoque`: Cria um novo estoque.
* `DELETE /api/estoque`: Deleta o estoque de uma farmácia (indicando a venda).
* `PUT /api/estoque`: Atualiza o estoque de 2 farmácias (indicando a transferência entre 2 farmácias).


>*Desenvolvido por: Gustavo Almeida*
