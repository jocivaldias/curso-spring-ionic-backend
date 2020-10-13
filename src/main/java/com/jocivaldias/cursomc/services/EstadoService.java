package com.jocivaldias.cursomc.services;

import com.jocivaldias.cursomc.domain.Categoria;
import com.jocivaldias.cursomc.domain.Cliente;
import com.jocivaldias.cursomc.domain.Estado;
import com.jocivaldias.cursomc.domain.Produto;
import com.jocivaldias.cursomc.repositories.CategoriaRepository;
import com.jocivaldias.cursomc.repositories.EstadoRepository;
import com.jocivaldias.cursomc.repositories.ProdutoRepository;
import com.jocivaldias.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repo;

    public List<Estado> findAll() {
        return repo.findAllByOrderByNome();
    }

}
