package br.com.primemacedo.comercial.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.model.StatusPedido;
import br.com.primemacedo.comercial.repository.Pedidos;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class EmissaoPedidoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject CadastroPedidoService cadastroPedidoService;
	@Inject Pedidos pedidos;
	@Inject EstoqueService estoqueService;
	
	@Transactional
	public Pedido emitir(Pedido pedido) {
		pedido = this.cadastroPedidoService.salvar(pedido);
		
		if (pedido.isNaoEmissivel()) {
			throw new NegocioException("Pedido não pode ser emitido com status "+ pedido.getStatus().getDescricao() + ".");
		}
		
		this.estoqueService.baixarItensEstoque(pedido);
		
		pedido.setStatus(StatusPedido.EMITIDO);
		
		pedido = this.pedidos.guardar(pedido);
		
		return pedido;
	}

}
