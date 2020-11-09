package br.com.everis.estacionamento.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.everis.estacionamento.model.Carro;
import br.com.everis.estacionamento.model.Cliente;
import br.com.everis.estacionamento.repository.CarroRepository;
import br.com.everis.estacionamento.repository.ClienteRepository;

@RestController 
@RequestMapping("/carro")
public class CarroController {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CarroRepository carroRepository;
	
	@GetMapping("/consultar")
	public List<Carro> consultarCarros(@AuthenticationPrincipal Cliente logado){
		return carroRepository.findByIdCliente(logado.getId());
	}
	
	@PostMapping("/cadastrar")
	public Carro cadastrar(@Valid Carro carro, @AuthenticationPrincipal Cliente logado) {
		//carro.setIdCliente(logado);
		carroRepository.save(carro);
		return null;
	}
	

}
