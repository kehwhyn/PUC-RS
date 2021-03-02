#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>

#define DATA_INIT 1000
#define FAT_SIZE 4096
#define OFFSET 0x400

#define NAME_SIZE 18
#define COMMAND_SIZE 6
#define STRING_SIZE 200
#define MAX_DIRS 32

/* ler o fat.part com
        hexdump -C fat.part
*/

/* entrada de diretorio, 32 bytes cada, FAT e diretorio com 32 entradas (1 cluster) */
struct dir_entry_s
{
        uint8_t filename[NAME_SIZE];
        uint8_t attributes : 1;
        uint8_t reserved[7];
        uint16_t first_block;
        uint32_t size;
};

struct dir_entry_s boot;

uint16_t fat[FAT_SIZE];

struct dir_entry_s root[MAX_DIRS];
struct dir_entry_s dir_entry[MAX_DIRS];

void simpleShell(void);
unsigned checkCommand(char const[STRING_SIZE]);
void init(char const[STRING_SIZE]); // inicializar o sistema
void load(char const[STRING_SIZE]); // carregar o sistema de arquivos
void ls(char const[STRING_SIZE]);   // listar diretorio
int mkdir(char const[STRING_SIZE]); // criar diretorio
int rmdir(char const[STRING_SIZE]); // excluir diretorio
void split_path(char const[STRING_SIZE], char[MAX_DIRS][NAME_SIZE], int *);
void teste_leitura();

int main()
{
        simpleShell();
        //teste_leitura();

        exit(EXIT_SUCCESS);
}

void simpleShell(void)
{
        char string[STRING_SIZE];

        unsigned running = 1;
        while (running)
        {

                printf("best_professor@simple_shell /:$ ");
                fgets(string, STRING_SIZE, stdin);

                if (strcmp(string, "\n"))
                {

                        char command[COMMAND_SIZE];
                        strcpy(command, strtok(string, " \n\0"));

                        unsigned aux = checkCommand(command);
                        if (aux)
                        {
                                char *path = strtok(NULL, " \n\0");

                                if (!strcmp(command, "init"))
                                        init(path);

                                else if (!strcmp(command, "load"))
                                        load(path);

                                else if (!strcmp(command, "ls"))
                                        ls(path);

                                else if (!strcmp(command, "mkdir"))
                                        mkdir(path);

                                else if (!strcmp(command, "rmdir"))
                                        rmdir(path);

                                else
                                        running = 0;
                        }
                        else
                        {

                                printf("%s: command not found\n", command);
                                printf("Available commands: ");
                                printf("init, load, ls, mkdir, rmdir, exit\n");
                        }
                }
        }
}

unsigned checkCommand(char const command[COMMAND_SIZE])
{
        char const commands[COMMAND_SIZE][COMMAND_SIZE] = {"init", "load", "ls", "mkdir", "rmdir", "exit"};
        for (unsigned iter = 0; iter < COMMAND_SIZE; iter++)
                if (!strcmp(command, commands[iter]))
                        return 1;
        return 0;
}

void init(char const string[STRING_SIZE])
{

        if (string != NULL)
        {
                printf("init: doesn't accept arguments\n");
                return;
        }

        FILE *fat_part = fopen("fat.part", "wb");
        if (!fat_part)
                exit(EXIT_FAILURE);

        int aux = 0xbb;
        fwrite(&aux, sizeof(char), 1, fat_part);

        aux = 0x0;
        for (int write_boot = 0; write_boot < 1023; write_boot++)
                fwrite(&aux, sizeof(char), 1, fat_part);

        aux = 0xfffd;
        fwrite(&aux, 2 * sizeof(char), 1, fat_part);
        for (int write_fat = 0; write_fat < 8; write_fat++)
        {
                aux = 0xfffe;
                fwrite(&aux, 2 * sizeof(char), 1, fat_part);
        }

        aux = 0xffff;
        fwrite(&aux, sizeof(char), 2, fat_part); //root na FAT

        aux = 0x0;
        for (int write_data_fat = 0; write_data_fat < 4085; write_data_fat++)
                fwrite(&aux, sizeof(char), 2, fat_part);

        fwrite(root, sizeof(struct dir_entry_s), 32, fat_part);

        //data clusterss
        for (int write_data = 0; write_data < 4086 * 1024; write_data++)
                fwrite(&aux, sizeof(char), 1, fat_part);

        fclose(fat_part);
}

