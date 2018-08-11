package br.com.primemacedo.comercial.view.pedidos;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.primemacedo.comercial.model.Cliente;
import br.com.primemacedo.comercial.model.EnderecoEntrega;
import br.com.primemacedo.comercial.model.FormaPagamento;
import br.com.primemacedo.comercial.model.ItemPedido;
import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.model.Produto;
import br.com.primemacedo.comercial.model.Usuario;
import br.com.primemacedo.comercial.repository.Clientes;
import br.com.primemacedo.comercial.repository.Produtos;
import br.com.primemacedo.comercial.repository.Usuarios;
import br.com.primemacedo.comercial.service.CadastroPedidoService;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;
import br.com.primemacedo.comercial.validation.SKU;

@Named
@ViewScoped
public class CadastroPedidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	@Inject
	private Clientes clientes;
	@Inject
	private Produtos produtos;
	@Inject
	private CadastroPedidoService cadastroPedidoService;

	@Produces
	@PedidoEdicao
	private Pedido pedido;
	
	private List<Usuario> vendedores;

	private Produto produtoLinhaEditavel;
	private String sku;

	public CadastroPedidoBean() {
		limpar();
	}

	public void inicializar() {
		if (FacesUtil.isNotPostBack()) {
			this.vendedores = this.usuarios.vendedores();

			this.pedido.adicionarItemVazio();

			this.recalcularPedido();
			System.out.println(":::: isNotPostBack()");
		}
	}

	private void limpar() {
		pedido = new Pedido();
		pedido.setEnderecoEntrega(new EnderecoEntrega());
	}

	public void salvar() {
		//Remove primeiro item da lista que sempre está vazio para depois salvar, caso tente salvar com o item vazio na lista uma nullPointerExeption será lançada
		this.pedido.removerItemVazio();
		
		try {
			this.pedido = this.cadastroPedidoService.salvar(this.pedido);
			FacesUtil.addInfoMessage("Pedido salvo com sucesso.");
		}finally {
			this.pedido.adicionarItemVazio();
			System.out.println(":::: Adiciona Item Vazio.");
		}
		
	}

	public List<Produto> completarProdroduto(String nome) {
		return this.produtos.findByName(nome);
	}

	public void recalcularPedido() {
		if (this.pedido != null) {
			this.pedido.reccalcularValorTotal();
		}
	}

	public void carregarProdutoPorSku() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtos.findBySku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}
	}

	public void carregarProdutoLinhaEditavel() {
		ItemPedido item = this.pedido.getItens().get(0);

		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.addWarnMessage("Já existe um item no pedido com o produto informado.");
			} else {

				item.setProduto(this.produtoLinhaEditavel);
				item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

				this.pedido.adicionarItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku=null;

				this.pedido.reccalcularValorTotal();
			}
		}
	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;
		
		for (ItemPedido item : this.getPedido().getItens()) {
			if (produto.equals(item.getProduto())) {
				existeItem=true;
				break;
			}
		}

		return existeItem;
	}

	public List<Cliente> carregarCliente(String nome) {
		return this.clientes.findByName(nome);
	}
	
	public void atualizarQuantidade(ItemPedido item, int linha) {
		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			}else {
				pedido.getItens().remove(linha);
			}
		}	

		this.pedido.reccalcularValorTotal();
	}
	
	
	public void pedidoAlterado(@Observes PedidoAlteradoEvent event) {
		this.pedido = event.getPedido();
	}
	
	
	public FormaPagamento[] getFormasPagamento() {
		return FormaPagamento.values();
	}

	public boolean isEditando() {
		return this.pedido.getId() != null;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	@SKU
	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}
