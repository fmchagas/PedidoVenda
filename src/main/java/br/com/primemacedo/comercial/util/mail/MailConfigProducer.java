package br.com.primemacedo.comercial.util.mail;

public class MailConfigProducer {
	
	/*@Produces @ApplicationScoped
	public SessionConfig getMailConfig() throws IOException {
		Properties props = new Properties();
		props.load(getClass().getResourceAsStream("/mail.properties"));
		
		SimpleMailConfig config = new SimpleMailConfig();
		config.setServerHost(props.getProperty("mail.server.host"));
		config.setServerPort(Integer.parseInt(props.getProperty("mail.server.port")));
		//config.setEnableSsl(Boolean.parseBoolean(props.getProperty("mail.enable.ssl")));
		config.setEnableTls(Boolean.parseBoolean(props.getProperty("mail.enable.tls")));
		config.setAuth(Boolean.parseBoolean(props.getProperty("mail.auth")));
		config.setUsername(props.getProperty("mail.username"));
		config.setPassword(props.getProperty("mail.password"));
		
		return config;
	}*/
}