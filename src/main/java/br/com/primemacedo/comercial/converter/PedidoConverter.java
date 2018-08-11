package br.com.primemacedo.comercial.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.repository.Pedidos;
import br.com.primemacedo.comercial.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {


	//@Inject
	private Pedidos pedidosRepository;

	public PedidoConverter() {
		//Injection de Produtos
		pedidosRepository = CDIServiceLocator.getBean(Pedidos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = pedidosRepository.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			Pedido pedido = (Pedido) value;
			return pedido.getId() == null ? null : pedido.getId().toString();
		}
		return "";
	}

}
