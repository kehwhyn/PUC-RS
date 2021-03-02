import socket
from zlib import crc32
from hashlib import md5

HOST: str = ''
PORT: int = 50007
ADDR: (str, int) = (HOST, PORT)
MSG_SIZE: int = 1024
MESSAGE = 0
CRC = 1
SEQ_NUM = 2


def main() -> None:
    confirmed: [str] = [' '] * 100

    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as udp_server:
        udp_server.bind(ADDR)

        print('waiting for client to make first contact...')
        client_file_checksum, client_addr = udp_server.recvfrom(MSG_SIZE)

        while True:
            print("receiving message from client...")
            data = udp_server.recv(MSG_SIZE)

            print("unpacking message...")
            packet = data.decode().split(';')
            if packet[0] == 'finish':
                break
            msg = packet[MESSAGE]
            crc = packet[CRC]
            seq_num = int(packet[SEQ_NUM])

            print("checking if message is valid...")
            tmp = str(crc32(msg.encode()))
            if not crc == tmp:
                print("message isn't valid.")
                print("sending same seq_num back.")
                udp_server.sendto(str(seq_num).encode(), client_addr)
                continue

            print("it is valid.")
            confirmed[seq_num] = msg
            udp_server.sendto(str(seq_num + 1).encode(), client_addr)

        write_file(confirmed)
        print(get_file_checksum())


def get_file_checksum():
    with open('received_message.txt', 'rb') as file:
        aux = md5()
        for line in file:
            aux.update(line)
    return aux.hexdigest()


def write_file(confirmed):
    with open('received_message.txt', 'w') as file:
        for line in confirmed:
            file.write(line)


if __name__ == '__main__':
    main()
