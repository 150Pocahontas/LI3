#include "catalog.h"

/**
 * @file catalog.c
 * @brief Ficheiro que contém a API relativa a implementação de catálogos.
 */

/*Estrutura que contém um array em que cada índice é um apontador para uma AVL */
struct catalog {
   AVL indice[NR_LETRAS];
};

/**
 * @file Função que inicializa um novo catálogo.
 * @return Novo catálogo nulo.
 */
Catalog initCat() {
   Catalog c = (Catalog) malloc (sizeof(struct catalog));
   for(int i = 0; i < NR_LETRAS; i++){
      c->indice[i] = initAVL();
   }
   return c;
}

/**
 * @brief Função que verifica se existe uma key num catálogo.
 * @param c Catalog onde a existência é verificada.
 * @param key *char a procurar no array.
 * @return Boolean com o resultado.
 */
Boolean existCat(Catalog c, char* key) {
   int i;
   if(key[0] >= '0' && key[0] <= '9') {
      i = 26 + key[0] - '0';
   }
   else if((key[0] >= 'a' && key[0] <= 'z') || (key[0] >= 'A' && key[0] <= 'Z')) {
      i = toupper(key[0]) - 'A';
   }
   else if(key[0] == '-') i = 36;
   else i = 37;

   return avlLookUp(c->indice[i], key);
}

/**
 * @brief Função que devolve a estrutura associada a uma key de um catálogo que lhe é passada como argumento.
 * @param c Catalog de onde será devolvida a estrutura.
 * @param key *char indicando o valor a procurar onde estará associada a estrutura.
 * @return void* com apontador para a estrutura ou NULL caso a mesma nao se encontre lá.
 */
void* getCatStruct(Catalog c, char* key){
   int i;
   if(key[0] >= '0' && key[0] <= '9') {
      i = 26 + key[0] - '0';
   }
   else if((key[0] >= 'a' && key[0] <= 'z') || (key[0] >= 'A' && key[0] <= 'Z')) {
      i = toupper(key[0]) - 'A';
   }
   else if(key[0] == '-') i = 36;
   else i = 37;

   return avlGetStruct(c->indice[i],key);
}

/**
 * @brief Função que insere no catálogo tendo como referência de posicionamento um char* key.
 * @param c Catalog onde insere.
 * @param key char* que indica o local onde inserir.
 * @param estrutura Conteúdo/Estrutura a inserir.
 * @return catálogo com a nova estrutura adicionada.
 */
Catalog insertCat(Catalog c, char* key, void * estrutura) {
   int i;
   if(key[0] >= '0' && key[0] <= '9') {
      i = 26 + key[0] - '0';
   }
   else if((key[0] >= 'a' && key[0] <= 'z') || (key[0] >= 'A' && key[0] <= 'Z')) {
      i = toupper(key[0]) - 'A';
   }
   else if(key[0] == '-') i = 36;
   else i = 37;

   c->indice[i] = avlInsert(c->indice[i],key,estrutura);
   return c;
}

/**
 * @brief Função que altera uma estrutura no catálogo tendo como referência de posicionamento um char* key.
 * @param c Catalog que vai ser alterado.
 * @param key char* que indica o local onde altera.
 * @param estrutura Conteúdo/Estrutura que vai alterar a existente.
 * @return catálogo atualizado.
 */
Catalog changeCat(Catalog c, char* key, void* estrutura) {
   int i;
   if(key[0] >= '0' && key[0] <= '9') {
      i = 26 + key[0] - '0';
   }
   else if((key[0] >= 'a' && key[0] <= 'z') || (key[0] >= 'A' && key[0] <= 'Z')) {
      i = toupper(key[0]) - 'A';
   }
   else if(key[0] == '-') i = 36;
   else i = 37;
   c->indice[i] = updateAVL(c->indice[i],key,estrutura);
   return c;
}

/**
 * @brief Função com o objetivo de limpar da memória um dado catálogo.
 * @param c Catalog a limpar da memória.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void freeCat(Catalog c, Funcao f) {
   int i;
   for(i = 0;i<NR_LETRAS;i++)
      avlFree(c -> indice[i],f);
   free(c);
}

/**
 * @brief Função que imprime um catálogo.
 * @param c Catalog a imprimir.
 */
void printCat(Catalog c) {
   int i;
   for(i = 0; i<NR_LETRAS;i++)
      printAVLString(c->indice[i]);
}

/**
 * @brief Função que devolve a AVL que se encontra numa determinada posição do array.
 * @param c Catalog onde se procura.
 * @param index int que determina a posição do array.
 * @return AVL que se encontra nessa posição do array.
 */
AVL getAVLCat(Catalog c, int index) {
  return c -> indice[index];
}