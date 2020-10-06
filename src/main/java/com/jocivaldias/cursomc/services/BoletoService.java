package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.PagamentoComBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instaneDoPedido){
        Calendar cal = Calendar.getInstance();
        cal.setTime(instaneDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
}
