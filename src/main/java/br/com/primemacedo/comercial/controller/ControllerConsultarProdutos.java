package br.com.primemacedo.comercial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@ViewScoped
public class ControllerConsultarProdutos implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<Integer> produtosFiltrados;
	
	
	public ControllerConsultarProdutos() {
		produtosFiltrados = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			produtosFiltrados.add(i);
		}
	}
	
	public void destroyWorld() {
		System.out.println("Excluindo....");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Excluir", "Produto escluido com suceeso!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     

	public List<Integer> getProdutosFiltrados() {
		return produtosFiltrados;
	}

}
