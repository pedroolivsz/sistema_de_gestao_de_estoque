package br.com.pedro.estoque;

import br.com.pedro.estoque.model.Estoque;
import br.com.pedro.estoque.model.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Scanner scan = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("======================");
            System.out.println("         Menu         ");
            System.out.println("======================");
            System.out.println("1. Adicionar Produtos");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Remover Produto");
            System.out.println("0. Sair");
            System.out.println("======================");
            System.out.print("Escolha: ");
            opcao = scan.nextInt();
            System.out.println("======================");
            scan.nextLine();
            switch(opcao) {
                case 1:
                    System.out.println("ID: ");
                    int id = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Nome: ");
                    String nome = scan.nextLine();
                    System.out.println("Quantidade: ");
                    int quantidade = scan.nextInt();
                    System.out.println("Preço unitário: ");
                    double preco = scan.nextDouble();
                    Produto novo = new Produto(id, nome, quantidade, preco);
                    estoque.adicionarProduto(novo);
                    break;
                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    System.out.print("Insira o ID a ser removido: ");
                    int idRemover = scan.nextInt();
                    estoque.removerProduto(idRemover);
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while(opcao!=0);
        scan.close();
    }
}
