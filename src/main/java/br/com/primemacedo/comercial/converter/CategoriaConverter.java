package br.com.primemacedo.comercial.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.primemacedo.comercial.model.Categoria;
import br.com.primemacedo.comercial.repository.Categorias;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {


	@Inject
	private Categorias categoriasRepository;


	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);

			retorno = categoriasRepository.findById(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return ((Categoria) value).getId().toString();
		}
		return "";
	}

}
