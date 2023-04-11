package br.com.xavecoding.school_bf;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.school_bf.service.CrudAlunoService;
import br.com.xavecoding.school_bf.service.CrudDisciplinaService;
import br.com.xavecoding.school_bf.service.CrudProfessorService;
import br.com.xavecoding.school_bf.service.RelatorioService;

@SpringBootApplication
public class SchoolBfApplication implements CommandLineRunner {
	private CrudProfessorService professorService;
	private CrudDisciplinaService disciplinaService;
	private CrudAlunoService alunoService;
	private RelatorioService relatorioService;

	// os objetos passados por parâmetro são injetados pelo Spring, automaticamente.
	// pq suas classes possuem a anotação @service.
	public SchoolBfApplication(CrudProfessorService professorService, CrudDisciplinaService disciplinaService, CrudAlunoService alunoService, RelatorioService relatorioService) {
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunoService = alunoService;
		this.relatorioService = relatorioService;
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
			System.out.println("3 - Aluno");
			System.out.println("4 - Relatório");

			int opcao = sc.nextInt();

			switch (opcao) {
			case 1:
				this.professorService.menu(sc);
				break;
			case 2:
				this.disciplinaService.menu(sc);
				break;
			case 3:
				this.alunoService.menu(sc);
				break;
			case 4:
				this.relatorioService.menu(sc);
				break;
			default:
				isTrue = false;
				break;
			}
		}
	}
}