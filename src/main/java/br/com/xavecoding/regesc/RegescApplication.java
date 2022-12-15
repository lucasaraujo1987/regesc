package br.com.xavecoding.regesc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@SpringBootApplication
public class RegescApplication implements CommandLineRunner{

	private ProfessorRepository repository;
	
	public RegescApplication(ProfessorRepository repository) {
		this.repository = repository;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RegescApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Professor professor = new Professor("Samuca", "xyz");
		this.repository.save(professor);
	}

}
