package br.com.pedro.estoque.view;

public class MenuView {
    public static void exibirCabecalho() {
        System.out.println("=============================");
        System.out.println("      SISTEMA DE ESTOQUE");
        System.out.println("=============================");
    }
    public static void exibirMenuPrincipal() {
        System.out.println("1\uFE0F⃣-Adicionar Produtos");
        System.out.println("2\uFE0F⃣-Listar Produtos");
        System.out.println("3\uFE0F⃣-Remover Produto");
        System.out.println("4\uFE0F⃣-Editar produto");
        System.out.println("5\uFE0F⃣-Buscar por nome");
        System.out.println("6\uFE0F⃣-Exibir valor total em estoque");
        System.out.println("7\uFE0F⃣-Ordenar produtos");
        System.out.println("0\uFE0F⃣-Sair");
    }
    public static void exibirMenuOrdenacao() {
        System.out.println("\uD83D\uDCCA Opções de ordenação: ");
        System.out.println("1\uFE0F⃣  Nome");
        System.out.println("2\uFE0F⃣  Quantidade");
        System.out.println("3\uFE0F⃣  Preço");
        System.out.println("0\uFE0F⃣  Sair");
    }
    public static void exibirMenuEdicao() {
        System.out.println("✏\uFE0F Opções de edição: ");
        System.out.println("1\uFE0F⃣  Nome");
        System.out.println("2\uFE0F⃣  Quantidade");
        System.out.println("3\uFE0F⃣  Preço");
        System.out.println("4\uFE0F⃣  Todos os campos");
        System.out.println("0\uFE0F⃣  Sair");
    }
    public static void exibirMensagemSucesso(String mensagem) {
        System.out.println("✅ " + mensagem);
    }
    public static void exibirMensagemErro(String mensagem) {
        System.out.println("❌ " + mensagem);
    }
}
