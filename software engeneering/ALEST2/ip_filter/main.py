#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import sys
import time
import argparse
from pathlib import Path
from collections import deque
from contextlib import contextmanager

LO = 0
HI = 1


def parse_arguments():
    parser = argparse.ArgumentParser(
        prog="main",
        usage="%(prog)s [options] path/to/file_or_directory",
        description="filters an IP filter from a file or directory")

    parser.add_argument(
        "-d",
        "--directory",
        type=str,
        metavar='path',
        help="path to the directory that contains the test files")

    parser.add_argument("-f",
                        "--file",
                        type=str,
                        metavar='path',
                        help="path to the desired test file")

    if len(sys.argv) == 1:
        parser.print_help()
        sys.exit(1)

    return parser.parse_args()


@contextmanager
def timeit_context(name):
    start_time = time.time()
    yield
    elapsed_time = time.time() - start_time
    print(f'[{name}] finished in {int(elapsed_time * 1000)} ms')
    print()


def main(args):
    if args.file:
        process_single_file(Path(args.file))
    else:
        run_all_files(Path(args.directory))


def process_single_file(file_path):
    with timeit_context(file_path.name):
        answer = least_possible_list_size(get_sorted_ip_list(file_path))
        print(">>>", file_path.name, "=>", answer)


def least_possible_list_size(ip_list):
    blocked_ips = deque([ip_list.pop(0)])

    for ip_pair in ip_list:
        last_added = len(blocked_ips) - 1

        if change_higher_value(ip_pair, blocked_ips[last_added]):
            blocked_ips[last_added][HI] = ip_pair[HI]

        elif not contained(ip_pair, blocked_ips[last_added]):
            blocked_ips.append(ip_pair)

    return len(blocked_ips)


def get_sorted_ip_list(chosen_file_path):
    with open(chosen_file_path, "r") as file:
        return sorted([int(value) for value in line.strip().split("-")]
                      for line in file)


def change_higher_value(pair_1, pair_2):
    return pair_2[LO] < pair_1[LO] < pair_2[HI] <= pair_1[HI]


def contained(pair_1, pair_2):
    return pair_2[LO] < pair_1[LO] < pair_1[HI] < pair_2[HI]


def run_all_files(directory):
    with timeit_context("All files"):
        for file_path in sorted(directory.iterdir()):
            process_single_file(file_path)


if __name__ == "__main__":
    args = parse_arguments()
    main(args)
