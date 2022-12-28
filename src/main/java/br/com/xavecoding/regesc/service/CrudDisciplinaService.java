package br.com.xavecoding.regesc.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.xavecoding.regesc.orm.Disciplina;
import br.com.xavecoding.regesc.orm.Professor;
import br.com.xavecoding.regesc.repository.DisciplinaRepository;
import br.com.xavecoding.regesc.repository.ProfessorRepository;

@Service
public class CrudDisciplinaService {
	private DisciplinaRepository disciplinaRepository;
	private ProfessorRepository professorRepository;
	
	public CrudDisciplinaService(DisciplinaRepository disciplinaRepository,
								 ProfessorRepository professorRepository) {
		this.professorRepository = professorRepository;
		this.disciplinaRepository  = disciplinaRepository;
	}

	public void menu(Scanner scanner) {
		Boolean isTrue = true;
		
		while(isTrue) {
			System.out.println("\nQual opção você quer executar?");
			System.out.println("0 - Voltar ao menu anterior");
			System.out.println("1 - Cadastrar novo Disciplina");
			System.out.println("2 - Atualizar uma Disciplina");
			System.out.println("3 - Visualizar todas Disciplina");
			System.out.println("4 - Deleta uma Disciplina");
			
			int opcao = scanner.nextInt();
			
			switch (opcao) {
			case 1: 
				this.cadastrar(scanner);
				break;	
			
			case 2:
				this.atualizar(scanner);
				break;
				
			case 3:
				this.visualizar(scanner);
				break;
				
			case 4:
				this.deletar(scanner);
				break;
				
			default:
				isTrue = false;
				break;
			}
		}
		System.out.println();
	}
	
	private void cadastrar(Scanner scanner) {
		System.out.print("Nome da disciplina: ");
		String nome = scanner.next();
		
		System.out.print("Semestre: ");
		Integer semestre = scanner.nextInt();
		
		System.out.println("Professor ID: ");
		Long professorId = scanner.nextLong();
		
		Optional<Professor> optional = professorRepository.findById(professorId);
		
		if(optional.isPresent()) {			
			Professor professor = optional.get();
			Disciplina disciplina = new Disciplina(nome, semestre, professor);
			disciplinaRepository.save(disciplina);
			System.out.println("Salva!\n");
			
			menu(scanner);
			
		}else {
			System.out.println("Prodessor ID " + professorId + " inválido\n");
			
			menu(scanner);
		}
	}
	
	private void atualizar(Scanner scanner) {
		System.out.print("Digite o id da disciplina a ser atualizada: ");
		Long id = scanner.nextLong();
		
		Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);
		
		if(optionalDisciplina.isPresent()) {
			Disciplina disciplina = optionalDisciplina.get();
			
			System.out.print("Nome da Disciplina: ");
			String nome = scanner.next();
			
			System.out.print("Semestre: ");
			Integer semestre = scanner.nextInt();
			
			System.out.println("Professor ID: ");
			Long professorId = scanner.nextLong();
			
			Optional<Professor> optionalProfessor = this.professorRepository.findById(professorId);
			if (optionalProfessor.isPresent()) {
				Professor professor = optionalProfessor.get();
				
				disciplina.setNome(nome);
				disciplina.setSemestre(semestre);
				disciplina.setProfessor(professor);
				
				disciplinaRepository.save(disciplina);
				System.out.println("Atualizado!\n");
			}
			else {
				System.out.println("Professor ID " +id+ " inválido\n");
			}			
			
		}else {
			System.out.println("O Id da disciplina informada: " + id + " é inválido\n");
		}
	}
	
	private void visualizar(Scanner scanner) {
		Iterable<Disciplina> disciplinas =  this.disciplinaRepository.findAll();
		for(Disciplina disciplina : disciplinas) {
			System.out.println(disciplina);
		}
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.print("Id: ");
		Long id = scanner.nextLong();
		this.disciplinaRepository.deleteById(id);
		System.out.println("Disciplina deletada\n");
	}
}
