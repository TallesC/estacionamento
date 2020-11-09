package br.com.everis.estacionamento.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.everis.estacionamento.model.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${estacionamento.jwt.expiration}")
	private String expiration;
	
	@Value("${estacionamento.jwt.secret}")
	private String secret;
	
	Date data = new Date();
	
	public String gerarToken(Authentication auth) {
		Cliente logado = (Cliente) auth.getPrincipal();
		return Jwts.builder()
				.setIssuer("API Estacionamento Digital")
				.setSubject(logado.getId().toString())
				.setIssuedAt(data)
				.setExpiration(new Date(data.getTime()+Long.parseLong(expiration)))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}

	public boolean isValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(body.getSubject());
	}

}
