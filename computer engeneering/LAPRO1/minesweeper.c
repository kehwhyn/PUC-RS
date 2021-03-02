#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

int found_mines = 0;

struct entry
{
    char symbol;
    int value;
};

void position_mines(
    struct entry *const board, int const row,
    int const required_mines)
{
    srand(time(NULL));

    int created_mines = 0;
    while (created_mines < required_mines)
    {

        int const entry = rand() % (row * row);

        if (board[entry].value != -1)
        {
            board[entry].value = -1;
            created_mines++;
        }
    }
}

int read_entry(
    const struct entry *const board, int const entry)
{
    if (board[entry].value == -1)
        return -1;

    if (board[entry].value == 0)
        return 0;

    return 1;
}

int read_top_left(const int row, const int column)
{
    return row - column - 1 >= 0 && (row - 1) % column != column - 1;
}

int read_top(const int row, const int column)
{
    return row - column >= 0;
}

int read_top_right(const int row, const int column)
{
    return row - column + 1 >= 0 && (row + 1) % column != 0;
}

int read_left(const int row, const int column)
{
    return row - 1 >= 0 && (row - 1) % column != column - 1;
}

int read_right(const int row, const int column)
{
    return row + 1 < column * column && (row + 1) % column != 0;
}

int read_bottom_left(const int row, const int column)
{
    return row + column - 1 < column * column && (row - 1) % column != column - 1;
}

int read_bottom(const int row, const int column)
{
    return row + column < column * column;
}

int read_bottom_right(const int row, const int column)
{
    return row + column + 1 < column * column && (row + 1) % column != 0;
}

int read_around_entry(
    const struct entry *const board, const int column,
    const int row, const int position)
{
    if (position == 0 && read_top_left(row, column))
        return read_entry(board, row - column - 1);

    if (position == 1 && read_top(row, column))
        return read_entry(board, row - column);

    if (position == 2 && read_top_right(row, column))
        return read_entry(board, row - column + 1);

    if (position == 3 && read_left(row, column))
        return read_entry(board, row - 1);

    if (position == 4 && read_right(row, column))
        return read_entry(board, row + 1);

    if (position == 5 && read_bottom_left(row, column))
        return read_entry(board, row + column - 1);

    if (position == 6 && read_bottom(row, column))
        return read_entry(board, row + column);

    if (position == 7 && read_bottom_right(row, column))
        return read_entry(board, row + column + 1);
}

int count_mines_around_entry(
    const struct entry *const board, const int column,
    const int entry)
{
    int mines = 0;
    for (int position = 0; position < 8; position++)
        if (read_around_entry(board, column, entry, position) == -1)
            mines++;
    return mines;
}

void calculate_entry_value(
    struct entry *const board, const int column)
{
    for (int entry = 0; entry < column * column; entry++)
        if (board[entry].value == 0)
            board[entry].value = count_mines_around_entry(
                board, column, entry);
}

void print_board_debug(
    struct entry *board, int const column)
{
    printf("    ");
    for (int i = 0; i < column; i++)
        printf(" %2d", i);

    printf("\n   ----");
    for (int i = 1; i < column; i++)
        printf("---");
    printf("\n");

    for (
        int i = 0, entries = column * column;
        entries; entries--)
    {
        if (i % column == 0)
            printf("%2d |", i / column);

        printf(" %2d", board->value);

        board++;

        if (++i % column == 0)
            printf("\n");
    }
    printf("\n");
}

void print_board(struct entry *board, int const column)
{

    printf("    ");
    for (int i = 0; i < column; i++)
        printf(" %2d", i);

    printf("\n   ----");
    for (int i = 1; i < column; i++)
        printf("---");
    printf("\n");

    for (
        int i = 0, entries = column * column;
        entries; entries--)
    {
        if (i % column == 0)
            printf("%2d |", i / column);

        printf(" %2c", board->symbol);

        board++;

        if (++i % column == 0)
            printf("\n");
    }
    printf("\n");
}

void show_blank(
    struct entry *const board, const int column,
    const int row, const int position)
{
    if (position == 0 && read_top_left(row, column))
    {
        if (read_entry(board, row - column - 1) == 0)
            board[row - column - 1].symbol = ' ';
    }
    else if (position == 1 && read_top(row, column))
    {
        if (read_entry(board, row - column) == 0)
            board[row - column].symbol = ' ';
    }
    else if (position == 2 && read_top_right(row, column))
    {
        if (read_entry(board, row - column + 1) == 0)
            board[row - column + 1].symbol = ' ';
    }
    else if (position == 3 && read_left(row, column))
    {
        if (read_entry(board, row - 1) == 0)
            board[row - 1].symbol = ' ';
    }
    else if (position == 4 && read_right(row, column))
    {
        if (read_entry(board, row + 1) == 0)
            board[row + 1].symbol = ' ';
    }
    else if (position == 5 && read_bottom_left(row, column))
    {
        if (read_entry(board, row + column - 1) == 0)
            board[row + column - 1].symbol = ' ';
    }
    else if (position == 6 && read_bottom(row, column))
    {
        if (read_entry(board, row + column) == 0)
            board[row + column].symbol = ' ';
    }
    else if (position == 7 && read_bottom_right(row, column))
        if (read_entry(board, row + column + 1) == 0)
            board[row + column + 1].symbol = ' ';
}

void check_blanks(
    struct entry *const board, const int column,
    const int row)
{
    for (int position = 0; position < 8; position++)
        show_blank(board, column, row, position);
}

