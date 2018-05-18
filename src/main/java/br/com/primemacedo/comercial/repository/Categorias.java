package br.com.primemacedo.comercial.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.primemacedo.comercial.model.Categoria;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> raizes(){
		return manager.createQuery("select c from Categoria c where categoriaPai is null", Categoria.class).getResultList();
	}
	
	public List<Categoria> subCategorias(Long id){
		return manager.createQuery("select c from Categoria c where categoriaPai ="+id, Categoria.class).getResultList();
	}
	
	public List<Categoria> subcategoriasDe(Categoria categoriaPai){
		return manager.createQuery("select c from Categoria c where categoriaPai =:raiz",
				Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}
	
	public Categoria findById(Long id) {
		return manager.find(Categoria.class, id);
	}

}
