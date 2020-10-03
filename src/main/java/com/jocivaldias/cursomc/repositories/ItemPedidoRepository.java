package com.jocivaldias.cursomc.repositories;

import com.jocivaldias.cursomc.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