void expand_blanks(struct entry *const board, int const column)
{
    for (int i = 0; i < column / 2; i++)
        for (int entry = 0; entry < column * column; entry++)
            if (board[entry].symbol == ' ')
                if (read_entry(board, entry) != -1)
                    check_blanks(board, column, entry);
}

void open_entry(
    struct entry *const board, const int column)
{
    int x, y;
    printf("Enter row and column (e.g.: 8 8): ");
    scanf("%d %d", &x, &y);

    if (x >= column || y >= column)
    {
        printf("There is no such coordinate!\n");
        return;
    }
    else
        printf("\nCoordinate: (%d,%d).\n", x, y);

    // (entry - y) / row == x
    const int entry = x * column + y;
    if (board[entry].symbol == 'B')
    {
        printf("Uncheck to check.\n");
        return;
    }

    if (board[entry].symbol != '#')
    {
        printf("Already opened!\n");
        return;
    }

    if (board[entry].value == -1)
        board[entry].symbol = '*';

    else if (board[entry].value == 0)
    {
        board[entry].symbol = ' ';
        expand_blanks(board, column);
    }
    else
    {
        char aux[2];
        sprintf(aux, "%d", board[entry].value);
        board[entry].symbol = aux[0];
    }
}

int check_if_game_ended(
    struct entry const *const board,
    const int column, const int total_mines)
{
    for (int entry = 0; entry < column * column; entry++)
        if (board[entry].symbol == '*')
            return 0;

    if (found_mines == total_mines)
        return 0;

    return 1;
}

void check_square(
    struct entry *const board, const int column)
{
    int x, y;
    printf("Enter row and column to (un)check (e.g: 8 8): ");
    scanf("%d %d", &x, &y);

    const int entry = x * column + y;
    if (board[entry].symbol != 'B')
    {
        board[entry].symbol = 'B';
        if (board[entry].value == -1)
            found_mines++;
    }
    else
    {
        board[entry].symbol = '#';
        if (board[entry].value == -1)
            found_mines--;
    }
}

void lista_pontuacao()
{
    FILE *const file = fopen("points.txt", "r");

    if (file == NULL)
        printf("Couldn't open score history.\n");

    else
    {
        double score = 0;
        char player[20];
        int i = 0;
        while (
            fscanf(file, "%s - %lf", player, &score) != EOF)
            printf("%d - %s - %.2lf\n", ++i, player, score);
    }
    printf("\n");
    fclose(file);
}

int play(
    struct entry *const board, int const column,
    int const total_mines)
{
    char input = ' ';
    while (
        input != 'o' && input != 'c' &&
        input != 's' && input != 'e')
    {
        printf("\
        \n(o) - Open square;\
        \n(c) - (Un)Check square as a mine;\
        \n(s) - Show score;\
        \n(e) - Exit.\
        \nEnter your choice: ");
        scanf("%c", &input);
    }

    if (input == 'o')
    {
        printf("\n");
        open_entry(board, column);
        return check_if_game_ended(board, column, total_mines);
    }
    else if (input == 'c')
    {
        printf("\n");
        check_square(board, column);
        return 1;
    }
    else if (input == 's')
    {
        printf("\n");
        lista_pontuacao();
        return 1;
    }
    else
    {
        printf("\n");
        return 0;
    }
}

double generate_score(
    const struct entry *const board,
    const int column, const int total_mines)
{
    double valid_entries = 0;

    for (int entry = 0; entry < column * column; entry++)
        if (board[entry].symbol != '#' &&
            board[entry].symbol != '*' &&
            board[entry].symbol != ' ')
            valid_entries++;

    const double pct_entries = valid_entries / (column * column - total_mines);

    return column * column * total_mines * pct_entries;
}

void save_score(
    char const player[20], double const score)
{
    FILE *const file = fopen("points.txt", "a");

    if (file == NULL)
        printf("Couldn't open file.\n");

    fprintf(file, "%s - %.2lf\n", player, score);

    fclose(file);
}

int main(const int argc, const char *const argv[])
{
    if (argc < 4)
    {
        printf(" \
            \nInvalid number of arguments! \
            \nPlease enter: \
            \n- Player name; \
            \n- Board size;\
            \n- Number of mines.\
            \n>>> e.g.: gcc minesweeper_cli.c && ./a.out Kevin 20 30\
            \n\nRun it again.\n");
        return 0;
    }
    else
        printf("\n \
        \nProgram name: %s.\nPlayer: %s. \
        \nBoard size: %sx%s. \
        \nMines: %s.\n\n",
               argv[0], argv[1], argv[2], argv[2], argv[3]);

    char player[20];
    strcpy(player, argv[1]);
    int const column = atoi(argv[2]);
    int const required_mines = atoi(argv[3]);

    struct entry *board = malloc(
        column * column * sizeof(struct entry));
    for (int i = 0; i < column * column; i++)
    {
        board[i].symbol = '#';
        board[i].value = 0;
    }

    position_mines(board, column, required_mines);

    calculate_entry_value(board, column);

    if (argc == 5 && !strcmp("DEBUG", argv[4]))
        print_board_debug(board, column);

    do
    {
        print_board(board, column);
    } while (play(board, column, required_mines));

    print_board(board, column);

    double const score = generate_score(board, column, required_mines);

    save_score(player, score);

    lista_pontuacao();

    board = NULL;
    free(board);

    return 0;
}