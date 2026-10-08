package com.springlab.week08;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.*;
import org.springframework.ws.server.endpoint.annotation.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

@SpringBootApplication
@EnableWs
public class Week08Application {
    public static void main(String[] args) {
        SpringApplication.run(Week08Application.class,args);
    }

    @Bean
    ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
            org.springframework.context.ApplicationContext context) {
        MessageDispatcherServlet servlet=new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet,"/ws/*");
    }

    @Bean
    XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
    }

    @Bean(name="users")
    DefaultWsdl11Definition usersWsdl(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl=new DefaultWsdl11Definition();
        wsdl.setPortTypeName("UsersPort");
        wsdl.setLocationUri("/ws");
        wsdl.setTargetNamespace("http://Sample User.com/users");
        wsdl.setSchema(usersSchema);
        return wsdl;
    }

    @Endpoint
    static class UserEndpoint {
        private static final String NS="http://Sample User.com/users";

        @PayloadRoot(namespace=NS,localPart="getUserRequest")
        @ResponsePayload
        public Source getUser(@RequestPayload Source request) {
            String xml="<getUserResponse xmlns=\""+NS+"\">"
                    +"<id>1</id><name>Sample User</name>"
                    +"<email>Sample User@example.com</email>"
                    +"</getUserResponse>";
            return new StreamSource(new StringReader(xml));
        }
    }
}

