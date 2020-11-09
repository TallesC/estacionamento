package br.com.everis.estacionamento.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.everis.estacionamento.model.Cliente;
import br.com.everis.estacionamento.repository.ClienteRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter{
	
	private TokenService tokenService;
	private ClienteRepository clienteRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, ClienteRepository clienteRepository) {
		this.tokenService = tokenService;
		this.clienteRepository = clienteRepository;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		boolean valido = tokenService.isValido(token);
		if(valido)
			autenticarUsuario(token);
		
		filterChain.doFilter(request, response);
		
	}

	private void autenticarUsuario(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Cliente usuario = clienteRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities() );
		SecurityContextHolder.getContext().setAuthentication(auth);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
			}

		return token.substring(7, token.length());
	}

}
;