#ifndef _MUSICO_H_
#define _MUSICO_H_

#include "artista.h"

//Só tem o ".h" pq n tem muito que implementar pois herda tudo do pai
class Musico : public Artista
{
	public:
  	  Musico(string nome = "ariel", int idade = 24) : Artista(nome,idade){}
  	  ~Musico(){}
};
#endif
