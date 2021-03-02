/*Alunos: Kevin Fiorentin e Nedison Velloso

    Para executar o Makefile na linha de comando, digite:

    make THREADS=4

    Sendo o valor depois do igual o número de
    threads que o programa deve executar.

    No exemplo ele cria 8 threads, sendo 4 prod e 4 cons.
 */

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

#define DEFAULT_NUMBER_OF_THREADS 1
#define TAM_BUFFER 100

void setup(int const);
void create(int const);
void join(int const);
void *producer(void *);
void produceItem(int *);
void enter_region(int const);
void leave_region(int const);
void *consumer(void *);
void consumeItem(int *);
void teardown(void);

sem_t empty, full;
int buffer[TAM_BUFFER] = {0}, begin = 0, end = 0;
int *flags = NULL, *turns = NULL, MAX_NUMBER_OF_THREADS = 0;
pthread_t *producers = NULL, *consumers = NULL;

int main(int const argc, char const *argv[])
{

    int const NUMBER_OF_THREADS = (argc == 2) ? atoi(argv[1]) : DEFAULT_NUMBER_OF_THREADS;

    setup(NUMBER_OF_THREADS);

    create(NUMBER_OF_THREADS);

    join(NUMBER_OF_THREADS);

    teardown();
    exit(EXIT_SUCCESS);
}

void setup(int const NUMBER_OF_THREADS)
{
    producers = (pthread_t *)calloc(NUMBER_OF_THREADS, sizeof(pthread_t));
    consumers = (pthread_t *)calloc(NUMBER_OF_THREADS, sizeof(pthread_t));

    MAX_NUMBER_OF_THREADS = NUMBER_OF_THREADS * 2;
    flags = (int *)calloc(MAX_NUMBER_OF_THREADS, sizeof(int));
    turns = (int *)calloc(MAX_NUMBER_OF_THREADS, sizeof(int));

    srand(time(NULL));

    sem_init(&empty, 0, TAM_BUFFER);
    sem_init(&full, 0, 0);
}

void create(int const NUMBER_OF_THREADS)
{
    for (int i = 0, error = 0, even = 0, odd = 0; i < NUMBER_OF_THREADS; i++)
    {

        even = 2 * i;
        error = pthread_create(&producers[i], NULL, producer, &even);
        if (error)
        {
            fprintf(stderr, "Couldn't create producer thread number %d\n", i);
            exit(EXIT_FAILURE);
        }

        odd = 2 * i + 1;
        error = pthread_create(&consumers[i], NULL, consumer, &odd);
        if (error)
        {
            fprintf(stderr, "Couldn't create consumer thread number %d\n", i);
            exit(EXIT_FAILURE);
        }
    }
}

void join(int const NUMBER_OF_THREADS)
{
    for (int i = 0, error = 0; i < NUMBER_OF_THREADS; i++)
    {

        error = pthread_join(producers[i], NULL);
        if (error)
        {
            fprintf(stderr, "Couldn't join producer thread number %d\n", i);
            exit(EXIT_FAILURE);
        }

        error = pthread_join(consumers[i], NULL);
        if (error)
        {
            fprintf(stderr, "Couldn't join consumer thread number %d\n", i);
            exit(EXIT_FAILURE);
        }
    }
}

void *producer(void *id)
{

    int *identifier = (int *)id;
    int item;

    sem_wait(&empty);

    produceItem(&item);

    enter_region(*identifier);

    buffer[end] = item;
    end = (end + 1) % TAM_BUFFER;
    printf("producer -> %d\n", item);

    leave_region(*identifier);

    sem_post(&full);
}

void produceItem(int *item)
{
    *item = rand() % 1000;
}

void enter_region(int const id)
{
    for (int i = 0; i < MAX_NUMBER_OF_THREADS; i++)
    {

        flags[id] = i;
        turns[i] = id;

        if (i != id)
            while (turns[i] != id && flags[i] >= i)
                ;
    }
}

void leave_region(int const id)
{
    flags[id] = -1;
}

void *consumer(void *id)
{

    int *identifier = (int *)id;
    int item;

    sem_wait(&full);

    enter_region(*identifier);

    item = buffer[begin];
    begin = (begin + 1) % TAM_BUFFER;
    printf("consumer -> %d\n", item);

    leave_region(*identifier);

    consumeItem(&item);

    sem_post(&empty);
}

void consumeItem(int *item)
{
    *item = 0;
}

void teardown(void)
{
    sem_destroy(&empty);
    sem_destroy(&full);
    free(producers);
    free(consumers);
    free(flags);
    free(turns);
}
