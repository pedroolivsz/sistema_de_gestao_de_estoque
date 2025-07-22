package br.com.pedro.estoque.util;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scan = new Scanner(System.in);

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return scan.nextLine();
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while(!scan.hasNextInt()) {
            System.out.println("❌ Valor inválido. Insira um valor inteiro!");
            scan.next();
            System.out.println(mensagem);
        }
        int valor = scan.nextInt();
        scan.nextLine();
        return valor;
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        String entrada = scan.next().replace(",", ".");
        scan.nextLine();
        try {
            return Double.parseDouble(entrada);
        } catch(NumberFormatException e) {
            System.out.println("❌ Valor inválido. Insira um valor inteiro!");
            return lerDouble(mensagem);
        }
    }

    public static String lerConfirmacao(String mensagem) {
        System.out.print(mensagem + " (s/n): ");
        return scan.next();
    }
}
