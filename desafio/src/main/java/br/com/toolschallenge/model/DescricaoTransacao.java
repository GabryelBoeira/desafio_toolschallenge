package br.com.toolschallenge.model;

import br.com.toolschallenge.enums.TipoStatusTransacao;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "DescricaoTransacao")
public class DescricaoTransacao extends AbstractPersistable<Long> {

    @Positive
    private BigDecimal valor;

    @NotNull
    private LocalDateTime dataHora;

    @NotBlank
    private String estabelecimento;

    private String nsu;
    private String codigoAutorizacao;
    private TipoStatusTransacao status;

    public DescricaoTransacao() {}

    public DescricaoTransacao(BigDecimal valor, LocalDateTime dataHora, String estabelecimento) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.estabelecimento = estabelecimento;
    }

    public DescricaoTransacao(BigDecimal valor, LocalDateTime dataHora, String estabelecimento, String nsu, String codigoAutorizacao, TipoStatusTransacao status) {
        this.valor = valor;
        this.dataHora = dataHora;
        this.estabelecimento = estabelecimento;
        this.nsu = nsu;
        this.codigoAutorizacao = codigoAutorizacao;
        this.status = status;
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHoram) {
        this.dataHora = dataHoram;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getNsu() {
        return nsu;
    }

    public void setNsu(String nsu) {
        this.nsu = nsu;
    }

    public String getCodigoAutorizacao() {
        return codigoAutorizacao;
    }

    public void setCodigoAutorizacao(String codigoAutorizacao) {
        this.codigoAutorizacao = codigoAutorizacao;
    }

    public TipoStatusTransacao getStatus() {
        return status;
    }

    public void setStatus(TipoStatusTransacao status) {
        this.status = status;
    }
}
