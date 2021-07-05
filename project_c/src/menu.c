/**
 * @file menu.c
 * @brief Ficheiro que contém a API relativa à implementação do menu.
 */

#include "menu.h"

static void printLOGO();


/**
 * @brief Função que imprime o logotipo plataforma.
*/ 
static void printLOGO(){
	printf("        _   _    ______    _         ________    _    \n");
	printf("       | | | |  |  ____|  | |       |   ___  |  | |   \n");
	printf("       | |_| |  | |       | |       |  |___| |  | |   \n");
	printf("       |_   _|  | |___    | |       |  ______|  | |   \n");
	printf("         | |    |  ___|   | |       | |         | |   \n");
	printf("         | |    | |       | |       | |         |_|   \n");
	printf("         | |    | |____   | |____   | |          _    \n");
	printf("         |_|    |______|  |______|  |_|         |_|   \n");
	printf("      -----------------------------------------------\n\n");	 
}


/**
 * @brief Função que imprime menu sem opção selecionada.
 */ void printMenu(Boolean v){
	if(system("clear"));
	printLOGO();
	printf("\n                 **** Bem vindo ao YELP ****");
	printf("\n%s** Sistema de gestão e consulta de recomendações de negócios **%s\n", MAGENTA, RESET);
	printf("\n   Escolha a operação que pretende realizar");

	if(v)LOG_GREEN("\n\n  Ler ficheiros csv");
	else LOG_RED("\n\n  Ler ficheiros csv");
	printf("\nClique %s<1><enter>%s para carregar os ficheiros", YELLOW,RESET);

	LOG_GREEN("\n\n Listar e consular numero de negócios por letra");
	printf("\nSintaxe a usar: x=businesses_started_by_letter(sgr,'letra pretendida');");

	LOG_GREEN("\n\n Consultar negócio");
	printf("\nSintaxe a usar: \n x=business_info(sgr,'Id do negócio pretendido');");

	LOG_GREEN("\n\n Listar negócios por avaliados por um utilizador");
	printf("\nSintaxe a usar: \n x=businesses_reviewed(sgr,'Id do utilizador pretendido');");

	LOG_GREEN("\n\n Listar negócios por stars e cidade");
	printf("\nSintaxe a usar: \n x=business_by_star_and_city(sgr,nºEstrelas,'nome da cidade pretendida');");

	LOG_GREEN("\n\n Top negócios por cidade");
	printf("\nSintaxe a usar: \n x=top_businesses_by_city(sgr,nº de negócios para listar);");

	LOG_GREEN("\n\n Listar e consultar número de utilizadores por cidade visitada");
	printf("\nSintaxe a usar: \n x=users_multiple_states(sgr);");

	LOG_GREEN("\n\n Top negócios por categoria");
	printf("\nSintaxe a usar: \n x=top_businesses_by_category(sgr,'categoria pretendida');");

	LOG_GREEN("\n\n Lista de reviews por palavra-chave");
	printf("\nSintaxe a usar: \n x=reviews_with_word(sgr,'palavra pretendida');");
	LOG_GREEN("\n\n Sair");
	printf("\nSintaxe a usar: quit;\n ");
	
}
/***************************PRINTQUERIES****************************/


/**
 * @brief Função que imprime os resultados da Querie 2.
 * @param a Lista de Negócios começados pela letra dada pelo Utilizador.
 * @param aux Letra dada pelo Utilizador.
 * @param i Página atual onde o Utilizador se encontra.
 * @param f Número de negócios por página.
 * @param num Total de elementos no array de Negócios.
 * @param pag Número total de páginas.
 */

void printQ2(char **a, int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 2",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("* %s \n", a[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌────────────────────────────────────────────────┐");
    printf("\n TOTAL DE NEGÓCIOS COMEÇADOS POR %c : %s%d%s\n", a[1][1],GREEN,num,RESET);
	printf("└────────────────────────────────────────────────┘");
	printf("             \n1 <- previous || 2 -> next");
    printf("%10s %s(-)%s negócios || 4 -> %s(+)%s negócios\n","3 <-",RED,RESET,RED,RESET);
}

/**
 * @brief Função que imprime os resultados da Querie 3.
 * @param 
 * @param ------
 * @param i Página atual onde o Utilizador se encontra.
 * @param f Número de negócios por página.
 * @param pag Número total de páginas.
 */
void printQ3( char* bname, char* city, char* state, int qtdReviews){

	printf("\n%s %16s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 3",RESET);
    printf("┌────────────────────────────────────────────────────────────────────┐\n");
    //printf("│ ID do negócio---------------------------%s%-40s%s\n",GREEN,businessID,RESET);
    printf("│ Nomde do negócio------------------------%s%-40s%s\n",GREEN,bname,RESET);
    printf("│ Cidade----------------------------------%s%-40s%s \n",GREEN,city,RESET);
    printf("│ Estado----------------------------------%s%-40s%s  \n",GREEN,state,RESET);
    printf("│ Quantidade de reviews-------------------%s%-40d%s\n",GREEN,qtdReviews,RESET);
    printf("└────────────────────────────────────────────────────────────────────┘\n");
}

