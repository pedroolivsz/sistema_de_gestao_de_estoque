package br.com.pedro.estoque;

import br.com.pedro.estoque.controller.EstoqueController;
import br.com.pedro.estoque.model.Estoque;
import br.com.pedro.estoque.util.InputHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();
        Scanner scan = new Scanner(System.in);
        EstoqueController controle = new EstoqueController(estoque);
        int opcao;

        do {
            controle.iniciar();
            System.out.println("=============================");
            opcao = InputHelper.lerInt("Escolha: ");
            System.out.println("=============================");
            switch(opcao) {
                case 1 -> controle.adicionarProduto();
                case 2 -> controle.listarProdutos();
                case 3 -> controle.removerProduto();
                case 4 -> controle.editarProduto();
                case 5 -> controle.buscarPorNome();
                case 6 -> controle.valorTotalEstoque();
                case 7 -> controle.ordenarProdutos();
                case 0 -> System.out.println("Encerrando sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while(opcao!=0);
        scan.close();
    }
}
