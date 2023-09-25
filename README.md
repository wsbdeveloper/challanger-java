# challanger-java


# Sistema de Gerenciamento de Parceiros Fake

Este é um sistema de gerenciamento de parceiros de uma empresa, desenvolvido em Java 17, com a integração de um banco de dados H2 para fins de expor idéias e conceitos.

## Visão Geral

Este sistema permite que uma empresa gerencie informações sobre seus parceiros. Ele inclui funcionalidades para:

- Cadastrar novos parceiros.
- Visualizar informações detalhadas de parceiros.
- Excluir parceiros.
- Realizar consultas e filtragens de parceiros com base em diferentes critérios.

## Requisitos

Para executar o sistema em sua máquina local, você precisará das seguintes ferramentas e tecnologias:

- **Java 17**: Certifique-se de ter o Java 17 instalado.

- **Docker**: Dentro do sistema há um arquivo docker-compose com as configurações do Redis.

## Configuração do Banco de Dados - Redis e Postgres 

No meu caso, escolhi o h2 para utilizar as funcionalidades porém poderia muito bem ser um postgres, deixei com o arquivo docker compose uma configuração para subir nosso banco postgres e utiliza-lo caso for necessário essa migração. Questões de avaliação deixei o H2.

Verifique se a porta **5432/tcp** está sendo utilizada, se caso estiver mude para rodar o comando dentro da pasta docker:

```bash
docker-compose up
```


