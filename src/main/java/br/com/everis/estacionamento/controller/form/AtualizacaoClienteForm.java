package br.com.everis.estacionamento.controller.form;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.everis.estacionamento.model.Cliente;
import br.com.everis.estacionamento.repository.ClienteRepository;

public class AtualizacaoClienteForm {
	
	private String primeiroNome;
	private String sobrenome;
	private String senha;
	
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);
		if(this.primeiroNome != null && !this.primeiroNome.isBlank()) 
			cliente.setPrimeiroNome(primeiroNome);
		if(this.sobrenome != null && !this.sobrenome.isBlank()) 
			cliente.setSobrenome(sobrenome);
		if(this.senha != null && !this.senha.isBlank()) 
			cliente.setSenha(new BCryptPasswordEncoder().encode(senha));
		return cliente;
	}

	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}

