package br.com.pedro.estoque;

import br.com.pedro.estoque.model.Estoque;
import br.com.pedro.estoque.model.Produto;
import br.com.pedro.estoque.util.InputHelper;

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
            opcao = InputHelper.lerInt("Escolha: ");
            System.out.println("======================");
            switch(opcao) {
                case 1:
                    String nome = InputHelper.lerString("Nome: ");

                    int quantidade = InputHelper.lerInt("Quantidade: ");

                    double preco = InputHelper.lerDouble("Preço unitário: ");

                    estoque.adicionarProduto(nome, quantidade, preco);
                    break;
                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    int idRemover = InputHelper.lerInt("Insira o ID a ser removido: ");
                    String confirmar = InputHelper.lerConfirmacao("Tem certeza que deseja remover o produto de ID: " + idRemover + "?");
                    if(confirmar.equalsIgnoreCase("s")) {
                        estoque.removerProduto(idRemover);
                    } else {
                        System.out.println("Operação concelada.");
                    }
                    break;
                case 4:
                    int editarId = InputHelper.lerInt("Insira o ID do produto a ser editado: ");

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
                    int escolha = InputHelper.lerInt("Escolha: ");

                    switch(escolha) {
                        case 1:
                            novoNome = InputHelper.lerString("Editar nome: ");
                            break;
                        case 2:
                            novaQuantidade = InputHelper.lerInt("Editar quantidade: ");
                            break;
                        case 3:
                            novoPreco = InputHelper.lerDouble("Editar preço: ");
                            break;
                        case 4:
                            novoNome = InputHelper.lerString("Editar nome: ");
                            novaQuantidade = InputHelper.lerInt("Editar quantidade: ");
                            novoPreco = InputHelper.lerDouble("Editar preço: ");
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }

                    estoque.editarProduto(editarId, novoNome, novaQuantidade, novoPreco);
                    break;
                case 5:
                    String buscarNome = InputHelper.lerString("Insira o nome que deseja encontrar: ");
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
