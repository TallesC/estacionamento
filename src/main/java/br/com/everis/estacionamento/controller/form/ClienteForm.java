package br.com.everis.estacionamento.controller.form;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import br.com.everis.estacionamento.model.Cliente;

public class ClienteForm {
	@NotBlank @NotNull
	private String primeiroNome;
	
	@NotBlank @NotNull
	private String sobrenome;
	
	@NotBlank @NotNull
	private String cpf;
	
	@NotBlank @NotNull
	private String senha;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate nascimento;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public Cliente converter() {
		return new Cliente(primeiroNome, sobrenome, cpf, senha, nascimento);
	}
	
	
}