/**
 * @brief Função que imprime os resultados da Querie 4 .
 * @param l Lista de Negócios com reviwes feitas pelo utilizador.
 * @param userID id do Utilizador.
 * @param i Página atual onde o Utilizador se encontra.
 * @param f Número de negócios por página.
 * @param num Total de elementos no array de Negócios.
 * @param pag Número total de páginas.
 */
void printQ4(char** l, char** l2,  int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 4",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("** %s  -  %s\n", l[i],l2[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌──────────────────────────────────────────────────────────────────────────┐");
    printf("\n TOTAL DE NEGÓCIOS COM REVIEWS FEITAS PELO USER: %s%d%s \n",GREEN,num,RESET);
	printf("└──────────────────────────────────────────────────────────────────────────┘");
}


/**
 * @brief Função que imprime os resultados da Querie 5 .
 * @param l Lista de Negócios com reviwes feitas pelo utilizador.
 * @param s stars do negócio.
 * @param i Página atual onde o Utilizador se encontra.
 * @param f Número de negócios por página.
 * @param num Total de elementos no array de Negócios.
 * @param pag Número total de páginas.
 */
void printQ5(char** l,char** l2, int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 5",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("** %s  -  %s\n", l[i],l2[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌─────────────────────────────────────────────────────────");
    printf("\n    TOTAL DE NEGÓCIOS COM OU x MAIS ESTRELAS: %s%d%s \n ",GREEN,num,RESET);
	printf("└─────────────────────────────────────────────────────────┘");
    printf("         \n","1 <- previous || 2 -> next");
    printf("%10s %s(-)%s negócios || 4 -> %s(+)%s negócios\n","3 <-",RED,RESET,RED,RESET);
}

/**
 * @brief Função que imprime os resultados da Querie 6.
 * @param l Lista de Negócios com mais stars.
 * @param num Total de elementos no array de Negócios.
 */
void printQ6(char** l,int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 6",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("* %s \n", l[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌──────────────────────────────────────────────┐");
    printf("      \nTOP negócios reviews por cidade \n          ");
	printf("└──────────────────────────────────────────────┘");
	printf("%22s\n","1 <- previous || 2 -> next");
    printf("%10s %s(-)%s review || 4 -> %s(+)%s review\n","3 <-",RED,RESET,RED,RESET);
}


/**
 * @brief Função que imprime os resultados da Querie 7.
 * @param l Lista de Negócios com mais stars.
 * @param num Total de elementos no array de Negócios.
 */
void printQ7(char** l, int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 7",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("* %s \n", l[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌────────────────────────────────────┐");
    printf("\n          TOP negócios       \n     ");
	printf("└────────────────────────────────────┘");
	printf("%22s\n","1 <- previous || 2 -> next");
    printf("%10s %s(-)%s review || 4 -> %s(+)%s review\n","3 <-",RED,RESET,RED,RESET);
}

/**
 * @brief Função que imprime os resultados da Querie 8.
 * @param l Lista de Negócios com mais stars.
 * @param num Total de elementos no array de Negócios.
 * @param categoria categoria de negócio.
 * businessID id de um negócio
 * bname nome de um negócio
 * s total de estrelas de um negócio
 */
void printQ8(char** l,int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 8",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("* %s \n", l[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌────────────────────────────────────────┐");
    printf("\n TOP NEGÓCIOS NA CATEGORIA PRETENDIDA \n");
    printf("└────────────────────────────────────────┘");
	printf("%22s\n","1 <- previous || 2 -> next");
    printf("%10s %s(-)%s review || 4 -> %s(+)%s review\n","3 <-",RED,RESET,RED,RESET);
}

/**
 * @brief Função que imprime os resultados da Querie 9.
 * @param a Lista de reviews começados que contem a palavra.
 * @param aux Letra dada pelo Utilizador.
 * @param i Página atual onde o Utilizador se encontra.
 * @param f Número de negócios por página.
 * @param num Total de elementos no array de Negócios.
 * @param pag Número total de páginas.
 */


void printQ9(char** l,int i, int f, int num, int pag){
	int atual = i + 1;
	int k = f;
	i = i * f;
	f = i + f;	
	printf("\n%s%42s%s\n",BOLDCYAN,"RESULTADOS DA QUERIE 9",RESET);
	for(;i<f && i<num;){
	    for(;i<f && i<num; i++){
	        printf("* %s \n", l[i]);
	    }
	    puts("\n");
    }
    printf("%16d/%d(%d)\n",atual,pag,k);
	printf("┌─────────────────────────────────────────────────┐");
    printf("\n    REVIEWS QUE CONTEM A PALAVRA PRETENDIDA: %s%d%s", GREEN,num,RESET);
	printf("└─────────────────────────────────────────────────┘");
	printf("%22s\n","1 <- previous || 2 -> next");
    printf("%10s %s(-)%s review || 4 -> %s(+)%s review\n","3 <-",RED,RESET,RED,RESET);
}