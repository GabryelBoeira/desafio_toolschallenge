package br.com.toolschallenge.service;

import br.com.toolschallenge.converter.TransacaoConverter;
import br.com.toolschallenge.dto.TransacaoDto;
import br.com.toolschallenge.enums.TipoStatusTransacao;
import br.com.toolschallenge.model.DescricaoTransacao;
import br.com.toolschallenge.model.Transacao;
import br.com.toolschallenge.repository.TransacaoRepository;
import br.com.toolschallenge.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;
    private TransacaoConverter transacaoConverter;

    @Autowired
    public TransacaoService(TransacaoRepository transacaoRepository, TransacaoConverter transacaoConverter) {
        this.transacaoRepository = transacaoRepository;
        this.transacaoConverter = transacaoConverter;
    }

    public List<TransacaoDto> buscarTodosOsRegistros() {
        List<Transacao> transacaoList = transacaoRepository.findAllByDescricaoStatusIn(List.of(TipoStatusTransacao.AUTORIZADO, TipoStatusTransacao.NEGADO));
        return transacaoConverter.converterModelList(transacaoList);
    }

    public TransacaoDto buscarTransacaoPorId(final String id) {
        try {
            Transacao transacao = transacaoRepository.findByIdAndDescricaoStatusIn(Long.parseLong(id), Arrays.asList(TipoStatusTransacao.AUTORIZADO, TipoStatusTransacao.NEGADO)).orElse(null);
            return transacaoConverter.converterModel(transacao);
        } catch (Exception e) {
            return null;
        }
    }

    public TransacaoDto buscarEstornoPorId(final String id) {
        try {
            Transacao transacao = transacaoRepository.findByIdAndDescricaoStatusIn(Long.parseLong(id), List.of(TipoStatusTransacao.CANCELADO)).orElseThrow(() -> null);
            return transacaoConverter.converterModel(transacao);
        } catch (Exception e) {
            return null;
        }
    }

    public TransacaoDto processarTransacao(TransacaoDto transacao) {
        Transacao transacaoModel = transacaoConverter.converterDto(transacao);
        if (transacaoModel == null) return null;
        DescricaoTransacao descricao = transacaoModel.getDescricao();

        if (descricao.getStatus() == null) descricao.setStatus(TipoStatusTransacao.AUTORIZADO);
        descricao.setNsu(Utils.GerarNumeros(6));
        descricao.setCodigoAutorizacao(Utils.GerarNumeros(8));

        transacaoModel.setDescricao(descricao);
        transacaoModel = transacaoRepository.save(transacaoModel);
        return transacaoConverter.converterModel(transacaoModel);
    }

}
