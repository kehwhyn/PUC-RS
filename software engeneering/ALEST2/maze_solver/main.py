#!/usr/bin/env python

import time
from collections import deque
from contextlib import contextmanager

FILE_PATH: int = 0
OFFSET: int = 1
VALUES: (str, int) = (
    ('/home/kehwhyn/Documents/cohen_t2/caso1_cohen.txt', 100),
    ('/home/kehwhyn/Documents/cohen_t2/caso2_cohen.txt', 200),
    ('/home/kehwhyn/Documents/cohen_t2/caso3_cohen.txt', 500),
    ('/home/kehwhyn/Documents/cohen_t2/caso4_cohen.txt', 1000),
    ('/home/kehwhyn/Documents/cohen_t2/caso5_cohen.txt', 2000),
    ('/home/kehwhyn/Documents/cohen_t2/caso6_cohen.txt', 5000),
    ('/home/kehwhyn/Documents/cohen_t2/caso7_cohen.txt', 20000),
)


@contextmanager
def timeit_context(name: str) -> None:
    start_time: float = time.time()
    yield
    elapsed_time: float = time.time() - start_time
    print(f'[{name}] finished in {int(elapsed_time * 1000)} ms')
    print()


def main() -> None:
    print('Welcome! :D')
    option: int = 0
    while option != 3:
        show_menu_options()
        option = read_user_input()
        if option == 1:
            choose_one_file()
        elif option == 2:
            process_all_files()
        elif option == 3:
            print('Bye! :D')
            break
        else:
            print()
            print('Invalid option. Please, try again.')


def show_menu_options() -> None:
    print('What would you like to do?')
    print('1 - Run one file.')
    print('2 - Run all files.')
    print('3 - Finish program.')


def read_user_input() -> int:
    return int(input('Enter your option: '))


def choose_one_file() -> None:
    show_files_options()
    while True:
        chosen_file: int = read_user_input()
        if chosen_file < len(VALUES):
            process_one_file(chosen_file)
            break
        print('Please, enter a valid number.')


def show_files_options() -> None:
    print()
    print('Here are all the available files:')
    for index, aux in enumerate(VALUES):
        file_name: str = aux[0].split('/')[5]
        print('{} - {}'.format(index, file_name))


def process_one_file(chosen_file: int) -> None:
    file_name: str = VALUES[chosen_file][FILE_PATH].split('/')[1]
    with timeit_context(file_name):
        maze: [str] = map_maze(VALUES[chosen_file][FILE_PATH])
        hero: int = maze.index('A')
        villain: int = maze.index('B')
        answer: int = shortest_path(
            maze,
            VALUES[chosen_file][OFFSET],
            hero,
            villain
        )
        print()
        print(f'>>> {file_name} => {answer}')


def map_maze(file_path: str) -> [str]:
    with open(file_path, 'r') as file:
        tmp: [str] = [
            char
            for line in file
            for char in line
            if char != '\n'
        ]
    return tmp


def shortest_path(maze: [str], offset: int, source: int, destiny: int) -> int:
    queue: deque[int] = deque([source])
    next_pos: int = 0
    maze[source] = 0
    while next_pos != destiny:
        curr_pos: int = queue.popleft()
        for next_pos in (curr_pos - offset, curr_pos - 1, curr_pos + offset, curr_pos + 1):
            if maze[next_pos] != '#' and type(maze[next_pos]) is not int:
                maze[next_pos] = maze[curr_pos] + 1
                queue.append(next_pos)
    return maze[destiny]


def process_all_files() -> None:
    print('This might take a while...')
    for chosen_file in range(len(VALUES)):
        process_one_file(chosen_file)


if __name__ == '__main__':
    main()
