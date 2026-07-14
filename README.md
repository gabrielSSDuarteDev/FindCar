# 🚗 FindCar — O "Tinder" de Oportunidades Automotivas

> API REST inteligente desenvolvida em **Java 21** e **Spring Boot 4** para monitoramento de oscilações de mercado da Tabela FIPE. O sistema automatiza o acompanhamento de preços de veículos, notificando o usuário de forma assíncrona assim que sua oportunidade ideal atinge o valor desejado.

---

## 🎯 O Problema Resolvido (Problem Solving)
Comprar ou investir em veículos exige um monitoramento exaustivo e diário das flutuações da Tabela FIPE. O **FindCar** elimina esse trabalho manual ao centralizar o consumo encadeado de APIs automotivas externas, permitindo que usuários criem **Alertas Inteligentes de Preço** (ex: *"Me avise se o Honda Civic 2020 cair abaixo de R$ 90.000"*). O sistema rastreia essas variações em segundo plano e gera alertas imediatos assim que o preço-alvo é atingido.

---

## 🛠️ Tecnologias & Ferramentas Utilizadas

* **Linguagem Principal:** Java 21 (utilizando recursos modernos como Records, Pattern Matching e Virtual Threads)
* **Framework:** Spring Boot 4 (Spring Web, Spring Data JPA, Spring Security)
* **Banco de Dados:** PostgreSQL (Persistência relacional de usuários, favoritos e históricos)
* **Segurança:** Spring Security + JWT (JSON Web Tokens) para autenticação stateless de rotas protegidas
* **DevOps & Containers:** Docker & Docker Compose (Isolamento completo do ecossistema de serviços)
* **Infraestrutura em Nuvem:** AWS (Deploy escalável utilizando instâncias EC2)
* **Automação (CI/CD):** GitHub Actions (Esteira automatizada de build, testes integrados e deploy contínuo)
* **Documentação:** Swagger UI / OpenAPI 3 (Interface interativa para testes rápidos de endpoints)

---

## 🏗️ Arquitetura do Projeto

O projeto adota uma estrutura modular focada em separação clara de responsabilidades, alta coesão e baixo acoplamento:

```text
br.com.alura.FindCar/
│
├── config/             # Segurança, JWT e configurações globais do Spring
├── controller/         # Endpoints da API REST (Controllers)
├── model/              # Entidades JPA (Mapeamento de Tabelas do Postgres)
├── dto/                # Records para tráfego seguro de dados (Request/Response)
├── repository/         # Camada de acesso ao banco (Spring Data JPA)
├── service/            # Regras de negócio e consumo de APIs externas
└── exception/          # Tratamento global e
