# 🚗 FindCar - Agregador & Alerta de Oportunidades Automotivas

> API REST inteligente desenvolvida em **Java 21** e **Spring Boot 4** para catalogação, monitoramento e alerta de oscilações de mercado na Tabela FIPE[cite: 5, 6]. Projetada sob práticas modernas de engenharia, arquitetura limpa, resiliência e esteiras automatizadas de DevOps.

---

## 🎯 O Problema Resolvido

Compradores e investidores do setor automotivo enfrentam dificuldades para monitorar manualmente as flutuações de preços da Tabela FIPE para fechar negócios no momento ideal. 

O **FindCar** resolve essa dor centralizando o consumo de dados automotivos externos e automatizando o rastreamento de preços. O usuário define o veículo e o preço desejado (alerta); em segundo plano, um serviço inteligente analisa o mercado de forma assíncrona e dispara notificações quando o valor atinge a meta estipulada pelo usuário.

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

### Backend Core
* **Java 21 & Spring Boot 4** (Spring Web, JPA, Security)
* **Jackson Core** (Desserialização assíncrona de payloads complexos da API externa)[cite: 5]
* **PostgreSQL** (Persistência de usuários, históricos e alertas)[cite: 5]
* **Spring Security & JWT** (Autenticação stateless e controle de acesso a rotas sensíveis)[cite: 5]

### DevOps & Infraestrutura
* **Docker & Docker Compose** (Containerização completa do ambiente local e de produção)
* **AWS (EC2 & S3)** (Hospedagem em nuvem e persistência de artefatos/históricos)
* **GitHub Actions** (Esteira automatizada de CI/CD para validação de builds, testes e deploy contínuo)

---

## 🔗 Demonstração e Testes (Deploy Ativo)

Para facilitar a avaliação técnica sem necessidade de rodar o código localmente, disponibilizamos as seguintes ferramentas:

* 🌐 **Interface Interativa (Swagger/OpenAPI):** [Acesse a Documentação Swagger](http://seu-ip-da-aws:8080/swagger-ui/index.html) *(Permite disparar requisições em tempo real contra nosso servidor)*
* 📦 **Coleção de Endpoints (Postman):** [Importar Workspace no Postman](https://documenter.getpostman.com/view/seu-link-publico)
* 📍 **URL Base de Produção (AWS):** `http://seu-ip-da-aws:8080/api/v1`

---

## 🗺️ Arquitetura de Pastas e Componentes

A aplicação segue uma estrutura modular de alta coesão e baixo acoplamento:

```text
src/main/java/br/com/alura/FindCar/
├── config/             # Configurações de Beans e Segurança (JWT Config)
├── controller/         # Endpoints REST expostos
├── dto/                # Records Java para Request/Response (Jackson)
├── exception/          # Tratamento Global de Exceções (ControllerAdvice)
├── model/              # Entidades mapeadas para o banco PostgreSQL
├── repository/         # Interfaces de persistência de dados
└── service/            # Lógica de consumo da API externa e agendamentos (Scheduler)
