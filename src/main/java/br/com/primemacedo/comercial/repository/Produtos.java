package br.com.primemacedo.comercial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.filter.ProdutoFilter;
import br.com.primemacedo.comercial.service.NegocioException;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Produto guardar(Produto produto) {
		return manager.merge(produto);
	}
	
	public Produto findById(Long id) {
		return manager.find(Produto.class, id);
	}

	public Produto findBySku(String sku) {
		try {
			return manager.createQuery("from Produto  where upper(sku)=:sku", Produto.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);

		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}

	@Transactional
	public void remover(Produto produto) {
		try {
			produto = findById(produto.getId());
			manager.remove(produto);
			manager.flush();//tudo marcado para exclusão serão excluidos
		} catch (PersistenceException e) {//caso item de pedito estiver referenciando lança exception
			throw new NegocioException("Produto não pode ser excluido.");
		}
	}

	public List<Produto> findByName(String nome) {
		return manager.createQuery("from Produto where upper(nome) like :nome",Produto.class)
				.setParameter("nome", nome.toUpperCase()+"%")
				.getResultList();
	}
}
