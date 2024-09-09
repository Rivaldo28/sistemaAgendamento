import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Transferencia } from '../model/transferencia.model';
import { Page } from '../model/page.model';

@Injectable({
  providedIn: 'root'
})
export class TransferenciaService {
  private apiUrl = 'http://localhost:8080/api-transferencia';

  constructor(private http: HttpClient) {}

  obterExtrato(page: number = 0, size: number = 10): Observable<Page<Transferencia>> {
    return this.http.get<Page<Transferencia>>(`${this.apiUrl}?page=${page}&size=${size}`).pipe(
      catchError(this.handleError)
    );
  }

    agendarTransferencia(transferencia: Transferencia): Observable<any> {
        return this.http.post<any>(`${this.apiUrl}`, transferencia);
    }
  
  private handleError(error: any): Observable<never> {
    console.error('Erro na requisição:', error);
    return throwError(() => new Error('Erro ao realizar a operação. Tente novamente.'));
  }
}
