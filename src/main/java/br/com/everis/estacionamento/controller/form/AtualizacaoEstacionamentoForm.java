package br.com.everis.estacionamento.controller.form;

import br.com.everis.estacionamento.model.Estacionamento;
import br.com.everis.estacionamento.repository.EstacionamentoRepository;

public class AtualizacaoEstacionamentoForm {
	
	private String nomeFantasia;

	private String endereco;

	private String razaoSocial;

	private String cnpj;

	private String telefone;
	

	public Estacionamento atualizar(Long id, EstacionamentoRepository estacionamentoRepository) {
		Estacionamento estacionamento = estacionamentoRepository.getOne(id);
		if(this.nomeFantasia != null && !this.nomeFantasia.isBlank()) 
			estacionamento.setNomeFantasia(this.nomeFantasia);
		if(this.endereco != null && !this.endereco.isBlank()) 
			estacionamento.setEndereco(this.endereco);
		if(this.razaoSocial != null && !this.razaoSocial.isBlank()) 
			estacionamento.setRazaoSocial(this.razaoSocial);
		if(this.cnpj != null && !this.cnpj.isBlank()) 
			estacionamento.setCnpj(this.cnpj);
		if(this.telefone != null && !this.telefone.isBlank()) 
			estacionamento.setTelefone(this.telefone);
		return estacionamento;
		
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}


	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	public String getEndereco() {
		return endereco;
	}


	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}


	public String getRazaoSocial() {
		return razaoSocial;
	}


	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

		
	
}
