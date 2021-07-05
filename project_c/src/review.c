/**
 * @file reviews.c
 * @brief Ficheiro que contém a API relativa à implementação das avaliações.
 */

#include "review.h"

struct review{
	int a;
	char* reviewID;
	char* userID;
	char* businessID;
	float stars;
	int useful;
	int funny;
	int cool;
	char* date;
	char* text;
};

/**
 * @file reviews.c
 * @brief Ficheiro que contém a API relativa à implementação dos reviews.
 */
struct catReviews{
	Catalog catalog;
};

struct pair {
    float totalStars;
    int total;
};


/**
 * @brief Função que cria uma Review.
 * @param cat CatReview Catálogo de reviews que vai ser criado e onde se vai inserir a informação.
 * @param b Business a ser inserido.
 * @param 
 */

Review createReview(){
	Review r = (Review) malloc((sizeof(struct review)));
	r -> reviewID = NULL;
	r -> userID = NULL;
	r -> businessID = NULL;
	r -> stars = 0;
	r -> useful = 0;
	r -> cool = 0;
	r -> date = NULL;
	r -> text = NULL;
	return r;
}

/**
 * @brief Função que altera uma review.
 * @param r Review que vai ser alterada.
 * @param rid char* apontador para o novo id de review.
 * @param 
 * @return Review alterado.
 */
Review changeReview(Review r,char* rid ,char* uid, char* bid, float stars, int useful,int funny,int cool,char* date,char* text){
	if( r-> reviewID) {
		free(r -> reviewID);
		free(r -> userID);
		free(r -> businessID);
		free(r -> date);
		free(r -> text);
	}
	r -> reviewID = malloc((strlen(rid) + 1) * sizeof(char));
	strcpy(r -> reviewID,rid);
	r -> userID = malloc((strlen(uid) + 1) * sizeof(char));
	strcpy(r -> userID,uid);
	r -> businessID = malloc((strlen(bid) + 1) * sizeof(char));
	strcpy(r -> businessID,bid);
	r -> stars = stars;
	r -> useful = useful;
	r -> funny = funny;
	r -> cool = cool;
	r -> date = malloc((strlen(date) + 1) * sizeof(char));
	strcpy(r->date,date);
	r -> text = malloc((strlen(text) + 1) * sizeof(char));
	strcpy(r -> text,text);
	return r;
}

/**
 * @brief Função que inicializa um catalogo de reviews.
 * @return CatReviews inicializada.
 */
CatReviews initCatReview(){
	CatReviews new = malloc((sizeof(struct catReviews)));
	new->catalog = initCat();
	return new;
}

/**
 * @brief Função que retorna o id de um Review.
 * @param r Review do qual se pretende obter o id.
 * @return char* com o id do Review.
 */
char* getReviewID(Review r){
  char *aux = malloc(sizeof(char)*(strlen(r->reviewID)+1));
  strcpy(aux,r->reviewID);
  return aux;
}

char* getReviewText(Review r){
  char *aux = malloc(sizeof(char)*(strlen(r->text)+1));
  strcpy(aux,r->text);
  return aux;
}

char* getBusinessIDFromReview(Review r){
    char *aux = malloc(sizeof(char)*(strlen(r->businessID)+1));
    strcpy(aux,r->businessID);
    return aux;
}

/**
 * @brief Função que insere um review num catálogos de review.
 * @param cat CatReview onde vai inserir o review.
 * @param r review a ser inserido.
 * @return CatReview com o review inserido.
 */
CatReviews insertReview(CatReviews cat, Review r){
    char *aux = r->reviewID;
    cat -> catalog = insertCat(cat->catalog,aux,r);
    free(aux);
    return cat;
}

float getStarsReview(Review r){
    return r->stars;
}

Catalog getCatalogReviews(CatReviews c) {
    return c->catalog;
}

/**
 * @brief Função que liberta da memória o espaço ocupado por um catálogo de negócios.
 * @param c CatReviews a ser limpo da memória.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void freeCatReviews(CatReviews c, Funcao f) {
	freeCat(c->catalog, f);
	free(c);
}

/*--------------------------------------------------QUERIE4--------------------------------------------------*/

/**
 * @brief Função que insere numa estrutura auxiliar TABLE as strings dos negócios com reviews feitas por um utilizdor.
 * @param cat CatReviews que contém as reviews.
 * @param userID char* id do utilizador.
 * @param q TABLE de onde obtemos as informações.
 */

void getBusinessReviewedbyUser(CatReviews cat,char * userID ,TABLE q){
	AVL tree;
	NodeAvl node;
  	int index;
  	for(index = 0; index<NR_LETRAS; index++){
   		tree = getAVLCat(cat->catalog,index);
   		node = getNodo(tree);
 		percorreReviewsQ4(node,userID,q);
   		freeNode(node);
    }
}

/**
 * @brief Função que percorre o nodo de cada árvore, de forma inorder, obtendo a string que lá está inserida e colocando-a na estrutura auxiliar TABLE.
 * @param tree NODO a percorrer.
 * @param q TABLE onde se vai inserir as strings.
 */
