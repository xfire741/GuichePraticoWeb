package com.senac.guichepratico.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.senac.guichepratico.model.Servico;

@Repository
@Transactional
public interface ServicoRepository extends CrudRepository<Servico, Long>{

}
