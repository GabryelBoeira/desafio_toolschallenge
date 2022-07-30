package br.com.toolschallenge.repository;

import br.com.toolschallenge.model.Transacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends CrudRepository<Transacao, Long> {

}
