package br.com.toolschallenge.dto;

import br.com.toolschallenge.enums.TipoFormaPagamento;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


public record FormaPagamentoDto(@NotNull TipoFormaPagamento tipo, @Positive Long parcelas) {
}

