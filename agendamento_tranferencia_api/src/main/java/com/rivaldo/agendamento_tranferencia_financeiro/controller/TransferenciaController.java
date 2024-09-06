package com.rivaldo.agendamento_tranferencia_financeiro.controller;

import com.rivaldo.agendamento_tranferencia_financeiro.model.Transferencia;
import com.rivaldo.agendamento_tranferencia_financeiro.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @PostMapping
    public ResponseEntity<Transferencia> agendarTransferencia(@RequestBody Transferencia transferencia) {
        return ResponseEntity.ok(service.agendarTransferencia(transferencia));
    }

    @GetMapping
    public List<Transferencia> listarTranferencia() {
        return service.listarTransferencia();
    }
}


