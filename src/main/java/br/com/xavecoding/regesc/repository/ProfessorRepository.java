package br.com.xavecoding.regesc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xavecoding.regesc.orm.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long>{

}
