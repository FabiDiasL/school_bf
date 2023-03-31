package br.com.xavecoding.school_bf.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Disciplina;
import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repository.ProfessorRepository;
import jakarta.transaction.Transactional;

@Service
public class CrudProfessorService {
	private ProfessorRepository professorRepository; // dependência da classe ProfessorRepository professorRepository.

	// o Spring cria, automaticamente, um objeto com a interface
	// ProfessorRepository.
	// e o injeta para nós no contrutor desta classe -> injeção de dependência.
	public CrudProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}

	@Transactional  //Esta anotação descreve um atributo de transação em um método individual ou em uma classe, para fazer transações com o BD (p/ @OneToMany). 
	public void menu(Scanner sc) {
		Boolean isTrue = true;

		while (isTrue) {

			System.out.println("Qual ação deseja realizar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo(a) Professor(a)");
			System.out.println("2 - Atualizar um(a) Professor(a)");
			System.out.println("3 - Listar todos os Professores");
			System.out.println("4 - Excluir um(a) Professor(a)");
			System.out.println("5 - Visualizar informações de um(a) Professor(a)");

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
				this.visualizarProfessor(sc);
				break;
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}

	private void cadastrar(Scanner sc) {
		System.out.println("Digite o nome do(a) professor(a):");
		String nome = sc.next();

		System.out.println("Digite a matrícula do(a) professor(a):");
		String matricula = sc.next();

		Professor professor = new Professor(nome, matricula);
		this.professorRepository.save(professor);
		System.out.println("Professor(a) salvo(a) com sucesso!\n");
	}

	private void atualizar(Scanner sc) {
		System.out.println("Digite o id do(a) professor(a) que deseja atualizar:");
		Long id = sc.nextLong();

		// esta classe Optional guarda um obj do tipo Professor e se não houver um obj
		// nesta busca ela terá um null.
		Optional<Professor> optional = this.professorRepository.findById(id);

		// se o hibernate conseguir acha um registro na tabela professores com o id
		// passado pelo usuário.
		if (optional.isPresent()) {

			System.out.println("Digite o nome do(a) professor(a):");
			sc.nextLine();
			String nome = sc.nextLine();

			System.out.println("Digite a matrícula do(a) professor(a):");
			String matricula = sc.next();

			Professor professor = optional.get();
			professor.setNome(nome);
			professor.setMatricula(matricula);
			this.professorRepository.save(professor); // atualiza o registro no BD.

			System.out.println("Professor(a) atualizado(a) com sucesso!\n");
		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}

	private void listar() {

		Iterable<Professor> professores = this.professorRepository.findAll();

		for (Professor professor : professores) {
			System.out.println(professor);
		}
		System.out.println();
	}

	private void excluir(Scanner sc) {
		System.out.println("Digite o id do(a) professor(a) que deseja excluir:");
		Long id = sc.nextLong();

		Optional<Professor> optional = this.professorRepository.findById(id);

		if (optional.isPresent()) {
			Professor professor = optional.get();

			this.professorRepository.deleteById(id);

			System.out.println("Professor(a) excluído(a) com sucesso!\n");

		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}
	
	@Transactional
	private void visualizarProfessor(Scanner sc) {
		System.out.println("Digite o id do(a) professor(a) que deseja informações:");
		Long id = sc.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if (optional.isPresent()) {
			Professor professor = optional.get();
			
			System.out.println("Professor: {" + professor.getNome());
			System.out.println("ID: " + professor.getId());
			System.out.println("Matrícula: " + professor.getMatricula());
			System.out.println("Disciplinas: [");
			
			for(Disciplina disciplina: professor.getDisciplinas()) {
				System.out.println("ID da disciplina: " + disciplina.getId());
				System.out.println("Nome da disciplina: " + disciplina.getNome());
				System.out.println("Ementa da disciplina: " + disciplina.getEmenta());
				System.out.println();
			}
			System.out.println("]\n}");			
		} else {
			System.out.println("O id " + id + " é inválido!\n");
		}
	}	
}