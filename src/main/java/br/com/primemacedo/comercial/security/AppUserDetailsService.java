package br.com.primemacedo.comercial.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.primemacedo.comercial.model.Grupo;
import br.com.primemacedo.comercial.model.Usuario;
import br.com.primemacedo.comercial.repository.Usuarios;
import br.com.primemacedo.comercial.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuarios usuarios = CDIServiceLocator.getBean(Usuarios.class);
		Usuario usuario = usuarios.porEmail(email);
		
		UsuarioSistema user = null;
		
		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGrupos(usuario));
		}else {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Grupo grupo : usuario.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}
}
