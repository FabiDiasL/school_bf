package br.com.xavecoding.school_bf.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Aluno;
import br.com.xavecoding.school_bf.orm.Disciplina;
import br.com.xavecoding.school_bf.repository.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class CrudAlunoService {

	private AlunoRepository alunoRepository;

	public CrudAlunoService(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@Transactional
	public void menu(Scanner sc) {
		Boolean isTrue = true;

		while (isTrue) {

			System.out.println("Qual ação deseja realizar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo(a) Aluno(a)");
			System.out.println("2 - Atualizar um(a) Aluno(a)");
			System.out.println("3 - Listar todos os Alunos");
			System.out.println("4 - Excluir um(a) Aluno(a)");
			System.out.println("5 - Visualizar informações de um(a) Aluno(a)");

			int opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				this.cadastrar(sc);
				break;
			case 2:
				this.atualizar(sc);
				break;
			case 3:
				this.listar();
				break;
			case 4:
				this.excluir(sc);
				break;
			case 5:
				this.visualizarAluno(sc);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void cadastrar(Scanner sc) {
		System.out.println("Digite o nome do(a) aluno(a):");
		sc.nextLine();
		String nome = sc.nextLine();

		System.out.println("Digite a idade do(a) aluno(a):");
		Integer idade = sc.nextInt();

		Aluno aluno = new Aluno(nome, idade);
		this.alunoRepository.save(aluno);
		System.out.println("Aluno(a) salvo(a) com sucesso!\n");
	}

	private void atualizar(Scanner sc) {
		System.out.println("Digite o id do(a) aluno(a) que deseja atualizar:");
		Long id = sc.nextLong();

		Optional<Aluno> optional = this.alunoRepository.findById(id);

		if (optional.isPresent()) {

			System.out.println("Digite o nome do(a) aluno(a):");
			sc.nextLine();
			String nome = sc.nextLine();

			System.out.println("Digite a idade do(a) aluno(a):");
			Integer idade = sc.nextInt();

			Aluno aluno = optional.get();
			aluno.setNome(nome);
			aluno.setIdade(idade);
			this.alunoRepository.save(aluno);

			System.out.println("Aluno(a) atualizado(a) com sucesso!\n");
		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}

	private void listar() {

		Iterable<Aluno> alunos = this.alunoRepository.findAll();

		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
		System.out.println();
	}

	private void excluir(Scanner sc) {
		System.out.println("Digite o id do(a) aluno(a) que deseja excluir:");
		Long id = sc.nextLong();

		Optional<Aluno> optional = this.alunoRepository.findById(id);

		if (optional.isPresent()) {
			Aluno aluno = optional.get();

			this.alunoRepository.deleteById(id);

			System.out.println("Aluno(a) excluído(a) com sucesso!\n");

		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}

	@Transactional
	private void visualizarAluno(Scanner sc) {
		System.out.println("Digite o id do(a) aluno(a) que deseja informações:");
		Long id = sc.nextLong();

		Optional<Aluno> optional = this.alunoRepository.findById(id);

		if (optional.isPresent()) {
			Aluno aluno = optional.get();

			System.out.println("Aluno: {" + aluno.getNome());
			System.out.println("ID: " + aluno.getId());
			System.out.println("Idade: " + aluno.getIdade());
			System.out.println("Disciplinas: [");

			if (aluno.getDisciplinas() != null) {
				for (Disciplina disciplina : aluno.getDisciplinas()) {
					System.out.println("ID da disciplina: " + disciplina.getId());
					System.out.println("Nome da disciplina: " + disciplina.getNome());
					System.out.println("Ementa da disciplina: " + disciplina.getEmenta());
					System.out.println();
				}
			}
			System.out.println("]\n}");
		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}
}