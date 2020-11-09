package br.com.everis.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Vagas")
public class Vagas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank @Enumerated(EnumType.STRING)
	private TipoVeiculo tipoVeiculo;
	
	@NotBlank
	private Integer quantidade;
	
	@OneToOne
	private Piso idPiso;
	
}
