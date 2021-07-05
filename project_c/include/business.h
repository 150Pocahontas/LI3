#ifndef BUSINESS_H
#define BUSINESS_H

#include "queriesStr.h"
#include "headers.h"
#include "catalog.h"

typedef struct business *Business;
typedef struct CatBusinesses *CatBusinesses;

Business createBusiness();
Business changeBusiness(Business b, char* id, char* bname, char* city,char* state, char** categories, int tam);
Business getBusiness_Struct (CatBusinesses c, char* b);
Business addReviewToBusiness(Business b, char *review);

CatBusinesses initBusinessCat();
CatBusinesses insertBusiness(CatBusinesses c, Business b);
CatBusinesses insertBCat(CatBusinesses cat, Business b, void* Estrutura);
CatBusinesses changeCatBusiness (CatBusinesses c, Business b, void *Estrutura);

void freeCatBusinesses (CatBusinesses c, Funcao f);
void printBusinesses (CatBusinesses c);
void freeBusiness(Business b);

char* getBusinessID(Business b);
char* getBusinessName(Business b);
char* getBusinessCity(Business b);
char **getBusinessReviews(Business b);

int getBusinessTotalReviewNumber(Business b);

Catalog getCatalogBusiness(CatBusinesses c);

Boolean existBusiness(CatBusinesses c, char *key);

/*--------------------------QUERIES--------------------------*/

void percorreBusinessQ2(NodeAvl tree, char l, TABLE q);
void printBusinessLetterQ2(CatBusinesses cat, char letra, TABLE q);

void getBusinessInfoQ3(CatBusinesses cat, char* bid, TABLE q);
Business procuraId(NodeAvl tree,char* id);

void getBusinessNameList(CatBusinesses cat, char** l, int a,TABLE q);

void getBusinessIdList(CatBusinesses cat,char* city,TABLE q);
void percorreBusinessQ5(NodeAvl node, char* city, TABLE q);

void percorreBusinessCity(NodeAvl node,TABLE q);
void getAllCities(CatBusinesses cat,TABLE q);

Boolean verifyState(CatBusinesses cat, char** business,int tam);

void percorreBusinessQ8(NodeAvl node, char* category, TABLE q);
void getBusnessFromCategory(CatBusinesses cat ,char* category, TABLE q);

#endif