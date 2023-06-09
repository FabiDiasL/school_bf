package br.com.xavecoding.school_bf.orm;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private Integer idade;

	@ManyToMany(mappedBy = "alunos")
	private Set<Disciplina> disciplinas; // um conjunto em java não permite repetição do mesmo elemento, diferente de
											// uma lista.

	@Deprecated
	public Aluno() {
	}

	public Aluno(String nome, Integer idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public Aluno(String nome, Integer idade, Set<Disciplina> disciplinas) {
		this.nome = nome;
		this.idade = idade;
		this.disciplinas = disciplinas;
	}

	public Long getId() {
		return id;
	}

	//public void setId(Long id) {
	//	this.id = id;
	//}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return "Aluno [id = " + id + ", nome = " + nome + ", idade = " + idade + "]";
	}
}