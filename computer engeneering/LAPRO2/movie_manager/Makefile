CC = g++
CFLAGS = -c

all: ata

ata: ator.o diretor.o filme.o main.o
    $(CC) ator.o diretor.o filme.o main.o -o ata

main.o: main.cpp
    $(CC) $(CFLAGS) main.cpp

filme.o: filme.cpp
    $(CC) $(CFLAGS) filme.cpp

diretor.o: diretor.cpp
    $(CC) $(CFLAGS) diretor.cpp

ator.o: ator.cpp
    $(CC) $(CFLAGS) ator.cpp

clean:
    rm -rf *.o ata