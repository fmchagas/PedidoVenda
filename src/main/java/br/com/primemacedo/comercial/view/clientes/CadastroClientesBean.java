package br.com.primemacedo.comercial.view.clientes;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Cliente;
import br.com.primemacedo.comercial.model.TipoPessoa;
import br.com.primemacedo.comercial.repository.Clientes;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClientesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	//@Inject
	private Cliente cliente;
	
	
	
	public void inicializar() {
		if (this.cliente == null) {
			limpar();
		}
	}
	
	
	public void salvar() {
		if (isEditando()) {
			cliente = clientes.guardar(cliente);
			FacesUtil.addInfoMessage("Edição de cliente salvo com sucesso.");
		}else {
			cliente = clientes.guardar(cliente);
			FacesUtil.addInfoMessage("Novo cliente adicionado com sucesso.");
			limpar();
		}
	}
	
	
	public TipoPessoa[] getTipoCliente() {
		return TipoPessoa.values();
	}

	private void limpar() {
		cliente = new Cliente();
	}
	
	public boolean isEditando() {
		return this.cliente.getId() != null;
	}
	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
