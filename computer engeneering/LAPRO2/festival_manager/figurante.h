//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef _FIGURANTE_H_
#define _FIGURANTE_H_

#include "artista.h"

//Só tem o ".h" pq n tem muito que implementar pois herda tudo do pai
class Figurante : public Artista
{
public:
	Figurante(string nome = "ariel", int idade = 24) : Artista(nome, idade) {}
	~Figurante() {}
};
#endif