void percorreReviewsQ4(NodeAvl node, char* uid, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreReviewsQ4(esq,uid,q);
	freeNode(esq); 

	Review r = (Review) getCont(node);
	if(strcmp(uid, r->userID) == 0)
		insertIdList(r->businessID,q);

	NodeAvl dir = getNodoDir(node);
    percorreReviewsQ4(dir,uid,q);
	freeNode(dir);
}

/*--------------------------------------------------QUERIE5--------------------------------------------------*/

void getBusinessListByStar(CatReviews cat, char** businessID,int qtdBusiness,float stars,int r,TABLE q){
	AVL tree;
	NodeAvl node;
	int index,i;
	for (i = 0; i < qtdBusiness; i++){
	  	for(index = 0; index<NR_LETRAS; index++){

	        tree = getAVLCat(cat->catalog,index);
	        node = getNodo(tree);

	        TABLE t = initTableList(r,5);
	        percorreReviewsQ5(node,businessID[i],t);

			char** list = getList(t);	 
			int a = getNumTotal(t);      
	        float totalStars = 0.0f;
        	int total = 0;

	        for (int j = 0; j < a; j++){
	        	Review r = procuraRId(node,list[j]);
	            totalStars += r->stars;
	            total++;
        	}
        	freeTableList(t);

        	if(total != 0 && totalStars/(float)total >=stars)
        		insertIdList(businessID[i],q);

        	free(node);
	    }
	}
}

void percorreReviewsQ5(NodeAvl node, char* businessID, TABLE t){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreReviewsQ5(esq,businessID,t);
	freeNode(esq); 

	Review r = (Review) getCont(node);
	if(strcmp(r->businessID,businessID)==0){
		insertIdList(r->reviewID,t);
	}
		
	NodeAvl dir = getNodoDir(node);
	percorreReviewsQ5(dir,businessID,t);
	freeNode(dir);
}

Review procuraRId(NodeAvl node ,char* id){
	node = nodeLookUpKey(node, id);
	return (Review) getCont(node);
}

/*--------------------------------------------------QUERIE6--------------------------------------------------*/
/*
void orderBusinessByStars(CatReviews cat, TABLE q){
	AVL auxiliar;
  	int index;
  	for(index = 0; index<NR_LETRAS; index++){
   		auxiliar = getAVLCat(cat->catalog,index);
    	percorreAVLReviewQ6(auxiliar,q); 
}

void percorreAVLReviewsQ4(AVL tree,TABLE q){
 	NodeAvl f = getNodo(tree);
 	percorreReviewQ6(f,q);
 	freeNode(f);
}

void percorreReviewsQ4(NodeAvl node, TABLE q){
	if(node==NULL) return;

	NodeAvl esq = getNodoEsq(node);
	percorreReviewsQ4(esq,uid,q);
	freeNode(esq); 

	Review r = (Review) getCont(node);
	insertBusinessbyStars(r->businessID,r->stars,q);

	NodeAvl dir = getNodoDir(node);
    percorreReviewsQ4(dir,uid,q);
	freeNode(dir);
}*/

/*--------------------------------------------------QUERIE8--------------------------------------------------*/
/*
void getBusinessAverageStars(CatReviews cat,char** business,int tam, int top, TABLE q){
	AVL tree;
	NodeAvl node;
	int index,i;
	char** starsList;
	char** businessList;
	for (i = 0; i < tam; i++){
		Pair stars;
		stars->total = 0;
		stars->totalStars = 0.0;

	  	for(index = 0; index<NR_LETRAS; index++){

	        tree = getAVLCat(cat->catalog,index);
	        node = getNodo(tree);

	        percorreReviewsQ5(node,business[i],stars);

        	free(node);
		}

		if(stars->total != 0){
			//char** aux = businessList;
			char** auxS = starsList;
			for(int j =0; j<top; j++){
				if(stars->totalStars/(float)(stars->total) > atof(auxS[i])){
					starsList[i] = stars->totalStars/(float)(stars->total);
					businessList[i] = business[i];
					j++;
				}
			}
		}    		
	}


}

*/
/*--------------------------------------------------QUERIE9--------------------------------------------------*/

void getReviewsByWordQ9(CatReviews cat ,char* word ,TABLE q){
	for(int i = 0; i < NR_LETRAS; ++i){
        AVL tree = getAVLCat(cat->catalog,i);
        NodeAvl node = getNodo(tree);
        percorreReviewsQ9(node,word,q);
        freeNode(node);
    }
}

void percorreReviewsQ9(NodeAvl node, char *word, TABLE q){
    if(node==NULL) return;

    NodeAvl esq = getNodoEsq(node);
    percorreReviewsQ9(esq, word,q);
    freeNode(esq);
   

    Review r = (Review) getCont(node);
    if(strstr(r->text,word) != NULL)	
    	insertList(r->reviewID,q);
        

    NodeAvl dir = getNodoDir(node);
    percorreReviewsQ9(dir, word,q);
    freeNode(dir);
}
