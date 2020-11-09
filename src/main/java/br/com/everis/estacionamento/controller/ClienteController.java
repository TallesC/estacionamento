package br.com.everis.estacionamento.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.everis.estacionamento.controller.dto.ClienteDto;
import br.com.everis.estacionamento.controller.form.AtualizacaoClienteForm;
import br.com.everis.estacionamento.controller.form.ClienteForm;
import br.com.everis.estacionamento.model.Cliente;
import br.com.everis.estacionamento.repository.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping
	public ClienteDto getCliente(@AuthenticationPrincipal Cliente logado) { 
		return new ClienteDto(logado);
	}
	
	@GetMapping("/consultar")
	public boolean consultaCliente(String cpf) { 
		Optional<Cliente> cliente = clienteRepository.findByCpf(cpf);
		if(cliente.isPresent())
			return true;
		return false;
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<ClienteDto> saveCliente(@RequestBody @Valid ClienteForm formCliente, UriComponentsBuilder uriBuilder) {
		Cliente cliente;
		cliente = formCliente.converter();
		clienteRepository.save(formCliente.converter());
	
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
		
	}
	
	@PutMapping("/atualizar")	@Transactional
	public ResponseEntity<ClienteDto> atualizar(@AuthenticationPrincipal Cliente logado, @RequestBody @Valid AtualizacaoClienteForm form){
		try {
			Cliente cliente = form.atualizar(logado.getId(), clienteRepository);
			System.out.println();
			return ResponseEntity.ok(new ClienteDto(cliente));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/inativar") @Transactional
	public ResponseEntity<ClienteDto> inativar(@AuthenticationPrincipal Cliente logado){
		Cliente cliente = clienteRepository.findById(logado.getId()).get();
		cliente.setStatus(false);
		return ResponseEntity.ok(new ClienteDto(logado));
	}
	
	@PostMapping("/alterarStatus") @Transactional
	public ResponseEntity<ClienteDto> gerenciar(Long id){
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			cliente.get().setStatus(!cliente.get().isStatus());
			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}
}
