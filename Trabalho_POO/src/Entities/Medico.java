package Entities;

import java.time.LocalDate;

public class Medico {
	
	private long id;
	private String nome;
	private int crm;
	private String areaAtuacao;
	private String telefone;
	private String endereco;
	private LocalDate contratacao;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCrm() {
		return crm;
	}
	
	public void setCrm(int crm) {
		this.crm = crm;
	}
	
	public String getAreaAtuacao() {
		return areaAtuacao;
	}
	
	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public LocalDate getContratacao() {
		return contratacao;
	}
	
	public void setContratacao(LocalDate dataContratacao) {
		this.contratacao = dataContratacao;
	}
}
