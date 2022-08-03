package br.com.toolschallenge.model;

import br.com.toolschallenge.enums.TipoFormaPagamento;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "FormaPagamento")
public class FormaPagamento extends AbstractPersistable<Long> {

    @NotNull
    private TipoFormaPagamento tipoFormaPagamento;

    @Positive
    private Long parcelas;

    public FormaPagamento(TipoFormaPagamento tipoFormaPagamento, Long parcelas) {
        this.tipoFormaPagamento = tipoFormaPagamento;
        this.parcelas = parcelas;
    }

    public FormaPagamento() {
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public TipoFormaPagamento getTipoFormaPagamento() {
        return tipoFormaPagamento;
    }

    public void setTipoFormaPagamento(TipoFormaPagamento tipoFormaPagamento) {
        this.tipoFormaPagamento = tipoFormaPagamento;
    }

    public Long getParcelas() {
        return parcelas;
    }

    public void setParcelas(Long parcelas) {
        this.parcelas = parcelas;
    }
}
