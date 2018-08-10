package br.com.primemacedo.comercial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.primemacedo.comercial.model.Usuario;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class Usuarios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public Usuario findById(Long id) {
		return manager.find(Usuario.class, id);
	}

	public List<Usuario> vendedores() {
		return manager.createQuery("from Usuario", Usuario.class).getResultList();
	}
}
