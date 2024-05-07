# Quarkus Project

Este é um projeto de exemplo para demonstrar como criar um aplicativo usando o Quarkus.

## Pré-requisitos

- Java 11 ou superior instalado
- Maven
- Docker e Docker Compose (opcional, apenas se você quiser usar recursos como banco de dados em contêineres)

## Como usar

1. Clone este repositório: `git clone https://github.com/acbueno/quarkus-project.git`
2. Navegue até o diretório do projeto: `cd quarkus-project`
3. Execute o aplicativo Quarkus: `./mvnw compile quarkus:dev`
4. Acesse `http://localhost:8080` para interagir com o aplicativo.

## Recursos incluídos

- Exemplos de endpoints RESTful
- Configuração de banco de dados com Hibernate e Panache
- Uso de Quarkus Extensions (RESTEasy, REST Client, Hibernate ORM, Panache)
- Utilização de recursos do Quarkus como Injeção de Dependência e Profiles

## Configuração do Banco de Dados

O projeto está configurado para usar um banco de dados H2 em memória por padrão. Você pode alterar as configurações do banco de dados no arquivo `application.properties`.

## Contribuição

Sinta-se à vontade para contribuir com novas funcionalidades, correções de bugs ou melhorias de código. Basta enviar um pull request!

## Licença

Este projeto está licenciado sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.
