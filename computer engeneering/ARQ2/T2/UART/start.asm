.text
.globl main

# SALTA PARA O IN�CIO DO C�DIGO DO USU�RIO
#####################################################
main:
        j MyMain

# ROTINA PARA TRATAMENTO DAS CHAMADAS DE INTERRUP��O
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
        eret
        jr $ra

# ENDERE�O DA TABELA DE ENDERE�AMENTO DE INTERRUP��ES
########################################################
TabelaDeInterrupcoes:
        j UART_CIN
        j UART_COUT

# ROTINAS PARA TRATAR AS INTERRUP��ES
#####################################
UART_CIN:
        subu $sp, $sp, 20
        sw $t0, 0($sp)
        sw $t3, 4($sp)
        sw $t4, 8($sp)
        sw $t6, 12($sp)
        sw $ra, 16($sp)

# ...

        lw $ra, 16($sp)
        lw $t6, 12($sp)
        lw $t4, 8($sp)
        lw $t3, 4($sp)
        lw $t0, 0($sp)
        addiu $sp, $sp, 20
        jr $ra

#############################################
UART_COUT:
# ...


# IN�CIO DO PROGRAMA DO USU�RIO NO ENDERE�O
#############################################
MyMain:
        li $t0, 0
        li $t1, 0
        li $t2, 0
SaltoMyMain:
        addiu $t0, $t0, 1
        addu $t1, $t1, $t0
        addu $t2, $t1, $t0
        j SaltoMyMain

# ENDERE�O DA UART
##################
.data  0xFFE00000
EnderecoUART:
