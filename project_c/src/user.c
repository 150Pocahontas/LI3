#include "user.h"
/**
 * @file users.c
 * @brief Ficheiro que contém a API relativa à implementação dos utilizadores.
 */
 struct user{
	char* userID;
	char* username;
	char** friends;
	int tam;
};

 struct catUsers{
	Catalog catalog;
};

/**
 * @brief Função que cria um novo utilizador.
 * @return novo utilizador nulo.
 */
User createUser(){
	User u = (User) malloc(sizeof(struct user));
	u -> userID = NULL;
	u -> username = NULL;
	u -> friends = NULL;	
	return u;
}

/**
 * @brief Função que altera o código de um determinado utilizador.
 * @param u User que vai ser alterado.
 * @param e char* apontador para o novo código do utilizador.
 * @return User alterado.
 */
User changeUser(User u, char* id, char* username, char** friends, int tam){
	if(u -> userID) {
		free(u -> userID);
		free(u -> username);
		free(u -> friends);
	}
	u -> userID = malloc((strlen(id) + 1) * sizeof(char));
	strcpy(u->userID,id);
	u->username = malloc((strlen(username) + 1) * sizeof(char));
	strcpy(u -> username,username);
	u -> friends = malloc(sizeof(char*) * tam);
	for(int i = 0; i < tam; i++) {
		u -> friends[i] = malloc(sizeof(char) * (strlen(friends[i]) + 1));
		strcpy(u->friends[i], friends[i]);
	}
	u->tam = tam; 
	return u;
}


/**
 * @brief Função que inicia um novo catálogo de utilizadores.
 * @return Novo catalog de utilizadores nulo.
 */
CatUsers initUsersCat() {
	CatUsers new = malloc(sizeof(struct catUsers));
	new -> catalog = initCat();
	return new;
}

/**
 * @brief Função que verifica se existe um utilizador.
 * @param c CatUsers onde se vai procurar.
 * @param char *key a procurar.
 * @return Boolean com o resultado.
 */
Boolean existUser (CatUsers c, char *key) {
	return existCat(c->catalog, key);
}

/**
 * @brief Função que insere um utilizador num catálogo.
 * @param c CatUsers onde se vai inserir o utilizador.
 * @param u Utilizador a ser inserido no catálogo de utilizadores.
 * @return CatUsers com o utilizador inserido.
 */
CatUsers insertUser(CatUsers c, User u){
	char *aux = u->userID;
	c->catalog = insertCat(c->catalog, aux, u);
	free(aux);
	return c;
}

/**
 * @brief Função com o objetivo de limpar da memória um dado catálogo de utilizadores.
 * @param c CatUsers a limpar da memória.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void freeCatUsers(CatUsers c, Funcao f){
	freeCat(c -> catalog, f);
	free(c);
}

/**
 * @brief Função que obtém o ID do utilizador.
 * @param u Utilizador do qual se pretende obter o código.
 * return apontador para um char com o código do utilizador.
 */
char* getUserID(User u){
	char *aux = malloc(sizeof(char)*(strlen(u -> userID) + 1));
	strcpy(aux, u->userID);
	return aux;
}


/**
 * @brief Função que obtém o nome do utilizador.
 * @param u Utilizador do qual se pretende obter o código.
 * return apontador para um char com o código do utilizador.
 */
char* getUsername(User u) {
	char *aux = malloc(sizeof(char)*(strlen(u -> username) + 1));
	strcpy(aux, u -> username);
	return aux;
}


/*-----------------------------------QUERIE7-----------------------------------*/

void getAllUsers(CatUsers cat ,TABLE q){
	AVL tree;
	NodeAvl node;
  	int index;
  	for (index = 0; index < NR_LETRAS; index++) {
        tree = getAVLCat(cat->catalog,index);
        node = getNodo(tree);
        percorreUsersQ9(node,q);
        freeNode(node);
    }
}

void percorreUsersQ9(NodeAvl node, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreUsersQ9(esq,q);
	freeNode(esq); 

	char* u = getString(node);
	insertList(u,q);
		
	NodeAvl dir = getNodoDir(node);
    percorreUsersQ9(dir,q);
	freeNode(dir);
}
