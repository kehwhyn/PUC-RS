import socket

HOST = 'localhost'
PORT = 50007
ADDR = (HOST, PORT)


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as client:
        with open('sent_1200_B.txt', 'rb') as file:
            for line in file:
                client.sendto(line, ADDR)

            client.sendto(b'', ADDR)


if __name__ == '__main__':
    main()
