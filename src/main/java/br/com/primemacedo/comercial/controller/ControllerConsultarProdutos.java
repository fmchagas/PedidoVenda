package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.repository.filter.ProdutoFilter;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ControllerConsultarProdutos implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private Produto produtoSelecionado;
	private List<Produto> produtosFiltrados;
	private ProdutoFilter filtro;

	public ControllerConsultarProdutos() {
		filtro = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public void excluir() {
		produtos.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);

		StringBuilder sb = new StringBuilder().append("Produto ").append(produtoSelecionado.getSku())
				.append(" Excluido com sucesso.");

		FacesUtil.addInfoMessage(sb.toString());
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoFilter getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}
