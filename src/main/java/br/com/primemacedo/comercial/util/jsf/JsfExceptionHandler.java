package br.com.primemacedo.comercial.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.primemacedo.comercial.service.NegocioException;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	
	private static Log log = LogFactory.getLog(JsfExceptionHandler.class);
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents().iterator();

		while (events.hasNext()) {
			ExceptionQueuedEvent event = events.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();

			Throwable tException = context.getException();

			NegocioException negocioException = getNegocioException(tException);

			boolean handled = false;

			try {
				if (tException instanceof ViewExpiredException) {
					handled = true;
					redirect("/");
				} else if (negocioException != null) {
					handled = true;
					FacesUtil.addErrorMessage(negocioException.getMessage());
				} else {
					handled = true;
					log.error("Erro de sistema: "+ tException.getMessage(), tException);
					redirect("/Erro.xhtml");
				}

			} finally {
				if (handled) {
					events.remove();
				}
			}
		}

		getWrapped().handle();
	}

	private NegocioException getNegocioException(Throwable tException) {
		if (tException instanceof NegocioException) {
			return (NegocioException) tException;
		} else if (tException.getCause() != null) {
			return getNegocioException(tException.getCause());
		}
		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			StringBuilder sbContextPath = new StringBuilder().append(contextPath).append(page);

			externalContext.redirect(sbContextPath.toString());
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}

	}

}
