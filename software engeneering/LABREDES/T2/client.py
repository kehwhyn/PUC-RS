#
import argparse
import sys
#
import socket
from zlib import crc32
from hashlib import md5

HOST = 'localhost'
PORT = 50007
ADDR = (HOST, PORT)


def main(args):
    file_contents = read_file(args.file_path)
    file_checksum = get_file_checksum(args.file_path)

    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as udp_client:
        print('making first contact with server...')
        udp_client.sendto(file_checksum.encode(), ADDR)

        try:
            print("sending file contents to server")
            seq_num = 0
            while True:
                print('preparing packet to send...')
                msg = file_contents[seq_num]
                err_ctrl = crc32(msg.encode())
                msg += ';' + str(err_ctrl) + ';' + str(seq_num)
                print('sending packet.')
                udp_client.sendto(msg.encode(), ADDR)

                print('waiting for ACK...')
                udp_client.settimeout(2)
                next_seq_num = udp_client.recv(1024)

                print('ACK received.')
                seq_num = int(next_seq_num.decode())

                if seq_num >= len(file_contents):
                    break

            udp_client.sendto('finish'.encode(), ADDR)

            print(file_checksum)

        except socket.timeout:
            print('time is up')


def parse_arguments():
    parser = argparse.ArgumentParser(
        description=''
    )

    parser.add_argument(
        '--file_path',
        dest='file_path',
        help='file absolute or relative path',
        default=None,
        type=str
    )

    if len(sys.argv) == 1:
        parser.print_help()
        sys.exit(1)

    return parser.parse_args()


def read_file(file_path):
    with open(file_path, 'r') as file:
        tmp = []
        while True:
            aux = file.read(512)
            if not aux:
                break
            tmp.append(aux)
        return tmp


def get_file_checksum(file_path: str):
    with open(file_path, 'rb') as file:
        aux = md5()
        for line in file:
            aux.update(line)
        return aux.hexdigest()


if __name__ == '__main__':
    main(
        parse_arguments()
    )
