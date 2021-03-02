#####################################################################################
# Kevin Fiorentin, Yasmin Elkfury e Icaro Keller - Organizaco de Computadores[590]  #
#####################################################################################

    .data

Q:  .asciiz "Digite um numero pra ver se ele eh primo:"
EP: .asciiz "Eh primo."
NEP:.asciiz "Nao eh primo."

    .text
    .globl main

main:
    la	    $a0, Q
    li	    $v0, 4
    syscall                 # cout << "Digite um numero pra ver se ele e primo" << endl;
    li	    $v0, 5
    syscall                 # cin >> N;
    li	    $t0, 2
    addiu 	$sp, $sp, -12
    sw	    $ra, 8($sp)	    # Empilha endereço de retorno pro SO
    sw	    $t0, 4($sp)	    # Empilha K
    sw	    $v0, 0($sp)	    # Empilha N
    jal	    eh_primo	    # eh_primo(k, n);
    lw	    $t0, 4($sp)	    # Desempilha o valor retornado da funcao
    lw	    $ra, 8($sp)
    bgtz    $t0, true	    # if(true) goto true; else continue;
    la	    $a0, NEP
    li	    $v0, 4
    syscall                 # cout << "Nao eh primo!" << endl;
    j       fim_oficial
true:
    la      $a0, EP
    li      $v0, 4
    syscall                 # cout << "Eh primo"!" << endl;
fim_oficial:
    addiu   $sp, $sp, 12    # Destroi a pilha
    li      $v0, 10
    syscall                 # return 0;
eh_primo:
    lw      $t0, 4($sp)	    # Desempilha K
    lw      $t1, 0($sp)	    # Desempilha N
    addiu   $sp, $sp, -12	# Aloca 3 espacos na piha
    sw      $ra, 8($sp)	    # Empilha o endereco de retorno pra main
    sw      $t0, 4($sp)	    # Empilha o K
    sw      $t1, 0($sp)	    # Empilha N
    li      $t3, 1		    # if (k >= n)
    bge     $t0, $t1, fim	# Return true;
    rem     $t2, $t1, $t0	# else if((n % k) == 0)
    li      $t3, 0
    beqz    $t2, fim	    # Return false;
    addiu   $t0, $t0, 1
    sw      $t0, 4($sp)
    jal     eh_primo	    # else eh_primo(k+1, n);
fim:
    lw      $ra, 8($sp)	    # Recupera o valor do endereço de retono pro main
    addiu   $sp, $sp, 12	# Destroi a pilha
    sw      $t3, 4($sp)	    # return true/false
    jr      $ra		        # Volta pro main
