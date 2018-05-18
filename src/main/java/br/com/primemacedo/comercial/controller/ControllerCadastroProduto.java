package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import br.com.primemacedo.comercial.model.Categoria;
import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Categorias;
import br.com.primemacedo.comercial.service.CadastroProdutoService;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class ControllerCadastroProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Categorias categorias;
	
	@Inject
	private CadastroProdutoService cadastroProdutoService;

	private Produto produto;
	private Categoria categoriaPai;

	private List<Categoria> categoriasRaizes;
	private List<Categoria> subCategorias;

	public ControllerCadastroProduto() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			categoriasRaizes = categorias.raizes();
			
			if (categoriaPai != null) {
				carregarSubCategorias();
			}
		}
	}

	public void carregarSubCategorias() {
			if (FacesUtil.isPostBack()) {
			subCategorias = categorias.subcategoriasDe(categoriaPai);
		}

	}

	public void salvar() {
		produto = cadastroProdutoService.salvar(produto);
		limpar();
		FacesUtil.addInfoMessage("Registro salvo com sucesso.");
	}

	private void limpar() {
		produto = new Produto();
		categoriaPai = null;
		subCategorias = new ArrayList<>();
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
