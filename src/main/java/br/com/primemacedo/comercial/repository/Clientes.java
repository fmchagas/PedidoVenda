package br.com.primemacedo.comercial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.primemacedo.comercial.model.Cliente;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	private Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}

	public Cliente findById(Long id) {
		return manager.find(Cliente.class, id);
	}

	public List<Cliente> findByName(String nome) {
		return this.manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	}

	public List<Cliente> clientes() {
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}
}
