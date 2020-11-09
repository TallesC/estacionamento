package br.com.everis.estacionamento.model;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "FaixaPreco")
public class FaixaPreco {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String dia;
	
	@NotBlank
	private SimpleDateFormat horaInicial;
	
	@NotBlank 
	private SimpleDateFormat horaFinal;
	

}
