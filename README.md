# App Viagem
Este mini projeto foi iniciado com o intuito de demonstrar e aprimorar os conhecimentos sobre desenvolvimento de REST API em Java com Spring Boot.

Utilizando a IDE ItelliJ inicialmente temos um projeto criado via Spring Initialzr, com as dependências de Spring Boot Dev Tools e Lombok para auxiliar na produtividade do desenvolvimento. A estruturação do projeto segue o padrão MVC com Service e Repository. Sendo que as requisições foram testadas via a aplicação Insomnia.
A ideia inicial foi utilizando do Spring Data JPA a definição das principais entidades, sendo que a entidade Pessoa foi criada para servir como base para as entidades Passageiro e Motorista. Seguindo a ideia de que seriam criados novos passageiros e motoristas diretamente, mas eles são armazenados como pessoas independentemente, então a entidade de Viagem é populada com as informações da viagem mais um motorista e lista de passageiros. Sendo que cada passageiro está associado com alguma viagem também.
O banco de dados utilizado foi o MariaDB, isso via o docker-compose. E populado via o Spring JPA Hibernate.

De modo geral o projeto traz o funcionamento padrão esperado, com as requisições sendo recebidas no Controller, que chama o Service para executar as devidas ações, que chama o Repository armazenando/alterando/pesquisando/deletando informações de acordo com o requisitado.
