//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef _ATOR_H_
#define _ATOR_H_

#include "artista.h"

//Só tem o ".h" pq n tem muito que implementar pois herda tudo do pai
class Ator : public Artista
{
	public:
  	  Ator(string nome = "ariel", int idade = 24) : Artista(nome,idade){}
  	  ~Ator(){}
};
#endif