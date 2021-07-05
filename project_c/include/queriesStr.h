#ifndef QUERIESSTR_H
#define QUERIESSTR_H

#include "headers.h"
#include "catalog.h"

typedef struct table *TABLE;

char** getList(TABLE q);
char** getIdList(TABLE q);

char* getbID(TABLE q);
char* getBName(TABLE q);
char* getBCity(TABLE q);
char* getBState(TABLE q);
int getBqtdReviews(TABLE q);

int getNumTotal(TABLE q);
int getNumTotalID(TABLE q);
int* getTam(TABLE q);

void insertList(char * key, TABLE q);
void insertIdList(char* bid , TABLE q);
void insertListInList(char** l, int n, TABLE q);

TABLE initTableList(int n, int query);
TABLE initTableBusiness(int query);
TABLE initTableLists(int n,int query);
TABLE initTable(int n, int query);

void freeTableList(TABLE q);
void freeTableBusiness(TABLE q);
void freeTableLists(TABLE q);

void insereBusinessInfo(char* bname, char* city, char* state, int qtdr ,TABLE q);
void insertCity(char* city, TABLE q);

TABLE getMemory(Catalog cat,char* var);
void saveMemory(Catalog cat,char* var, TABLE q);
int getQuery(TABLE q);
char* getPath(TABLE q);

void insertPath(char* path, TABLE q);
TABLE fromCSV(char* path);
#endif