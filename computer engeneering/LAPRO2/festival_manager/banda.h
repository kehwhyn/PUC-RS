//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef _BANDA_H_
#define _BANDA_H_

#include "musico.h"

// Tudo bem intuitivo
class Banda
{
private:
	string nome, estilo;
	Artista *integrantes = new Musico[4]; //Pode se fazer isso pois Musico é uma classe derivada de Artista
	int nIntegrantes;

public:
	Banda(string nome = "GABI AMARANTOS", string estilo = "TECNO BREGA");
	~Banda();
	void add_integrantes(Musico);
	string get_nome();
	string get_estilo();
	int get_nintegrantes();
	Artista get_integrantes(int);
};
#endif
