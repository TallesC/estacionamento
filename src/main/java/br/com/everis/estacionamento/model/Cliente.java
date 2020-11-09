package br.com.everis.estacionamento.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity 
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "cpf",name="Cliente"))
public class Cliente implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	public Cliente(){}

	public Cliente(@NotBlank String primeiroNome, @NotBlank String sobrenome, @NotBlank String cpf,
			@NotBlank String senha, LocalDate nascimento) {
		this.primeiroNome = primeiroNome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.senha = senha;
		this.nascimento = nascimento;
	}

	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank @NotNull
	private String primeiroNome;
	
	@NotBlank @NotNull
	private String sobrenome;
	
	@NotBlank @NotNull
	private String cpf;
	
	@NotBlank @NotNull
	private String senha;
	
	@NotNull
	private boolean status = true;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate nascimento;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

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

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.cpf;
	}

	@Override
	public boolean isAccountNonExpired() {
		return status;
	}

	@Override
	public boolean isAccountNonLocked() {
		return status;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return status;
	}

	@Override
	public boolean isEnabled() {
		return status;
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
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
