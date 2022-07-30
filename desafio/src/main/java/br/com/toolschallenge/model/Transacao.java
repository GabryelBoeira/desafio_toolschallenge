package br.com.toolschallenge.model;

import br.com.toolschallenge.enums.FormaPagamentoTransacao;
import br.com.toolschallenge.enums.StatusTransacao;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Transacao")
public class Transacao extends AbstractPersistable<Long> {

    @NotNull
    private FormaPagamentoTransacao formaPagamento;

    @NotNull
    private StatusTransacao status;

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public FormaPagamentoTransacao getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoTransacao formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusTransacao getStatus() {
        return status;
    }

    public void setStatus(StatusTransacao status) {
        this.status = status;
    }
}
