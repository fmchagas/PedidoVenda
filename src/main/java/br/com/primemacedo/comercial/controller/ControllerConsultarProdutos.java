package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.repository.filter.ProdutoFilter;

@Named
@ViewScoped
public class ControllerConsultarProdutos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private List<Produto> produtosFiltrados;
	private ProdutoFilter filtro;

	public ControllerConsultarProdutos() {
		filtro = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
		//System.out.println();
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

}
