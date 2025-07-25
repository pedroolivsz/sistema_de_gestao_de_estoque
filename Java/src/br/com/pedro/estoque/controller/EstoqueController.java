package br.com.pedro.estoque.controller;

import br.com.pedro.estoque.model.Estoque;
import br.com.pedro.estoque.model.Produto;
import br.com.pedro.estoque.util.InputHelper;
import br.com.pedro.estoque.view.MenuView;

public class EstoqueController {

    private final Estoque estoque;

    public EstoqueController(Estoque estoque) {
        this.estoque = estoque;
    }

    public void iniciar() {
        MenuView.exibirCabecalho();
        MenuView.exibirMenuPrincipal();
    }
    public void adicionarProduto() {
        String novoNome = InputHelper.lerNome("Nome: ");
        int novaQuantidade = InputHelper.lerInt("Quantidade: ");
        double novoPreco = InputHelper.lerDouble("Preço: ");
        estoque.adicionarProduto(novoNome, novaQuantidade, novoPreco);
    }
    public void listarProdutos() {
        estoque.listarProdutos();
    }
    public void removerProduto() {
        int idRemove = InputHelper.lerInt("Insira o ID que deseja remover: ");
        String confirmar = InputHelper.lerConfirmacao("Tem certeza que deseja remover o item de ID: " + idRemove + "?");
        if(confirmar.equalsIgnoreCase("s")) {
            if(estoque.buscarPorId(idRemove) != null) {
                estoque.removerProduto(idRemove);
            } else {
                MenuView.exibirMensagemErro("Produto não encontrado!");
            }
        } else {
            MenuView.exibirMensagemErro("Operação cancelada!");
        }
    }
    public void editarProduto() {
        int editarId = InputHelper.lerInt("Insira o ID do produto a ser editado: ");

        Produto produtoAtual = estoque.buscarPorId(editarId);

        if(produtoAtual == null) {
            System.out.println("❌ Produto não encontrado!");
            return;
        }

        String novoNome = produtoAtual.getNome();
        int novaQuantidade = produtoAtual.getQuantidade();
        double novoPreco = produtoAtual.getPreco();

        System.out.println("=============================");
        MenuView.exibirMenuEdicao();
        System.out.println("=============================");
        int escolha = InputHelper.lerInt("Escolha: ");

        switch(escolha) {
            case 0:
                System.out.println("Saindo do modo de edição...");
                break;
            case 1:
                novoNome = InputHelper.lerNome("Editar nome: ");
                break;
            case 2:
                novaQuantidade = InputHelper.lerInt("Editar quantidade: ");
                break;
            case 3:
                novoPreco = InputHelper.lerDouble("Editar preço: ");
                break;
            case 4:
                novoNome = InputHelper.lerNome("Editar nome: ");
                novaQuantidade = InputHelper.lerInt("Editar quantidade: ");
                novoPreco = InputHelper.lerDouble("Editar preço: ");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }

        estoque.editarProduto(editarId, novoNome, novaQuantidade, novoPreco);
    }
    public void buscarPorNome() {
        String buscarNome = InputHelper.lerNome("Insira o nome que deseja encontrar: ");
        estoque.buscarPorNome(buscarNome);
    }
    public void valorTotalEstoque() {
        estoque.valorTotalEmEstoque();
    }
    public void ordenarProdutos() {
        MenuView.separador();
        MenuView.exibirMenuOrdenacao();
        MenuView.separador();
        int escolha = InputHelper.lerInt("Escolha: ");
        estoque.ordenarPor(escolha);
        MenuView.exibirMensagemSucesso("Ordenação concluida com sucesso.");
    }
}
