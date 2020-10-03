package com.jocivaldias.cursomc.repositories;

import com.jocivaldias.cursomc.domain.Categoria;
import com.jocivaldias.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
