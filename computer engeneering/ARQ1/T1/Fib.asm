# Compute several Fibonacci numbers and put in array, then print
	.data
fibs:	.word	0 : 19		# "array" of words to contain fib values
size: 	.word	19             	# size of "array" (agrees with array declaration)
prompt:	.asciiz	"How many Fibonacci numbers to generate? (2 <= x <= 19)"
space:	.asciiz  " "          # space to insert between numbers
head: 	.asciiz  "The Fibonacci numbers are:\n"
	.text
	.globl main

main:
la   $s0, fibs        # load address of array
la   $s5, size        # load address of size variable
nop
nop
nop
lw   $s5, 0($s5)      # load array size
      
addiu   $s2, $0, 1          # 1 is the known value of first and second Fib. number
nop
nop
nop
sw   $s2, 0($s0)      # F[0] = 1
nop
nop
nop
sw   $s2, 4($s0)      # F[1] = F[0] = 1
addiu $s1, $s5, -2     # Counter for loop, will execute (size-2) times
      
      # Loop to compute each Fibonacci number using the previous two Fib. numbers.
loop: 
lw   $s3, 0($s0)      # Get value from array F[n-2]
lw   $s4, 4($s0)      # Get value from array F[n-1]
nop
nop
nop
addu  $s2, $s3, $s4    # F[n] = F[n-1] + F[n-2]
nop
nop
nop
sw   $s2, 8($s0)      # Store newly computed F[n] in array
addiu $s0, $s0, 4      # increment address to now-known Fib. number storage
addiu $s1, $s1, -1     # decrement loop counter
nop
nop
nop
bne $s1, $0, loop        # repeat while not finished
     
      # Fibonacci numbers are computed and stored in array. Print them.
la   $a0, fibs        # first argument for print (array)
addu  $a1, $zero, $s5  # second argument for print (size) 

jr 	$ra
		
