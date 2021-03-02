#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# client program using udp
import socket

HOST = '127.0.0.1'  # The remote host
PORT = 50008        # The same port as used by the server
ADDR = (HOST, PORT)
MSG_SIZE = 1024

INTRO = "Olá, bem-vindx(s) ao Jogo da Forca!"
OPCOES = """
1 - Regras
2 - Jogar
3 - Multiplayer
4 - Parar de jogar
"""

def mostrar_regras():
    print("""
O jogo é baseado em temas/perguntas e respostas.
Você recebe uma pergunta e tem que adivinhar qual a reposta.
O número de tentativas é 5, caso passe de disso você perde o jogo.
5 é o mesmo número de partes do corpo que desenhamos no papel quando jogamos a forca.
Só letras erradas diminuem o número de tentativas.
Caso você queria adivinhar a palavra, você pode fazer isso. Errar só diminui as tentativas.
""")

def jogar():
    #cria conexão com o servidor e fecha quando a conexão acabar
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
        #manda uma primeira mensagem pro servidor descobrir o endereço
        sock.sendto("primeira conexão com o servidor".encode(), ADDR)
        #loop principal da comunicação
        while True:
            data, _ = sock.recvfrom(MSG_SIZE)
            data = data.decode()

            #se a string recebida do servidor conter essa frase
            #termina a conexão com o servidor
            if "Fim do jogo!" in data:
                #exibe os dados finais do jogo
                print(data)
                break

            #exibe as informações fornecidas pelo servidor
            resp = input(data)
            #envia a resposta para o servidor
            sock.sendto(resp.encode(), ADDR)

def main():
    #um breve menu para que o usuário possa interagir
    #com o programa
    while True:
        print(INTRO)
        print(OPCOES)
        opcao = input("Digite sua opção: ")

        if opcao == "1":
            mostrar_regras()
        elif opcao == "2":
            print("Vamos jogar então!")
            jogar()
        elif opcao == "3":
            print("not implemented yet.")
        elif opcao == "4":
            print("Obrigado por jogar! <3")
            break
        else:
            print("Opção inválida, por favor digite novamente.")

if __name__ == "__main__":
    main()