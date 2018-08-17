package br.com.primemacedo.comercial.view.clientes;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Cliente;
import br.com.primemacedo.comercial.repository.Clientes;
import br.com.primemacedo.comercial.service.NegocioException;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;



@Named
@ViewScoped
public class ConsultarClientes implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes repoClientes;
	
	private Cliente clienteSelecionado;
	
	private List<Cliente> listaClientes;
	
	public void pesquisar() {
		listaClientes = repoClientes.clientes();
	}
	
	public void excluir() {
		try {
			throw new NegocioException("Funcionalidade não implementada.");
		} catch (NegocioException ne) {
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
}
