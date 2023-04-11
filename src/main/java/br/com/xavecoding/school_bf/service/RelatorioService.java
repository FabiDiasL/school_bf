package br.com.xavecoding.school_bf.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Aluno;
import br.com.xavecoding.school_bf.repository.AlunoRepository;

@Service
public class RelatorioService {
	private AlunoRepository alunoRepository;

	public RelatorioService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	public void menu(Scanner sc) {
		Boolean isTrue = true;

		while (isTrue) {

			System.out.println("\nQual relatório você deseja?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Gerar relatório de Alunos por um nome");
			System.out.println("2 - Gerar relatório de Alunos por um nome e uma idade");

			int opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				this.alunosPorNome(sc);
				break;
			case 2:
				this.alunosPorNomeEIdade(sc);
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
	
	private void alunosPorNomeEIdade(Scanner sc) {
		System.out.println("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();
		
		System.out.println("Idade menor ou igual a: ");
		Integer idade = sc.nextInt();
		
		List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);
		
		for (Aluno aluno : alunos) {
			System.out.println(alunos);
		}		
	}
}