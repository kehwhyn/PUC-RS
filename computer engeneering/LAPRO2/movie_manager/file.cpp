#include "filme.h"

Filme::Filme(string n, string a)
{
	nome = n;
	ano = a;
}

Filme::~Filme() {}

void Filme::set_nome(string n)
{
	nome = n;
}

void Filme::set_ano(string a)
{
	ano = a;
}

string Filme::get_nome()
{
	return nome;
}

string Filme::get_ano()
{
	return ano;
}

int Filme::get_qnt()
{
	return qnt;
}

Diretor Filme::get_diretor()
{
	return ah;
}

Ator Filme::get_elenco(string n)
{
	int i;
	i = 0;
	while (i < qnt)
	{
		if (elenco[i].get_nome() == n)
			return elenco[i];
		i++;
	}
}

bool Filme::pesquisar_ator(string n)
{
	int i;
	i = 0;
	while (i < qnt)
	{
		if (elenco[i].get_nome() == n)
		{
			return 1;
		}
		i = i + 1;
	}
	return 0;
}

void Filme::alterar_diretor(const Diretor &D)
{
	ah = D;
}

void Filme::alterar_elenco(const Ator &A)
{
	int i, Achei;
	i = Achei = 0;

	Ator test = A;

	while (Achei == 0 && i < qnt && qnt < 10)
	{
		if (elenco[i].get_nome() == test.get_nome())
			if (i < qnt)
			{
				while (i < qnt - 1)
				{
					elenco[i] = elenco[i + 1];
					i = i + 1;
				}
				qnt--;
				Achei = 1;
			}
			else
				qnt--;
		else
		{
			qnt++;
			elenco[qnt] = A;
		}
	}
}

void Filme::consulta_ator(string n)
{
	for (int i = 0; i < qnt; i++)
		if (n == elenco[i].get_nome())
			cout << ano << nome << endl;
}

void Filme::consulta_diretor(string n)
{
	if (n == ah.get_nome())
		cout << ano << nome << endl;
}

void Filme::consulta_filme(string n)
{
	if (n == nome)
		cout << toString();
}

string Filme::toString()
{
	stringstream exibir;

	exibir << "Filme:" << nome << endl;
	exibir << "Diretor:" << ah.get_nome() << endl;
	exibir << "Elenco:" << endl;

	for (int i = 0; i < qnt; i++)
		exibir << elenco[i].get_nome() << endl;

	return exibir.str();
}