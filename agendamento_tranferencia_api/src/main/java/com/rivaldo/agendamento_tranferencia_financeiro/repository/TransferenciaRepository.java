package com.rivaldo.agendamento_tranferencia_financeiro.repository;

import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    Page<Transferencia> findAllByOrderByDataCriacaoDesc(Pageable pageable);
    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia BETWEEN :startDate AND :endDate")
    Page<Transferencia> findByDataTransferenciaBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                                       Pageable pageable);
}
