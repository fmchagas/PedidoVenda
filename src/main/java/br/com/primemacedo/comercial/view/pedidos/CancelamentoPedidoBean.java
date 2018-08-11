package br.com.primemacedo.comercial.view.pedidos;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.service.CancelamentoPedidoService;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@RequestScoped
public class CancelamentoPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Inject CancelamentoPedidoService cancelamentoPedidoService;
	
	private @Inject Event<PedidoAlteradoEvent> pedidoAlteradoEvent;
	
	@Inject
	@PedidoEdicao
	private Pedido pedido;
	
	
	public void cancelarPedido() {
		this.pedido = this.cancelamentoPedidoService.cancelar(this.pedido);
		this.pedidoAlteradoEvent.fire(new PedidoAlteradoEvent(this.pedido));
		
		
		FacesUtil.addInfoMessage("Pedido alterado com sucesso.");
	}


}
