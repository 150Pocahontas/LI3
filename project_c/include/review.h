#ifndef REVIEW_H
#define REVIEW_H

#include "headers.h"
#include "catalog.h"
#include "user.h"
#include "business.h"
#include "avlTree.h"

typedef struct review *Review;
typedef struct catReviews *CatReviews;
typedef struct pair *Pair;

Review createReview();
Review changeReview(Review r,char* rid ,char* uid, char* bid, float stars, int useful,int funny,int cool,char* date,char* text);
Review getReview_Struct(CatReviews c, char *b);

CatReviews initCatReview();
CatReviews insertReview(CatReviews cat, Review r);
Catalog getCatalogReviews(CatReviews c);

char* getReviewID(Review r);
char* getReviewText(Review r);

void getBusinessReviewedbyUser(CatReviews cat,char * uid ,TABLE q);
void percorreReviewsQ4(NodeAvl node, char* uid, TABLE q);
void freeReview(Review r);
void freeCatReviews(CatReviews c, Funcao f);

float getStarsReview (Review r);

void  getReviewListbyStars(CatReviews cat, float stars, char*** reviews, int totalreviews, TABLE q);

void getBusinessListByStar(CatReviews cat, char** businessID,int qtdBusiness,float stars,int r,TABLE q);
void percorreReviewsQ5(NodeAvl node, char* businessID, TABLE t);
Review procuraRId(NodeAvl node ,char* id);


void getBusinessAverageStars(CatReviews cat,char** business,int tam, int top, TABLE q);

void getReviewsByWordQ9(CatReviews cat ,char* word ,TABLE q);
void percorreReviewsQ9(NodeAvl node, char *word, TABLE q);

#endif
