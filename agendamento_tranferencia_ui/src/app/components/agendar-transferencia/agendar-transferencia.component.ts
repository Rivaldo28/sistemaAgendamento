import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Transferencia } from 'src/app/model/transferencia.model';
import { TransferenciaService } from 'src/app/services/transferencia.service';
import { ToastrService } from 'ngx-toastr';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-agendar-transferencia',
  templateUrl: './agendar-transferencia.component.html',
  styleUrls: ['./agendar-transferencia.component.css']
})
export class AgendarTransferenciaComponent implements OnInit {
  transferenciaForm: FormGroup;
  loading = false;

  constructor(private fb: FormBuilder, 
    private transferenciaService: TransferenciaService,
    private toastr: ToastrService) {
    this.transferenciaForm = this.fb.group({
      contaOrigem: ['', Validators.required],
      contaDestino: ['', Validators.required],
      valor: [null, [Validators.required, Validators.min(0.01)]],
      taxa: [null], 
      dataTransferencia: ['',  Validators.required],
      dataAgendamento: [{ value: new Date().toISOString().split('T')[0], disabled: true }, Validators.required]
    });
  }

  ngOnInit(): void {
    this.loading = true;
      setTimeout(() => {
        this.loading = false;
      }, 500);
  }

    public agendarTransferencia() {
    if (this.transferenciaForm.valid) {

        const transferencia: Transferencia = {
            contaOrigem: this.transferenciaForm.value.contaOrigem,
            contaDestino: this.transferenciaForm.value.contaDestino,
            valor: this.transferenciaForm.value.valor 
              ? parseFloat(this.transferenciaForm.value.valor.toString().replace(',', '.').replace(/[^0-9.-]+/g, "")) 
              : 0,
            taxa: this.transferenciaForm.value.taxa !== null ? this.transferenciaForm.value.taxa : null,
            dataTransferencia: this.transferenciaForm.get('dataTransferencia')?.value || null,
            dataAgendamento: new Date().toISOString().split('T')[0]
        };

        this.transferenciaService.agendarTransferencia(transferencia).subscribe(
            response => {
                this.toastr.success('Transferência feita com sucesso.', 'Sucesso!');
                this.limpar();
            },
            error => {
              const errorMessage =  error.error;
              Swal.fire({
                icon: "error",
                title: "Oops...",
                text: errorMessage,
              });
            }
        );
    } else {
        this.toastr.error('Por favor, preencha todos os campos obrigatórios.', 'Erro!');
    }
  } 

  public limpar() {
    this.transferenciaForm.reset({
      contaOrigem: '',
      contaDestino: '',
      valor: null,
      dataTransferencia: ''
    });
  }

  get isFormEmpty(): boolean {
    return !Object.values(this.transferenciaForm.value).some(value => value);
  }


}