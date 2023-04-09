package br.com.xavecoding.school_bf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.school_bf.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
	public abstract List<Aluno> findByName(String nome);
}
