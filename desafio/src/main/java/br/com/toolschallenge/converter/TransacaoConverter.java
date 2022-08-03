package br.com.toolschallenge.converter;

import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.model.Transacao;
import br.com.toolschallenge.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class TransacaoConverter extends Converter<TransacaoDto, Transacao> {

    private static DescricaoTransacaoConverter descricaoConverter;

    private static FormaPagamentoConverter formaPagamentoConverter;

    public TransacaoConverter(DescricaoTransacaoConverter descricaoConverter, FormaPagamentoConverter formaPagamentoConverter) {
        super(TransacaoConverter::converterModel, TransacaoConverter::converterDto);
        this.descricaoConverter = descricaoConverter;
        this.formaPagamentoConverter = formaPagamentoConverter;
    }

    private static Transacao converterModel(TransacaoDto dto) {
        if (dto == null) return null;

        return new Transacao(Long.parseLong(dto.id()), dto.cartao(),
                descricaoConverter.converterDto(dto.descricao()),
                formaPagamentoConverter.converterDto(dto.formaPagamento()));
    }

    private static TransacaoDto converterDto(Transacao model) {
        if (model == null) return null;

        return new TransacaoDto(model.getId().toString(), model.getCartao(),
                descricaoConverter.converterModel(model.getDescricao()),
                formaPagamentoConverter.converterModel(model.getFormaPagamento()));
    }

}
