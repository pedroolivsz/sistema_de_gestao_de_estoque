package br.com.pedro.estoque.util;

import br.com.pedro.estoque.view.MenuView;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scan = new Scanner(System.in);

    public static String lerNome(String mensagem) {
        String nome;
        do {
            System.out.print(mensagem);
            nome = scan.nextLine().trim();
        } while(!Validador.validarNome(nome));
        return nome;
    }

    public static int lerInt(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            if(scan.hasNextInt()) {
                int quantidade = scan.nextInt();
                scan.nextLine();
                if(Validador.validarInt(quantidade)) {
                    return quantidade;
                }
            } else {
                MenuView.exibirMensagemErro("Valor inválido. Insira um valor inteiro!");
                scan.nextLine();
            }
        }
    }

    public static double lerDouble(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            String entrada = scan.next().replace(",", ".");
            scan.nextLine();
            try {
                double valor = Double.parseDouble(entrada);
                if(valor >= 0) {
                    return valor;
                } else {
                    System.out.println("❌ Valor inválido. Insira um valor positivo.");
                }
            } catch(NumberFormatException e) {
                System.out.println("❌ Valor inválido. Insira um valor decimal!");
            }
        }
    }

    public static String lerConfirmacao(String mensagem) {
        String resposta;
        do {
            System.out.print(mensagem + " (s/n): ");
            resposta = scan.next().trim().toLowerCase();
            if(!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("❌ Resposta inválida. Responda apenas com 's' ou 'n'.");
            }
        } while(!resposta.equals("s") && !resposta.equals("n"));
        scan.nextLine();
        return resposta;
    }
}
