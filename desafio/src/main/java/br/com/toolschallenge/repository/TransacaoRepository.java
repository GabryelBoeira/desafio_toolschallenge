package br.com.toolschallenge.repository;

import br.com.toolschallenge.enums.TipoStatusTransacao;
import br.com.toolschallenge.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    List<Transacao> findAllByDescricaoStatusIn(List<TipoStatusTransacao> status);

    Optional<Transacao> findByIdAndDescricaoStatusIn(Long id, List<TipoStatusTransacao> status);
}
