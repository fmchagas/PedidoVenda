package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.primemacedo.comercial.service.NegocioException;

@Named
@ViewScoped
public class ControllerCadastroPedido implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Integer> itens;
	
	public ControllerCadastroPedido() {
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public void salvar() {
		throw new NegocioException("Pedido não pode ser salvo, pois ainda não foi implementado.");
	}

	public List<Integer> getItens() {
		return itens;
	}
	
	
}
