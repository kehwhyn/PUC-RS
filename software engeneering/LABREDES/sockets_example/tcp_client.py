import socket

HOST = 'localhost'
PORT = 50007
ADDR = (HOST, PORT)


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client:
        client.connect(ADDR)
        with open('sent_1200_B.txt', 'rb') as file:
            for line in file:
                client.send(line)

            client.send(b'')


if __name__ == '__main__':
    main()