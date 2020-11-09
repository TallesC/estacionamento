package br.com.everis.estacionamento.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.everis.estacionamento.model.Cliente;
import br.com.everis.estacionamento.repository.ClienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> cliente =  clienteRepository.findByCpf(username);
		if (cliente.isPresent())
			return cliente.get();
		
		throw new UsernameNotFoundException("CPF não cadastrado.");
		
	}
	
}
