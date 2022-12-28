package br.com.xavecoding.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xavecoding.regesc.orm.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long>{

}
