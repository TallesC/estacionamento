package br.com.everis.estacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.everis.estacionamento.controller.dto.EstacionamentoDto;
import br.com.everis.estacionamento.controller.form.AtualizacaoEstacionamentoForm;
import br.com.everis.estacionamento.model.Estacionamento;
import br.com.everis.estacionamento.repository.EstacionamentoRepository;

import java.util.List;
import java.util.Optional;
import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController 
@RequestMapping("/estacionamentos")
public class EstacionamentoController {

	@Autowired
	EstacionamentoRepository estacionamentoRepository;

	@GetMapping
	public List<EstacionamentoDto> getEstacionamentos() {
		List<Estacionamento> estacionamentos = estacionamentoRepository.findAll();
		return EstacionamentoDto.converter(estacionamentos);
	}
	
	@PostMapping
	public ResponseEntity<EstacionamentoDto> saveEstacionamento(@RequestBody @Valid Estacionamento estacionamento, UriComponentsBuilder uriBuilder) {
		estacionamentoRepository.save(estacionamento);
		
		URI uri = uriBuilder.path("/estacionamento/{id}").buildAndExpand(estacionamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new EstacionamentoDto(estacionamento));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EstacionamentoDto> detalhar(@PathVariable Long id){
		Optional<Estacionamento> estacionamento = estacionamentoRepository.findById(id);
		if(estacionamento.isPresent())
			return ResponseEntity.ok(new EstacionamentoDto(estacionamento.get()));
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<EstacionamentoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoEstacionamentoForm form){
		try {
			Estacionamento estacionamento = form.atualizar(id, estacionamentoRepository);
			return ResponseEntity.ok(new EstacionamentoDto(estacionamento));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id){
		try {
			estacionamentoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
}
