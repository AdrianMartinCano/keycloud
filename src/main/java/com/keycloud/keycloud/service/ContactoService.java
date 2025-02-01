package com.keycloud.keycloud.service;

import com.keycloud.keycloud.model.Contacto;
import com.keycloud.keycloud.repository.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Properties;


@Service
public class ContactoService {

    @Autowired
    private ContactoRepository contactoRepository;

    @Autowired
    private EmailService emailService;
    private final String emailTo;


    public ContactoService(EmailService emailService, ContactoRepository contactoRepository) {
        this.emailService = emailService;
        this.contactoRepository = contactoRepository;
        this.emailTo = loadEmailFromProperties(); // Carga el email al iniciar
    }


    public Contacto guardarMensajeContacto(Contacto c){

        String cuerpoMensaje = "<html>" +
                "<body>" +
                "<h2>Nuevo Mensaje de Contacto</h2>" +
                "<p><strong>Nombre:</strong> " + c.getNombre() + "</p>" +
                "<p><strong>Email:</strong> " + c.getEmail() + "</p>" +
                "<p><strong>Mensaje:</strong></p>" +
                "<p style='border-left: 4px solid #007BFF; padding-left: 10px;'>" + c.getMensaje() + "</p>" +
                "</body>" +
                "</html>";
        emailService.sendHtmlEmail(emailTo, c.getAsunto(), cuerpoMensaje);


        return contactoRepository.save(c);
    }


    private String loadEmailFromProperties() {
        try {
            Resource resource = new ClassPathResource("email-config.properties");
            Properties props = PropertiesLoaderUtils.loadProperties(resource);
            return props.getProperty("email.to");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
