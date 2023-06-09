package br.com.xavecoding.school_bf.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String matricula;

	@OneToMany(mappedBy = "professor")
	private List<Disciplina> disciplinas;

	@Deprecated
	public Professor() {
	}

	public Professor(String nome, String matricula) {
		this.nome = nome;
		this.matricula = matricula;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	// Ação de ON REMOVE SET NULL.
	@PreRemove
	public void atualizaDisciplinasOnRemove() {
		System.out.println("****** atualizaDisciplinasOnRemove ******");
		for (Disciplina disciplina : this.getDisciplinas()) {
			disciplina.setProfessor(null);
		}
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", matricula=" + matricula + "]";
	}
}