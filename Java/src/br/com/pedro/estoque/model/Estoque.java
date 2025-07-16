package br.com.pedro.estoque.model;

import java.util.ArrayList;

public class Estoque {
    private ArrayList<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto item) {
        produtos.add(item);
        System.out.println("Produto adicionado com sucesso!");
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
        Produto encontrado = null;
        for(Produto item : produtos) {
            if(item.getId() == id) {
                encontrado = item;
                break;
            }
        }
        if(encontrado != null) {
            produtos.remove(encontrado);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Produto não enocntrado!");
        }
    }
}
