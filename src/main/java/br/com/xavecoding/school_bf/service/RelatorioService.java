package br.com.xavecoding.school_bf.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Aluno;
import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repository.AlunoRepository;
import br.com.xavecoding.school_bf.repository.ProfessorRepository;

@Service
public class RelatorioService {
	private AlunoRepository alunoRepository;
	private ProfessorRepository professorRepository; 

	public RelatorioService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
		this.alunoRepository = alunoRepository;
		this.professorRepository = professorRepository;
	}

	public void menu(Scanner sc) {
		Boolean isTrue = true;

		while (isTrue) {

			System.out.println("\nQual relatório você deseja?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Gerar relatório de Alunos por um nome");
			System.out.println("2 - Gerar relatório de Alunos por um nome e uma idade menor ou igual");
			System.out.println("3 - Gerar relatório de Alunos por um nome e uma idade maior ou igual");
			System.out.println("4 - Gerar relatório de Alunos por um nome, uma idade maior ou igual e uma disciplina em que esteja matriculado(a)");
			System.out.println("5 - Gerar relatório de Professor atribuído a uma disciplina");

			int opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				this.alunosPorNome(sc);
				break;
			case 2:
				this.alunosPorNomeEIdadeMenorIgual(sc);
				break;
			case 3:
				this.alunosPorNomeEIdadeMaiorIgual(sc);
				break;
			case 4:
				this.alunosPorNomeEIdadeMaiorIgualMatriculado(sc);
				break;
			case 5:
				this.professorAtribuido(sc);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void alunosPorNome(Scanner sc) {
		System.out.println("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();

		List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);

		for (Aluno aluno : alunos) {
			System.out.println(alunos);
		}
	}

	private void alunosPorNomeEIdadeMenorIgual(Scanner sc) {
		System.out.println("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();

		System.out.println("Idade: ");
		Integer idade = sc.nextInt();

		List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);

		for (Aluno aluno : alunos) {
			System.out.println(alunos);
		}
	}

	private void alunosPorNomeEIdadeMaiorIgual(Scanner sc) {
		System.out.println("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();

		System.out.println("Idade: ");
		Integer idade = sc.nextInt();

		List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaior(nome, idade);

		// Outra forma de escrever o comando for each
		alunos.forEach(System.out::println);
	}

	private void alunosPorNomeEIdadeMaiorIgualMatriculado(Scanner sc) {
		System.out.println("Nome do(a) aluno(a): ");
		sc.nextLine();
		String nomeAluno = sc.nextLine();

		System.out.println("Idade: ");
		Integer idadeAluno = sc.nextInt();

		System.out.println("Nome da disciplina: ");
		String nomeDisciplina = sc.nextLine();

		List<Aluno> alunos = this.alunoRepository.findNomeIdadeIgualOuMaiorMatriculado(nomeAluno, idadeAluno,
				nomeDisciplina);

		alunos.forEach(System.out::println);
	}
	
	private void professorAtribuido(Scanner sc) {
		System.out.println("Nome do(a) professor(a): ");
		sc.nextLine();
		String nomeProfessor = sc.nextLine();

		System.out.println("Nome da disciplina: ");
		String nomeDisciplina = sc.nextLine();

		List<Professor> professores = this.professorRepository.findProfessorAtribuido(nomeProfessor, nomeDisciplina);

		professores.forEach(System.out::println);
	}
}