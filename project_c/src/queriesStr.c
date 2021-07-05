/**
 * @file queriesStructs.c
 * @brief Ficheiro que contém a API relativa à implementação das estruturas.
 */

#include "queriesStr.h"

struct table{
    char* variavel;
    int query;
    char* path;

    char** list;
    int numTotal; 

    char** idList;
    int numTotalID;
    
    //char* bID;
    char* bName;
    char* bCity;
    char* bState;
    int bReviews;

    char** stars;


};

/**
 * @brief Função que devolve a lista.
 * @param q TABLE que é estrutura onde se vai buscar a lista de strings.
 * @return char** que é a lista de strings presente na estrutura.
 */
char** getList(TABLE q){
    return q->list;
}

/**
 * @brief Função que devolve a lista.
 * @param q TABLE que é estrutura onde se vai buscar a lista de strings.
 * @return char** que é a lista de strings presente na estrutura.
 */
char** getIdList(TABLE q){
    return (q->idList);
}


/**
 * @brief Função que devolve o número total de elementos em TABLE.
 * @param q TABLE que é estrutura onde se vai buscar o número total de elementos.
 * @return int que é o número total de elementos.
 */
int getNumTotal(TABLE q){
    return q->numTotal;
}

/**
 * @brief Função que devolve o número total de elementos em TABLE.
 * @param q TABLE que é estrutura onde se vai buscar o número total de elementos.
 * @return int que é o número total de elementos.
 */
int getNumTotalID(TABLE q){
    return q->numTotalID;
}


/**
 * @brief Função que insere na estrutura uma key.
 * @param key char* que é a string a inserir na list da estrutura.
 * @param q TABLE que é estrutura onde será inserida a key dada.
 */
void insertList(char* key, TABLE q){
    q->list[q->numTotal] = malloc((strlen(key) +1) * sizeof(char));
    strcpy(q->list[q->numTotal++],key);
}

/**
 * @brief Função que insere na estrutura uma key.
 * @param key char* que é a string a inserir na list da estrutura.
 * @param q TABLE que é estrutura onde será inserida a key dada.
 */
void insertIdList(char* key, TABLE q){
    q->idList[q->numTotalID] = malloc((strlen(key) +1) * sizeof(char));
    strcpy(q->idList[q->numTotalID++],key);
}

/**
 * @brief Função que inicializa a estrutura TABLE.
 * @param n int que contém o tamanho do espaço necessário alocar.
 * @return q que é a estrutura inicializada.
 */

TABLE initTableList(int n,int query){
	TABLE q = (TABLE) malloc(sizeof(struct table));
    q->query =query;
	q->numTotal = 0;
	q->list = malloc (n* sizeof (char*));
	return q;
}

/**
 * @brief Função que liberta o espaço alocado por uma estrutura TABLE.
 * @param q TABLE que é estrutura a ser libertada.
 */
void freeTableList(TABLE q){
 	if (q->list){
 		int i;
 		for (i = 0; i< q->numTotal; i++)
 			free(q->list[i]);
 		free(q->list);
 	}
 	free(q);
}


void freeTableBusiness(TABLE q){
    free(q->bName);
    free(q->bCity);
    free(q->bState);
    free(q);
}

void insereBusinessInfo( char* bname, char* city, char* state, int qtdr ,TABLE q){
    
    q->bName = bname;
    q->bCity = city;
    q->bState = state;
    q->bReviews = qtdr;
}


char* getBName(TABLE q){
    return q->bName;
} 
char* getBCity(TABLE q){
    return q->bCity;
} 
char* getBState(TABLE q){
    return q->bState;
} 

int getBqtdReviews(TABLE q){
    return q->bReviews;
} 

void freeTableLists(TABLE q){
    int i;
    if(q->list){
        for (i = 0; i< q->numTotal; i++)
            free(q->list[i]);
        free(q->list);
    }
    if(q->idList){
        for (i = 0; i< q->numTotal; i++)
            free(q->idList[i]);
        free(q->idList);
    }
    free(q);
}

/**
 * @brief Função que insere na estrutura uma key.
 * @param key char* que é a string a inserir na list da estrutura.
 * @param q TABLE que é estrutura onde será inserida a key dada.
 */

void insertCity(char* city, TABLE q){
    for(int i= 0; i<q->numTotalID;i++)
        if(q->list[i]==city) return;
    q->list[q->numTotal] = malloc((strlen(city) +1) * sizeof(char));
    strcpy(q->list[q->numTotal++],city);
}

/*--------------------------Show---------------------------*/

TABLE initTable(int n,int query){
    TABLE q = (TABLE) malloc(sizeof(struct table));
    q->query = query;
    q->variavel = NULL;
   // q->bID = NULL;
    q->path = NULL;
    q->bName = NULL;
    q->bCity = NULL;
    q->bState = NULL;
    q->bReviews = 0;
    q->numTotalID = 0;
    q->numTotal = 0;
    q->idList = malloc (n* sizeof (char*));
    q->list = malloc (n* sizeof (char*));
    return q;
}



void saveMemory(Catalog cat,char* var, TABLE q){
    q->variavel = var;
    cat = insertCat(cat,q->variavel,q);
}

TABLE getMemory(Catalog cat,char* var){
    TABLE q = (TABLE) getCatStruct(cat,var);
    return q;
}

int getQuery(TABLE q){
    return q-> query;
}

/*---------------------------fromCSV-------------------------*/
char* getPath(TABLE q){
    return q-> path;
}

void insertPath(char* path, TABLE q){
    q->path = path;
}

TABLE fromCSV(char* path){
    TABLE q = initTable(0,10);
    insertPath(path, q);
    return q;
}