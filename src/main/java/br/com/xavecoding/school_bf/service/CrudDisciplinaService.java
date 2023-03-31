package br.com.xavecoding.school_bf.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Disciplina;
import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repository.DisciplinaRepository;
import br.com.xavecoding.school_bf.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {
	private DisciplinaRepository disciplinaRepository;
	private ProfessorRepository professorRepository;

	public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.professorRepository = professorRepository;
	}

	public void menu(Scanner sc) {
		Boolean isTrue = true;

		while (isTrue) {

			System.out.println("Qual ação deseja realizar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar nova Disciplina");
			System.out.println("2 - Atualizar uma Disciplina");
			System.out.println("3 - Listar todas as Disciplinas");
			System.out.println("4 - Excluir uma Disciplina");

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
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void cadastrar(Scanner sc) {
		System.out.println("Digite o nome da disciplina:");
		sc.nextLine();
		String nome = sc.nextLine();

		System.out.println("Digite a ementa da disciplina:");
		String ementa = sc.nextLine();

		System.out.println("Digite o ID do(a) professor(a):");
		Long professorId = sc.nextLong();

		Optional<Professor> optional = this.professorRepository.findById(professorId);

		if (optional.isPresent()) {

			Professor professor = optional.get();

			Disciplina disciplina = new Disciplina(nome, ementa, professor);
			disciplinaRepository.save(disciplina);

			System.out.println("Disciplina salva com sucesso!\n");
		} else {
			System.out.println("O ID " + professorId + " é inválido.");
		}
	}

	private void atualizar(Scanner sc) {
		System.out.println("Digite o id da disciplina que deseja atualizar:");
		Long id = sc.nextLong();

		// esta classe Optional guarda um obj do tipo disciplina e se não houver um obj
		// nesta busca ela terá um null.
		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

		// se o hibernate conseguir acha um registro na tabela disciplina com o id
		// passado pelo usuário.
		if (optionalDisciplina.isPresent()) {
			Disciplina disciplina = optionalDisciplina.get();

			System.out.println("Digite o nome da disciplina:");
			sc.nextLine();
			String nome = sc.nextLine();

			System.out.println("Digite a ementa da disciplina:");
			String ementa = sc.nextLine();

			System.out.println("Digite o ID do(a) professor(a):");
			Long professorId = sc.nextLong();

			Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);

			if (optionalProfessor.isPresent()) {
				Professor professor = optionalProfessor.get();

				disciplina.setNome(nome);
				disciplina.setEmenta(ementa);
				disciplina.setProfessor(professor);
				this.disciplinaRepository.save(disciplina); // atualiza o registro no BD.

				System.out.println("Disciplina atualizada com sucesso!\n");
			} else {
				System.out.println("O id " + id + " para professor(a) é inválido!\n");
			}

		} else {
			System.out.println("O id " + id + " para disciplina é inválido!\n");
		}
	}

	private void listar() {

		Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();

		for (Disciplina disciplina : disciplinas) {
			System.out.println(disciplina);
		}
		System.out.println();
	}

	private void excluir(Scanner sc) {
		System.out.println("Digite o id da disciplina que deseja excluir:");
		Long id = sc.nextLong();

		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

		if (optionalDisciplina.isPresent()) {
			Disciplina disciplina = optionalDisciplina.get();

			this.disciplinaRepository.deleteById(id);

			System.out.println("Disciplina " + disciplina.getNome() + " excluída com sucesso!\n");

		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}
}