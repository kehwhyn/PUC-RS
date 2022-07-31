# Arquitetura de Processadores na Prática – TP2

## 2. FORMAÇÃO DOS GRUPOS

Formação dos grupos: Os grupos deverão ser de 2 a 3 alunos. Não se aceita trabalhos individuais.

## 3. TRABALHO A SER DESENVOLVIDO E REGRAS DO JOGO

### 3.1

Implementar uma função recursiva para computar se um dado número é primo. O algoritmo básico de pesquisa binária a usar é dado abaixo, como uma função escrita em C:

```c

boolean ehprimo(int k, int n) {
   if (k >= n)			        // Tem algo a ser feito?
      return true;		        // Senão, retorna sim, número é primo
   else if ((n % k) == 0)	    // n é divisível por k?
      return false;		        // sim, encontramos um divisor, k
   else				            // Senão, k não é divisor
      return ehprimo(k+1, n);	// Neste caso, procuramos mais adiante
}
```

### 3.2

Assuma que k inicialmente é sempre 2 e teste as chamadas:

```c

ehprimo(2,1);      # Retorna true
ehprimo(2,7);      # Retorna true
ehprimo(2,6);      # Retorna true
ehprimo(2,7919);   # Retorna true
```

### 3.3

No caso 4 acima o programa demorou para executar? E se você usasse um número realmente grande? Tem como melhorar o algoritmo de verificação de primalidade? Olhe primos grandes, por exemplo, no site https://primes.utm.edu/lists/small/millions/.

### 3.4

Elabore um **programa principal** na mesma linguagem de montagem (do MIPS) para testar a função **ehprimo**. Lembre-se que este programa principal deve não apenas (1) chamar a rotina, mas também (2) passar argumentos para esta como exigido no item 3 abaixo, todos via pilha, (3) receber o valor de retorno, (4) imprimir este valor na console do simulador. Note que quem usar a pilha deve sempre esvaziá-la dos dados lá colocados antes da chamada. Defina uma área de dados adequada para o programa. Acrescente variáveis, se considerar necessário.

### 3.5

Respeite as seguintes convenções:

* Passagem de argumentos – todos os argumentos da função devem ser passados através da pilha, apontada pelo registrador $sp;
* O retorno do valor resultante da execução da função deve também ocorrer através da pilha.

### 3.6

Responda:

1. Qual o número do registrador $sp no conjunto de registradores do MIPS e qual o seu valor inicial (atribuído pelo simulador MARS)?
1. Qual é o primeiro valor escrito na pilha, e qual o significado do mesmo?
1. Mostre o conteúdo da pilha ao entrar na **terceira** chamada aninhada de um dos ramos de recursão (use a opção File→Dump Memory do simulador MARS).
1. Qual o conteúdo do registrador $sp neste momento?
1. Isto implica quanto espaço alocado na pilha?
1. Observar o retorno do procedimento recursivo. O valor do registrador $sp volta ao valor original? (**Se isto não ocorrer seu programa está incorreto**, pois sua execução deixa lixo na pilha).
1. Em qual linha de código este valor é re-estabelecido?
1. Em qual endereço da área de dados é escrita a resposta do seu programa?

## 4. FORMATO DO TRABALHO E ENTREGA

O trabalho deverá ser entregue via sala do Moodle até a data de 10/10/2017 (terça-feira), contendo o código fonte da aplicação **COMENTADO SEMANTICAMENTE**, as respostas das perguntas dos itens 4 e 5 acima e exemplos de telas do simulador MARS, mostrando alguns dos passos da execução do programa, com comentários.

## 5. VALOR DO TRABALHO

Este trabalho vale 25% da nota de Trabalho Prático da disciplina. Lembrando que TP corresponde a 40% da composição da nota de G1, este TP2 corresponde a 10% do G1.
