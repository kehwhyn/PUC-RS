#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# O que eu quero codar aqui?
#   - uma classe menu
#   - enviar as opções para o cliente
#   - processar a resposta do cliente
#   - múltiplos jogadores
#   - threads para quem mais se conectar
#   - hints

# Como vai funcionar tudo?
#   - cliente começa recebendo uma mensagem de introdução
#   - ele manda a resposta para o servidor e o servidor executa as funções

# server program using udp
import socket
from random import randrange

HOST = ''       # symbolic name meaning all available interfaces
PORT = 50008    # arbitrary non-privileged port
MSG_SIZE = 1024


def jogar(sock, addr):
    #nosso pequeno banco de dados para a execução do programa
    PERGUNTAS = [
        "Interligam redes que diferem bastante entre si",
        "Solução para o problema do IPv4",
        "Protocolo mais popular da internet",
        "Protocolo de emails",
        "O que TCP garante?",
        "Em que tipo de aplicações é usado UDP?",
        "Quem define o Default Gateway?",
        "O que DCHP fornece?",
        "Que protocolo converte nome de máquina para seu endereço IP?",
        "Forma de roteamento entre máquinas de diferentes redes",
    ]
    RESPOSTAS = [
        "roteadores",
        "network address translator",
        "http",
        "simple message trasfer protocol",
        "transferencia de dados",
        "entrega imediata",
        "administrador da rede",
        "enderecos ip temporarios",
        "domain name system",
        "indireto"
    ]

    indice_palavra_sorteada = randrange(len(PERGUNTAS))
    palavra_sorteada = RESPOSTAS[indice_palavra_sorteada].split(" ")
    chances_restantes = 5
    #strings em python são imutáveis
    #["_"] * len(word) cria um vetor com elementos "_" repetidos pelo tamanho da word
    #criei esse vetor de vetores para poder manipular o que é mostrado ao usuário
    letras_reveladas = [ ["_"] * len(word)
        for word in palavra_sorteada
    ]

    acertou = False
    while chances_restantes > 0 and not acertou:
        #aqui a mensagem agrupa todos os dados que serão exibidos
        #para o cliente
        msg = f"Número restante de chances: {chances_restantes}\n"
        msg += PERGUNTAS[indice_palavra_sorteada] + "\n"
        for palavra in letras_reveladas:
            msg += "".join(palavra) + " "
        msg += "\n\n"
        msg += "Digite uma letra: "
        sock.sendto(msg.encode(), addr)

        #recebe a resposta do usuário
        data, addr = sock.recvfrom(MSG_SIZE)
        data = data.decode().lower()
        
        #isalpha() só é verdadeiro caso a string contenha só
        #caracteres do alfabeto
        if data.isalpha():
            #se o usuário forneceu só uma letra, então ele está
            #tentando adivinhar a palavra
            if len(data) == 1:

                letra_encontrada = False
                #temos dois laços
                #um percorre o primeiro vetor que contem as palavras separadas
                for ind, palavra in enumerate(palavra_sorteada):
                    #e o segundo percorre a string caratere por caractere
                    for indice, caractere in enumerate(palavra):
                        #se existe então substitui no vetor de exibição
                        if data == caractere:
                            letra_encontrada = True
                            letras_reveladas[ind][indice] = data
                #se o usuário errar a letra, diminiu as chances
                if not letra_encontrada:
                    chances_restantes -= 1
                
                #aqui é uma variável auxiliar para ver se o usuário
                #acertou a palavra indo letra por letra
                aux = ""
                for palavra in letras_reveladas:
                    aux += "".join(palavra) + " "
                aux = aux.strip()
                acertou = aux == RESPOSTAS[indice_palavra_sorteada]

            else:
                #o usuário tentou adivinhar a palavra
                acertou = data == RESPOSTAS[indice_palavra_sorteada]
                #se não acertou diminui as tentativas
                if not acertou:
                    chances_restantes -= 1
    
    #aqui no final é escolhido qual mensagem final
    #será enviada ao usuário dependendo do desempenho dele no jogo
    if chances_restantes == 0:
        msg = "\nAcabaram-se as chances :(\n Fim do jogo! x.x\n"
    if acertou:
        msg = f"\nParabéns, você adivinhou a palavra! :) \n{RESPOSTAS[indice_palavra_sorteada]}\n Fim do jogo! ^.^\n"
    sock.sendto(msg.encode(), addr)
    
    return addr


def main():
    #cria conexão e fecha quando a conexão acabar
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
        
        #reserva este endereço
        sock.bind((HOST, PORT))

        #loop principal da comunicação
        while True:
            #recebe o endereço dos clientes que tentarem
            #se comunicar com ele 
            _, addr = sock.recvfrom(MSG_SIZE)
            addr = jogar(sock, addr)

if __name__ == "__main__":
    main()