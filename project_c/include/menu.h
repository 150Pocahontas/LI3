#ifndef MENU_H
#define MENU_H

#include "headers.h"
#include "colors.h"
#include "business.h"
#include <sys/ioctl.h>

void printMenu(Boolean v);

void printQ2(char **a, int i, int f, int num, int pag);
void printQ3(char* bname, char* city, char* state, int qtdReviews);
void printQ4(char ** l, char** l2,  int i, int f, int num, int pag);
void printQ5(char ** l,char** l2, int i, int f, int num, int pag);
void printQ6(char** l, int i, int f, int num, int pag);
void printQ7(char** l,int i, int f, int num, int pag);
void printQ8(char** l,int i, int f, int num, int pag);
void printQ9(char** l,int i, int f, int num, int pag);

#endif