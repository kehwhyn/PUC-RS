#####################################################################
## ARQUITETURA MIPS_S - teste de todas as instru��es
## Autor: Ney Calazans (ney.calazans@pucrs.br)
## Teste � exaustivo quanto ao total de instru��es, n�o quanto a
## 	cobertura de caso de testes poss�veis
##
## Para simular este programa no MIPS_S, 
##	execute por 13,60 microssegundos
##
## �ltima altera��o: 21/07/2017
#####################################################################
	.text			# Declara��o de in�cio do segmento de texto
	.globl	main		# Declara��o de que o r�tulo main � global
########################################
# testes de instru��es individuais
########################################
main:	lui	$t0,0xf3	#
	ori	$t0,$t0,0x23	# $t0<= 0x00f30023
	lui	$t1,0x52	#
	ori	$t1,$t1,0xe2	# $t1<= 0x005200e2
	lui	$t2,0x00	#
	ori	$t2,$t2,0x8f	# $t2<= 0x0000008f
	beq	$t1,$t2,loop	# Obviamente, esta instru��o nunca deve saltar
	nop			# Não faz nada mesmo
	bne	$t1,$t2,next_i	# Obviamente, esta instru��o sempre deve saltar
	addiu	$t2,$t2,0x8f	# Obviamente, esta instru��o nunca deve executar
next_i:	addu	$t3,$t0,$t1	# $t3<= 0x00f30023 + 0x005200e2 = 0x01450105
	subu	$t4,$t0,$t1	# $t4<= 0x00f30023 - 0x005200e2 = 0x00a0ff41
	subu	$t5,$t1,$t1	# $t5<= 0x0
	and	$t6,$t0,$t1	# $t6<= 0x00f30023 and 0x005200e2 = 0x00520022
	or	$t7,$t0,$t1	# $t7<= 0x00f30023 or  0x005200e2 = 0x00f300e3
	xor	$t8,$t0,$t1	# $t8<= 0x00f30023 xor 0x005200e2 = 0x00a100c1
	nor	$t9,$t0,$t1	# $t9<= 0x00f30023 nor 0x005200e2 = 0xff0cff1c
	multu	$t0,$t1		# Hi & Lo <= 0x00f30023 * 0x005200e2 = 0x00004dd6e1bc1ee6
	mfhi	$s0		# $s0<= 0x00004dd6
	mflo	$s1		# $s1<= 0xe1bc1ee6
	divu	$t0,$t1		# Hi,Lo<= 0x00f30023 mod,/ 0x005200e2 = 0x4efe5f,0x00000002
	addiu	$t0,$t0,0x00ab	# $t0<= 0x00f30023  +  0x000000ab = 0x00f300ce
	andi	$t0,$t0,0x00ab	# $t0<= 0x00f300ce and 0x000000ab = 0x0000008a
	xori	$t0,$t0,0xffab	# $t0<= 0x0000008a xor 0x0000ffab = 0x0000ff21
	sll	$t0,$t0,4	# $t0<= 0x000ff210 (deslocado 4 bits para a esquerda)
	srl	$t0,$t0,9	# $t0<= 0x000007f9 (deslocado 9 bits para a direita)
	addiu	$s2,$zero,8	# $s2<= 0x00000008
	sllv	$t0,$t9,$s2	# $t0<= 0x0007f900
	sllv	$t0,$t0,$s2	# $t0<= 0x07f90000
	sllv	$t0,$t0,$s2	# $t0<= 0xf9000000
	sra	$t0,$t0,4	# $t0<= 0xff900000
	srav	$t0,$t0,$s2	# $t0<= 0xffff9000
	srlv	$t0,$t0,$s2	# $t0<= 0x00ffff90
	la	$t0,array	# coloca em $t0 o endere�o inicial do vetor array (0x10010000)
	lbu	$t1,6($t0)	# $t1<= 0x000000ef (primero byte � terceiro byte do segundo elemento)
	xori	$t1,$t1,0xff	# $t1<= 0x00000010, inverte byte inferior
	sb	$t1,6($t0)	# segundo byte do segundo elemento do vetor <= 10 (resto n�o muda)
					# CUIDADO, mudou elemento do array a ser processado por soma_ct
	addiu	$t0,$zero,0x1	# $t0<= 0x00000001
	subu	$t0,$zero,$t0	# $t0<= 0xffffffff
	bgez	$t0,loop	# Esta instru��o nunca deve saltar, pois $t0 = -1
	slt	$t3,$t0,$t1	# $t3<= 0x00000001, pois -1 < 10
	sltu	$t3,$t0,$t1	# $t3<= 0x00000000, pois (2^32)-1 > 10
	slti	$t3,$t0,0x1	# $t3<= 0x00000001, pois -1 < 1
	sltiu	$t3,$t0,0x1	# $t3<= 0x00000000, pois (2^32)-1 > 1
