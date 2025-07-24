package br.com.pedro.estoque.util;

import br.com.pedro.estoque.view.MenuView;

public class Validador {
    public static boolean validarNome(String nome) {
        if(nome.isEmpty()) {
            MenuView.exibirMensagemErro("O nome não pode estar vazio.");
            return false;
        } else if(nome.length() < 2) {
            MenuView.exibirMensagemErro("O nome é muito pequeno.");
            return false;
        } else if(nome.matches("\\d+")) {
            MenuView.exibirMensagemErro("O nome não pode conter apenas números.");
            return false;
        } else if(!nome.matches("[A-Za-zÀ-ÖØ-öø-ÿ0-9 ]+")) {
            MenuView.exibirMensagemErro("O nome possui caracteres inválidos");
            return false;
        } else if(!Character.isLetter(nome.charAt(0))) {
            MenuView.exibirMensagemErro("O nome não pode começar com números.");
            return false;
        } else {
            return true;
        }
    }
    public static boolean validarInt(int inteiro) {
        if(inteiro < 0) {
            MenuView.exibirMensagemErro("A valor não pode ser negativo");
            return false;
        }
        return true;
    }
}
