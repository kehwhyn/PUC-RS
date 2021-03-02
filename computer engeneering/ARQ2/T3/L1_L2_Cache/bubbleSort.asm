        .data

vec:    .word 	29 76 5 75 20 54 34 85 79 36 79 76 81 36 23 89 11 19 97 32
tam:    .word 	80

        .text
        .globl 	main

main:   lw	$t4, tam		# int tam = 20;
        addu    $t0, $zero, $zero   	# int i = 0;
        addu    $t1, $t0, 4         	# int j = i + 1;
        addu    $t2, $zero, $zero  	# int aux = 0;
for2:   lw	$t6, vec($t0)
        lw	$t7, vec($t1)
	blt	$t6, $t7, else		# if (i < j) goto else
        lw      $t2, vec($t0)       	# aux = vec[i];
        lw	$t5, vec($t1)
        sw	$t5, vec($t0)		# vec[i] = vec[j];
        sw	$t2, vec($t1)		# vec[j] = aux;
else:   addu	$t1, $t1, 4		# j++
for1:   blt	$t1, $t4, for2		# if (j < TAM) goto for2
	addu	$t0, $t0, 4		# i++
	addu	$t1, $t0, 4		# j = i + 1
	blt	$t0, $t4, for1		# if (i < TAM) goto else
fim:	j	fim