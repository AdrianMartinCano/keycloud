package com.keycloud.keycloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

//Esta clase sirve para la configuración del email, así no hace falta publicar los datos.
//Hay que cambiar la ruta del archivo, pero ha de tener estos datos:

/*
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=direccionEmailSinComillas
spring.mail.password=ClaveDeTercerosSinComillasNiEspacios
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.transport.protocol=smtp
* */
@Configuration
@PropertySource("classpath:email-config.properties")
public class EmailConfig {

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;


    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        //Configuraciones avanzadas:
        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.smtp.auth", "true"); // Habilitar la autenticación
        properties.put("mail.smtp.starttls.enable", "true"); // Habilitar STARTTLS
        properties.put("mail.smtp.starttls.required", "true"); // Asegurarse de que STARTTLS sea obligatorio
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // Asegura la confianza en el servidor SMTP de Gmail
        properties.put("mail.transport.protocol", "smtp"); // Usar el protocolo SMTP
        properties.put("mail.smtp.connectiontimeout", "5000"); // Tiempo de espera para conexión
        properties.put("mail.smtp.timeout", "5000"); // Tiempo de espera para operación
        properties.put("mail.smtp.writetimeout", "5000"); // Tiempo de espera para la escritura


        return mailSender;
    }
}