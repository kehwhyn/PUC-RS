//headers
#include "filme.h"
#include <fstream>
#include <ctime>
#include <cstdlib>
#define LA 45
#define LF 400
#define LD 25

void menu(int op, Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int &sla, int &conta, int &contd);//Menu do programa principal
void consulta_filme(string n); //Exibe os dados do filme pedido
void cadastrar_filme(string n, Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int &sla, int &conta, int &contd);//Nome diz muita coisa
void enche_tudo(Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int &sla, int &conta, int &contd);//Pega informacoes dos arquivos
//void guarda_tudo(Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int sla, int conta, int contd);//Guarda alteracoes nos arquivos


/***************************************	Programa principal 	*************************************************************/
int main()
{
	Ator Yasmin[45]; //Vetor de atores
	Diretor Kevin[25]; //Vetor de diretores
	Filme As_Aventuras_de_LAPROII[300]; //Vetor de filmes
	int op, sla, conta, contd, i; //Op do menu, sla/conta/contd variaveis de controle
    
	sla = conta = contd = op = 0; //Define as variaveis
    
	srand(time(NULL)); //Alimenta a semente
    
	//enche_tudo(As_Aventuras_de_LAPROII, Yasmin, Kevin, sla, conta, contd); //Preenche as classes
	ifstream arqEntrada;
	string ata, ano, aux;
	int estado = 0;
	//0 = procura sustenido
	//1 = está no nome
	//2 = está pegando a lista
   	 
	arqEntrada.open("BD_Atores.txt", ios::in);
    	 
	do
	{
    	getline(arqEntrada, ata);
               	 
    	if(ata[0] == '#')
    	{
        	estado = 1;
        	continue;
    	}
   	 
    	if(estado == 1){
        	Yasmin[conta] = Ator(ata);
        	conta++;
        	estado = 2;
        	continue;
    	}
   	 
    	if(estado == 2)    
        	if(ata.size() > 2)
        	{
            	aux = ata.substr(2, ata.size() - 8);
            	ano = ata.substr(ata.size() - 5, 4);
        	}
                  	 
    	As_Aventuras_de_LAPROII[sla] = Filme(aux, ano);
    	As_Aventuras_de_LAPROII[sla].alterar_elenco(Yasmin[conta-1]);
    	sla++;
	}while(!arqEntrada.eof());
    
	if(arqEntrada.bad()|| !arqEntrada.eof())
	{
    	cout << "Erro fatal!" << endl;
    	exit(1);  // Aborta programa
	}

	arqEntrada.close(); //Ate aqui ta certo
    
	arqEntrada.open("BD_Diretores.txt", ios::in);
   	 
   do
	{
    	getline(arqEntrada, ata);
               	 
    	if(ata[0] == '#')
    	{
        	estado = 1;
        	continue;
    	}
   	 
    	if(estado == 1){
        	Kevin[contd] = Diretor(ata);
        	contd++;
        	estado = 2;
        	continue;
    	}
   	 
    	if(estado == 2)    
        	if(ata.size() > 2)
        	{
            	aux = ata.substr(2, ata.size() - 8);
            	ano = ata.substr(ata.size() - 5, 4);
        	}
                  	 
    	As_Aventuras_de_LAPROII[sla] = Filme(aux, ano);
    	sla++;
	}while(!arqEntrada.eof());
    
	if(arqEntrada.bad()|| !arqEntrada.eof())
	{
    	cout << "Erro fatal!" << endl;
    	exit(1);  // Aborta programa
	}

	arqEntrada.close();
    
	for(i = 0; i < LF; i++)
    	cout << As_Aventuras_de_LAPROII[i].toString();
    
	//menu(op, As_Aventuras_de_LAPROII, Yasmin, Kevin, sla, conta, contd); //Menu do programa

	//guarda_tudo(As_Aventuras_de_LAPROII, Yasmin, Kevin, sla, conta, contd); //Escreve todas as alterações no arquivo
    
	return 0; //Termina o programa
}