########################################
# soma uma constante a um vetor
########################################
soma_ct:la	$t0,array	# coloca em $t0 o endere�o do vetor (0x10010000)
	la	$t1,size	# coloca em $t1 o endere�o do tamanho do vetor 
	lw	$t1,0($t1)	# coloca em $t1 o tamanho do vetor
	la	$t2,const	# coloca em $t2 o endere�o da constante
	lw	$t2,0($t2)	# coloca em $t2 a constante
loop:	blez	$t1,end_add	# se/quando tamanho �/torna-se 0, fim do processamento
	lw	$t3,0($t0)	# coloca em $t3 o pr�ximo elemento do vetor
	addu	$t3,$t3,$t2	# soma constante
	sw	$t3,0($t0)	# atualiza no vetor o valor do elemento
	addiu	$t0,$t0,4	# atualiza ponteiro do vetor. Lembrar, 1 palavra=4 posi��es de mem�ria
	addiu	$t1,$t1,-1	# decrementa contador de tamanho do vetor
	j	loop		# continua execu��o
########################################
# teste de subrotinas aninhadas
########################################
end_add:li	$sp,0x10010800	# Para poder simular o hardware, inicializa-se o $sp c/valor adequado
	addiu	$sp,$sp,-4	# assume-se $sp inicializado, aloca espa�o na pilha
				# no MARS o $sp � inicializado com 0x7FFFEFFC. Usamos 0x10010800.
	sw	$ra,0($sp)	# salva endere�o de retorno de quem chamou (trap handler)
	jal	sum_tst		# salta para subrotina sum_tst
	lw	$ra,0($sp)	# ao retornar, recupera endere�o de retorno da pilha
	addiu	$sp,$sp,4	# atualiza apontador de pilha
end:	jr	$ra		# FIM DO PROGRAMA AQUI. volta para o "sistema operacional" 
# In�cio da primeira subrotina: sum_tst
sum_tst:la	$t0,var_a	# pega endere�o da primeira vari�vel (pseudo-instru��o)
	lw	$t0,0($t0)	# toma o valor de var_a e coloca em $t0
	la	$t1,var_b	# pega endere�o da segunda vari�vel (pseudo-instru��o)
	lw	$t1,0($t1)	# toma o valor de var_b e coloca em $t1
	addu	$t2,$t1,$t0	# soma var_a com var_b e coloca resultado em $t2
	addiu	$sp,$sp,-8	# aloca espa�o na pilha
	sw	$t2,0($sp)	# no topo da pilha coloca o resultado da soma
	sw	$ra,4($sp)	# abaixo do topo coloca o endere�o de retorno
	la	$t3,ver_ev	# pega endere�o da subrotina ver_ev (pseudo-instru��o)
	jalr	$ra,$t3		# chama subrotina que verifica se resultado da soma � par
	lw	$ra,4($sp)	# ao retornar, recupera endere�o de retorno da pilha
	addiu	$sp,$sp,8	# atualiza apontador de pilha
	jr	$ra		# Rotina acaba AQUI. Retorna para quem chamou
# In�cio da segunda subrotina: ver_ev. Trata-se de subrotina folha, que n�o usa pilha
ver_ev:	lw	$t3,0($sp)	# tira dados to topo da pilha (par�metro)
	andi	$t3,$t3,1	# $t3 <= 1 se par�metro � �mpar, 0 caso contr�rio
	jr	$ra		# e retorna
########################################
.data			# �rea de dados
########################################
# para trecho que soma constante a vetor
array:	.word	0xabcdef03 0xcdefab18 0xefabcd35 0xbadcfeab 0xdcfebacd 0xfebadc77 0xdefabc53 0xcbafed45
				# terceiro byte da segunda palavra (0xef) vira 0x10 antes de exec soma_ct
size:	.word	0x8
const:	.word	0xffffffff	# constante -1 em complemento de 2
# para trecho de teste de chamadas de subrotinas
var_a:	.word	0xff		#
var_b:	.word	0x100		#
