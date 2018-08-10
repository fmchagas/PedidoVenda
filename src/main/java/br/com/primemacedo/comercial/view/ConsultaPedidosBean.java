package br.com.primemacedo.comercial.view;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.model.StatusPedido;
import br.com.primemacedo.comercial.repository.Pedidos;
import br.com.primemacedo.comercial.repository.filter.PedidoFilter;

@Named
@ViewScoped
public class ConsultaPedidosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pedidos pedidos;

	private List<Pedido> pedidosFiltrados;
	private PedidoFilter filtro;

	public ConsultaPedidosBean() {
		filtro = new PedidoFilter();
	}

	public void pesquisar() {
		this.pedidosFiltrados = this.pedidos.filtrados(this.filtro);
	}
	
	public StatusPedido[] getStatues() {
		return StatusPedido.values();
	}

	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}

	public PedidoFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(PedidoFilter filtro) {
		this.filtro = filtro;
	}
}