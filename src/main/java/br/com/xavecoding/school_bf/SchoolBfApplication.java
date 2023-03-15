package br.com.xavecoding.school_bf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.xavecoding.school_bf.orm.Professor;
import br.com.xavecoding.school_bf.repositorio.ProfessorRepositorio;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class SchoolBfApplication implements CommandLineRunner{	
	private ProfessorRepositorio repositorio;
	
	public SchoolBfApplication(ProfessorRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public static void main(String[] args) {
		SpringApplication.run(SchoolBfApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Melo", "abcd");
		
		System.out.println("Professor antes do save");
		System.out.println(professor);
		
		this.repositorio.save(professor);
		
		System.out.println("Professor depois do save");
		System.out.println(professor);
	}
}