/***************************************	Menu do programa principal 	*************************************************************/
/*void menu(int op, Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int &sla, int &conta, int &contd)
{

	string n, n1;
	Ator aux;
	int i, j, Achei;
    
	do //Menu bem auto-explicativo
	{ 	 
    	cout << "Escolha uma das opcoes abaixo: " << endl;
    	cout << "1. Consultar ator." << endl;
    	cout << "2. Consultar diretor." << endl;
    	cout << "3. Consultar filme." << endl;
    	cout << "4. Cadastrar um novo filme." << endl;
    	cout << "5. Remover/Al
   	 
    	terar Diretor." << endl;
    	cout << "6. Remover/Alterar Ator." << endl;
    	cout << "7. Sair do Programa!" << endl;
    	cin >> op; //Le a opcao escolhida

    	switch(op)
    	{
        	case 1: cout << "Digite o nome do ator:" << endl; //Pede o nome do ator a ser consultado
                	getline(cin, n); // Le o nome
               	 
                	cout << n << endl; //Imprime o nome do ator
                	for(i = 0; i < sla; i++)
                	{
                    	aux = As_Aventuras_de_LAPROII[i].get_elenco(n);
                    	if(aux.get_nome() == n)// Busca na lista de filmes se o ator esta no elenco
                        	cout << As_Aventuras_de_LAPROII[i].get_ano() << As_Aventuras_de_LAPROII[i].get_ano() << endl;
                	}
                	break;
       	 
        	case 2: cout << "Digite o nome do Diretor:" << endl; //Pede o nome do diretor a ser consultado
                	getline(cin, n); // Le o nome

                	cout << n << endl;
               	 
                	for(i = 0; i < sla; i++)
                    	if(As_Aventuras_de_LAPROII[i].get_diretor().get_nome() == n) //Busca na lista de filmes o nome do diretor
                        	cout << As_Aventuras_de_LAPROII[i].get_ano() << As_Aventuras_de_LAPROII[i].get_nome() << endl; //Se sim, imprime o nome e o ano do filme
                   	 
                	break;

        	case 3: cout << "Digite o nome do filme: " << endl; //Pede o nome do filme a ser consultado
                	getline(cin, n); //Le
               	 
                	for(i = 0; i < sla; i++)
                    	if(n == As_Aventuras_de_LAPROII[i].get_nome())
                        	cout << As_Aventuras_de_LAPROII[i].toString(); //Imprime as informacoes do filme

                	break;
       	 
        	case 4: cout << "Digite o nome do filme a ser cadastrado:" << endl; //Pede um nome do filme pra cadastrar
                	getline(cin, n); //Le
               	 
                	i = Achei = 0; //Define o contador e a var de controle
                	while(i < contd && Achei != 1) //Se achar ou terminar de percorrer o vetor, termina
                	{
                    	if(As_Aventuras_de_LAPROII[i].get_nome() == n) //Se o nome do filme for igual o informado
                        	Achei = 1; //Diz que achou
                    	i++; //Incrementa o contador caso ainda nao tenha encontrado
                	}
                	if(!Achei) //Se nao achar, pode cadastrar um novo filme
                    	cadastrar_filme(n, As_Aventuras_de_LAPROII, Yasmin, Kevin, sla, conta, contd);
                	break;
               	 
       	 
        	case 5: cout << "Digite o nome do diretor a remover/alterar:" << endl; //Pede o nome do diretor a ser alterado
                	getline(cin, n); //Le o nome do diretor
                	cout << "Digite o nome do filme:" << endl; //Pede o nome do filme aonde o diretor sera alterado
                	getline(cin, n1); //Le o nome do filme
               	 
                	i = Achei = 0; //Define o contador e a var de controle
                	while(i < contd && Achei != 1) //Se achar ou terminar de percorrer o vetor termina
                	{
                    	if(Kevin[i].get_nome() == n) //Se o nome estiver na lista de diretores, entao pode alterar
                    	{
                        	if(As_Aventuras_de_LAPROII[i].get_nome() == n1) //Se achar o filme, pode alterar o seu diretor
                            	As_Aventuras_de_LAPROII[i].alterar_diretor(Kevin[i]);
                        	Achei = 1; //Diz que achou
                    	}
                    	i++; //Incrementa o contador caso ainda nao tenha achado
                	}
                	if(!Achei) //Se nao achar, informa que o diretor nao faz parte da lista
                    	cout << "Diretor não cadastrado." << endl;
                   	 
                	break;
       	 
        	case 6: cout << "Digite o nome do ator a remover/adicionar:" << endl; //Pede o nome do ator a ser alterado
                	getline(cin, n); //Le o nome do ator
                	cout << "Digite o nome do filme:" << endl; //Pede o nome do filme aonde o ator sera alterado
                	getline(cin, n1); //Le o nome do filme
               	 
                	i = Achei = 0; //Define o contador e a var de controle
                	while(i < conta && Achei != 1) //Se achar ou terminar de percorrer o vetor termina
                	{
                    	if(Yasmin[i].get_nome() == n) //Se o nome estiver na lista de atores, entao pode alterar
                    	{
                        	if(As_Aventuras_de_LAPROII[i].get_nome() == n1) //Se achar o filme, pode alterar o seu ator
                            	As_Aventuras_de_LAPROII[i].alterar_elenco(Yasmin[i]);
                        	Achei = 1; //Diz que achou
                    	}
                    	i++; //Incrementa o contador caso ainda nao tenha achado
                	}
                	if(!Achei) //Se nao achar, informa que o ator nao faz parte da lista
                    	cout << "Ator não cadastrado." << endl;
                   	 
                	break;
       	 
        	case 7: cout << "Tchau <3" << endl; //Termina o programa
                	break;
       	 
        	default: cout << "Opção Invalida!" << endl; //Caso digite algum valor sem ser os que estão no menu
                 	break;
    	}
	}while(op != 7);
    
}

/***************************************	Cadastrar filme 	*************************************************************/
void cadastrar_filme(string n, Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int &sla, int &conta, int &contd)
{
	string aa, ano;
    
	ano = 2017 - (rand()%30 + 1); //Gera um ano aleatório
    
	sla++; //Incrementa a variavel de controle
	As_Aventuras_de_LAPROII[sla] = Filme(n,ano); //Adiciona um novo filme
    
	cout << "Qual diretor deseja adicionar?" << endl; //Pede o nome do novo diretor
	cin >> aa; //Le
	contd++; //Incrementa variavel de controle dos diretores
	Kevin[contd] = Diretor(aa); // Salva o novo diretor
    
	cout << "Qual ator deseja adicionar?" << endl; //Pede o nome do novo diretor
	cin >> aa; //Le
	conta++; //Incrementa variavel de controle dos atores
	Yasmin[conta] = Ator(aa); //Salva o novo ator
   	 
	As_Aventuras_de_LAPROII[sla].alterar_diretor(Kevin[contd]); //Adiciona o diretor ao filme criado
	As_Aventuras_de_LAPROII[sla].alterar_elenco(Yasmin[conta]); //Adiciona o ator ao filme criado
}

