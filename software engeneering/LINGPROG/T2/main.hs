{-
  Nomes:
   - Jessica Freua
   - Jonatas Vangroll Lemos
   - Kevin Boucinha
   - Marcelo Azevedo
-}

import Data.List
import Data.Char

-- Exercicio 1: Ordena em ordem ascendente uma lista de listas a partir do tamanho das sublistas
mySort :: [[a]] -> [[a]]
mySort = sortOn length



-- Exercicio 2: Aplica de forma alternada duas funções passadas como argumentos aos elementos de uma lista
myMap :: (a -> b) -> (a -> b) -> [a] -> [b]
myMap func1 func2 (x : y : xs) = func1 x : func2 y : myMap func1 func2 xs



-- Exercicio 3: A partir da função myMap, defina um função luhn :: [Int] -> Bool que implemente o algoritmo de Luhn para a validações de números de cartão de crédito para códigos de cartão de qualquer tamanho. Mais informações sobre o algoritmo de Luhn podem ser encontradas na aula #6 do dia 12/05.
-- A entrada é um array de inteiros e retorna um boolean (true quando o num é válido e false quando não é)
luhn :: [Int] -> Bool
-- faz a composição das funções
luhn = luhn' . reverse

luhn' :: [Int] -> Bool
-- para entrada de tamanho 0 ou 1
luhn' xs | length xs <= 1 = False
         | otherwise = sum (myMap id luhnDouble xs) `mod` 10 == 0

luhnDouble :: Int -> Int
luhnDouble x = if (2 * x) > 9
    then (2 * x) - 9
    else 2 * x



-- Exercício 4: Construa um programa em Haskell capaz de converter um número octal (na forma forma de string) em um número decimal. Trate uma entrada inválida com 0 octal. Não use funções prontas de conversão, construa a sua própria versão usando suas próprias funções ou as funções disponíveis no prelude.hs.
octToDec :: [Char] -> Int
octToDec (x : []) = charToInt x 0
octToDec (x : xs) = (charToInt x (length xs)) + (octToDec xs)

charToInt :: Char -> Int -> Int
charToInt c i = (myCast c) * (8 ^ i) 

myCast :: Char -> Int
myCast n | n == '0' = 0
         | n == '1' = 1
         | n == '2' = 2
         | n == '3' = 3
         | n == '4' = 4
         | n == '5' = 5
         | n == '6' = 6
         | n == '7' = 7
         | otherwise = 0 -- Trate uma entrada inválida com 0 octal.