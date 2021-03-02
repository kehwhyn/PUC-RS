import socket

HOST = ''
PORT = 50007
ADDR = (HOST, PORT)
MSG_SIZE = 1024


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as server:
        server.bind(ADDR)

        with open('received_udp_1200_B.txt', 'wb') as file:
            while True:
                data = server.recv(MSG_SIZE)
                if data == b'':
                    break
                file.write(data)


if __name__ == '__main__':
    main()
