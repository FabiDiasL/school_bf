package br.com.xavecoding.school_bf.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.com.xavecoding.school_bf.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long> {

	// este comando faz com que o Spring Data monte o sql para a entidade aluno
	// (mapeada por este repository).
	// função que busca um aluno do BD utilizando o ínicio do nome.
	// vai procurar todos os registros da tabela de Alunos, na coluna nome e buscar
	// os nomes que começam com a string que será passada.
	List<Aluno> findByNomeStartingWith(String nome);

	// função que busca um aluno do BD utilizando o ínicio do nome e uma idade menor
	// ou igual a idade passada.
	List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome, Integer idade);

	// Tipo de busca JPQL
	@Query("SELECT a FROM Aluno a WHERE a.nome like :nome% AND a.idade :idade >= :idade")
	List<Aluno> findNomeIdadeIgualOuMaior(String nome, Integer idade);

	// JPQL
	@Query("SELECT a FROM Aluno a INNER JOIN a.disciplinas d WHERE a.nome like :nomeAluno% AND a.idade >= :idadeAluno AND d.nome like :nomeDisciplina%")
	List<Aluno> findNomeIdadeIgualOuMaiorMatriculado(String nomeAluno, Integer idadeAluno, String nomeDisciplina);
}