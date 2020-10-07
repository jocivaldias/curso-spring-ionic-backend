package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void simpleEmail(SimpleMailMessage msg);
}
