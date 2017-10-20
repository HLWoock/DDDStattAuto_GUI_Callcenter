package de.woock.ddd.stattauto.gui.callcenter;

import java.awt.EventQueue;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.web.client.RestTemplate;

import de.woock.ddd.stattauto.gui.callcenter.view.TabView;

@EnableJms
@EnableDiscoveryClient
@SpringBootApplication
public class DddStattAuto_Gui_Callcenter {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DddStattAuto_Gui_Callcenter.class).headless(false)
                                                                       .web(false)
                                                                       .run(args);
	}
	
	@Bean 
	public CommandLineRunner demo(TabView gui) {
		return (args) -> {
			EventQueue.invokeLater(() -> gui.setVisible(true));
		};
	}
	
	  @Bean
	    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
	    		                                        DefaultJmsListenerContainerFactoryConfigurer configurer) throws JMSException {
	        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	        factory.setConnectionFactory(connectionFactory);
	        configurer.configure(factory, connectionFactory);
	        
	        return factory;
	    }
	  
	    @Bean
	    public RestTemplate restTemplate() {
	    	return new RestTemplate();
	    }
	    
	   
	    @Bean
	    public ConnectionFactory connectionFactory() {
	    	return new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
	    }
}
