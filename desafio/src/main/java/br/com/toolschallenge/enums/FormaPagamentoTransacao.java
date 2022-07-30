package br.com.toolschallenge.enums;

public enum FormaPagamentoTransacao {

    A_VISTA("Pagamento a vista"),
    PARCELADO_LOJA("Parcelamento realizado pela Loja"),
    PARCELADO_EMISSOR("Parcelamento realizado pelo emissor");

    private String descricao;

    FormaPagamentoTransacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
