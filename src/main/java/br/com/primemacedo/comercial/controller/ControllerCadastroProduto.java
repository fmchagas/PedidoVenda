package br.com.primemacedo.comercial.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Produto;

@Named
@ViewScoped
public class ControllerCadastroProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Produto produto;
	
	public ControllerCadastroProduto() {
		produto = new Produto();
	}
	
	public void salvar() {
		
	}

	public Produto getProduto() {
		return produto;
	}
		
}
