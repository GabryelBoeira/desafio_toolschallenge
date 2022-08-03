package br.com.toolschallenge.converter;

import br.com.toolschallenge.dto.DescricaoTransacaoDto;
import br.com.toolschallenge.model.DescricaoTransacao;
import br.com.toolschallenge.util.Converter;
import org.springframework.stereotype.Component;

@Component
public class DescricaoTransacaoConverter extends Converter<DescricaoTransacaoDto, DescricaoTransacao> {

    public DescricaoTransacaoConverter() {
        super(DescricaoTransacaoConverter::converterModel, DescricaoTransacaoConverter::converterDto);
    }

    private static DescricaoTransacao converterModel(DescricaoTransacaoDto dto) {
        if (dto == null) return null;

        return new DescricaoTransacao(dto.valor(), dto.dataHora(), dto.estabelecimento(), dto.nsu(), dto.codigoAutorizacao(), dto.status());
    }

    private static DescricaoTransacaoDto converterDto(DescricaoTransacao model) {
        if (model == null) return null;

        return new DescricaoTransacaoDto(model.getValor(), model.getDataHora(), model.getEstabelecimento(), model.getNsu(), model.getCodigoAutorizacao(), model.getStatus());
    }

}
