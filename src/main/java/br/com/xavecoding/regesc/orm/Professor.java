package br.com.xavecoding.regesc.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, unique = true)
	private String prontuario;
	
	@OneToMany(mappedBy = "professor")
	private List<Disciplina> disciplinas;
	
	@Deprecated
	public Professor() {}
	
	public Professor(String nome, String prontuario) {
		this.nome = nome;
		this.prontuario = prontuario;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProntuario() {
		return prontuario;
	}

	public void setProntuario(String prontuario) {
		this.prontuario = prontuario;
	}

	@Override
	public String toString() {
		return "Professor [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + "]";
	}
	
	
}
