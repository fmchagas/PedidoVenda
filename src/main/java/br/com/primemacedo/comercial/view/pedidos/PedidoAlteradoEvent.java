package br.com.primemacedo.comercial.view.pedidos;

import br.com.primemacedo.comercial.model.Pedido;

public class PedidoAlteradoEvent {

	private Pedido pedido;

	public PedidoAlteradoEvent(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Pedido getPedido() {
		return pedido;
	}
	
}
