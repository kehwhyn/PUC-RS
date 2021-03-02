#ifndef __DIRETOR_H
#define __DIRETOR_H

#include <iostream>
#include <string>
#include <sstream>
using namespace std;

class Diretor {
	private:
    	string nome;
   	 
	public:
    	Diretor(string nome = "sem nome");
    	~Diretor();
    	string get_nome();
    	string toString();
};
#endif