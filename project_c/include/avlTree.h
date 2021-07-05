#ifndef AVLTREE_H
#define AVLTREE_H

#include "headers.h"

typedef struct nodeAvl *NodeAvl;
typedef struct avl* AVL;
typedef void(*Funcao)(void*);

AVL initAVL();
AVL avlInsert(AVL tree, char* key, void* estrutura);
AVL updateAVL(AVL tree, char* value, void* estrutura);

NodeAvl newNode(NodeAvl node, char* key, void* v);
NodeAvl rightRotate(NodeAvl y);
NodeAvl leftRotate(NodeAvl x);
NodeAvl insertNode(NodeAvl node, char* info, void* v);
NodeAvl nodeLookUpKey(NodeAvl node, char* key);
NodeAvl updateNode(NodeAvl node, char* value, void* estrutura);
NodeAvl getNodo(AVL a);
NodeAvl getNodoEsq(NodeAvl n);
NodeAvl getNodoDir(NodeAvl n);

void treeFree(NodeAvl node, void(*f) (void*));
void freeNode(NodeAvl node);
void printAvlContent(NodeAvl tree);
void printAVLString(AVL tree);
void avlFree(AVL nodo, void(*f) (void*));
void* avlGetStruct(AVL node, char* value);
void* getCont(NodeAvl n);
void* nodeGetStruct(NodeAvl node, char* value);

int height(NodeAvl n);
int getBalance(NodeAvl n);
int avlCount(AVL tree);
int avlGetSize(AVL tree);
int nodeLookUp(NodeAvl node, char* value);

char* getString(NodeAvl n);

Boolean avlLookUp(AVL tree, char* value);

#endif