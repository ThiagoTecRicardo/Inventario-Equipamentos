# 📦 Inventário de Equipamentos

Sistema de inventário para notebooks corporativos, desenvolvido em **Java + Spring Boot**, com suporte a **PostgreSQL** e execução via **Docker Compose**.  
O objetivo é centralizar e gerenciar informações de equipamentos de TI de forma simples e escalável.

---
## 🚀 Instalação e Configuração

### Pré-requisitos
- **Java 17+**
- **Maven**
- **Docker** (para banco de dados PostgreSQL)

---
### Clonar o repositório
```bash
git clone https://github.com/ThiagoTecRicardo/Inventario-Equipamentos.git
cd Inventario-Equipamentos
```
### Executar com Maven

```bash
mvn clean install
mvn spring-boot:run
```
### Executar com Docker Compose

```bash
docker-compose up -d
```
---
## 🛠️ Funcionalidades

### Funcionario:
- CRUD completo de equipamentos (cadastro, consulta, atualização, exclusão).

### Equipamento:
- CRUD completo de equipamentos (cadastro, consulta, atualização, desvicular funcionario e exclusão).
- Registro de informações como:
- Marca
- Modelo
- Número de série
- Projeto
- Data Compra
- Funcionario Responsavel
- Estrutura modular para futuras expansões (ex.: integração com APIs externas).

## 📂 Estrutura do Projeto

<img width="1045" height="439" alt="image" src="https://github.com/user-attachments/assets/40157ade-22ad-4f7d-ad0a-3c79960f1ee8" />


## 📊 Roadmap
- [X] Implementar API REST completa
- [X] Persistência em PostgreSQL
- [ ] Criar testes automatizados
- [ ] Documentação técnica detalhada

## 📌 Exemplos de Uso

### Cadastro de Funcinario via API

```bash
POST /api/funcionario/registrar
{
 "nome": "Thiago"
}
```
### Consulta de funcionario cadastrados

```bash
GET /api/funcionario/listar
```

### Cadastro de equipamento via API

```bash
POST /api/equipamento/registrar
{
    "marca": "APPLE",
    "modelo": "MacBook Pro M3",
    "numeroSerie": "APPLE987654",
    "status": "EM_USO",
    "projeto": "Projeto Novo Horizonte",
    "equipamento": "NOTEBOOK",
    "funcionario": {
        "codigo": 2
    }
}
```
### Consulta de equipamentos cadastrados

```bash
GET /api/equipamento/listar
```

## 👨‍💻 Autor
- **Projeto desenvolvido por Thiago Ricardo**
