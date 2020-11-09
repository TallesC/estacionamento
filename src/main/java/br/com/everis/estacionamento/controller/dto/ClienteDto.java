package br.com.everis.estacionamento.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.everis.estacionamento.model.Cliente;

public class ClienteDto {
	
	private String primeiroNome;
	private String sobrenome;
	
	public ClienteDto(Cliente cliente) {
		this.primeiroNome = cliente.getPrimeiroNome();
		this.sobrenome = cliente.getSobrenome();
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	
	public static List<ClienteDto>converter(List<Cliente> clientes) {		
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());	
	}

}
