package br.com.pedro.estoque.model;

import br.com.pedro.estoque.view.MenuView;

import java.util.ArrayList;
import java.util.Comparator;

public class Estoque {
    private final ArrayList<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public Produto buscarPorId(int id) {
        for(Produto item : produtos) {
            if(item.getId() == id) {
                return item;
            }
        }
        return null;
    }
    public void adicionarProduto(String nome, int quantidade, double preco) {
        Produto item = new Produto(proximoId, nome, quantidade, preco);
        produtos.add(item);
        System.out.println("\uD83D\uDCE6 Produto adicionado com sucesso!");
        proximoId++;
    }
    public void listarProdutos() {
        if(produtos.isEmpty()) {
            MenuView.exibirMensagemErro("Estoque vazio!");
            return;
        }
        for(Produto item : produtos) {
            System.out.println(item);
        }
    }
    public void removerProduto(int id) {
        Produto encontrado = buscarPorId(id);
        if(encontrado != null) {
            produtos.remove(encontrado);
            MenuView.exibirMensagemSucesso("Produto removido com sucesso!");
        } else {
            MenuView.exibirMensagemErro("Produto não encontrado.");
        }
    }
    public void editarProduto(int id, String nome, int quantidade, double preco) {
        Produto produtoEditado = buscarPorId(id);
        if(produtoEditado == null) {
            MenuView.exibirMensagemErro("Produto não encontrado.");
            return;
        }
        produtoEditado.setNome(nome);
        produtoEditado.setQuantidade(quantidade);
        produtoEditado.setPreco(preco);
        MenuView.exibirMensagemSucesso("Produto alterado com sucesso!");
    }
    public void buscarPorNome(String buscarNome) {
        boolean encontrado = false;
        for(Produto p : produtos) {
            if(p.getNome().toLowerCase().contains(buscarNome.toLowerCase())) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if(!encontrado) {
            MenuView.exibirMensagemErro("Nenhum item com esse nome foi encontrado!");
        }
    }
    public void valorTotalEmEstoque() {
        double valorTotal = 0;
        for(Produto p : produtos) {
            valorTotal += p.getPreco()*p.getQuantidade();
        }
        System.out.printf("O valor total em estoque: R$%.2f\n", valorTotal);
    }
    public void ordenarPor(int criterio) {
        switch (criterio) {
            case 0 -> System.out.println("Saindo do menu de ordenação...");
            case 1 -> produtos.sort(Comparator.comparing(Produto::getNome));
            case 2 -> produtos.sort(Comparator.comparingInt(Produto::getQuantidade));
            case 3 -> produtos.sort(Comparator.comparingDouble(Produto::getPreco));
            default -> MenuView.exibirMensagemErro("Opção invalida!");
        }
    }
}
