//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#ifndef _ARTISTA_H_
#define _ARTISTA_H_

#include <string>
#include <sstream>
using namespace std;

//Tudo bem intuitivo
class Artista
{
private:
	string nome;
	int idade;

public:
	Artista(string nome = "ariel", int idade = 24);
	~Artista();
	string get_nome();
	int get_idade();
	string toString();
};
#endif