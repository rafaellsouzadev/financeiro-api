package com.rafael.financeiro.api.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafael.financeiro.api.model.Usuario;
import com.rafael.financeiro.api.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email); // pega o usuario do banco
		
		Usuario usuario = usuarioOptional.orElseThrow(() 
				-> new UsernameNotFoundException("Usuário e/ou senha incorretos")); // verifica se o usuario existe
		
		return new UsuarioSistema(usuario, getPermissaoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissaoes(Usuario usuario) {
		
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();		
		
		usuario.getPermissoes().forEach(
				p -> authorities.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase()))); // carrega as permissao do usuario
		
		return authorities;
	}

}
