package com.jocivaldias.cursomc.repositories;

import com.jocivaldias.cursomc.domain.Pagamento;
import com.jocivaldias.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
