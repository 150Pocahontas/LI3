/* @file businesses.c
 * @brief Ficheiro que contém a API relativa à implementação dos negócios.
 */
#include "business.h"


struct business{
	char* businessID;
	char* bname;
	char* city;
	char* state;
	char** categorys;
	int tam;

	char** reviews;
	int qtdReviews;
};

struct CatBusinesses{
	Catalog catalog;
};


/**
 * @brief Função que cria um negócio.
 * @return novo negócio criado.
 */
Business createBusiness() {
	Business b = (Business) malloc(sizeof(struct business));
	b->businessID = NULL;
	b->bname = NULL;
	b->city = NULL;
	b->state = NULL;
	b->categorys = NULL;
	b -> qtdReviews = 0;
	b -> reviews = NULL;
	return b;
}

/**
 * @brief Função que altera um negócio.
 * @param b Negócio a ser alterado.
 * @param id novo código do negócio.
 * @return novo negócio criado.
 */
Business changeBusiness(Business b, char* id, char* bname, char* city,char* state, char** categorys, int tam){
	if(b -> businessID) {
		free(b -> businessID);
		free(b -> bname);
		free(b -> city);
		free(b -> state);
		free(b -> categorys);
	}
	b -> businessID = malloc((strlen(id) + 1) * sizeof(char));
	strcpy(b -> businessID, id);
	b -> bname = malloc((strlen(bname) + 1) * sizeof(char));
	strcpy(b -> bname, bname);
	b->city = malloc((strlen(city) + 1) * sizeof(char));
	strcpy(b -> city, city);
	b -> state = malloc((strlen(state) + 1) * sizeof(char));
	strcpy(b->state, state);
	b -> categorys = malloc(sizeof(char*) * tam);
	for(int i = 0; i < tam; i++) {
		b -> categorys[i] = malloc(sizeof(char) * (strlen(categorys[i]) + 1));
		strcpy(b -> categorys[i], categorys[i]);
	}
	b->tam = tam;
	return b;
}

/**
 * @brief Função que inicializa um catálogo de negócios.
 * @return CatBusinesses inicializado.
 */
CatBusinesses initBusinessCat() {
	CatBusinesses new = malloc(sizeof(struct CatBusinesses));
	new->catalog = initCat();
	return new;
}

/**
 * @brief Função que verifica se um negócio existe.
 * @param c CatBusinesses onde procura.
 * @param key char* utilizado como referência de procura.
 * @return Boolean com o resultado.
 */
Boolean existBusiness(CatBusinesses c, char *key) {
	return existCat(c->catalog, key);
}

/**
 * @brief Função que insere um negócio num catálogos de negócios.
 * @param cat CatBusiness onde vai inserir o negócio.
 * @param b negócio a ser inserido.
 * @return CatBusiness com o negócio inserido.
 */
CatBusinesses insertBusiness(CatBusinesses c, Business b) {
	char* aux = b->businessID;
	c->catalog = insertCat(c->catalog, aux, b);
	free(aux);
	return c;
}	

/**
 * @brief Função que liberta da memória o espaço ocupado por um catálogo de negócios.
 * @param c CatBusinesses a ser limpo da memória.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void freeCatBusinesses (CatBusinesses c, Funcao f) {
	freeCat(c->catalog, f);
	free(c);
}

/**
 * @brief Função que retorna o código de um negócio.
 * @param b Negocio do qual se pretende obter o código.
 * @return char* com o código do negócio.
 */
char* getBusinessID(Business b) {
	char *aux = malloc(sizeof(char)*(strlen(b->businessID) + 1));
	strcpy(aux, b->businessID);
	return aux;
}

char **getBusinessReviews(Business b){
    char ** aux = malloc(sizeof(char*)*b->qtdReviews);
    for (int i = 0; i < b->qtdReviews; ++i) {
        aux[i] = malloc(sizeof(char)* (strlen(b->reviews[i])+1));
        strcpy(aux[i],b->reviews[i]);
    }
    return aux;
}

