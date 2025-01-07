# Microserviços: Java - Spring boot
## Descrição do projeto
1. Criação de um sistama de eventos e ingressos por meio de 2 microserviços que permite fazer o cadastro de um evento e a compra de ingressos do evento.
## Tecnologias usadas:
* Mensageria: RabbitMQ - CloudAMQP
* Banco de Dados: MongoDB Compass
* Java: Versão 17
* Spring: Versão 3.4.1
* Docker
* Consumo de API: ViaCEP
* Cliente HTTP: OpenFeign
* AWS
## Funcionalidades MS Evento
* POST: criar Evento
* Get By id: Achar o evento por id
* Get All: Achar todos os eventos
* Get Sorted: Achar por ordem
* PUT: Atualizar um evento
* Delete By Id: deletar um evento por id
## Funcionalidades MS Ticket
* POST: criar Ticket
* Get By id: Achar o ticket por id
* Get All: Achar todos os tickets
* Get Has tickets: Achar se um evento tem tickets disponiveis
* PUT: Atualizar um ticket
* Delete By Id: deletar um ticket por id
1. **Clone o repositório:**
   ```bash
   [git clone https://github.com/seu-usuario/microsservico-a.git](https://github.com/Christakisbanza/ms_Desafio_03.git)
2. **Execute o projeto*
