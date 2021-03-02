//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef _ABSTRATA_H_
#define _ABSTRATA_H_

#include <string>
#include <sstream>
using namespace std;

//Tudo muito intuitivo
class AtracaoAbstrata
{
private:
	string titulo, horario;
	int duracao;

public:
	AtracaoAbstrata(string titulo = "Sem titulo", string horario = "00:00", int duracao = 0);
	~AtracaoAbstrata();
	virtual string get_artistas(); //É virtual pois as classes filhas podem ter o mesmo metodo
	virtual string get_titulo();   //mas com comportamento diferente
	int get_duracao();
	string get_horario();
};
#endif