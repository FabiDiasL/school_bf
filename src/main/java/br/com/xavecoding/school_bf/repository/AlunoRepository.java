package br.com.xavecoding.school_bf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.school_bf.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {
	
	//este comando faz com que o Spring Data monte o sql para a entidade aluno (mapeada por este repository). 
	// função que busca um aluno do BD utilizando o nome. 
	List<Aluno> findByNome(String nome); 
}