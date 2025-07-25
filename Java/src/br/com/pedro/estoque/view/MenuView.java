package br.com.pedro.estoque.view;

public class MenuView {
    public static void exibirCabecalho() {
        System.out.println("=============================");
        System.out.println("      SISTEMA DE ESTOQUE");
        System.out.println("=============================");
    }
    public static void exibirMenuPrincipal() {
        System.out.println("1️⃣-Adicionar Produtos");
        System.out.println("2️⃣-Listar Produtos");
        System.out.println("3️⃣-Remover Produto");
        System.out.println("4️⃣-Editar produto");
        System.out.println("5️⃣-Buscar por nome");
        System.out.println("6️⃣-Exibir valor total em estoque");
        System.out.println("7️⃣-Ordenar produtos");
        System.out.println("0️⃣-Sair");
    }
    public static void separador() {
        System.out.println("=============================");
    }
    public static void exibirMenuOrdenacao() {
        System.out.println("\uD83D\uDCCA Opções de ordenação: ");
        System.out.println("1️⃣  Nome");
        System.out.println("2️⃣  Quantidade");
        System.out.println("3️⃣  Preço");
        System.out.println("0️⃣  Sair");
    }
    public static void exibirMenuEdicao() {
        System.out.println("✏️ Opções de edição: ");
        System.out.println("1️⃣  Nome");
        System.out.println("2️⃣  Quantidade");
        System.out.println("3️⃣  Preço");
        System.out.println("4️⃣  Todos os campos");
        System.out.println("0️⃣  Sair");
    }
    public static void exibirMensagemSucesso(String mensagem) {
        System.out.println("✅ " + mensagem);
    }
    public static void exibirMensagemErro(String mensagem) {
        System.out.println("❌ " + mensagem);
    }
}
