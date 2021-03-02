.text
.globl main

# SALTA PARA O INÍCIO DO CÓDIGO DO USUÁRIO
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
        eret
        jr $ra

# ENDEREÇO DA TABELA DE ENDEREÇAMENTO DE INTERRUPÇÕES
########################################################
TabelaDeInterrupcoes:
        j UART_CIN
        j UART_COUT

# ROTINAS PARA TRATAR AS INTERRUPÇÕES
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


# INÍCIO DO PROGRAMA DO USUÁRIO NO ENDEREÇO
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

# ENDEREÇO DA UART
##################
.data  0xFFE00000
EnderecoUART:
