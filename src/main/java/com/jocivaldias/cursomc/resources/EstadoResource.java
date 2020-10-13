package com.jocivaldias.cursomc.resources;

import com.jocivaldias.cursomc.domain.Cidade;
import com.jocivaldias.cursomc.domain.Cliente;
import com.jocivaldias.cursomc.domain.Estado;
import com.jocivaldias.cursomc.domain.Produto;
import com.jocivaldias.cursomc.dto.CidadeDTO;
import com.jocivaldias.cursomc.dto.ClienteDTO;
import com.jocivaldias.cursomc.dto.EstadoDTO;
import com.jocivaldias.cursomc.dto.ProdutoDTO;
import com.jocivaldias.cursomc.resources.utils.URL;
import com.jocivaldias.cursomc.services.CidadeService;
import com.jocivaldias.cursomc.services.EstadoService;
import com.jocivaldias.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EstadoDTO>> findAll(){
        List<Estado> list = service.findAll();
        List<EstadoDTO> listDto = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value = "/{estadoId}/cidades",method = RequestMethod.GET)
    public ResponseEntity<List<CidadeDTO>> findAll(@PathVariable Integer estadoId){
        List<Cidade> list = cidadeService.findAllByEstadoIdOrderByNome(estadoId);
        List<CidadeDTO> listDto = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }


}
