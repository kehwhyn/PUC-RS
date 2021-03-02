# Gerenciador de Filmes

## 1. Introdução

O objetivo deste trabalho é praticar a programação utilizando a linguagem C++. Este trabalho, que deverá ser feito **individualmente ou em duplas**, consiste em modelar e implementar um sistema para selecionar elencos para filmes de Hollywood, além de conter um histórico dos filmes realizados, contendo 3 estruturas principais: `atores`, `diretores` e `filmes`.

## 2. Detalhes técnicos

Cada estrutura citada acima deve ser implementada como uma classe. A seguir serão detalhadas essas classes:

### 2.1. Atores

Um **ator(a)** é definido por um nome. A ele será atribuído uma lista de filmes em que ele já atuou ou esta selecionado para atuar. Deve ser possível realizar as seguintes operações nesta classe:

* Construtor, que recebe o nome do autor;
* Obter o nome do ator.

Obs.: cada ator pode ter atuado ou poderá atuar em um ou mais filmes.

### 2.2 Diretores

Um **diretor(a)** é definido por um nome. A ele será atribuído uma lista de filmes em que ele já dirigiu ou esta selecionado para dirigir. Deve ser possível realizar as seguintes operações nesta classe:

* Construtor, que recebe o nome do diretor;
* Obter o nome do diretor.

### 2.3 Filmes

Um **filme** consiste em um nome e um ano de lançamento, além disso, deve ser cadastrado um 1 diretor e pelo menos 1 estrela (ator) em seu elenco. Deve ser possível realizar as seguintes operações nesta classe:

* Construtor, que recebe o nome e o ano de lançamento;
* Alterar (adicionar/remover) o diretor do filme;
* Alterar (adicionar/remover) atores ao elenco do filme;
* Obter o nome do filme;
* Obter o ano de lançamento do filme;
* Obter o diretor do filme;
* Obter a lista de atores que fazem parte do elenco do filme;
* Obter o número de atores escalados para atuarem no filme;
* Pesquisar se um ator faz parte do elenco daquele filme;
* Obter as informações do filme concentrados em um método (`toString()`).

Para adicionar um ator ou diretor ao filme, deverá ser criado um método que receba o objeto pertinente (Ator ou Diretor) **já criado e inicializado externamente**, ou seja, não devem ser recebidos os dados dos atores e do diretor individualmente. Por exemplo:

```c
void Filme::adicionarAtor (Ator novo) { ... }
```

Para armazenar os atores, pode ser utilizado um vetor de Atores, bastando controlar quantos já foram criados - para este trabalho, limitaremos o vetor em **10 atores**.

## Funcionamento do programa

A lista de filmes pode ser armazenada em vetores criados no programa principal. O sistema deverá então oferecer três opções para consulta dos dados:

### 3.1 Consulta de Atores

Dado o nome de um ator (a), o sistema deverá mostrar todos os filmes em que este faça parte do elenco. Essa listagem deve ser feita em forma de uma tabela, exibindo o ano de lançamento e o nome do filme. Exemplo:

```
Ator: Scarlett Johansson
Ano Filme
2008 Vicky Cristina Barcelona
2006 Scoop - O Grande Furo
2006 O Grande Truque
2005 Match Point - Ponto Final
```

### 3.2 Consulta de Diretores

Dado o nome de um diretor (a), o sistema deverá mostrar todos os filmes em que este tenha dirigido. Essa listagem deve ser feita em forma de uma tabela, exibindo o ano de lançamento e o nome do filme. Exemplo:

```
Diretor: Woody Allen
Ano Filme
2015 O Homem Irracional
2014 Magia ao Luar
2011 Meia-Noite em Paris
2010 Você Vai Conhecer o Homem dos Seus Sonhos
2008 Vicky Cristina Barcelona
2006 Scoop - O Grande Furo
2005 Match Point - Ponto Final
1998 Celebridades
1997 Desconstruindo Harry
1996 Todos Dizem Eu Te Amo
```

### 3.3 Consulta de Filmes

Dado o nome do filme, o sistema deverá mostrar as informações pertinentes a tal. Também deve ser apresentada em forma de tabela, contendo o nome do filme, ano de lançamento, diretor(a) e o nome dos atores do elenco. Por exemplo:

```
Filme: O Resgate do Soldado Ryan (1998)
Diretor: Steven Spielberg
Atores: Tom Hanks
Matt Damon
Vin Diesel
```

### 3.4. Cadastro de um novo filme

O usuário pode também cadastrar um novo filme. Ele deverá indicar somente nome, pois o ano deverá ser incluído automaticamente de acordo com o ano em que o programa esta sendo usado. Para isso, utilizar a biblioteca “`ctime`”. Na sequência do cadastro, deverá ser indicado qual o diretor e os atores que fazem parte do novo filme.

Obs.: Não deverá ser criado nenhum novo ator (a) ou diretor (a), eles devem ser usados do cadastro que já esta nos arquivos.

### 3.5. Armazenamento em arquivos

Como os filmes já existem, além dos diretores e atores, no começo do programa devemos carregar o sistema através da leitura de dois arquivos texto (“`BD_Atores.txt`” e “`BD_Diretores.txt`”) contendo os atores e diretores já cadastrados no sistema com seus respectivos filmes. Ao final da execução do programa, devemos atualizar os dois arquivos para que se considere as alterações feitas durante a execução do programa (adição de novos filmes). Os arquivos têm a seguinte estrutura:

![modelo_leitura](assets/modelo_estrutura.png)

Onde “`#`” é o separador entre atores/diretores. E “`--`” significa que aquela linha contém o nome
e ano de lançamento de um filme que faz parte do ator/diretor corrente.

## 4. Avaliação

Devem ser criadas as classes `Atores`, `Diretores` e `Filmes`. A criação de classes adicionais, além de outros métodos que julgar necessário, fica dependente da sua implementação - um dos objetivos deste trabalho é a modelagem adequada dos atributos e métodos dessas classes.

O código-fonte deve estar adequadamente comentado (nas suas partes/algoritmos principais, pelo menos) e no seu início deve haver um comentário com o nome do (s) aluno (s).

Leia com atenção os critérios de avaliação:
* Implementação adequada da lógica.
* Leitura apropriada das entradas do usuário e apresentação das informações na forma de texto no terminal.
* O trabalho terá entre **1** e **2** componentes. Os arquivos contendo o código-fonte (.cpp) devem ser compactados e submetidos pelo Moodle até a data e hora especificadas. O arquivo compactado deve ter os nomes e os últimos sobrenomes dos alunos, da seguinte forma: `nome1_sobrenome1_nome2_sobrenome2.zip`
* O código-fonte deve estar identado e comentado adequadamente.
* **O programa deverá ser executado no sistema operacional Linux. Não serão aceitos trabalhos que não compilem corretamente no g++, sob hipótese nenhuma.**
* **Deve OBRIGATORIAMENTE conter** um ***Makefile***, para facilitar a compilação do programa.
* A nota do trabalho depende da apresentação deste no laboratório, na data marcada. Trabalhos entregues, mas não apresentados terão sua nota anulada automaticamente. Durante a apresentação, **todos os alunos** devem estar presentes e aptos a responder quaisquer perguntas. Respostas insatisfatórias ou a ausência do aluno acarretarão em **anulação** da nota do trabalho.
* **A cópia parcial ou completa do trabalho terá como consequência a atribuição de nota ZERO ao trabalho dos alunos envolvidos**.