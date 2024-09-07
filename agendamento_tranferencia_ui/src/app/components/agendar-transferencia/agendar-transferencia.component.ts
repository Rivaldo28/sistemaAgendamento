import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Transferencia } from 'src/app/model/transferencia.model';
import { TransferenciaService } from 'src/app/services/transferencia.service';

@Component({
  selector: 'app-agendar-transferencia',
  templateUrl: './agendar-transferencia.component.html',
  styleUrls: ['./agendar-transferencia.component.css']
})
export class AgendarTransferenciaComponent implements OnInit {
  transferenciaForm: FormGroup;

  constructor(private fb: FormBuilder, private transferenciaService: TransferenciaService) {
    // Inicialize o formulário
    this.transferenciaForm = this.fb.group({
      contaOrigem: ['', Validators.required],
      contaDestino: ['', Validators.required],
      valor: [null, [Validators.required, this.positiveNumberValidator]],
 /*      taxa: [0, [Validators.required, Validators.min(0)]], */
/*       dataTransferencia: ['', Validators.required], */
      dataAgendamento: ['', Validators.required]
    });
  }

  ngOnInit(): void {}
  

  public agendarTransferencia() {
    if (this.transferenciaForm.valid) {
      const transferencia: Transferencia = this.transferenciaForm.value;
      console.log('Payload enviado:', transferencia); // Para depuração
      this.transferenciaService.agendarTransferencia(transferencia).subscribe(
        response => {
          console.log('Transferência agendada:', response);
        },
        error => {
          alert('Erro: ' + error.message);
        }
      );
    } else {
      alert('Por favor, preencha todos os campos obrigatórios.');
    }
  }


  positiveNumberValidator(control: any) {
    // Remove 'R$ ' e substitui vírgula por ponto
    const value = control.value ? control.value.replace('R$ ', '').replace(/\./g, '').replace(',', '.') : '0';
    
    const numericValue = parseFloat(value);
    return numericValue > 0 ? null : { invalidNumber: true };
  }



}