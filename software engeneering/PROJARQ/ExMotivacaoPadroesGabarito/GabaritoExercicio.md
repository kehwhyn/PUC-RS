# Gabarito do exercício

## Enunciado do exercício

Analise as classes, Carro, Motor e TanqueCombustivel. Verifique a lista de
solicitacoes e apresente o código modificado de maneira a atender a todas.
Apresente também um relatório que discuta o impacto das modificações propostas e
se as classes originais poderiam ter sido pensadas de outra forma para dar conta
melhor de eventuais modificações.

Para cada uma das solicitações que seguem crie uma instancia do modelo respectivo, 
abasteça, faça uma viagem e chame o método "toString" para exibir a situação final.

## Questões
1) Crie um novo modelo de carro chamado "Esportivo". Este é movido a gasolina,
   consome 6 Km/lt e o tanque tem capacidade para 45 litros.

   - para resolver esta questão basta criar uma nova instancia passando os parâmetros corretos

2) Crie um novo modelo de carro chamado "Utilitario". Este é movido a diesel,
   consome 5 Km/lt e o tanque tem capacidade para 70 litros.

   - para resolver esta questão basta criar uma constante a mais na enumeração "TipoCombustivel" e criar uma nova instancia passando os parâmetros corretos

3) Crie um novo modelo de carro chamado "SUV". Este é movido a gasolina, consome
   8 Km/lt, o tanque tem capacidade para 55 litros e é "FLEX" porque já prevê
   uma futura modificação nos motores.

   - para resolver esta questão será necessário alterar o construtor da classe Carro porque o tipo de combustivel do tanque "FLEX" é diferente do motor "gasolina". Note que se seguirmos este caminho, porém, a alteração terá impacto nas outras instâncias já criadas. Ou então usamos sobrecarga no construtor de "Carro", mas neste caso será necessária documentação adicional.

4) Crie um novo modelo de carro chamado "SUVFlex". Este é FLEX (gasolina ou
   alcool) e consome 8 Km/lt de gasolina ou 6 Km/lt de alcool. O tanque é "FLEX"
   e tem 65 litros de capacidade. Por simplicidade assuma que o conteúdo do tanque
   é sempre o do último abastecimento.

   - Note que se usamos o mesmo tipo de solução que a da questão 3 começaremos a ter cada vez mais parâmetros no construtor de carro ou cada vez mais versões de construtor. Isso exige mais documentação e a lógica interna da classe fica cada vez mais complicada.

5) Crie um novo modelo de carro chamado "Econo". É movido a gasolina, tanque tem
   capacidade para 55 litros e caracterizam-se por ser extremamente econômicos.
   O consumo deste quando novo é de 20 Km/lt, porém este consumo reduz 1 Km/lt a
   cada 5000 Km até que se estabiliza em 10 Km/lt.

   - Este caso fica ainda mais complicado, porque tem um motor cujo comportamento varia com a quilometragem. Neste caso podemos colocar um "if" no motor dependendo do um parâmetro que indique se é "Econo" ou criar uma classe específica para este tipo de motor derivada da classe Motor original e colocar o "if" no construtor de Carro.

Note que as soluções apresentadas vão tornando o código cada vez mais complexo. Essa complexidade é decorrente da forte dependência da classe Carro para com Motor e TanqueCombustivel. Além disso note que essas dependências estão "escondidas" dentro da classe Carro. Quem usa Carro nem sabe da existência de Motor e Tanque de Combustivel. Essa é a razão que nos leva a ter de alterar o construtor de Carro toda a vez que Motor ou TanqueDeCombustivel sofrem alteracoes. 

Uma maneira de tornar essas dependências explícitas é fazer com que elas sejam "injetadas" pelo construtor. Injeção de dependência é um padrão de projeto (veremos mais adiante) muito usado para tornar 
as dependências de uma classe explícitas. Isso facilita a configuração das classes das quais se depende. Melhor ainda é se as dependências forem de interfaces e não de classes. Neste caso é possível ter diferentes implementações para as dependências sem que seja necessário alterar a classe dependente. No caso poderemos ter diferentes implementações para Motor e TanqueCombustivel sem ter de alterar a classe Carro.
