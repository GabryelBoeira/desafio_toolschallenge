package br.com.toolschallenge.enums;

public enum StatusTransacao {

    AUTORIZADO("Transação autorizada"),
    NEGADO("Transação Negada");

    private String descricao;

    StatusTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
