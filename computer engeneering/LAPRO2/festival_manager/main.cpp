//-----------------------------------------------------
// Kevin Fiorentin e Yasmin Elkfury - LAPROII[590]
//-----------------------------------------------------

#include "novela.h"
#include "festival.h"
#include <iostream>
using namespace std;

int main()
{
 // Aqui eu podia ter feito um vetor AtracaoAbstrata de tam = 2 para armazenar o Festival e a Novela
 // Resto da implementacao mt intuitiva sem mts segredos
 
    Festival nova("PAO", "23:00", 200);
    Banda *novo;
    Musico *papa;
    Novela oe("Joaos e Marias", "20:00", 60);
    Ator *oie;
    
    cout << "------------- FESTIVAL ------------------" << endl;
    
    novo = new Banda("Fulano & Ciclano", "Setanejo Pos-graduado");
   	 papa = new Musico("Fulano",24);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("Ciclano",27);
   	 novo->add_integrantes(*papa);
    nova.add_banda(*novo);
    
    novo = new Banda("Os Efes", "Funcional Metal");
   	 papa = new Musico("F1",23);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("F2",34);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("F3",21);
   	 novo->add_integrantes(*papa);    
    nova.add_banda(*novo);

    novo = new Banda("Gauderios Cromaticos", "Jazz Farroupilha");
   	 papa = new Musico("Gaudencio",45);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("Florencio",56);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("Clemencio",53);
   	 novo->add_integrantes(*papa);
   	 papa = new Musico("Prudencio",55);
   	 novo->add_integrantes(*papa);
    nova.add_banda(*novo);
    
    
    cout << endl;
    cout << "DURACAO: " << nova.get_duracao() << endl;
    
    cout << endl;
    cout <<  "HORARIO: " << nova.get_horario() << endl;
    
    cout << endl;
    cout << "TITULO: " << nova.get_titulo() << endl;
    
    cout << endl;
    cout << "ARTISTAS: " << nova.get_artistas() << endl;    
    
    cout << "------------ NOVELA ------------------" << endl;

    oie = new Ator("Joao I", 20);
    oe.add_ator(*oie);
    
    oie = new Ator("Joao II", 23);
    oe.add_ator(*oie);

    oie = new Ator("Joao III", 25);
    oe.add_ator(*oie);
    
    oie = new Ator("Joao IV", 26);
    oe.add_ator(*oie);
    
    
    oie = new Ator("Maria I", 21);
    oe.add_ator(*oie);
    
    oie = new Ator("Maria II", 24);
    oe.add_ator(*oie);
    
    oie = new Ator("Maria III", 26);
    oe.add_ator(*oie);
    
    oie = new Ator("Maria IV", 27);
    oe.add_ator(*oie);
    
    cout << endl;
    cout << "DURACAO: " << oe.get_duracao() << endl;

    cout << endl;
    cout << "HORARIO: " << oe.get_horario() << endl;
    
    cout << endl;
    cout << "TITULO: " << oe.get_titulo() << endl;
    
    cout << endl;
    cout << "ARTISTAS: " << oe.get_artistas() << endl;
    
    delete novo;
    delete papa;
    delete oie;   
}