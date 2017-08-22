package jms;

import java.util.Arrays;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@SpringBootApplication
@EnableJms
public class JmsJavaconfigApplication {

  @Autowired
  private ConnectionFactory connectionFactory;

  public static void main(String[] args) {
    SpringApplication.run(JmsJavaconfigApplication.class, args);
  }

  /**
   * without explicitly creating this bean, the default bean's setup will try and call setAutoStartup
   * on DefaultJmsListenerContainerFactory.  there is a version issues between activemq and spring jms
   * and the method is not implemented.  Creating our own bean resolves the problem but beware, you
   * may want some additional properties set in a full implementation so refer to the default
   * bean set up method for additional functionality
   */
  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrency("1-1");
    return factory;
  }
}