export interface Transferencia {
  id?: number;
  contaOrigem: string;
  contaDestino: string;
  valor: number;
  taxa: number;
  aviso?: string;
  dataTransferencia: string;
  dataAgendamento: string | null;
}