/**
 * @brief Função que devolve a estrutura associada a um negócio de um catálogo que lhe é passado como argumento.
 * @param c CatBusinesses de onde será devolvida a estrutura.
 * @param b Negócio indicando o negócio a procurar onde estará associada a estrutura.
 * @return Estrutura ou NULL caso a mesma nao se encontre lá.
 */
Business getBusiness_Struct(CatBusinesses c, char *b) {
	Business e = (Business) getCatStruct(c->catalog, b);
	return e;
}


Business addReviewToBusiness(Business b, char *review){
    b->reviews = realloc(b->reviews,sizeof(char *)*(b->qtdReviews+1));
    b->reviews[b->qtdReviews] = malloc(sizeof(char)* (strlen(review)+1));
    strcpy(b->reviews[b->qtdReviews++],review);
    return b;
}

/*-----------------------------------QUERIE2-----------------------------------*/


/**
 * @brief Função que insere numa estrutura auxiliar TABLE as strings dos negócios e o número total dos negócios começados pela letter dada.
 * @param cat CatBusinesses que contém os negócios.
 * @param letter char pela qual são iniciados os negócios que se pretende imprimir.
 * @param q TABLE de onde obtemos as informações.
 */

void printBusinessLetterQ2(CatBusinesses cat, char letter, TABLE q){
 	AVL tree;
 	NodeAvl node;
  	int index;
  	for(index = 0; index < NR_LETRAS; index++){
   		tree = getAVLCat(cat->catalog,index);
    	node = getNodo(tree);
 		percorreBusinessQ2(node,letter,q);
 		freeNode(node);
    }
}

/**
 * @brief Função que percorre o nodo de cada árvore, de forma inorder, obtendo a string que lá está inserida e colocando-a na estrutura auxiliar TABLE.
 * @param tree NODO a percorrer.
 * @param q TABLE onde se vai inserir as strings.
 */
void percorreBusinessQ2(NodeAvl node, char l, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreBusinessQ2(esq,l,q);
	freeNode(esq); 

	Business b = (Business) getCont(node);
	if(b->bname[0] == l)
		insertList(b->bname,q);

	NodeAvl dir = getNodoDir(node);
    percorreBusinessQ2(dir,l,q);
	freeNode(dir);
}

/*-----------------------------------QUERIE3-----------------------------------*/

/**
 * @brief Função que insere numa estrutura auxiliar TABLE as informações dos negócios
 * @param bid char pela qual são iniciados os negócios que se pretende imprimir.
 * @param q TABLE de onde obtemos as informações.
 */

void getBusinessInfoQ3(CatBusinesses cat, char* bid, TABLE q){
	int i;
	if(bid[0] >= '0' && bid[0] <= '9') {
      i = 26 + bid[0] - '0';
    }
    else if((bid[0] >= 'a' && bid[0] <= 'z') || (bid[0] >= 'A' && bid[0] <= 'Z')){
    	i = toupper(bid[0]) - 'A';
    }
    else if(bid[0] == '-') i = 36;
    else i = 37;

    AVL tree = getAVLCat(cat->catalog,i);
	NodeAvl node = getNodo(tree);
    Business b = procuraId(node,bid);
 	freeNode(node);
    insereBusinessInfo( b->bname,b->city,b->state,b->qtdReviews,q);
}

Business procuraId(NodeAvl node ,char* id){
	node = nodeLookUpKey(node, id);
	return (Business) getCont(node);
}

/*-----------------------------------QUERIE4-----------------------------------*/


void getBusinessNameList(CatBusinesses cat, char** l, int a,TABLE q){
	int j,i;
	char* bid;
    for(j=0; j<a ;j++){
    	bid = l[j];
    	if(bid[0] >= '0' && bid[0] <= '9') {
          i = 26 + bid[0] - '0';
   	    }
  	    else if((bid[0] >= 'a' && bid[0] <= 'z') || (bid[0] >= 'A' && bid[0] <= 'Z')){
  	    	i = toupper(bid[0]) - 'A';
   	    }
    	else if(bid[0] == '-') i = 36;
        else i = 37;
    	
       	AVL tree = getAVLCat(cat->catalog,i);
       	NodeAvl node = getNodo(tree);
    	Business b = procuraId(node,bid);
 		freeNode(node);
   	    insertList(b->bname,q);
    }
}

