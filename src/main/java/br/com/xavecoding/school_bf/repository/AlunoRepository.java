package br.com.xavecoding.school_bf.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.school_bf.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
}
