//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#include "abstrata.h"

AtracaoAbstrata::AtracaoAbstrata(string titulo, string horario, int duracao)
{
	this->titulo = titulo;
	this->horario = horario;
	this->duracao = duracao;
}

AtracaoAbstrata::~AtracaoAbstrata() {}

string AtracaoAbstrata::get_artistas()
{
}

string AtracaoAbstrata::get_titulo()
{
	return this->titulo;
}

int AtracaoAbstrata::get_duracao()
{
	return this->duracao;
}

string AtracaoAbstrata::get_horario()
{
	return this->horario;
}