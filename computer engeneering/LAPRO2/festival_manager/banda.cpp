//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#include "banda.h"

Banda::Banda(string nome, string estilo)
{
    this->nome = nome;
    this->estilo = estilo;
    nIntegrantes = 0;
}
 
Banda::~Banda(){}

void Banda::add_integrantes(Musico no)
{
    integrantes[nIntegrantes++] = no; //O vetor recebe o musico e atualiza a var de controle
}

string Banda::get_nome()
{
    return this->nome;
}
 
string Banda::get_estilo()
{
    return this->estilo;
}

int Banda::get_nintegrantes()
{
    return this->nIntegrantes;
}

Artista Banda::get_integrantes(int i)
{
	return integrantes[i];
}
