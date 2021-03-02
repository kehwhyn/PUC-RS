        .data
vec:    .word 	29 76 5 75 20 54 34 85 79 36 79 76 81 36 23 89 11 19 97 32
tam:    .word 	80

        .text
        .globl 	main

main:   lw	$t4, tam		# int tam = 20;
        addu    $t0, $zero, $zero   	# int i = 0;
loop:	lw	$t1, vec($t0)		# int aux = vec[i]
	addu	$t1, $t1, 2		# aux += 2
	sw	$t1, vec($t0)		# vec[i] = aux
	addu	$t0, $t0, 4		# i++
	blt	$t0, $t4, loop		# if (i < tam) goto loop
	addu	$t0, $zero, $zero
fim:	j	fim