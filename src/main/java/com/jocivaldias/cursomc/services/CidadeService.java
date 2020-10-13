package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.Cidade;
import com.jocivaldias.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repo;

    public List<Cidade> findAllByEstadoIdOrderByNome(Integer estadoId) {
        return repo.findAllByEstadoIdOrderByNome(estadoId);
    }

}
