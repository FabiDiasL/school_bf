package br.com.xavecoding.school_bf;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.school_bf.service.CrudDisciplinaService;
import br.com.xavecoding.school_bf.service.CrudProfessorService;

@SpringBootApplication
public class SchoolBfApplication implements CommandLineRunner {
	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;

	// os objetos passados por parâmetro são injetados pelo Spring, automaticamente.
	// pq suas classes possuem a anotação @service.
	public SchoolBfApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService) {
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolBfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner sc = new Scanner(System.in);

		while (isTrue) {
			System.out.println("Com qual entidade deseja interagir?");
			System.out.println("0 - Sair");
			System.out.println("1 - Professor");
			System.out.println("2 - Disciplina");

			int opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				this.professorService.menu(sc);
				break;
			case 2:
				this.disciplinaService.menu(sc);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}
}