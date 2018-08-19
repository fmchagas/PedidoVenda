package br.com.primemacedo.comercial.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.repository.Pedidos;

@FacesConverter(forClass = Pedido.class)
public class PedidoConverter implements Converter {


	@Inject
	private Pedidos pedidosRepository;


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Pedido retorno = null;

		if (StringUtils.isNotEmpty(value)) {
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
