package br.com.everis.estacionamento.model;

import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "Ticket")
public class Ticket {
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private SimpleDateFormat horaEntrada;
	
	@NotBlank
	private String codigoBarra;
	
	private SimpleDateFormat horaSaida;
	
	private double valor;
	
	private double tempoPermanencia;
	
	private String codigoConfirmacaoPagamento;
	
	@OneToOne
	private Carro idCarro;
	
	@OneToOne
	private Estacionamento idEstacionamento;
	
	

}
