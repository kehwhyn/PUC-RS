import socket

HOST = ''
PORT = 50007
ADDR = (HOST, PORT)
MSG_SIZE = 1024


def main():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
        server.bind(ADDR)
        server.listen(1)
        conn, _ = server.accept()
        with conn:
            with open('received_tcp_1200_B.txt', 'wb') as file:
                while True:
                    data = conn.recv(MSG_SIZE)
                    if data == b'':
                        break
                    file.write(data)


if __name__ == '__main__':
    main()
