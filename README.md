# 💸 Gestão de Custos – API de Despesas com Spring Boot

Este projeto é uma API REST para **gestão de despesas pessoais**, construída com Spring Boot, seguindo uma arquitetura em camadas (Controller, Service, Repository) e preparada para deploy na Render.

---

## 🚀 Funcionalidades

- ✅ Cadastro de despesa (`POST /gestao/create`)
- ✅ Busca de despesas por e‑mail, com filtro **opcional** por data (`GET /gestao/{email}?data=YYYY-MM-DD`)
- ✅ Tratamento de erros com objeto de resposta padronizado (`ErrorMessage`)
- ✅ Seeder opcional para carga massiva de dados de teste (`GestaoDeDespesaSeeder`)

Exemplo de endpoint em produção (Render):

```text
GET https://deploy-gestao-custos-4oev.onrender.com/gestao/kevin@gmail.com
```

---

## 🧱 Arquitetura do projeto

Pacote base: `br.com.javadevweek.gestao_custos`

- `controller`
    - `GestaoDespesaController` – expõe os endpoints REST de criação e consulta de despesas.
- `service`
    - `CatastroDespesaService` – regra de negócio para cadastro de despesas.
    - `BuscarDespesaService` – regra de busca por e‑mail e data.
- `repository`
    - `DespesaRepository` – acesso ao banco via Spring Data JPA.
- `entity`
    - `Despesa` – representação da despesa na base de dados.
- `custom_messages`
    - `ErrorMessage` – modelo de resposta de erro (mensagem + código).
- `performance`
    - `GestaoDeDespesaSeeder` – gera grande volume de dados para testes de performance (via `CommandLineRunner`, comentado por padrão).

---

## 📡 Endpoints principais

### 📌 Criar despesa

`POST /gestao/create`

Body (JSON):

```json
{
  "descricao": "Almoço",
  "valor": 35.90,
  "data": "2026-03-24",
  "categoria": "ALIMENTACAO",
  "email": "kevin@gmail.com"
}
```

- Retorno de sucesso: objeto `Despesa` salvo.
- Erros de validação/regra de negócio retornam um `ErrorMessage` com:
    - `message` – descrição do erro
    - `code` – código interno, exemplo: `"INVALID_PARAMS"`

---

### 📌 Buscar despesas por e‑mail (com data opcional)

`GET /gestao/{email}?data=YYYY-MM-DD`

Exemplos:

```text
GET /gestao/kevin@gmail.com
GET /gestao/kevin@gmail.com?data=2026-03-23
```

- Sem `data`: retorna todas as despesas do e‑mail.
- Com `data`: filtra as despesas daquele dia.

---

## 🧪 Seeder de performance (opcional)

A classe `GestaoDeDespesaSeeder` gera **150.000 registros** de teste no banco H2, todos para o e‑mail `performance@gmail.com`, com:

- Descrição: `Gasto nº X`
- Valor variando entre 10 e 59
- Datas nos últimos 30 dias

Para ativar:

1. Descomente `@Component` na classe `GestaoDeDespesaSeeder`.
2. Suba a aplicação localmente.
3. Após a primeira subida, comente novamente se não quiser popular sempre.

---

## 🛠️ Tecnologias usadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (arquivo)
- Maven
- Docker (build e deploy na Render)

---

## ⚙️ Configuração de banco (H2 arquivo)

Arquivo `application.properties`:

```properties
spring.application.name=gestao-custos

# ============= CONFIGURACOES H2 ============= #
spring.datasource.url=jdbc:h2:file:./data/gesta-despesa
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Como rodar o projeto localmente

```bash
# Clonar o repositório
git clone https://github.com/Kkilmer/gestao-custos.git
cd gestao-custos

# Build (sem testes)
mvn clean package -DskipTests

# Rodar a aplicação
mvn spring-boot:run
```

Aplicação padrão em:  
`http://localhost:8080`

---

## ☁️ Deploy na Render (Docker)

O projeto utiliza um Dockerfile multi‑stage que:

1. Usa uma imagem Maven + JDK para gerar o `.jar`.
2. Usa uma imagem JDK leve apenas para rodar o `.jar` gerado.

Fluxo simplificado:

```bash
# Build da imagem
docker build -t gestao-custos .

# Rodar localmente
docker run -p 8080:8080 gestao-custos
```

---

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido para praticar:

- Design em camadas (Controller → Service → Repository)
- Consumo de API REST com JSON simples
- Integração com H2 em modo arquivo
- Preparação de ambiente para deploy (Maven + Docker + Render)
- Escrita de mensagens de erro padronizadas para o cliente