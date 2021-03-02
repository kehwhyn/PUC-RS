#include "diretor.h"

Diretor::Diretor(string n) {
	nome = n;
}

Diretor::~Diretor() {}

string Diretor::get_nome() {
	return nome;
}

string Diretor::toString() {
	stringstream exibir;

	exibir << "Nome:" << nome << endl;

	return exibir.str();
}