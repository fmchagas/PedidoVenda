package br.com.primemacedo.comercial.view.pedidos;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.service.EmissaoPedidoService;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@RequestScoped //@ViewScoped
public class EmissaoPedidoBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmissaoPedidoService emissaoPedidoService;
	
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	@Inject
	private Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	
	public void emitirPedido() {
		this.pedido.removerItemVazio();
		
		try {
			this.pedido=emissaoPedidoService.emitir(this.pedido);
			//Lancar um evento CDI
			this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
			
			FacesUtil.addInfoMessage("Pedido emitido com sucesso!");
		} finally {
			this.pedido.adicionarItemVazio();
		}
	}

}
