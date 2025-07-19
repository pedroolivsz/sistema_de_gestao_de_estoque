package br.com.pedro.estoque;

import br.com.pedro.estoque.model.Estoque;
import br.com.pedro.estoque.model.Produto;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Scanner scan = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("======================");
            System.out.println("         Menu         ");
            System.out.println("======================");
            System.out.println("1. Adicionar Produtos");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Remover Produto");
            System.out.println("4. Editar produto");
            System.out.println("5. Buscar por nome");
            System.out.println("6. Exibir valor total em estoque");
            System.out.println("7. Ordenar por nome");
            System.out.println("8. Ordenar por quantidade");
            System.out.println("9. Ordenar por preço");
            System.out.println("0. Sair");
            System.out.println("======================");
            System.out.print("Escolha: ");
            opcao = scan.nextInt();
            System.out.println("======================");
            scan.nextLine();
            switch(opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scan.nextLine();

                    System.out.print("Quantidade: ");
                    int quantidade = scan.nextInt();

                    System.out.print("Preço unitário: ");
                    String precoStr = scan.next().replace(",", ".");
                    double preco = Double.parseDouble(precoStr);
                    scan.nextLine();

                    estoque.adicionarProduto(nome, quantidade, preco);
                    break;
                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    System.out.print("Insira o ID a ser removido: ");
                    int idRemover = scan.nextInt();
                    System.out.printf("Tem certeza que deseja remover o produto de ID: %d", idRemover);
                    String confirmar = scan.next();
                    if(confirmar.equalsIgnoreCase("s")) {
                        estoque.removerProduto(idRemover);
                    } else {
                        System.out.println("Operação concelada.");
                    }
                    break;
                case 4:
                    System.out.print("Insira o ID do produto a ser editado: ");
                    int editarId = scan.nextInt();
                    Produto produtoAtual = estoque.buscarPorId(editarId);
                    if(produtoAtual == null) {
                        System.out.println("❌ Produto não encontrado!");
                        break;
                    }
                    String novoNome = produtoAtual.getNome();
                    int novaQuantidade = produtoAtual.getQuantidade();
                    double novoPreco = produtoAtual.getPreco();
                    System.out.println("==============================");
                    System.out.println("1. Editar nome");
                    System.out.println("2. Editar quantidade");
                    System.out.println("3. Editar preço");
                    System.out.println("4. Editar todos os atributos");
                    System.out.println("==============================");
                    System.out.print("Escolha: ");
                    int escolha = scan.nextInt();
                    scan.nextLine();
                    String strPreco;
                    switch(escolha) {
                        case 1:
                            System.out.print("Editar nome:");
                            novoNome = scan.nextLine();
                            break;
                        case 2:
                            System.out.print("Editar quantidade: ");
                            novaQuantidade = scan.nextInt();
                            break;
                        case 3:
                            System.out.print("Editar preço: ");
                            strPreco = scan.next().replace(",", ".");
                            novoPreco = Double.parseDouble(strPreco);
                            break;
                        case 4:
                            System.out.print("Editar nome:");
                            novoNome = scan.nextLine();

                            System.out.print("Editar quantidade: ");
                            novaQuantidade = scan.nextInt();

                            System.out.print("Editar preço: ");
                            strPreco = scan.next().replace(",", ".");
                            novoPreco = Double.parseDouble(strPreco);
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    estoque.editarProduto(editarId, novoNome, novaQuantidade, novoPreco);
                    break;
                case 5:
                    System.out.print("Insira o nome que deseja encontrar: ");
                    String buscarNome = scan.nextLine();
                    estoque.buscarPorNome(buscarNome);
                    break;
                case 6:
                    estoque.valorTotalEmEstoque();
                    break;
                case 7:
                    estoque.ordenarPorNome();
                    break;
                case 8:
                    estoque.ordenarPorQuantidade();
                    break;
                case 9:
                    estoque.ordenarPorPreco();
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
