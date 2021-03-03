MAKE RUN - para rodar o projeto

O trabalho pode ser feito em grupos de até 4 alunos e consiste no desenvolvimento de um interpretador de comandos para uma máquina fictícia. A implementação deve ser em linguagem Java. Cópias da Internet ou entre grupos terão nota zero para todos os envolvidos assim como aqueles cujos programas que não compilarem.

A máquina a ser simulada tem como memória 1000 valores inteiros de 1 byte cada. Estes valores devem ser indexáveis por um valor inteiro que vai de 0 até 999. Inicialmente, a máquina tem todas as posições de memória com o valor 0.

A máquina dispõe apenas de dois registradores:
    1- O ponteiro de dados que armazena um valor inteiro positivo indicando qual a posição de memória deve ser usada.
    2- O ponteiro de programa que armazena um inteiro representando o índice do comando atual do programa.
Ambos registrados são inicializados em 0.

Existem ainda três arquivos que toda a máquina deve operar:
   1 - SOURCE que representa o arquivo o programa está descrito. Este arquivo deve ser aberto em modo de leitura.
   2 - IF onde cada entrada deve ocupar exclusivamente uma linha.
   3 - OF onde cada linha corresponde a uma operação de saída da máquina.

Os programas serão descritos em uma linguagem onde cada comando é um caractere. Abaixo está a lista de comandos: 

\> incrementa o ponteiro de dados para a próxima posição (uma unidade à direita).

\< decrementa o ponteiro de dados para a posição anterior (uma unidade à esquerda).
+    incrementa em uma unidade a posição apontada pelo ponteiro de dados.
-    decrementa em uma unidade a posição apontada pelo ponteiro de dados.
[    se a posição apontada pelo ponteiro de dados é 0, então desloque o ponteiro de programa para o próximo comando em sequência ao ] correspondente. Caso contrário, avance o ponteiro de programa.
]    se a posição apontada pelo ponteiro de dados é diferente de 0, então retroceda o ponteiro de programa para o [ correspondente.
,    lê uma linha do arquivo IF e o armazena na posição apontada pelo ponteiro de dados
.    escreve no arquivo OF o byte apontado pelo ponteiro de dados.
$    termina o programa e imprime o conteúdo da memória no arquivo OF.

Quaisquer comandos diferentes dos especificados abaixo devem ser ignorados e o ponteiro de programa incrementado.

Um programa válido nada mais é do que uma String terminada em $. Um programa pode ter mais de um comando de terminação.
Todo comando [ possui um comando ] correspondente e vice-versa.

Apenas entradas válidas, isto é, sem erros de sintaxe, serão testadas.