void load(char const string[STRING_SIZE])
{

        if (string != NULL)
        {
                printf("load: doesn't accept arguments\n");
                return;
        }

        FILE *fat_part = fopen("fat.part", "rb");
        if (!fat_part)
                exit(EXIT_FAILURE);

        fseek(fat_part, 0x400, SEEK_SET);
        for (int read_fat = 0; read_fat < 4095; read_fat++)
                fread(&fat[read_fat], 1, 2, fat_part);

        //da pra trazer o root também pra memória

        fclose(fat_part);
}

void ls(char const string[STRING_SIZE])
{
        FILE *fat_part = fopen("fat.part", "rb+");

        fseek(fat_part, 0x2400, SEEK_SET);
        fread(&root, 32, 32, fat_part);
        fclose(fat_part);
        int k = 0, num_filhos;

        if (string == NULL)
        {
                k = 0;
                while (k < MAX_DIRS)
                {
                        if (root[k].first_block > 0)
                        {
                                printf("%s\t", (char *)root[k].filename);
                                num_filhos++;
                        }
                        k++;
                }
                if (num_filhos > 0)
                        printf("\n");
                //carrega o root do fat.part
                //itera sobre ele
                //printa os diretórios, se existirem
                return;
        }

        char path[MAX_DIRS][NAME_SIZE];
        int count = 0;
        split_path(string, path, &count);

        for (int i = 0; i < count; i++)
        {

                for (int j = 0; j < MAX_DIRS; j++)
                {

                        if (!strcmp(path[i], (char *)root[j].filename))
                        {

                                uint16_t aux = root[j].first_block;
                                if (aux != 0xFFFF)
                                {
                                        //tenho subdirs
                                        //carrego meus subdirs
                                        int endereco = fat[aux];
                                        FILE *fat_part = fopen("fat.part", "rb+");

                                        fseek(fat_part, endereco, SEEK_SET);
                                        fread(&root, 32, 32, fat_part);
                                        fclose(fat_part);
                                        //tenho subdirs
                                        //aux contém o índice para o bloco da FAT que contém o endereço do subdir
                                        //carrego meus subdir da memória -> criar função para isso
                                        //reseto o valor de j
                                        j = 0;

                                        if (count - 1 == i)
                                        {
                                                //sou o fim da string
                                                //printo o nome dos meus subdirs (se o nome deles forem != NULL?)
                                                //dou um break

                                                k = 0;
                                                while (k < MAX_DIRS)
                                                {
                                                        if (root[k].first_block > 0)
                                                        {
                                                                printf("%s\t", (char *)root[k].filename);
                                                                num_filhos++;
                                                        }
                                                        k++;
                                                }
                                                if (num_filhos > 0)
                                                        printf("\n");
                                                break;
                                        }
                                }
                        }
                        else if (count - 1 == i && j == MAX_DIRS - 1)
                        {
                                printf("%s: Diretório não encontrado\n", path[i]);
                        }
                }
                if (count - 1 == i)
                        break;
        }
}

