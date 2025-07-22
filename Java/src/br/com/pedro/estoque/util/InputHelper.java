package br.com.pedro.estoque.util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scan = new Scanner(System.in);

    public static String lerString(String mensagem) {
        String entrada;
        do {
            System.out.print(mensagem);
            entrada = scan.nextLine().trim();
            if(entrada.isEmpty()) {
                System.out.println("❌ O campo não pode estar vazio.");
            }
        } while(entrada.isEmpty());
        return entrada;
    }

    public static String lerNome(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            String nome = scan.nextLine().trim();

            if(nome.isEmpty()) {
                System.out.println("❌ O campo não pode estar vazio.");
            } else if(nome.length() < 2) {
                System.out.println("❌ O nome deve conter pelo menos 2 caracteres.");
            } else if(nome.matches("\\d+")) {
                System.out.println("❌ O nome não deve conter somente números.");
            } else if(!nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ0-9 ]+")) {
                System.out.println("❌ O nome contém caracteres inaválidos.");
            } else if(!Character.isLetter(nome.charAt(0))) {
                System.out.println("❌ O nome não deve começar com um número.");
            } else {
                return nome;
            }
        }
    }

    public static int lerInt(String mensagem) {
        while(true) {
            System.out.print(mensagem);
            if(scan.hasNextInt()) {
                int quantidade = scan.nextInt();
                scan.nextLine();
                if(quantidade >= 0) {
                    return quantidade;
                } else {
                    System.out.println("❌ O valor não poder ser negativo.");
                }
            } else {
                System.out.println("❌ Valor inválido. Insira um valor inteiro!");
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
