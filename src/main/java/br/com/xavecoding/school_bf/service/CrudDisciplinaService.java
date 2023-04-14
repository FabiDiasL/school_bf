package br.com.xavecoding.school_bf.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Aluno;
import br.com.xavecoding.school_bf.orm.Disciplina;
import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repository.AlunoRepository;
import br.com.xavecoding.school_bf.repository.DisciplinaRepository;
import br.com.xavecoding.school_bf.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {
	private DisciplinaRepository disciplinaRepository;
	private ProfessorRepository professorRepository;
	private AlunoRepository alunoRepository;

	public CrudDisciplinaService(DisciplinaRepository disciplinaRepository, ProfessorRepository professorRepository,
			AlunoRepository alunoRepository) {
		this.disciplinaRepository = disciplinaRepository;
		this.professorRepository = professorRepository;
		this.alunoRepository = alunoRepository;
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
			System.out.println("5 - Matricular alunos");

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
				this.matricularAlunos(sc);
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

			Set<Aluno> alunos = this.matricular(sc);

			Disciplina disciplina = new Disciplina(nome, ementa, professor);
			disciplina.setAlunos(alunos);
			disciplinaRepository.save(disciplina);

			System.out.println("Disciplina salva com sucesso!\n");
		} else {
			System.out.println("O ID " + professorId + " de professor é inválido.");
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

				Set<Aluno> alunos = this.matricular(sc);

				disciplina.setNome(nome);
				disciplina.setEmenta(ementa);
				disciplina.setProfessor(professor);
				disciplina.setAlunos(alunos);

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

	private Set<Aluno> matricular(Scanner sc) {
		Boolean isTrue = true;
		Set<Aluno> alunos = new HashSet<>();

		while (isTrue) {
			System.out.println("Digite o ID do aluno a ser matriculado (digite 0 para sair): ");
			Long alunoId = sc.nextLong();

			if (alunoId > 0) {
				System.out.println("alunoId: " + alunoId);
				Optional<Aluno> optional = this.alunoRepository.findById(alunoId);
				if (optional.isPresent()) {
					alunos.add(optional.get());
				} else {
					System.out.println("Nenhum aluno possui o id " + alunoId + "!");
				}
			} else {
				isTrue = false;
			}
		}
		return alunos;
	}

	private void matricularAlunos(Scanner sc) {
		System.out.println("Digite o id da disciplina para matricular alunos: ");
		Long id = sc.nextLong();

		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

		if (optionalDisciplina.isPresent()) {

			Disciplina disciplina = optionalDisciplina.get();
			Set<Aluno> novosAlunos = this.matricular(sc);
			disciplina.getAlunos().addAll(novosAlunos);
			this.disciplinaRepository.save(disciplina);
		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}
}