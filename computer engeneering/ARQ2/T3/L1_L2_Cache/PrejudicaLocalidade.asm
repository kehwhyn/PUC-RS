          .data
vecend:   .word 	0x10010000 0x10010120 0x10010220 0x10010320 0x10010420 0x10010520 0x100103c0 0x10010340 0x10010160 0x100100c0 0x100102c0
vecdad:	  .word		29 76 5 75 20 54 34 85 79 36 79
tam:      .word 	44

        .text
        .globl 	main

main:   lw	$t4, tam		# int tam = 20;
        addu    $t0, $zero, $zero   	# int i = 0;
loop:	lw	$t1, vecdad($t0)	# busca dado do vetor de dados
	lw	$t2, vecend($t0)	# busca endereço onde vai escrever
	addu	$t1, $t1, 2		# soma mais dois ao dado
	sw	$t1, ($t2)		# escreve no endereço
	addu	$t0, $t0, 4		# i++
	blt	$t0, $t4, loop		# if (i < tam) goto loop
	addu	$t0, $zero, $zero
fim:	j	fim
