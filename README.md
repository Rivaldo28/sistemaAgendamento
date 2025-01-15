# sistemaFaturamento

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

Instalação do JDK Java 11

Certifique-se de ter o JDK Java 11 instalado em seu sistema. Você pode baixar o JDK 11 do site oficial da Oracle ou usar uma alternativa como o OpenJDK.
Compilação do Projeto

Navegue até o diretório do projeto back-end usando o terminal ou prompt de comando.
Execute o comando mvn install para compilar o projeto e resolver as dependências. Este comando usa o Apache Maven, uma ferramenta de gerenciamento e construção de projetos Java.
Alternativamente, você pode abrir o projeto no IntelliJ IDEA:
Abra o IntelliJ IDEA.
Selecione File > Open... e escolha o diretório do projeto.
IntelliJ IDEA irá detectar o projeto Maven e carregar as dependências automaticamente.

Instalação do Node.js

Certifique-se de ter o Node.js 14.18.1 instalado em seu sistema. Você pode baixar o Node.js do site oficial. Verifique a versão após a instalação usando o comando:
bash
Copiar código
node -v
Certifique-se de que a versão exibida seja 14.18.1 ou superior.
Instalação das Dependências do Projeto

Navegue até o diretório do projeto front-end usando o terminal ou prompt de comando.
Execute o comando npm install para instalar todas as dependências listadas no arquivo package.json do projeto Angular. Isso garante que todas as bibliotecas e pacotes necessários para o projeto estejam disponíveis.
Executando o Servidor de Desenvolvimento

Após a instalação das dependências, execute o comando:
bash
Copiar código
npm start
ou
bash
Copiar código
ng serve
para iniciar o servidor de desenvolvimento Angular. O projeto estará disponível em http://localhost:4200 por padrão.
