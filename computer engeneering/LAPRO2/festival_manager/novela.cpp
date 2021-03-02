#include "novela.h"

Novela::Novela(string titulo, string horario, int duracao) : AtracaoAbstrata(titulo, horario, duracao)
{
}

Novela::~Novela(){}

void Novela::add_ator(Ator b)
{
    art[vc++] = b;
}

string Novela::get_artistas()
{
    stringstream exibir;
    
    exibir << "[";
    for(int i = 0; i < vc; i++)
   	 exibir << "(Ator: " << art[i].toString() << "), ";
    exibir << "]" << endl;
    
    return exibir.str();
}