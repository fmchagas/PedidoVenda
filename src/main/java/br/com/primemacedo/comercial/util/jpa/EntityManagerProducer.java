package br.com.primemacedo.comercial.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*@ApplicationScoped Armazena uma instancia só para a aplicação.*/

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory factory;

	public EntityManagerProducer() {
		factory = Persistence.createEntityManagerFactory("PedidoPU");
	}

	@Produces @RequestScoped //@RequestScoped criará uma EntityManager a cada requisição
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager menager) {//no final da requisição será chamado o @Disposes
		menager.close();
	}
}