/***************************************	Iniciaçizar variaveis 	*************************************************************/
/*void enche_tudo(Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LAD], Diretor Kevin[LAD], int &sla, int &conta, int &contd)
{

}

/***************************************	Salvar alteracoes no arquivo 	*************************************************************/
/*void guarda_tudo(Filme As_Aventuras_de_LAPROII[LF], Ator Yasmin[LA], Diretor Kevin[LD], int sla, int conta, int contd)
{
	ofstream arqSaida; //Var pra manipulacao de arquivo
	int i, k;
    
	arqSaida.open("BD_Atores.txt", ios::out); //Prepara o arquivo pra escrita
    
	i = 0; //Define o contador
	do
	{
    	arqSaida << '#' << endl; //Antes do nome do ator aparece #
    	arqSaida << Yasmin[i].get_nome(); //Passa o nome do ator
    	for(k = 0; k < sla; k++)
        	if(Yasmin[i].get_nome() == As_Aventuras_de_LAPROII[k].get_elenco(Yasmin[i].get_nome()).get_nome()) //Procura na lista de filmes o ator, se achar imprime os dados
            	arqSaida << "--" << As_Aventuras_de_LAPROII[k].get_nome() << " " << "(" << As_Aventuras_de_LAPROII[k].get_ano() << ")" << endl;
    	i++;
	}while(i < conta); //Acaba quando terminar de percorrer a lista de atores
      	 
	arqSaida.close(); //Fecha o arquivo
    
	arqSaida.open("BD_Diretores.txt", ios::out); //Prepara o arquivo para a escrita
    
	i = 0; //Define o contador
	do
	{
    	arqSaida << '#' << endl; //Anted do nome do diretor aparece #
    	arqSaida << Kevin[i].get_nome() << endl; //Passa o nome do diretor
    	for(k = 0; k < sla; k++)
        	if(Kevin[i].get_nome() == As_Aventuras_de_LAPROII[k].get_elenco(Kevin[i].get_nome()).get_nome()) //Procura na lista de filmes o diretor, se achar imprime os dados
            	arqSaida << "--" << As_Aventuras_de_LAPROII[k].get_nome() << " " << "(" << As_Aventuras_de_LAPROII[k].get_ano() << ")" << endl;
    	i++;
	}while(i < contd); //Acaba quando termina de percorrer toda a lista de diretores
      	 
	arqSaida.close(); //Fecha o arquivo
}*/
