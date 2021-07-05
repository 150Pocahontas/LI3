#ifndef CATALOG_H
#define CATALOG_H

#include "avlTree.h"
#include "headers.h"

typedef struct catalog *Catalog;

AVL getAVLCat(Catalog c, int index);

Catalog initCat();
Catalog insertCat(Catalog c, char* key, void * estrutura);
Catalog changeCat(Catalog c, char* key, void* estrutura);

void freeCat(Catalog c, Funcao f);
void printCat(Catalog c);
void* getCatStruct(Catalog c, char* key);

Boolean existCat(Catalog c, char* key);

#endif