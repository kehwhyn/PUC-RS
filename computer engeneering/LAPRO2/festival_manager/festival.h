//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef    _FESTIVAL_H_
#define    _FESTIVAL_H_

#include "abstrata.h"
#include "banda.h"

// Sem mts misterios aqui tambem
class Festival : public AtracaoAbstrata
{
    private:
   	 Banda *ba = new Banda[3];
   	 int vc = 0;
    public:
   	 Festival(string titulo = "sem", string horario = "00:00", int duracao = 0);
   	 ~Festival();
   	 void add_banda(Banda);
   	 string get_artistas();
   	 string get_titulo();
};
#endif