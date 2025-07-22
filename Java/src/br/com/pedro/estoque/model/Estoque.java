package br.com.pedro.estoque.model;

import java.util.ArrayList;
import java.util.Comparator;

public class Estoque {
    private final ArrayList<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;

    public Produto buscarPorId(int id) {
        for(Produto p : produtos) {
            if(p.getId() == id) {
                return p;
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
            System.out.println("Estoque vazio!");
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
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("❌Produto não encontrado.");
        }
    }
    public void editarProduto(int id, String nome, int quantidade, double preco) {
        Produto produtoEditado = buscarPorId(id);
        produtoEditado.setNome(nome);
        produtoEditado.setQuantidade(quantidade);
        produtoEditado.setPreco(preco);
        System.out.println("✅Produto alterado com sucesso!");
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
            System.out.println("Nenhum item com esse nome foi encontrado!");
        }
    }
    public void valorTotalEmEstoque() {
        double valorTotal = 0;
        for(Produto p : produtos) {
            valorTotal += p.getPreco()*p.getQuantidade();
        }
        System.out.printf("O valor total em estoque: R$%.2f\n", valorTotal);
    }
    public void ordenarPorNome() {
        produtos.sort(Comparator.comparing(Produto::getNome));
        listarProdutos();
    }
    public void ordenarPorQuantidade() {
        produtos.sort(Comparator.comparingInt(Produto::getQuantidade));
        listarProdutos();
    }
    public void ordenarPorPreco() {
        produtos.sort(Comparator.comparingDouble(Produto::getPreco));
        listarProdutos();
    }
}
