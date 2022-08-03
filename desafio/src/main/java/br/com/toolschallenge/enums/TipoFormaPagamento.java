package br.com.toolschallenge.enums;

public enum TipoFormaPagamento {

    AVISTA("Pagamento a vista"),
    PARCELADO_LOJA("Parcelamento realizado pela Loja"),
    PARCELADO_EMISSOR("Parcelamento realizado pelo emissor");

    private String descricao;

    TipoFormaPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
