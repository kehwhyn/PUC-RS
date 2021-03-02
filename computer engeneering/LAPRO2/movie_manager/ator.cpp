#include "ator.h"

Ator::Ator(string n) {
	nome = n;
}

Ator::~Ator(){}

string Ator::get_nome() {
	return nome;
}

string Ator::toString() {
	stringstream exibir;

	exibir << "Nome:" << nome << endl;

	return exibir.str();
}