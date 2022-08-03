package br.com.toolschallenge.dto;

import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public record TransacaoDto(
        @Nullable String id,
        @NotBlank String cartao,
        @Valid DescricaoTransacaoDto descricao,
        @Valid FormaPagamentoDto formaPagamento
) {

}
