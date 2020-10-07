package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Pedido obj) {
        SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(obj);
        simpleEmail(smm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj){
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(obj.getCliente().getEmail());
        smm.setFrom(sender);
        smm.setSubject("Pedido confirmado! Código: " + obj.getId());
        smm.setSentDate(new Date(System.currentTimeMillis()));
        smm.setText(obj.toString());
        return smm;
    }
}
