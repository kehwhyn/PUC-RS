//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#include "artista.h"

Artista::Artista(string nome, int idade)
{
    this->nome = nome;
    this->idade = idade;
}

Artista::~Artista(){}

string Artista::get_nome()
{
	return this->nome;
}

int Artista::get_idade()
{
	return this->idade;
}

string Artista::toString()
{
    stringstream exibir;
    
    exibir << get_nome() << ", " << get_idade();
    
    return exibir.str();
}