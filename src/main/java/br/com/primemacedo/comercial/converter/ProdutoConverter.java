package br.com.primemacedo.comercial.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {


	//@Inject
	private Produtos produtosRepository;

	public ProdutoConverter() {
		produtosRepository = CDIServiceLocator.getBean(Produtos.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;

		if (value != null) {
			Long id = new Long(value);

			retorno = produtosRepository.findById(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Produto) value).getId().toString();
		}
		return "";
	}

}
