package br.com.toolschallenge.util;

public class Utils {

    public static String GerarNumeros(Integer tamanho) {
        if (tamanho == null) tamanho = 8;

        String[] caracteres = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < tamanho; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }


}
