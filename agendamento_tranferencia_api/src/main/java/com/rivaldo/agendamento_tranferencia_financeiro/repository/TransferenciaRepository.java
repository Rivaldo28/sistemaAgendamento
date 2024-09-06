package com.rivaldo.agendamento_tranferencia_financeiro.repository;

import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