/*-----------------------------------QUERIE5-----------------------------------*/

void getBusinessIdList(CatBusinesses cat,char* city,TABLE q){
	AVL tree;
	NodeAvl node;
  	int index;
  	for (index = 0; index < NR_LETRAS; index++) {
        tree = getAVLCat(cat->catalog,index);
        node = getNodo(tree);
        percorreBusinessQ5(node,city,q);
        freeNode(node);
    }
}

void percorreBusinessQ5(NodeAvl node, char* city, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreBusinessQ5(esq,city,q);
	freeNode(esq); 

	Business b = (Business) getCont(node);
	if(strcmp(b->city,city)==0)
		insertList(b->businessID,q);
		
	NodeAvl dir = getNodoDir(node);
    percorreBusinessQ5(dir,city,q);
	freeNode(dir);
}


/*-----------------------------------QUERIE5_6-----------------------------------*/


void getAllCities(CatBusinesses cat,TABLE q){
	AVL tree;
  	int index;
  	for(index = 0; index < NR_LETRAS; index++){
   		tree = getAVLCat(cat->catalog,index);
   		NodeAvl node = getNodo(tree);
 		percorreBusinessCity(node,q);
   		freeNode(node);
    }
}

/**
 * @brief Função que percorre o nodo de cada árvore, de forma inorder, obtendo a string que lá está inserida e colocando-a na estrutura auxiliar TABLE.
 * @param tree NODO a percorrer.
 * @param q TABLE onde se vai inserir as strings.
 */
void percorreBusinessCity(NodeAvl node,TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreBusinessCity(esq,q);
	freeNode(esq); 

	Business b = (Business) getCont(node);
	insertCity(b->city,q);

	NodeAvl dir = getNodoDir(node);
    percorreBusinessCity(dir,q);
	freeNode(dir);
}

/*-----------------------------------QUERIE7-----------------------------------*/

Boolean verifyState(CatBusinesses cat, char** business,int tam){
	int i;
	char* bid = business[0];

	if(bid[0] >= '0' && bid[0] <= '9') {
      i = 26 + bid[0] - '0';
    }
    else if((bid[0] >= 'a' && bid[0] <= 'z') || (bid[0] >= 'A' && bid[0] <= 'Z')){
    	i = toupper(bid[0]) - 'A';
    }
    else if(bid[0] == '-') i = 36;
    else i = 37;

    AVL tree = getAVLCat(cat->catalog,i);
	NodeAvl node = getNodo(tree);

    Business b = procuraId(node,bid);
    char* state = b->state;

	for(int j = 1; i < tam; i++){
		bid = business[j];
		if(bid[0] >= '0' && bid[0] <= '9') {
      		i = 26 + bid[0] - '0';
    	}
    	else if((bid[0] >= 'a' && bid[0] <= 'z') || (bid[0] >= 'A' && bid[0] <= 'Z')){
    		i = toupper(bid[0]) - 'A';
    	}
    	else if(bid[0] == '-') i = 36;
   		else i = 37;

		b = procuraId(node,business[j]);
		if(strcmp(b->state,state)==0) return true;
	}
 	freeNode(node);
 	return false;
}

/*-----------------------------------QUERIE8-----------------------------------*/

void percorreBusinessQ8(NodeAvl node, char* category, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreBusinessQ8(esq,category,q);
	freeNode(esq); 

	Business b = (Business) getCont(node);
	for(int i=0; i<(b->tam);i++){
		if(strcmp(b->categorys[i],category)==0)
			insertList(b->businessID,q);
	}
	
	NodeAvl dir = getNodoDir(node);
    percorreBusinessQ8(dir,category,q);
	freeNode(dir);
}

void getBusnessFromCategory(CatBusinesses cat ,char* category, TABLE q){
	AVL tree;
	NodeAvl node;
  	int index;
  	for (index = 0; index < NR_LETRAS; index++) {
        tree = getAVLCat(cat->catalog,index);
        node = getNodo(tree);
        percorreBusinessQ8(node,category,q);
        freeNode(node);
    }
}
