package br.com.primemacedo.comercial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.primemacedo.comercial.model.Cliente;
import br.com.primemacedo.comercial.service.NegocioException;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@Transactional
	public Cliente guardar(Cliente cliente) {
		return manager.merge(cliente);
	}
	
	@Transactional
	public void remover(Cliente cliente) throws NegocioException {
		try {
			cliente = findById(cliente.getId());
			manager.remove(cliente);
			manager.flush();//tudo marcado para exclusão serão excluidos
		} catch (PersistenceException e) {//caso cliente estiver referenciando lança exception
			throw new NegocioException("Cliente não pode ser excluido.");
		}
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
