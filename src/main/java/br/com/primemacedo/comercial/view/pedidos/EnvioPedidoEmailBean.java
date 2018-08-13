package br.com.primemacedo.comercial.view.pedidos;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.primemacedo.comercial.model.Pedido;
import br.com.primemacedo.comercial.util.jsf.FacesUtil;
import br.com.primemacedo.comercial.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioPedidoEmailBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject @PedidoEdicao
	private Pedido pedido;
	
	
	public void enviarPedido() {
		/*MailMessage message = mailer.novaMensagem();
		
		message.to(this.pedido.getCliente().getEmail())
		.subject("Pedido "+ this.pedido.getId())
		.bodyHtml("Este email foi enviado de forma automatica não responder.</br>"
				+"Itens do Pedido </br> "
				+ pedido.getItens().get(0).getProduto().getNome())
		.bodyHtml(this.montabodyHtml())
		.send();*/
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}
	
	/*public void enviarPedidoComTemplate() {
		MailMessage message = mailer.novaMensagem();
		EmailMessage emailMessage;
		
		message.to(this.pedido.getCliente().getEmail())
		.subject("Pedido "+ this.pedido.getId())

       emailMessage = new MailMessageImpl(mailConfig)
        		.from(MailTestUtil.getAddressHeader(fromName, fromAddress))
        		.replyTo(MailTestUtil.getAddressHeader(replyToName, replyToAddress))
        		.to(person)
                .subject(subject)
                
                .bodyHtml(new VelocityTemplate(
                		Resources.asCharSource(Resources.getResource("/emails/pedido.template"), Charsets.UTF_8)
                		.read()))
                .bodyHtml(new VelocityTemplate(Resources.getResource("/emails/pedido.template").getFile()))
                
                .put("pedido", this.pedido)
                .put("numberTool", new NumberTool())
                .put("locale",new Locale("pt", "BR"))
                .importance(MessagePriority.HIGH)
                .addAttachment(new URLAttachment("http://design.jboss.org/seam/logo/final/seam_mail_85px.png", "seamLogo.png",
                                ContentDisposition.INLINE))
                .send();
		
		
		.bodyHtml(new VelocityTemplate("D:/Documentos/workspace/PedidoVenda/src/main/resources/emails/pedido.template"))
		.put("pedido",this.pedido)
		.put("numberTool",new NumberTool())
		.put("locale",new Locale("pt", "BR"))
		
		FacesUtil.addInfoMessage("Pedido enviado por e-mail com sucesso!");
	}*/
	
	private String montabodyHtml() {
		StringBuilder sb = new StringBuilder();

        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">" + "\r\n");
        sb.append("<body>" + "\r\n");
        sb.append("<p><b>Hey there" + pedido.getCliente().getNome() + "</b></p>" + "\r\n");
        sb.append("<p>This is an example <i>HTML</i> email sent by Fernando and SimpleMail.</p>" + "\r\n");
        sb.append("<table>");
        	sb.append("<tr>");
        		sb.append("<td style=\"width: 100px\">Número:</td>");
        		sb.append("<td>").append(pedido.getId()).append("</td>");
        	sb.append("</tr>");
        	sb.append(" <tr>");
        			sb.append("<td>Cliente:</td>");
        			sb.append("<td>").append(this.pedido.getCliente().getNome()).append("</td>");
        	sb.append("</tr>");
        	sb.append("<tr>");
        			sb.append(" <td>Valor total:</td>");
        			sb.append("<td>R$</td>").append(this.pedido.getValorTotal()).append("</td>");
        	sb.append(" </tr>");
        sb.append("</table>"+"\r\n");
        
        
        String itens="<table border=\"1\" cellspacing=\"0\" cellpadding=\"3\">\n" + 
        	"<tr>\n" + 
        		"<th>Produto</th>\n" + 
        		"<th>Valor unitário</th>\n" + 
        		"<th>Quantidade</th>\n" + 
        		"<th>Valor total</th>\n" + 
        	"</tr>\n";
        
        for (int i = 0; i < pedido.getItens().size(); i++) {
        	
        			if(pedido.getItens().get(i).getId()!=null ){
        				itens=itens+"<tr>\n" + 
				     		"<td>"+ this.pedido.getItens().get(i).getProduto().getNome() +"</td>\n" + 
				     		"<td>"+ this.pedido.getItens().get(i).getValorUnitario()+"</td>\n" + 
				     		"<td>"+ this.pedido.getItens().get(i).getQuantidade() +"</td>\n" + 
				     		"<td>"+ this.pedido.getItens().get(i).getValorTotal() +"</td>\n" + 
				     		"</tr>\n"; 
        			}
		}

    	itens=itens+"</table>";
    	
    	sb.append(itens);
        
        sb.append("<p>It has an alternative text body for mail readers that don't support html.</p>" + "\r\n");
        sb.append("</body>" + "\r\n");
        sb.append("</html>");

        return sb.toString();
	}
	
}
