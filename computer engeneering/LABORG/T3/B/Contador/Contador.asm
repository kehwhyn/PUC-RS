# ------------------------------------------------------ #
# Alunos: Kevin Fiorentin e Yasmin Elkfury - LABORG[590] #
# ------------------------------------------------------ #
            	.data
        
out_dec_s:	.word 0x10008000
out_un:		.word 0x10008001
out_dz:		.word 0x10008002
out_ct:		.word 0x10008003
 
            .text
            .globl  main    

main:
	addu	$t6, $zero, $ra		# Guarda o endereço de retorno
	addu	$t0, $zero, $zero 	# Contador1 = 0
	addu	$t1, $zero, $zero 	# Contador2 = 0
	addu	$t2, $zero, $zero 	# Contador3 = 0
        addu	$t3, $zero, $zero 	# Contador4 = 0
        addiu	$t4, $zero, 9     	# Variavel de controle para o limite do tempo
cont1:					# Rotulo de todos os lacos
	jal	f_perde_tempo		# Chama a funcao
	la	$t5, out_dec_s		# Obtem endereço do decimo de segundo
	lw	$t5, 0($t5)		# Escreve o endereço no registrado
	addu	$t4, $t4, $zero		# Pra igualar ao resto
	addiu	$t0, $t0, 1		# Incrementa o contador1
	sb	$t0, 0($t5)		# Escreve na memoria para passar pro Display
	bne	$t0, $t4, cont1		# Se for diferente continua contando
cont2:					# Para indicar onde comeca o contador3
	jal	f_perde_tempo		# Chama a funcao
	la	$t5, out_un		# Obtem endereço da unidade
	lw	$t5, 0($t5)		# Escreve o endereço no registrado
	addu	$t0, $zero, $zero	# CC zera o contador1
	addiu	$t1, $t1, 1		# Incrementa o contador 2
	sb	$t1, 0($t5)		# Escreve na memoria para passar pro Display
	bne	$t1, $t4, cont1		# Se for diferente continua contando
cont3:					# Para indicar onde comeca o contador3
	jal	f_perde_tempo		# Chama a funcao
	la	$t5, out_dz		# Obtem endereço da dezena
	lw	$t5, 0($t5)		# Escreve o endereço no registrado
	addu	$t1, $zero, $zero	# CC zera o contador2
	addiu	$t2, $t2, 1		# Incrementa o contador3
	sb	$t2, 0($t5)		# Escreve na memoria para passar pro Display
	bne	$t2, $t4, cont1		# Se for diferente continua contando
cont4:					# Para indicar onde comeca o contador3
	jal	f_perde_tempo		# Chama a funcao
	la	$t5, out_ct		# Obtem endereço da centena
	lw	$t5, 0($t5)		# Escreve o endereço no registrado
	addu	$t2, $zero, $zero	# CC zera o contador3
	addiu	$t3, $t3, 1		# Incrementa o contador4
	sb	$t3, 0($t5)		# Escreve na memoria para passar pro Display
	bne	$t3, $t4, cont1		# Se for diferente continua contando   
fim:					# Rotulo que indica o fim
	addu	$ra, $zero, $t6		# Recupera o endereço
	jr	$ra			# Volta para quem o chamou
f_perde_tempo:				# Funcao pra contar devagar
	addiu	$t5, $zero, 15000000	# Adiciona o valor para perder tempo
loop:	addiu	$t5, $t5, -1		# Decrementa
	bne	$t5, $zero, loop	# Confere para ver se ja chegou ao fim
	jr	$ra			# Volta para onde foi chamado
