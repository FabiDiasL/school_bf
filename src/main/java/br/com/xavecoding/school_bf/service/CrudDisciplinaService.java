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
				//this.atualizar(sc);
				break;
			case 3:
				//this.listar();
				break;
			case 4:
				//this.excluir(sc);
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
		String nome = sc.next();

		System.out.println("Digite a ementa da disciplina:");
		String ementa = sc.nextLine();
		
		System.out.println("Digite o ID do(a) professor(a):");
		Long professorId = sc.nextLong();

		Optional<Professor> optional = this.professorRepository.findById(professorId);
		
		if (optional.isPresent()) {

			Professor professor = optional.get();
			
			Disciplina disciplina = new Disciplina(nome, ementa, professor);
			disciplinaRepository.save(disciplina);
			
			System.out.println("Disciplina salva com sucesso!");
		}else {
			System.out.println("O ID " + professorId + " de professor é inválido.");
		}		
	}
}
