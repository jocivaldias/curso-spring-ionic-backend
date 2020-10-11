package com.jocivaldias.cursomc;

import com.jocivaldias.cursomc.domain.*;
import com.jocivaldias.cursomc.domain.enums.EstadoPagamento;
import com.jocivaldias.cursomc.domain.enums.TipoCliente;
import com.jocivaldias.cursomc.repositories.*;
import org.hibernate.boot.model.relational.SimpleAuxiliaryDatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Value("${spring.mail.password}")
    private String username;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("JOCIVAL PICA PRETA" + username);
    }
}
