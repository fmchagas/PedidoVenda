package br.com.primemacedo.comercial.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ControllerConsultaPedidos {

	private List<Integer> pedidosFiltrados;
	
	public ControllerConsultaPedidos() {
		pedidosFiltrados = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			pedidosFiltrados.add(i);
		}
	}

	public List<Integer> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	
}