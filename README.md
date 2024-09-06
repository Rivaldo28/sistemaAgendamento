# sistemaTesteTecnico

## Descrição

O sistema de agendamento de transferências financeiras permite que os usuários agendem transferências entre contas, especificando valores e datas. A aplicação é construída com Java 11 e Spring Boot 2.7.8 para o backend e Angular para o frontend.

## Tecnologias Utilizadas

- **Backend**:
  - Java 11
  - Spring Boot 2.7.8
  - H2 Database (banco de dados em memória)
- **Frontend**:
  - Angular
  - TypeScript
  - HTML/CSS

## Funcionalidades

1. **Agendamento de Transferências**:

   - O usuário pode agendar uma transferência fornecendo as seguintes informações:
     - Conta de origem (formato: XXXXXXXXXX)
     - Conta de destino (formato: XXXXXXXXXX)
     - Valor da transferência
     - Taxa (calculada automaticamente conforme tabela de taxas)
     - Data da transferência (data em que será realizada)
     - Data de agendamento (data atual)

2. **Cálculo da Taxa**:

   - A taxa sobre o valor transferido é calculada com base na seguinte tabela:
     ```
     | Dias   | Transferência | R$ Taxa  | Percentual |
     |--------|----------------|----------|------------|
     | 0-10   | 0              | 3,00     | 2,5%       |
     | 11-20  | 10             | 12,00    | 0,0%       |
     | 21-30  | 20             | 0,00     | 8,2%       |
     | 31-40  | 30             | 0,00     | 6,9%       |
     | 41-50  | 40             | 0,00     | 4,7%       |
     | >50    | 50+            | 0,00     | 1,7%       |
     ```
   - Caso não haja taxa aplicável, um alerta é exibido e a transferência não é permitida.

3. **Extrato de Agendamentos**:
   - O usuário pode visualizar um extrato de todas as transferências agendadas.

## Estrutura do Projeto
