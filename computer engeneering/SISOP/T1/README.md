# Trabalho Prático 1

O primeiro trabalho prático da disciplina de Sistemas Operacionais consiste na implementação de primitivas para garantir a exclusão mútua (mutex) inteiramente por software (ou seja, utilizando espera ocupada). As primitivas a serem implementadas são *enter_region()* (ou *lock()*) e *leave_region()* (ou *unlock()*).

As primitivas implementadas não devem utilizar semáforos nem mecanismos de exclusão mútua já presentes na biblioteca pthreads. Utilize o algoritmo de Peterson que implementa exclusão mútua por software.

Para demonstrar a sua implementação, utilize a biblioteca pthreads e implemente o problema dos produtores / consumidores (consulte o material de aula sobre sincronização com semáforos). As regiões críticas devem ser protegidas por sua implementação de mutex (o restante da sincronização com semáforos contadores deve ser mantida). O número de threads da aplicação deve ser parametrizável (definido em uma macro ou passado como parâmetro para o programa).

O trabalho deve ser realizado em duplas. Não esqueçam de identificar-se em um comentário no início do código enviado por um dos integrantes pelo Moodle.

O trabalho deverá ser postado no Moodle até as 23hs55min do dia 17/10/2019.