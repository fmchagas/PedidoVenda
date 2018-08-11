package br.com.primemacedo.comercial.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.model.StatusPedido;
import br.com.primemacedo.comercial.repository.Pedidos;
import br.com.primemacedo.comercial.util.jpa.Transactional;

public class CadastroPedidoService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pedidos pedidos;

	@Transactional
	public Pedido salvar(Pedido pedido) {
		if (pedido.isNovo()) {
			pedido.setDataCadastro(new Date());
			pedido.setStatus(StatusPedido.ORCAMENTO);
		}
		
		pedido.reccalcularValorTotal();
		
		if (pedido.isNaoAlteravel()) {
			throw new NegocioException("Pedido não pode ser alterado no status" 
											+ pedido.getStatus().getDescricao()+".");
		}
		
		
		if (pedido.getItens().isEmpty()) {
			throw new NegocioException("O pedido deve possuir pelo menos um item.");
		}
		
		if (pedido.isValorTotalNegotivo()) {
			throw new NegocioException("O valor total do pedido não pode ser negativo.");
		}
		
		
		pedido=this.pedidos.guardar(pedido);
		return pedido;
	}
}
