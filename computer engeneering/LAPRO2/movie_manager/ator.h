#ifndef __ATOR_H
#define __ATOR_H

#include <iostream>
#include <string>
#include <sstream>
using namespace std;

class Ator
{
private:
	string nome;

public:
	Ator(string n = "Sem nome");
	~Ator();
	string get_nome();
	string toString();
};
#endif