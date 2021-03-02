# --------------------------
# Alunos: Kevin Fiorentin e Yasmin Elkfury - LABORG[590]
# --------------------------
.data
  BISSEXTO:  .word 2016 2012 2008 2004 2000
           	1916 1911 1908 1904 1900
   		1861 1857 1852 1846 1844
   		1728 1727 1726 1725 1723
 
  LINHA:		 .word 4
  COLUNA:   	 .word 5
  CNT:  		 .word 0
  RESULTADO:     .word 0 0 0 0 0
  TEXTO_1:  	 .asciiz "Qual linha deseja verificar?"
  TEXTO_2:  	 .asciiz "Total de anos bisextos: "
  TEXTO_3:  	 .asciiz "\nOs anos bisextos são: "
  TEXTO_4:  	 .asciiz ","

.text
.globl  main    

main:
      lw    $t0, LINHA   	 #Contem o valor da linha
    lw    $t1, COLUNA   	 #Contem o valor da coluna
    subi    $t0, $t0, 1
cachorro:
    la    $a0, TEXTO_1   	 #Carrega a pergunta
    li    $v0, 4   		 #Prepara o sistema pra imprimir
    syscall   			 #Imprime
    li    $v0, 5   		 #Prepara o sistema pra ler do teclado
    syscall   			 #Le o valor   	 
    bgt    $v0, $t0, cachorro    #Se o valor lido for maior que a linha, repete
    move    $t2, $v0   	 #Pega a linha escolhida pelo usuario e guarda
    li    $t3, 4
    mul     $t3, $t1, $t3   	 #Muliplica o numero de colunas por 4
	 mul     $t3, $t3, $t2   	 #Multiplica o resultado pelo numero de linhas
    la     $t3, BISSEXTO($t3)    #Ponteiro pro primeiro elemento da linha lida
cabra:
    ble     $t1, $zero, camundongo    #for(i = 5; i > 0; i--)
    lw     $t2, 0($t3)   	 #Carrega o conteudo do ponteiro
    move    $a0, $t2   	 #Envia o ano para a funcao
    jal    f_bissexto   	 #f_bissexto(l,i)
    subi    $t1, $t1, 1   	 #i--
    addi    $t3, $t3, 4   	 #Incrementa ponteiro para o proximo ano
    beqz    $v0, cabra   	 #if(ano) se for = 0 n escreve no vetor
    sw    $v0, RESULTADO($t8)    #Resultado[CNT] = valor
    addi    $t8, $t8, 4   	 
    addi    $t4, $t4, 1   	 #CNT++
    sw    $t4, CNT($zero)   	 #Guarda o valor de CNT na memoria
    j    cabra
camundongo:
    jal    imprimir   	 #imprimir()
    li    $v0, 10   		 
    syscall   			 #return 0
    

imprimir:
    la     $a0, TEXTO_2    	 #Carrega a string
    li    $v0, 4   		 #Prepara o sistema para imprimir
    syscall   			 #Imprime
    lw    $a0, CNT   	 #Carrega o inteiro
    li    $v0, 1   		 #Prepara sistema para imprimir
    syscall   			 #Imprime
    la    $a0, TEXTO_3   	 #Carrega a string
    li    $v0, 4   		 #Prepara sistema para imprimir
    syscall   			 #Imprime
    la    $t0, RESULTADO($zero)    #Ponteiro para o 1º elemento
    lw    $s7, CNT   	 #Prepara o contador para o laco
coelho:    
    lw    $t1, 0($t0)   	 #Carrega o valor do primeiro elemento do vetor
    move    $a0, $t1   	 #Prepara para imprimir o valor
    li    $v0, 1   		 #Prepara sistema para imprimir
    syscall   			 #Imprime
    la    $a0, TEXTO_4   	 #Carrega a string
    li    $v0, 4   		 #Prepara sistema para imprimir
    syscall   			 #Imprime
    addi    $t0, $t0, 4   	 #Vai para o proximo elemento
    subi    $s7, $s7, 1   	 #Decrementa o contador
    bgt  	 $s7, $zero, coelho    #Compara se o contador chegou a zero
      jr     $ra
      
f_bissexto:
    li    $t5, 4   		 
    li    $t6, 100
    li    $t7, 400
    rem    $s0, $a0, $t5   	 # Ano mod 4
    slti    $s0, $s0, 1   	 # Se for 0, 1
    rem    $s1, $a0, $t7   	 # Ano mod 400
    slti    $s1, $s1, 1   	 # Se for 0, 1
    rem    $s2, $a0, $t6   	 # Ano mod 100
    sgt    $s2, $s2, $zero   	 # Se for difente de 0, 1
    or    $s1, $s1, $s2   	 # um OU entre o 400 e o 100
    and    $s0, $s0, $s1   	 # um AND entre o ou de cima e o 4
    beqz    $s0, camelo   	 # Se for zero, retorna 0
    move    $v0, $a0   	 # CC retorna o ano
    j     camaleao   	 #Salta pro fim
camelo:
    move    $v0, $zero   	 #Retorna 0
camaleao:
    jr $ra   			 #Volta pro main


