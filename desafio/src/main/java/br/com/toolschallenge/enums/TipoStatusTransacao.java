package br.com.toolschallenge.enums;

public enum TipoStatusTransacao {

    AUTORIZADO("Transação autorizada"),
    NEGADO("Transação Negada"),
    CANCELADO("Transação Negada");

    private String descricao;

    TipoStatusTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
