package br.com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name="Preficacao")
public class Precificacao {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double valorMinimo;
	private int tempoValorMinimo;
	
	private int tolerancia;
	
	@NotBlank
	private double fracao; 
	
	private double valorHora;
	
	@OneToOne
	private Piso idPiso;
	
	@OneToOne
	private FaixaPreco idFaixaPreco;
	

}
