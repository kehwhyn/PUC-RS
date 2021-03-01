# De A até B já é bem longe

Um estúdio de filmes de franquia de super-herói resolveu inovar: no seu próximo  filme haverá um gigantesco labirinto onde os heróis e vilões terão que achar uns aos outros e combater-se até... bom, até o filme acabar. Esse é todo o roteiro.

Agora o estúdio está preocupado com o projeto do labirinto e já que você é um dos três especialistas mundiais em labirintos para filmes, acaba de ser contratado para fazer alguns estudos preliminares. Basicamente, seu problema começa quando você recebe projetos de labirinto contendo os seguintes símbolos:

```
# representa uma parede impenetrável; 
. representa espaço livre; 
A representa um herói genérico que se move para cima, para baixo, esquerda e direita mas não nas diagonais; 
B representa um vilão genérico que está parado fazendo seu plano maligno. 
```

Um exemplo de labirinto está abaixo. Depois de receber os dados você deve descobrir o comprimento do caminho mais curto do herói até o vilão. Isso é uma estimativa de quanto tempo o herói vai levar para chegar lá e é importante para equilibrar a etapa de perseguição e a etapa de combates mortais. Neste exemplo, descobrimos que o herói precisa de pelo menos 49 movimentos para chegar até o vilão.

```
######################################## #..#.....#..........#........#....#....# #..#.....#...................#....#....# #........#....#.....#........#....#....# #........#....#.....####..####.........# #..#.....##.######.##........#....#....# #.###.####...#......#........#.........# #........#...#......#........#....#....# #............#......#........#....#....# #..#.....#...#......#........########.## #..#.....#..........#........#..#......# #..#................#........#..#......# #..#.....#...#......##..#####...#......# ###############.##.##........#.......B.# #............#......#...#....#.........# #...A........#......#...#....#..#......# #......#.....##..####........#..#......# #......#.....#......#...#....#..#......# ###..#########......####.#####.######### #............#......#..........#.......# #....#.......#......#......#...........# #....#.........#.####......#...#.......# #....#.......#.............#.....###.### #....#..............#..........#...#...# #....#.......#......#.###.##...#...#...# #....#.......#.##.###......#...#.......# #....#.......#......#......#...#.......# #............#......#......#...#...#...# #....#.......#......#......#...#...#...# ######################################## 
```

Claro, a vida em Hollywood não é sempre tão simples, e depois de ser contra tado você descobre que o estúdio está
planejando labirintos de tamanhos que vão até 20000×20000. Enfim, agora você não pode mais recusar.

Sua tarefa é receber os casos de teste (que o estúdio vai colocar na página da disciplina) e resolver o problema para cada um deles. Ao final você deve apresentar um relatório descrevendo: 
* Qual o problema sendo resolvido; 
* Como o problema foi modelado; 
* Como é o processo de solução, apresentando exemplos e algoritmos; 
* Os resultados dos casos de teste; 
* Conclusões.
