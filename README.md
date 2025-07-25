# 📦 Sistema de Gestão de Estoque em Java
Este é um sistema simples e funcinal de **gestão de estoque**, desenvolvido em **Java** com persistênicia de dados em **JSON**, utilizando a biblioteca [Gson](https://github.com/google/gson). Ideal para estudos de programação orientada a objetos, manipulação de arquivos e criação de sistemas em camadas.

---

## 🧠 Funcionalidades
- ✅ Cadastro de produtos
- ✏️ Edição de produtos
- ❌ Remoção de produtos
- 📄 Listagem de produtos
- 🔍 Buscar por nome
- 📊 Ordenação por nome, quantidade ou preço
- 💾 Salvamento automático em ".json"
- 🔁 Carregamento automático ao iniciar o arquivo

---

## 🛠 Como executar o projeto

### 1. Clone o repositório:

bash
git clone https://github.com/pedroolivsz/sistema_de_gestao_de_estoque.git
cd estoque-java

### 2. Baixe a biblioteca Gson:

[Baixar Gson](https://repo1.maven.org/maven2/com/google/code/gson/gson/2.10.1/gson-2.10.1.jar)

### 3. Copile e execute:

javac -cp gson-2.10.1.jar src/**/*.java
java -cp .;gson-2.10.1.jar src/Main

💡 Ou execute o **Main.java** pela sua IDE (como Intellij ou Eclipse) adicionando o **.jar** como biblioteca externa.

---

## 📂 Estrutura do projeto

sistema_de_gestao_de_estoque/
├── src/
│   ├── model/        # Classe Produto e Estoque
│   ├── controller/   # Classe EstoqueController
│   ├── view/         # Interface de menus
│   ├── util/         # ArquivoHelper, Validador e InputHelper
│   └── Main.java     # Ponto de entrada do sistema
├── estoque.json      # Arquivo onde os produtos são salvos
└── README.md

---

## 💻 Tecnologias usadas

- Linguagem Java 17+
- Biblioteca Gson
- Terminal/console
- Padrão MVC (Model-View-Controller)
- POO (Programação Orientada a Obijetos)

---

## 👨‍💻 Autor

Desenvolvido por Joao Pedro
Como parte dos estudo de Java, POO e estruturas de software.
Se gostou, ⭐ deixe uma estrela no repositório!

---

**Este projeto foi feito com 💙 para fins educacionais!