int mkdir(char const string[STRING_SIZE])
{

        if (string == NULL)
        {
                printf("mkdir: needs arguments\n");
                return 0;
        }

        char path[MAX_DIRS][NAME_SIZE];
        int count = 0, ult = 0, j = 0;
        split_path(string, path, &count);
        //tenho subdirs
        //carrego meus subdirs
        FILE *fat_part = fopen("fat.part", "rb+");

        fseek(fat_part, 0x2400, SEEK_SET);
        fread(&root, 32, 32, fat_part);
        fclose(fat_part);
        for (int i = 0; i < count; i++)
        {
                for (int j = 0; j < MAX_DIRS; j++)
                {
                        if (!strcmp(path[i], (char *)root[j].filename))
                        {
                                if (count - 1 == i)
                                {
                                        printf("Diretório já existe!\n");
                                        return 0;
                                }

                                uint16_t aux = root[j].first_block;
                                int aux2 = fat[aux];

                                if (aux2 != 0xFFFF && strcmp(path[count - 2], (char *)root[j].filename))
                                {
                                        //tenho subdirs
                                        //carrego meus subdirs
                                        int endereco = fat[aux];
                                        FILE *fat_part = fopen("fat.part", "rb+");

                                        fseek(fat_part, endereco, SEEK_SET);
                                        fread(&root, 32, 32, fat_part);
                                        fclose(fat_part);
                                }
                        }
                }

                if ((count - 1 == i) && (i > 0))
                {

                        ult = 0;
                        for (j = 0; j < MAX_DIRS; j++)
                        {
                                if (!strcmp(path[i - 1], (char *)root[j].filename))
                                {
                                        ult = j;
                                        break;
                                }
                        }
                        if (ult == 0 && j == MAX_DIRS)
                        {
                                printf("Impossível criar diretório\n");
                                break; //nao deveria estar escrevendo
                        }
                        //cria filho
                        int k;

                        uint16_t aux = root[ult].first_block;
                        //tenho subdirs
                        //carrego meus subdirs
                        int endereco = fat[aux];
                        FILE *fat_part = fopen("fat.part", "rb+");

                        fseek(fat_part, endereco, SEEK_SET);
                        fread(&dir_entry, 32, 32, fat_part);
                        fclose(fat_part);

                        for (int j = 0; j < MAX_DIRS; j++)
                        {
                                if (dir_entry[j].first_block == 0)
                                {
                                        strcpy((char *)dir_entry[j].filename, path[i]);
                                        dir_entry[j].attributes = 1;
                                        for (k = 10; k < FAT_SIZE; k++)
                                                if (fat[k] == 0)
                                                {
                                                        fat[k] = 0xFFFF;
                                                        if (j == 0)
                                                                fat[root[ult].first_block] = (k - 11) * OFFSET + 0x2800;

                                                        dir_entry[j].first_block = k;
                                                        break;
                                                }

                                        FILE *fat_part = fopen("fat.part", "rb+");

                                        fseek(fat_part, 0x400, SEEK_SET);
                                        fwrite(fat, 2, FAT_SIZE, fat_part);

                                        fseek(fat_part, fat[root[ult].first_block], SEEK_SET);
                                        fwrite(dir_entry, 32, 32, fat_part);

                                        fclose(fat_part);
                                        break;
                                }
                        }
                }

                if (count - 1 == i && i == 0)
                {

                        for (int j = 0; j < MAX_DIRS; j++)
                        {
                                if (root[j].first_block == 0)
                                {
                                        ult = j;
                                        strcpy((char *)root[j].filename, path[i]);
                                        root[j].attributes = 1;
                                        for (int k = 10; k < FAT_SIZE; k++)
                                                if (fat[k] == 0)
                                                {
                                                        fat[k] = 0xFFFF;
                                                        root[j].first_block = k;
                                                        break;
                                                }
                                        FILE *fat_part = fopen("fat.part", "rb+");

                                        fseek(fat_part, 0x400, SEEK_SET);
                                        fwrite(fat, 2, FAT_SIZE, fat_part);

                                        fwrite(root, 32, 32, fat_part);

                                        fclose(fat_part);
                                        break;
                                }
                        }
                }
        }

        memset(dir_entry, 0, sizeof(struct dir_entry_s));
        memset(root, 0, sizeof(struct dir_entry_s));
        return 0;
}

void teste_leitura()
{
        uint16_t aux = 0x27FE - 0x400;
        int endereco = fat[aux];
        FILE *fat_part = fopen("fat.part", "rb");
        fseek(fat_part, aux, SEEK_SET);
        fread(&dir_entry, 32, 32, fat_part);
        printf("%s", dir_entry[0].filename);
}

