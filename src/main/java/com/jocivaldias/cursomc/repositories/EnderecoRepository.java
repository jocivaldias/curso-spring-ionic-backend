package com.jocivaldias.cursomc.repositories;

import com.jocivaldias.cursomc.domain.Cliente;
import com.jocivaldias.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
