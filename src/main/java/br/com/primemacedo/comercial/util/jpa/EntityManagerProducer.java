package br.com.primemacedo.comercial.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;

/*@ApplicationScoped Armazena uma instancia só para a aplicação.*/

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("PedidoPU");
	}

	@Produces @RequestScoped //@RequestScoped criará uma EntityManager/Session a cada requisição
	public Session createEntityManager() {
		return (Session) factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes Session menager) {//no final da requisição será chamado o @Disposes
		menager.close();
	}
}
