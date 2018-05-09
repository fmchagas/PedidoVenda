package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.EnderecoEntrega;
import br.com.primemacedo.comercial.model.Pedido;

@Named
@ViewScoped
public class ControllerCadastroPedido implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<Integer> itens;
	
	private Pedido pedido;
	
	public ControllerCadastroPedido() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
		itens = new ArrayList<>();
		itens.add(1);
	}
	
	public void salvar() {
	}

	public List<Integer> getItens() {
		return itens;
	}

	public Pedido getPedido() {
		return pedido;
	}

}
