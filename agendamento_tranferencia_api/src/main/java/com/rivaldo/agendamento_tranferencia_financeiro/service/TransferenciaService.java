package com.rivaldo.agendamento_tranferencia_financeiro.service;

import com.rivaldo.agendamento_tranferencia_financeiro.exception.TaxaNaoAplicavelException;
import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import com.rivaldo.agendamento_tranferencia_financeiro.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    public Page<Transferencia> listarTransferencias(Pageable pageable) {
        return repository.findAllByOrderByDataCriacaoDesc(pageable);
    }

    public Page<Transferencia> listarTransferenciasPorData(Date startDate, Date endDate, Pageable pageable) {
        return repository.findByDataTransferenciaBetween(startDate, endDate, pageable);
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        double taxaPercentual = calcularTaxaPercentual(transferencia.getDataTransferencia());

        if (taxaPercentual < 0) {
            throw new TaxaNaoAplicavelException("Não há taxa aplicável para a data fornecida. Transferência não permitida.");
        }

        double taxa = (taxaPercentual / 100) * transferencia.getValor();
        transferencia.setTaxa(taxa);
        transferencia.setDataCriacao(LocalDateTime.now());
        transferencia.setAviso("Transferência agendada com taxa de " + taxaPercentual + "%");

        transferencia.setDataAgendamento(LocalDate.now());
        return repository.save(transferencia);
    }

    private double calcularTaxaPercentual(LocalDate dataTransferencia) {
        LocalDate hoje = LocalDate.now();
        long dias = java.time.temporal.ChronoUnit.DAYS.between(hoje, dataTransferencia);

        if (dias == 0) return 2.5;
        if (dias >= 1 && dias <= 10) return 0.0;
        if (dias >= 11 && dias <= 20) return 8.2;
        if (dias >= 21 && dias <= 30) return 6.9;
        if (dias >= 31 && dias <= 40) return 4.7;
        if (dias >= 41 && dias <= 50) return 1.7;
        return -1;
    }
}
