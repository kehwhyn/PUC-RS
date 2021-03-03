###############################################################################################
# Exemplo de programa em linguagem de montagem do MIPS : Manipulando vetores
# Autor: Ney Calazans
# Fun��o: Este programa soma o valor de uma constante (const) a cada elemento do vetor array
###############################################################################################

# PUS IOS NOPS AONDE EU ACHEI DEPENDENCIAS
        
        .text                   # Diretiva para o montador - adiciona o que vem abaixo
        			# 	� mem�ria de programa do processador
        .globl  main            # Declara o r�tulo main Como sendo global
        			# 	este � o ponto a partir de onde se inicia a execu��o
main:
        la      $t0, array      # o registrador $t0 cont�m o endere�o do vetor
        la      $t1, size       # obt�m o endere�o da posi��o da mem�ria de dados onde se guarda
        nop
        nop
        nop			# o tamanho do vetor
        lw      $t1, 0($t1)     # o registrador $t1 cont�m o tamanho do vetor
        la      $t2, const      # obt�m o endere�o da constante const
        nop
        nop
        nop
        lw      $t2, 0($t2)     # o registrador $t2 cont�m a constante a somar
loop:   blez    $t1, end        # se o tamanho chega a 0, fim do processamento
        lw      $t3, 0($t0)     # obt�m um elemento do vetor
        nop
        nop
        nop
        addu    $t3, $t3, $t2   # soma a constante
        nop
        nop
        nop
        sw      $t3, 0($t0)     # atualiza o vetor
        addiu   $t0, $t0 ,4     # atualiza o apontador do vetor
        			# lembrar que 1 palavra no MIPS ocupa 4 endere�os consecutivos de mem�ria
        addiu   $t1,$t1,-1      # decrementa o contador de tamanho do vetor
        j       loop            # continua a execu��o
        
end:    jr       $ra		# Agora volta para o programa monitor

        .data                   # Diretiva para o montador - adiciona o que vem abaixo
        			# 	� mem�ria de dados do processador
array:  .word   0x12 0xff 0x3 0x14 0x878 0x31  0x62 0x10 0x5 0x16 0x20 # o vetor
                                # A diretiva ,.word carrega a lista de inteiros em posi��es successivas
                                # 	da mem�ria de dados
size:   .word   11              # Vari�vel que armazena o tamanho do vetor
const:  .word   0x100           # Constante a somar a cada elemento do vetor
