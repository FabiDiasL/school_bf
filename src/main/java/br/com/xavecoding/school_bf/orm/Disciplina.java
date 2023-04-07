package br.com.xavecoding.school_bf.orm;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String ementa;

	@ManyToOne()
	@JoinColumn(name = "professor_id", nullable = true)
	private Professor professor;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "disciplinas_alunos", joinColumns = @JoinColumn(name = "disciplina_fk"), inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
	private Set<Aluno> alunos;

	@Deprecated
	public Disciplina() {
	}

	public Disciplina(String nome, String ementa, Professor professor) {
		this.nome = nome;
		this.ementa = ementa;
		this.professor = professor;
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

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Disciplina [id=" + id + ", nome=" + nome + ", ementa=" + ementa + ", professor=" + professor
				+ ", alunos=" + alunos + "]";
	}
}