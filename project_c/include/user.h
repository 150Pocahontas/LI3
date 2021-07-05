#ifndef USER_H
#define USER_H

#include "headers.h"
#include "catalog.h"
#include "queriesStr.h"

typedef struct user *User;
typedef struct catUsers *CatUsers;

User createUser();
User changeUser(User u, char* id, char* username, char** friends,int tam);

Boolean existUser (CatUsers c, char *key);

CatUsers initUsersCat();
CatUsers insertUser (CatUsers c, User u);
CatUsers insertUserCat (CatUsers c, User u, void* Estrutura);
CatUsers changeUsersCat (CatUsers c, User u, void * Estrutura);
Catalog getCatalogUsers(CatUsers c);

void freeCatUsers (CatUsers c, Funcao f);
void printUsers(CatUsers c);
void freeUser(User u);

char* getUserID(User u);
char* getUsername(User u);
void* getUserStruct(CatUsers c, User u);

void getAllUsers(CatUsers cat ,TABLE q);
void percorreUsersQ9(NodeAvl node, TABLE q);

#endif