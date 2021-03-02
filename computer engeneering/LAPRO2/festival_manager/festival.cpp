//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#include "festival.h"

Festival::Festival(string titulo, string horario, int duracao) : AtracaoAbstrata(titulo, horario, duracao)
{
}

Festival::~Festival() {}

void Festival::add_banda(Banda b)
{
    ba[vc++] = b;
}

string Festival::get_artistas()
{
    stringstream exibir;

    exibir << "[";
    for (int i = 0; i < vc; i++)
        for (int j = 0; j < ba[i].get_nintegrantes(); j++)
            exibir << "(Musico: " << ba[i].get_integrantes(j).toString() << "), ";
    exibir << "]" << endl;

    return exibir.str();
}

string Festival::get_titulo()
{
    stringstream exibir;

    exibir << "Festival das bandas com ";
    for (int i = 0; i < vc; i++)
        exibir << ba[i].get_nome() << ", ";
    exibir << "." << endl;

    return exibir.str();
}