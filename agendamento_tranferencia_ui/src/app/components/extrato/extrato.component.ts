import { Component, OnInit } from '@angular/core';
import { Transferencia } from 'src/app/model/transferencia.model';
import { TransferenciaService } from 'src/app/services/transferencia.service';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.css']
})
export class ExtratoComponent implements OnInit {
  transferencias: Transferencia[] = [];
  totalPages: number = 0;  
  currentPage: number = 0;  
  itemsPerPage: number = 6; 

  constructor(private transferenciaService: TransferenciaService) { }

  ngOnInit(): void {
    this.carregarTransferencias();
  }
  
  carregarTransferencias(page: number = 0, size: number = 6): void {
    this.transferenciaService.obterExtrato(page, size).subscribe({
      next: (data) => {
        this.transferencias = data.content; 
        this.totalPages = data.totalPages;  
        this.currentPage = data.number;       
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  mudarPagina(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.carregarTransferencias(page, this.itemsPerPage);
    }
  }

  formatCurrency(value: number): string {
    const real = 'R$ 0,00';
    if (value !== null && value !== undefined) {
      return value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    }
    return real;
  }

}
