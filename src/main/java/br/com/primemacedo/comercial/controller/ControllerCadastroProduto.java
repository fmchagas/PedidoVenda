package br.com.primemacedo.comercial.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class ControllerCadastroProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	public void salvar() {
		throw new RuntimeException("Teste de exceção");
	}
}
