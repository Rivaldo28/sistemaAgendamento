package com.rivaldo.agendamento_tranferencia_financeiro.service;

import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import com.rivaldo.agendamento_tranferencia_financeiro.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    public List<Transferencia> listarTransferencia() {
        return repository.findAll();
    }

    public Transferencia agendarTransferencia(Transferencia transferencia) {
        double taxa = calcularTaxa(transferencia.getDataTransferencia(), transferencia.getValor());
        long qtDias = 0;

        if (taxa == qtDias) {
//            throw new IllegalArgumentException("Transferência sem taxa aplicável.");
            transferencia.setAviso("Transferência agendada com taxa de 0%");
        }
        transferencia.setTaxa(taxa);
        transferencia.setDataAgendamento(LocalDate.now());
        return repository.save(transferencia);
    }


    private double calcularTaxa(LocalDate dataTransferencia, Double valor) {
        long dias = LocalDate.now().until(dataTransferencia).getDays();

        System.out.println("Dias até a transferência: " + dias);

        if (dias >= 0 && dias <= 10) return valor * 0.025;
        if (dias >= 11 && dias <= 20) return 0.0;
        if (dias >= 21 && dias <= 30) return valor * 0.069;
        if (dias >= 31 && dias <= 40) return valor * 0.047;
        if (dias >= 41 && dias <= 50) return valor * 0.017;
        return 0.0;
    }

}
