#ifndef __FILME_H
#define __FILME_H

#include "ator.h"
#include "diretor.h"
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

class Filme {
	private:
    	string nome, ano; //Nome do filme
    	int qnt; //Ano do filme, e variavel de controle p/atores
    	Ator elenco[10]; //Contem as informacoes do elenco
    	Diretor ah; //Contem as informacoes do diretor

	public:
    	Filme(string n = "sem nome", string a = "0"); //Construtor com parametro padrao
    	~Filme(); //Destrutor
    	void set_nome(string n);
    	void set_ano(string a);
    	string get_nome(); //Retorna o nome do filme
    	string get_ano(); //O ano do filme
    	int get_qnt(); //Quantidade de pessoas no elenco
    	Diretor get_diretor(); //Acessa o diretor
    	Ator get_elenco(string n); //Acessa o elenco
    	bool pesquisar_ator(string n); //Retorna 1 se o ator esta na lista, cc 0
    	void alterar_diretor(const Diretor &D); //Adiciona um novo diretor
    	void alterar_elenco(const Ator &A); //Altera o elenco
    	void consulta_ator(string n); //Exibe todos os filmes em que o ator atuou
    	void consulta_diretor(string n); //Exibe todos os filmes que dirigiu
    	void consulta_filme(string n); //Se for igual ao nome do filme faz um toString()
    	string toString(); //Imprime os dados da classe
};
#endif