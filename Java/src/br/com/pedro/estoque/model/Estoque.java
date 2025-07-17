package br.com.pedro.estoque.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Estoque {
    private final ArrayList<Produto> produtos = new ArrayList<>();
    private int proximoId = 1;
    Scanner scan = new Scanner(System.in);

    public void adicionarProduto(String nome, int quantidade, double preco) {
        Produto item = new Produto(proximoId, nome, quantidade, preco);
        produtos.add(item);
        System.out.println("Produto adicionado com sucesso!");
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
        Produto encontrado = null;
        for(Produto item : produtos) {
            if(item.getId() == id) {
                encontrado = item;
                break;
            }
        }
        if(encontrado != null) {
            System.out.print("Tem certeza que deseja remover o produto de ID:"+id+"?(s/n) ");
            String confimacao = scan.next();
            if(confimacao.equalsIgnoreCase("s")) {
                produtos.remove(encontrado);
                System.out.println("Produto removido com sucesso!");
            } else {
                System.out.println("Operação concelada.");
            }
        } else {
            System.out.println("Produto não enocontrado!");
        }
    }
    public void editarProduto(int id, Scanner scan) {
        Produto produto = null;
        String novoNome;
        int novaQuantidade;
        double novoPreco;
        String precoStr;
        for(Produto p : produtos) {
            if(p.getId() == id) {
                produto = p;
                break;
            }
        }
        if(produto == null) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }
        System.out.println("Produto encontrado: " + produto);
        System.out.println("1. Editar nome");
        System.out.println("2. Editar quantidade");
        System.out.println("3. Editar preço");
        System.out.println("4. Editar todos os atributos");
        int escolha = scan.nextInt();
        scan.nextLine();
        switch(escolha) {
            case 1:
                System.out.print("Editar nome:");
                novoNome = scan.nextLine();
                produto.setNome(novoNome);
                break;
            case 2:
                System.out.print("Editar quantidade: ");
                novaQuantidade = scan.nextInt();
                produto.setQuantidade(novaQuantidade);
                break;
            case 3:
                System.out.print("Editar preço: ");
                precoStr = scan.next().replace(",", ".");
                novoPreco = Double.parseDouble(precoStr);
                produto.setPreco(novoPreco);
                break;
            case 4:
                System.out.print("Editar nome:");
                novoNome = scan.nextLine();
                produto.setNome(novoNome);

                System.out.print("Editar quantidade: ");
                novaQuantidade = scan.nextInt();
                produto.setQuantidade(novaQuantidade);

                System.out.print("Editar preço: ");
                precoStr = scan.next().replace(",", ".");
                novoPreco = Double.parseDouble(precoStr);
                produto.setPreco(novoPreco);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
        System.out.println("✅Produto alterado com sucesso!");
    }
}
