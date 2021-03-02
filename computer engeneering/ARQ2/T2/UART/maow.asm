.text
.globl main

# SALTA PARA O IN�?CIO DO CÓDIGO DO USU�?RIO
#####################################################
main:
        j MyMain

# ROTINA PARA TRATAMENTO DAS CHAMADAS DE INTERRUPÇÃO
#####################################################
DesvioParaTratamentoDeInterrupcoes:
        subu $sp, $sp, 4
        sw $ra, 0($sp)
        la $k0, EnderecoUART
        lw $k0, 0($k0)
        sll $k0, $k0, 0x02
        la $k1, TabelaDeInterrupcoes
        addu $k0, $k0, $k1
        jalr $k0
        lw $ra, 0($sp)
        addiu $sp, $sp, 4
        # eret realiza a volta via o EPC do coprocessador 0
        mtc0 $ra,$14
        eret

# ENDEREÇO DA TABELA DE ENDEREÇAMENTO DE INTERRUPÇÕES
########################################################
TabelaDeInterrupcoes:
        j CPU_TO_UART
        j UART_TO_CPU

# ROTINAS PARA TRATAR AS INTERRUPÇÕES
#####################################
CPU_TO_UART:
        subu $sp, $sp, 20
        sw $t0, 0($sp)
        sw $t3, 4($sp)
        sw $t4, 8($sp)
        sw $t6, 12($sp)
        sw $ra, 16($sp)

	# codigo para tratamento da UART

        lw $ra, 16($sp)
        lw $t6, 12($sp)
        lw $t4, 8($sp)
        lw $t3, 4($sp)
        lw $t0, 0($sp)
        addiu $sp, $sp, 20
        jr $ra

#############################################
UART_TO_CPU:

# IN�?CIO DO PROGRAMA DO USU�?RIO NO ENDEREÇO
#############################################
MyMain:
        li $t0, 0
        li $t1, 0
        li $t2, 0
        jal ProcessamentoDaUARTInit
SaltoMyMain:
        addiu $t0, $t0, 1
        addu $t1, $t1, $t0
        jal ProcessamentoDaUART
        addu $t2, $t1, $t0
        j SaltoMyMain


# Funcao que simula a computacao
# registrador t7 guardara o ciclo atual da UART
ProcessamentoDaUARTInit:
	lw $t7,  CiclosParaInterrupcaoUART
	jr $ra
ProcessamentoDaUART:
	subi $t7, $t7, 1
	# beleza fabiano !
	blez $t7, InterrupcaoDeFato
	# senao, volte ao processamento normal
	jr $ra
	
# simulacao de recebimento de interrupcao
InterrupcaoDeFato:
	# esse processo deve ser feito em HARDWARE
	subi $sp, $sp, 4
	sw $ra, 0($sp)
	jal DesvioParaTratamentoDeInterrupcoes
	# reinicializa contador de ciclos
	jal ProcessamentoDaUARTInit
	lw $ra, 0($sp)
	addi $sp, $sp, 4
	jr $ra
	

# segmento de data comum
.data 0x10010000
CiclosParaInterrupcaoUART:	.word 20

# ENDEREÇO DA UART
##################
# NOTE que o endereco verdadeiro para o hardware eh FFE00000
# mas para o simulador MARS utilizaremos o segmento de memory-mapped IO (FFFF0000)
# note que ao ler o endereco, o hardware ira responder, mas nesse teste o valor de
# respostas eh hard-coded (0)
.data 0xFFFF0000
EnderecoUART:			.word 0


