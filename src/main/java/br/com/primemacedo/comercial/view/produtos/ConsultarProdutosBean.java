package br.com.primemacedo.comercial.view.produtos;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.repository.filter.ProdutoFilter;
import br.com.primemacedo.comercial.service.NegocioException;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ConsultarProdutosBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Produtos produtos;

	private Produto produtoSelecionado;
	private List<Produto> produtosFiltrados;
	private ProdutoFilter filtro;

	public ConsultarProdutosBean() {
		filtro = new ProdutoFilter();
	}

	public void pesquisar() {
		produtosFiltrados = produtos.filtrados(filtro);
	}

	public void excluir() {
		try {
			produtos.remover(produtoSelecionado);
			produtosFiltrados.remove(produtoSelecionado);

			StringBuilder sb = new StringBuilder().append("Produto ").append(produtoSelecionado.getSku())
					.append(" Excluido com sucesso.");

			FacesUtil.addInfoMessage(sb.toString());
			
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
		
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
