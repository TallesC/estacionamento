package br.com.everis.estacionamento.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.everis.estacionamento.model.Estacionamento;

public class EstacionamentoDto{
	
	private String nomeFantasia;
	private String endereco;
	private String cnpj;
	private String telefone;
	
	public EstacionamentoDto(Estacionamento estacionamento) {
		this.nomeFantasia = estacionamento.getNomeFantasia();
		this.endereco = estacionamento.getEndereco();
		this.cnpj = estacionamento.getCnpj();
		this.telefone = estacionamento.getTelefone();
	}
	
	public static List<EstacionamentoDto>converter(List<Estacionamento> estacionamentos) {		
		return estacionamentos.stream().map(EstacionamentoDto::new).collect(Collectors.toList());	
	}
	
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getTelefone() {
		return telefone;
	}
	
}
