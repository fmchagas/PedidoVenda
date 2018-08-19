package br.com.primemacedo.comercial.view.produtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.primemacedo.comercial.model.Categoria;
import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Categorias;
import br.com.primemacedo.comercial.service.CadastroProdutoService;
import br.com.primemacedo.comercial.service.NegocioException;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;

	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategorias;

	public CadastroProdutoBean() {
		limpar();
	}

	public void inicializar() {
		if (this.produto == null) {
			limpar();
		}
		categoriasRaizes = categorias.raizes();

		if (categoriaPai != null) {
			carregarSubCategorias();
		}
	}

	public void carregarSubCategorias() {
		subCategorias = categorias.subcategoriasDe(categoriaPai);
	}

	public void salvar() {

		try {
			if (isEditando()) {
				produto = cadastroProdutoService.salvar(produto);
			} else {
				produto = cadastroProdutoService.salvar(produto);
				limpar();
			}

			FacesUtil.addInfoMessage("Registro salvo com sucesso.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subCategorias = new ArrayList<>();
	}

	public boolean isEditando() {
		return this.produto.getId() != null;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;

		if (this.produto != null) {
			this.categoriaPai = this.produto.getCategoria().getCategoriaPai();
		}
	}

	public List<Categoria> getCategoriasRaizes() {
		return categoriasRaizes;
	}

	public void setCategoriasRaizes(List<Categoria> categoriasRaizes) {
		this.categoriasRaizes = categoriasRaizes;
	}

	@NotNull
	public Categoria getCategoriaPai() {
		return categoriaPai;
	}

	public void setCategoriaPai(Categoria categoriaPai) {
		this.categoriaPai = categoriaPai;
	}

	public List<Categoria> getSubCategorias() {
		return subCategorias;
	}

}
