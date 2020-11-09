package br.com.everis.estacionamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.everis.estacionamento.model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {

	List<Carro> findByIdCliente(Long id);

}
