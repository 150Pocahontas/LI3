/**
 * @file sgr.c
 * @brief Ficheiro que contém a API relativa à implementação da interface
 */

#include "sgr.h"

struct sgr{
	int valid;
	char usersFilePath[BUFFER]; 
	char businessFilePath[BUFFER];
	char reviewsFilePath[BUFFER];
	int qtdUsers;
	int qtdBusiness;
	int qtdReviews;
	CatUsers users;
	CatBusinesses businesses;
	CatReviews reviews;
};

static int getTotalBusinessSGR(SGR sgr);
static int getTotalReviewsSGR(SGR sgr);
static int getTotalUsersSGR(SGR sgr);
static int numeroVirgulas(char* s);


char* readFileLine(FILE *f) {
	char *res = malloc(sizeof(char) * BUFFER);
	int tamBuffer = BUFFER;
	int tam = 0;
	char c;
	int flag = 1;
	while(flag) {
		c = (char)fgetc(f);
		if(c == '\n') {
			flag = 0;
			c = '\0';
		}
		if(c == EOF) {
			free(res);
			return NULL;
		}
		res[tam++] = c;
		if(tam == tamBuffer) {
			res = realloc(res,tamBuffer * 2);
			tamBuffer *= 2;
		}
	}
	return res;
}

/**
 * @brief Função que inicializa o SGR.
 * @return SGR inicializado.
 */
SGR initSGR(){
	SGR sgr = malloc(sizeof(struct sgr));
	sgr->valid = 0;
	sgr->users = initUsersCat();
	sgr->businesses = initBusinessCat();
	sgr->reviews = initCatReview();
	return sgr;
}

/**
 * @brief Função que destrói o SGR, libertando o espaço da memória por ele ocupado.
 * @param sgr SGR que contém a informação dos módulos.
 */
void destroySGR(SGR sgr){
	freeCatUsers(sgr->users,NULL);
	freeCatBusinesses(sgr->businesses,NULL);
	freeCatReviews(sgr->reviews,NULL);
    free(sgr);
}

/**
 * @brief Função que verifica se o SGR é válido.
 * @param sgv SGR que contém a informação dos módulos.
 * @param Boolean com o resultado.
 */
Boolean SGRvalid(SGR sgr){
  if(sgr) return sgr->valid;
  return false;
}

/****************************** Queriss *****************************/ 

/**
 * @brief Função que conta o número de vírgulas
 * @param s string
 * @return número de vírgulas
 */
static int numeroVirgulas(char* s) {
	int contador = 0;
	for (int i = 0; s[i] != '\0'; i++) {
		if(s[i] == ',') contador++;
	}
	return contador;
}

/*--------------------------------------------------QUERIE1--------------------------------------------------*/
/**
 * @brief Função que carrega o conteúdo dos ficheiros para os respetivos catálogos.
 * @param sgr SGR que contém a informação dos módulos.
 * @param users  char * que contém o caminho para o ficheiro dos utilizadores.
 * @param businesses char * que contém o caminho para o ficheiro dos negócios.
 * @param reviews char * que contém o caminho para o ficheiro das reviews.
 * @return SGR com o conteúdo dos ficheiros carregado.
 */
