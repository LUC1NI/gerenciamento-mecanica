# 🛠️ Gerenciamento de Oficina Mecânica

Bem-vindo ao projeto **Gerenciamento de Oficina Mecânica**! Este é um sistema desenvolvido em Java para facilitar a administração e organização das atividades diárias de uma oficina mecânica.

## 📋 Sobre o Projeto

O sistema oferece uma solução prática para o controle de **clientes**, **funcionários** e **veículos**, permitindo o cadastro, consulta e gerenciamento dessas entidades de forma eficiente. O projeto utiliza persistência de dados via serialização de objetos, garantindo que as informações sejam salvas e recuperadas entre as execuções.

## ✨ Funcionalidades

O sistema conta com um **Menu Principal** interativo via console, oferecendo as seguintes opções:

- **👥 Gerenciar Clientes**: Cadastro e visualização de clientes.
- **🔧 Gerenciar Funcionários**: Controle da equipe, incluindo cargos e salários.
- **🚗 Gerenciar Veículos**: Registro e associação de veículos aos clientes.
- **💾 Persistência de Dados**: Funcionalidade "Salvar tudo" para gravar os dados em arquivos locais.
- **🔄 Carregamento Automático**: Os dados são carregados automaticamente ao iniciar o sistema.

## 🚀 Tecnologias Utilizadas

- **Linguagem**: [Java](https://www.java.com/)
- **Persistência**: Serialização de Objetos (Java IO)
- **Interface**: Console (Terminal)

## 📂 Estrutura do Projeto

A estrutura do código fonte está organizada da seguinte forma:

```
src/
├── controller/   # Lógica de controle (Cliente, Funcionario, Veiculo)
├── dados/        # Armazenamento dos arquivos de dados (.ser)
├── dal/          # Data Access Layer (DAOs para persistência)
├── model/        # Modelos de dados (Entidades)
├── views/        # Interfaces de usuário (Console Views)
├── util/         # Utilitários e Repositórios
├── App.java      # Classe principal (Entry Point)
└── Preload.java  # Carga inicial de dados (para testes)
```

## ⚙️ Como Executar

### Pré-requisitos

- **Java JDK** instalado (Recomendado versão 8 ou superior).

### Passos

1. **Clone o repositório** (ou baixe os arquivos):
   ```bash
   git clone https://github.com/seu-usuario/gerenciamento-mecanica.git
   ```

2. **Navegue até a pasta do projeto**:
   ```bash
   cd gerenciamento-mecanica/mecanica
   ```

3. **Compile o projeto**:
   ```bash
   javac -d bin -sourcepath src src/App.java
   ```

4. **Execute a aplicação**:
   ```bash
   java -cp bin App
   ```

## 👥 Autores

Este projeto foi desenvolvido por:

- **João Vitor Lucini** 

---
*Desenvolvido com fins acadêmicos para a disciplina de Programação Orientada a Objetos.*
