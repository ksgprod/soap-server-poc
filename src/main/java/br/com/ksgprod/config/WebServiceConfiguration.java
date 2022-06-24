package br.com.ksgprod.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfiguration extends WsConfigurerAdapter {
	
	private static final String WS = "/ws";
	private static final String CUSTOMER_PORT = "CustomerPort";
	
	@Value("${ws.namespace.uri}")
    private String nameSpaceUri;
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(messageDispatcherServlet, WS.concat("/*"));
	}
	
	@Bean
	public XsdSchema customerSchema() {
		return new SimpleXsdSchema(new ClassPathResource("customer-information.xsd"));
	}
	
	@Bean(name = "customers")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema customerSchema) {
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
		definition.setPortTypeName(CUSTOMER_PORT);
		definition.setTargetNamespace(this.nameSpaceUri);
		definition.setLocationUri(WS);
		definition.setSchema(customerSchema);
		return definition;
	}
	
}