SGR load_sgr(char *users, char *businesses, char *reviews){
	SGR sgr = initSGR();

	strcpy(sgr->usersFilePath,users);
	strcpy(sgr->businessFilePath,businesses);
	strcpy(sgr->reviewsFilePath,reviews);


	/*Inicialização do catálogo de users*/

	FILE* u = fopen(users,"r");

    char *token;
    char firstLine[BUFFER];
    char *string;

    int nv;

    char* userID;
    char* username;
    char** friends;
    
    int i = 0;

    if(fgets(firstLine,BUFFER,u)); //Skip first line

    while(true){
        User us = createUser();
        token = readFileLine(u);
        if(token == NULL) break;
        string = strdup(token);

        userID = strtok(string,";");
        username = strtok(NULL,";");
        char* aux = strtok(NULL, ";");
        if(aux != NULL) {
			nv = numeroVirgulas(aux);
		}
		else{
			nv = -1;
		}
        friends = malloc(sizeof(char*) * (nv + 1));
        friends[0] = strtok(aux, ",");
        for (int c = 1; c < nv + 1; c++){
            friends[c] = strtok(NULL, ",");
        }

        us = changeUser(us,userID,username,friends,nv + 1);
        free(string);
        free(token);
        sgr->users = insertUser(sgr->users,us);
        i++;
    }
    sgr->qtdUsers = i;
	/*Fecho dos ficheiro previamente lido*/
	fclose(u);


	/*Inicialização do catálogo de business*/

	FILE* b = fopen(businesses,"r");

	char* businessID;
	char* bname;
	char* city;
	char* state;
	char** categorys;
	i = 0;

	if(fgets(firstLine,BUFFER,b)); //Skip first line

	while(true){
        Business bs = createBusiness();
		token = readFileLine(b);
		if(token == NULL) break;
		string = strdup(token);

		businessID = strtok(string,";");
		bname = strtok(NULL,";");
		city = strtok(NULL,";");
	 	state = strtok(NULL,";");

	 	char* aux2 = strtok(NULL, ";");
	 	if(aux2 != NULL) {
			nv = numeroVirgulas(aux2);
		}
		else{
			nv = -1;
		}
	 	categorys = malloc(sizeof(char*) * (nv + 1));
	 	categorys[0] = strtok(aux2, ",");
	 	for (int c = 1; c < nv + 1; c++){
			categorys[c] = strtok(NULL, ",");
		}
		bs = changeBusiness(bs,businessID,bname,city,state,categorys,nv + 1); 
		free(string);
		free(token);
		sgr->businesses = insertBusiness(sgr->businesses,bs);
		i++;
	}
	sgr->qtdBusiness = i;

	/*Fecho dos ficheiro previamente lido*/
	fclose(b);


	/*Inicialização do catálogo de reviwes*/

	FILE* r = fopen(reviews,"r");

    char* reviewID;
    char* rUserID;
    char* rBusinessID;
    float stars;
    int useful;
    int funny;
    int cool;
    char* date;
    char* text;
    i = 0;

    if(fgets(firstLine,BUFFER,r));
    while(true){
        Review rs = createReview();
        token = readFileLine(b);
		if(token == NULL) break;
		string = strdup(token);
		reviewID = strtok(string,";");
		rUserID = strtok(NULL,";");
		rBusinessID = strtok(NULL,";");
	 	stars = atof(strtok(NULL,";"));
	 	useful = atoi(strtok(NULL,";"));
	 	funny = atoi(strtok(NULL,";"));
	 	cool = atoi(strtok(NULL,";"));
	 	date = strtok(NULL,";");
	 	text = strtok(NULL,"\0");
		rs = changeReview(rs,reviewID,rUserID,rBusinessID,stars,useful,funny,cool,date,text);
		sgr->reviews = insertReview(sgr -> reviews,rs);

		free(token);
		free(string);
		i++;
	}
	
	sgr->qtdReviews = i;
	/*Fecho dos ficheiro previamente lido*/
	fclose(r);

	sgr->valid = 1;
	puts("Loaded");
	return sgr;
}


/*--------------------------------------------------QUERIE2--------------------------------------------------*/

/**
 * @brief Função que retorna uma estrutura com os negócios começados por uma certa letra.
 * @param SGR SGR que contém a informação dos módulos.
 * @param letter char pela qual os negócios devem começar.
 * @return q Table estrutura com os negócios.
 */

TABLE businesses_started_by_letter(SGR sgr, char letter){
    TABLE  q = initTable(getTotalBusinessSGR(sgr),2);
    printBusinessLetterQ2(sgr->businesses, letter, q);
    return q;
}

/**
 * @brief Função que retorna o a quantidade total de negócios do SGR.
 * @param SGR sgr que contém a informação dos módulos.
 * @return int com o número total de negócios.
 */
static int getTotalBusinessSGR(SGR sgr){
  return sgr->qtdBusiness;
}

/*--------------------------------------------------QUERIE3--------------------------------------------------*/

/**
 * @brief Função que retorna uma estrutura com as informações de um negócio
 * @param sgr SGR que contém a informação dos módulos.
 * @return q TABLE estrutura com info dos negócios.
 */
TABLE business_info(SGR sgr, char *business_id) {
	TABLE q = initTable(getTotalBusinessSGR(sgr),3);
	getBusinessInfoQ3(sgr->businesses, business_id, q);
	return q;
}

