package br.com.toolschallenge.model;

import br.com.toolschallenge.enums.TipoStatusTransacao;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Transacao")
public class Transacao extends AbstractPersistable<Long> {

    @NotBlank
    @Column(name = "cartao")
    private String cartao;

    @Valid
    @OneToOne( cascade =  CascadeType.ALL, orphanRemoval = true, optional = false, targetEntity = DescricaoTransacao.class)
    @JoinColumn(name = "descricao_id", nullable = false)
    private DescricaoTransacao descricao;

    @Valid
    @OneToOne(cascade =  CascadeType.ALL, orphanRemoval = true, optional = false, targetEntity = FormaPagamento.class)
    @JoinColumn(name = "formaPagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    public Transacao(long id, String cartao, DescricaoTransacao descricao, FormaPagamento formaPagamento) {
        super.setId(id);
        this.cartao = cartao;
        this.descricao = descricao;
        this.formaPagamento = formaPagamento;
    }

    public Transacao() {
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public DescricaoTransacao getDescricao() {
        return descricao;
    }

    public void setDescricao(DescricaoTransacao descricao) {
        this.descricao = descricao;
    }

}
