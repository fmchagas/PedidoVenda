package br.com.primemacedo.comercial.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class CadastroProdutoService implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Produtos produtos;

	@Transactional
	public Produto salvar(Produto produto) {
		Produto produtoExistente = produtos.findBySku(produto.getSku());
		
		if (produtoExistente != null) {
			throw new NegocioException("Já existe um produto com o SKU informado");
		}
		
		return produtos.guardar(produto);
	}
	
	public Produto alterar(Produto produto) {
		//TODO develop business roles
		return produto;
	}
	
	public Produto excluir(Produto produto) {
		//TODO develop business roles
		return produto;
	}
}
