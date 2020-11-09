package br.com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity @Table(name = "Piso")
public class Piso {
	@Id	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String identificao;
	
	@NotBlank
	private Integer numVagas;
	
	@OneToOne
	private Estacionamento idEstacionamento;
	

}