/**
 * @brief Função que verifica se um dado negócio existe no SGR.
 * @param sgr SGR que contém a informação dos módulos.
 * @param business char* a ser procurado
 * @return Boolean com o resultado.
 */
Boolean existBusinessSGR(SGR sgr, char* businessID){
  return existBusiness(sgr->businesses,businessID);
}



/*--------------------------------------------------QUERIE4--------------------------------------------------*/

TABLE businesses_reviewed(SGR sgr, char *user_id){
    TABLE q = initTable(getTotalBusinessSGR(sgr),4);
    getBusinessReviewedbyUser(sgr->reviews,user_id,q);
    char**  l = getIdList(q);
    int a = getNumTotalID(q);
    getBusinessNameList(sgr->businesses,l,a,q);
    return q;
}

/**
 * @brief Função que verifica se um dado user existe no SGR.
 * @param sgr SGR que contém a informação dos módulos.
 * @param user char* a ser procurado.
 * @return Boolean com o resultado.
 */
Boolean existUserSGR(SGR sgr, char* userID){
    return existUser(sgr->users,userID);
}

/*--------------------------------------------------QUERIE5--------------------------------------------------*/

TABLE business_by_star_and_city(SGR sgr,float stars,char *cidade){
    TABLE t = initTable(getTotalBusinessSGR(sgr),5);
    getBusinessIdList(sgr->businesses,cidade,t);
    char** l = getList(t);
    int a = getNumTotal(t);
    int r = getTotalReviewsSGR(sgr);
    TABLE q = initTable(getTotalBusinessSGR(sgr),5);
    getBusinessListByStar(sgr->reviews,l,a,stars,r,q);
    freeTableList(t);

    l = getIdList(q);
    a = getNumTotalID(q);
    getBusinessNameList(sgr->businesses,l,a,q);
    return q;
}

/**
 * @brief Função que retorna o a quantidade total de reviews do SGR.
 * @param SGR sgr que contém a informação dos módulos.
 * @return int com o número total de negócios.
 */
static int getTotalReviewsSGR(SGR sgr){
    return sgr->qtdReviews;
}

/*--------------------------------------------------QUERIE6--------------------------------------------------*/
/*
TABLE top_businesses_by_city(SGR sgr, int top){
	TABLE q = initTable(getTotalBusinessSGR(sgr,6));
    getAllCities(sgr->businesses,q);
    char** l = getList(q);
    int a = getNumTotal(q);
    freeTableList(q);
    for(int i =0;i<a;i++){
        float s = 5;
        int c = 0;
        for(s=5;c<top;s-=0.1){
            q = business_by_star_and_city(sgr,s,l[i]);
            c = getNumTotal(q); 
        }    
    }
    return q;
}
*/
/*--------------------------------------------------QUERIE7--------------------------------------------------*/
TABLE international_users(SGR sgr) {
    TABLE q = initTable(getTotalUsersSGR(sgr),7);
    getAllUsers(sgr->users,q);
    char** l = getList(q);
    int a = getNumTotal(q);
    TABLE t = initTable(getTotalUsersSGR(sgr),7);
    for(int i = 0; i<a; i++){
        getBusinessReviewedbyUser(sgr->reviews,l[i],q);
        char** l2 = getIdList(q);
        int tam = getNumTotalID(q);
        if(verifyState(sgr->businesses,l2,tam)) insertList(l[i],t);
    }
    freeTableLists(q);
    return t;
}

/**
 * @brief Função que retorna o a quantidade total de reviews do SGR.
 * @param SGR sgr que contém a informação dos módulos.
 * @return int com o número total de negócios.
 */
static int getTotalUsersSGR(SGR sgr){
    return sgr->qtdUsers;
}

/*--------------------------------------------------QUERIE8--------------------------------------------------*/

/*TABLE top_businesses_with_category(SGR sgr,int n, char* category){
	TABLE q = initTable(n,8);
	getBusnessFromCategory(sgr->businesses,category,n,q);
	char** l = getList(q);
	int a = getNumTotal(q);
	getBusinessAverageStars(sgr->reviews,l,a,q);
}*/

/*--------------------------------------------------QUERIE9--------------------------------------------------*/

TABLE reviews_with_word(SGR sgr, char *word){
    TABLE q = initTable(getTotalReviewsSGR(sgr),9);
    getReviewsByWordQ9(sgr->reviews,word,q);
    return q;
}
