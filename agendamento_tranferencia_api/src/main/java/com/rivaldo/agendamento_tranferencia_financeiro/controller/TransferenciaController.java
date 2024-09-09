package com.rivaldo.agendamento_tranferencia_financeiro.controller;

import com.rivaldo.agendamento_tranferencia_financeiro.exception.TaxaNaoAplicavelException;
import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import com.rivaldo.agendamento_tranferencia_financeiro.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-transferencia")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @PostMapping
    public ResponseEntity<?> agendarTransferencia(@RequestBody Transferencia transferencia) {
        try {
            System.out.println("Transferência recebida: " + transferencia);
            System.out.println("Data de agendamento: " + transferencia.getDataAgendamento());
            Transferencia agendada = service.agendarTransferencia(transferencia);
            return ResponseEntity.ok(agendada);
        } catch (TaxaNaoAplicavelException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @ExceptionHandler(TaxaNaoAplicavelException.class)
    public ResponseEntity<String> handleTaxaNaoAplicavelException(TaxaNaoAplicavelException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @GetMapping
    public Page<Transferencia> getTransferencias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return service.listarTransferencias(PageRequest.of(page, size));
    }

}