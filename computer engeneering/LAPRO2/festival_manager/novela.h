#ifndef _NOVELA_H_
#define _NOVELA_H_

#include "abstrata.h"
#include "ator.h"

// Idem do festival
class Novela : public AtracaoAbstrata
{
    private:
   	 Artista *art = new Artista[8];
   	 int vc = 0;
    public:
   	 Novela(string titulo = "sem", string horario = "00:00", int duracao = 0);
   	 ~Novela();
   	 void add_ator(Ator);
   	 string get_artistas();
};
#endif