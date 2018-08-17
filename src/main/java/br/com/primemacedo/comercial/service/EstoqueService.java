package br.com.primemacedo.comercial.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.primemacedo.comercial.model.ItemPedido;
import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.repository.Pedidos;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	Pedidos pedidos;

	@Transactional
	public void baixarItensEstoque(Pedido pedido) throws NegocioException {
		pedido = this.pedidos.porId(pedido.getId());

		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}

	public void retornarItensEstoque(Pedido pedido) {
		pedido = this.pedidos.porId(pedido.getId());
		
		for (ItemPedido item : pedido.getItens()) {
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
	}

}