int rmdir(char const string[STRING_SIZE])
{

        if (string == NULL)
        {
                printf("rmdir: need arguments\n");
                return 0;
        }

        char path[MAX_DIRS][NAME_SIZE];
        int count = 0, fat_aux1, fat_aux2, num_filhos = 0;
        ;
        split_path(string, path, &count);
        FILE *fat_part = fopen("fat.part", "rb+");

        fseek(fat_part, 0x2400, SEEK_SET);
        fread(&root, 32, 32, fat_part);
        fclose(fat_part);
        for (int i = 0; i < count; i++)
        {

                memcpy(&dir_entry, &root, 32 * sizeof(struct dir_entry_s));
                for (int j = 0; j < MAX_DIRS; j++)
                {

                        if (!strcmp(path[i], (char *)root[j].filename))
                        {

                                uint16_t aux = root[j].first_block;

                                int endereco = fat[aux];
                                if (!strcmp(path[count - 1], (char *)root[j].filename))
                                {
                                        num_filhos = 0;
                                        for (int k = 0; k < MAX_DIRS; k++)
                                        {
                                                if (root[k].first_block != 0 && root[k].attributes != 0)
                                                {

                                                        num_filhos++;
                                                }
                                        }
                                }
                                if (endereco != 0xFFFF)
                                {
                                        fat_aux1 = root[j].first_block; //salvar endereco do pai
                                        //tenho subdirs
                                        //carrego meus subdirs
                                        int endereco = fat[aux];
                                        FILE *fat_part = fopen("fat.part", "rb+");

                                        fseek(fat_part, endereco, SEEK_SET);
                                        fread(&root, 32, 32, fat_part);
                                        fclose(fat_part);
                                        if (count - 1 == i)
                                        {
                                                //sou o final da string
                                                //não posso remover
                                                printf("rmdir: falhou em remover '%s/': Diretório não vazio.\n", path[i]);
                                                break;
                                        }
                                }
                                else
                                {
                                        FILE *fat_part = fopen("fat.part", "rb+");
                                        fat_aux2 = dir_entry[j].first_block; //pra nao ser jogado no lixo
                                        if (fat_aux1 < 10 || count == 1)
                                        {
                                                fseek(fat_part, 0x2400, SEEK_SET);
                                        }
                                        else
                                        {
                                                fseek(fat_part, fat[fat_aux1], SEEK_SET);
                                        }
                                        for (int n = 0; n < MAX_DIRS; n++)
                                        {
                                                if (!strcmp(path[i], (char *)dir_entry[n].filename))
                                                {
                                                        memset(dir_entry[n].filename, 0, NAME_SIZE);
                                                        dir_entry[n].first_block = 0;
                                                        dir_entry[n].attributes = 0;
                                                }
                                        }
                                        fwrite(dir_entry, 32, 32, fat_part);

                                        fseek(fat_part, 0x400, SEEK_SET);

                                        if (num_filhos < 2 && fat_aux1 > 9)
                                        {
                                                fat[fat_aux1] = 0xFFFF; //pai vira folha
                                        }
                                        if (fat_aux2 > 9)
                                                fat[fat_aux2] = 0x0;
                                        fwrite(fat, 2, FAT_SIZE, fat_part);

                                        fclose(fat_part);

                                        memset(dir_entry, 0, sizeof(struct dir_entry_s));
                                        memset(root, 0, sizeof(struct dir_entry_s));
                                        return 0;
                                }
                        }
                        else if (count - 1 == i && j == MAX_DIRS - 1)
                                printf("%s: Diretório não encontrado\n", path[i]);
                }
        }

        memset(dir_entry, 0, sizeof(struct dir_entry_s));
        memset(root, 0, sizeof(struct dir_entry_s));
        return 0;
}

void split_path(char const string[STRING_SIZE], char path[MAX_DIRS][NAME_SIZE], int *count)
{
        int aux = 0;

        char *directory = strtok((char *)string, "/");
        strcpy(path[aux++], string);
        while (directory = strtok(NULL, "/"))
                strcpy(path[aux++], directory);

        *count = aux;
}