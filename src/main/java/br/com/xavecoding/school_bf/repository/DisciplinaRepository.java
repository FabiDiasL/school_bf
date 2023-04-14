package br.com.xavecoding.school_bf.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.xavecoding.school_bf.orm.Disciplina;

@Repository
public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {

}