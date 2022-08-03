package br.com.toolschallenge.converter;

import br.com.toolschallenge.dto.FormaPagamentoDto;
import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.model.FormaPagamento;
import br.com.toolschallenge.model.Transacao;
import br.com.toolschallenge.util.Converter;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class FormaPagamentoConverter extends Converter<FormaPagamentoDto, FormaPagamento> {

    public FormaPagamentoConverter() {
        super(FormaPagamentoConverter::converterModel, FormaPagamentoConverter::converterDto);
    }

    private static FormaPagamentoDto converterDto(FormaPagamento model) {
        if (model == null) return null;

        return new FormaPagamentoDto(model.getTipoFormaPagamento(), model.getParcelas());
    }

    private static FormaPagamento converterModel(FormaPagamentoDto dto) {
        if (dto == null) return null;

        return new FormaPagamento(dto.tipo(), dto.parcelas());
    }
}
