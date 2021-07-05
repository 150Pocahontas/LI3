#ifndef SGR_H
#define SGR_H

#include "headers.h"
#include "user.h"
#include "business.h"
#include "menu.h"
#include "review.h"
#include "queriesStr.h"

typedef struct sgr *SGR;

SGR initSGR();

void free_SGR(SGR sgr);
void destroySGR(SGR sgr);

Boolean SGRvalid(SGR sgr);
Boolean existBusinessSGR(SGR sgr, char* businessID);
Boolean existUserSGR(SGR sgr, char* userID);
Boolean existCitySGR(SGR sgr, char* city);

SGR load_sgr(char *users, char *businesses, char *reviews);
TABLE businesses_started_by_letter(SGR sgr, char letter);
TABLE business_info(SGR sgr, char *business_id);
TABLE businesses_reviewed(SGR sgr, char *user_id);
TABLE business_by_star_and_city(SGR sgr,float stars,char *cidade);
TABLE top_businesses_by_city(SGR sgr, int top);
TABLE international_users(SGR sgr); 
TABLE top_businesses_with_category(SGR sgr,char* categorie,int n);
TABLE reviews_with_word(SGR sgr, char *word);

void reviews_with_word_aux(SGR sgr,NodeAvl node, char *word, char ***res,int *tam);

#endif