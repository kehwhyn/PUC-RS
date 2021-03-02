# Definição do Primeiro Trabalho

O primeiro trabalho consiste no desenvolvimento de um jogo de perguntas e respostas sobre o conteúdo visto até o momento da disciplina de Redes. O trabalho deve seguir a arquitetura cliente/servidor e utilizar sockets para a comunicação.

O grupo deverá definir as regras do jogo como, por exemplo:
* definir o número de participantes;
* dar pontos para o jogador a medida que ele acerte respostas;
* ter níveis diferentes de dificuldade;
* seguir um caminho;
* entre outras.

Ao fim do jogo, a conexão de rede deve ser desfeita, de forma que seja possível voltar para o menu do jogo e jogar novamente.

O jogo deve ser implementado com UDP e caso uma resposta não seja recebida o servidor deverá enviá-la novamente. Para que essa funcionalidade seja testada será necessário fazer com que a placa de rede perca pacotes, utilizando o *netem* para Linux ou *clumsy* para Windows.

Cada grupo deverá definir as regras do jogo em um documento que será entregue no dia da apresentação do trabalho. Este documento também deve descrever como é feita a troca de mensagens entre cliente e servidor (descrever os tipos de mensagens definidos). Além disso, será necessário explicar como o jogo foi implementado.

Seja criativo no seu jogo e garanta que ele funciona corretamente, sem bugs! Além disso, preze pela jogabilidade, dando informações aos usuários sempre que possível e necessário.

## Resultados e Entrega

**Grupos**: até 3 integrantes.

**Entrega e Apresentações**: 18 de maio

**Entrega final:**
* Texto, com no mínimo 4 páginas, descrevendo as regras e funcionamento do jogo e como o mesmo foi implementado, envolvendo estruturas de dados, threads, classes principais, mecanismos de sincronização utilizados, formato das mensagens trocadas, etc.
* Código fonte comentado

**Apresentação:**
* **A apresentação será feita no Zoom**
* **Todos** participantes devem estar presentes e prontos para responder as perguntas.

**IMPORTANTE:** Não serão aceitos trabalhos entregues fora do prazo. Trabalhos que não compilam ou que não executam não serão avaliados. Todos os trabalhos serão analisados e comparados. Caso seja identificada cópia de trabalhos, todos os trabalhos envolvidos receberão nota ZERO.