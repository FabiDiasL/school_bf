package br.com.xavecoding.school_bf.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repository.ProfessorRepository;

@Service
public class CrudProfessorService {
	private ProfessorRepository professorRepository; //dependência da classe ProfessorRepository professorRepository.
	
	//o Spring cria, automaticamente, um objeto com a interface ProfessorRepository.
	//e o injeta para nós no contrutor desta classe -> injeção de dependência. 
	public CrudProfessorService(ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
	}
	
	public void menu(Scanner sc) {
		Boolean isTrue = true;
		
		while(isTrue) {
			
			System.out.println("Qual ação deseja realizar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo Professor");
			System.out.println("2 - Atualizar um Professor");
			System.out.println("3 - Excluir um Professor");
			
			int opcao = sc.nextInt();
			
			switch(opcao) {
			case 1:
				this.cadastrar(sc);
				break;
			case 2:
				this.atualizar(sc);
				break;
			case 3:
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
		System.out.println("Digite o nome do professor:");
		String nome = sc.next();
		
		System.out.println("Digite a matrícula do professor:");
		String matricula = sc.next();
		
		Professor professor = new Professor(nome, matricula);
		this.professorRepository.save(professor);
		System.out.println("Professor salvo com sucesso!\n");	
	}
	
	private void atualizar(Scanner sc) {
		System.out.println("Digite o id do professor que deseja atualizar:");
		Long id = sc.nextLong();
		
		//esta classe Optional guarda um obj do tipo Professor e se não houver um obj nesta busca ela terá um null.
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		//se o hibernate conseguir acha um registro na tabela professores com o id passado pelo usuário.
		if(optional.isPresent()) {
						
			System.out.println("Digite o nome do professor:");
			String nome = sc.next();
			
			System.out.println("Digite a matrícula do professor:");
			String matricula = sc.next();
			
			Professor professor = optional.get();
			professor.setNome(nome);
			professor.setMatricula(matricula);
			this.professorRepository.save(professor); // atualiza o registro no BD.
			
			System.out.println("Professor atualizado com sucesso!\n");		
		}else {
			System.out.println("O id " + id + " é inválido!\n");
		}			
	}
	
	private void excluir(Scanner sc) {
		System.out.println("Digite o id do professor que deseja atualizar:");
		Long id = sc.nextLong();
		
		Optional<Professor> optional = this.professorRepository.findById(id);
		
		if(optional.isPresent()) {
			Professor professor = optional.get();
			this.professorRepository.deleteById(id);
			
			System.out.println("Professor excluído com sucesso!\n");			
		}else {
			System.out.println("O id " + id + " é inválido!\n");
		}		
	}
}