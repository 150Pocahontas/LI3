#include "interpreter.h"
#include "time.h"

#define user_Path_default "./dados/users_full.csv"
#define business_Path_default "./dados/business_full.csv"
#define reviews_Path_default "./dados/reviews_1M.csv"

static SGR loadFromFiles(SGR sgr);
static Boolean isFloat(char* s);
static void remove_spaces(char* s);

    clock_t t1;
    char option;

/**
 * @brief Função que vai receber inputs do utilizador, 
 *   passa-os às funções respetivas e retorna o output.
 * @param sgr SGR que contém a informação dos módulos.
 * @return int com o output.
 */

static int isFloat(char* s){
    int dot = 1;
    for (int i = 0; s[i] != '\n' && s[i] != '\0'; ++i) {
        if(i != 0 && s[i] == '.' && dot == 1){
            dot = 0;
        }else if(s[i]>='0' && s[i]<='9'){}else return false;
    }
    return true;
}

static void remove_spaces(char* s) {
    const char* d = s;
    do{
        while (*d == ' ') {
            ++d;
        }
    } while ((*s++ = *d++));
}


int interpreter(SGR sgr){
    
    TABLE q;
    Catalog variaveis = initCat();

    int aux1;
    int aux2;
    int aux3;
    int pag;
    char charAux;
    char letra;
    char input[BUFFER];
    char* funcao;
    char* segundaParte;
    char* variavel;
    Boolean segue = false;

    while(1){
        Boolean valid = SGRvalid(sgr);
        printMenu(valid);
        printf("\n> ");

        if(fgets(input,BUFFER,stdin));
        remove_spaces(input);

        if(input[0] == 'q' && input[1] == 'u' && input[2] == 'i' && input[3] == 't'){
            return END;
        }
        

        if (input[0] == '1'){
            if(system("clear"));
            sgr = loadFromFiles(sgr);
        }else{
            char *p = strtok(input, "(");
            char* primeiraParte = strdup(p); // dar free a tudo que tenha strdup
            p = strtok(NULL,")");
            segundaParte = strdup(p);

            /* toCSV */
            if(input[0] == 't' && input[1] == 'o' && input[2] == 'C' && input[3] == 'S' && input[4] == 'V'){
                    char* var = strdup(strtok(segundaParte,","));
                    char* delim = strdup(strtok(NULL,","));
                    char* path = strdup(strtok(NULL,")"));
                    FILE *fpt;
                    fpt = fopen(path, "w+");

                    TABLE t = getMemory(variaveis,var);
                    int numtID = getNumTotalID(t);
                    int numt = getNumTotal(t);
                    char** listId = getIdList(t);
                    char** list = getList(t);
                    
                    for(int i = 0 ; i < numtID ; i++){
                        if(numt>0){
                            fprintf(fpt,"%s %s %s\n", listId[i], delim, list[i]);
                        }else{
                            fprintf(fpt,"%s\n", listId[i]);
                        }
                    }
                    fclose(fpt);
            }

            /*--------------show-----------*/
            if(primeiraParte[0] == 's' && primeiraParte[1] == 'h' && primeiraParte[2] == 'o' && primeiraParte[3] == 'w'){
                q = getMemory(variaveis,segundaParte);
                if(getQuery(q) == 2){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char **d = getList(q);
                    if (aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                    if(system("stty cooked"));
                    if(system("clear"));

                    printQ2(d, aux1, aux2, aux3, pag);
                    if(system("stty raw"));
                    
                    while ((charAux = toupper(getchar())) != 13){
                        if(charAux == '2' && aux1 < pag-1)
                            aux1++;
                        else if(charAux == '1' && aux1 > 0)
                            aux1--;
                        else if(charAux == '3' && aux2 > NR_PAGINAS){
                            aux2 -= NR_PAGINAS;
                            aux1 = 0;
                            if(aux3 % aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                            aux2 += NR_PAGINAS;
                            aux1 = 0;
                            if(aux3%aux2 == 0) 
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }
                           
                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ2(d,aux1,aux2,aux3,pag);
                        if(system("stty raw"));                    
                    }
                }
                if(getQuery(q) == 3){
                    if(system("clear"));
                    char* bname = getBName(q); 
                    char* city = getBCity(q); 
                    char* state = getBState(q); 
                    int breviews  = getBqtdReviews(q); 
                    printQ3(bname, city, state, breviews);
                    
                }
                if(getQuery(q) == 4){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char** dID = getIdList(q);
                    char** dName = getIdList(q);

                    if ( aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                    if(system("stty cooked"));
                    if(system("clear"));        
                    
                    printQ4(dID,dName,aux1,aux2,aux3,pag);

                    while ((charAux = toupper(getchar())) != 13){
                        if(charAux == '2' && aux1 < pag-1)
                            aux1++;
                        else if(charAux == '1' && aux1 > 0)
                            aux1--;
                        else if(charAux == '3' && aux2 > NR_PAGINAS){
                            aux2 -= NR_PAGINAS;
                            aux1 = 0;
                            if(aux3 % aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                            aux2 += NR_PAGINAS;
                            aux1 = 0;
                            if(aux3%aux2 == 0) 
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }
                    }

                }
                if(getQuery(q) == 5){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotalID(q);
                    char** dID = getIdList(q);
                    char** dName = getList(q);

                    if ( aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                    if(system("stty cooked"));
                    if(system("clear"));

                    printQ5(dID,dName,aux1, aux2, aux3, pag);

                    if(system("stty raw"));
                    while ((charAux = toupper(getchar())) != 13){
                        if(charAux == '2' && aux1 < pag-1)
                            aux1++;
                        else if(charAux == '1' && aux1 > 0)
                            aux1--;
                        else if(charAux == '3' && aux2 > NR_PAGINAS){
                            aux2 -= NR_PAGINAS;
                            aux1 = 0;
                            if(aux3 % aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                            aux2 += NR_PAGINAS;
                            aux1 = 0;
                            if(aux3%aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }

                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ5(dID,dName,aux1, aux2, aux3, pag);
                        if(system("stty raw"));
                    }
                }
                /*if(getQuery(q) == 6){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char** dName= getList(q);
                    char** dId = getIdList(q);

                    if ( aux3 % aux2 == 0)
                        pag = aux3/aux2;
                        else pag = aux3 / aux2+1;

                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ6(dId, dName,aux1,aux2,aux3,pag);

                        if(system("stty raw"));

                        while ((charAux = toupper(getchar())) != 13){
                            if(charAux == '2' && aux1 < pag-1)
                                aux1++;
                            else if(charAux == '1' && aux1 > 0)
                                aux1--;
                            else if(charAux == '3' && aux2 > NR_PAGINAS){
                                aux2 -= NR_PAGINAS;
                                aux1 = 0;
                                if(aux3 % aux2 == 0)
                                    pag = aux3/aux2;
                                else pag = aux3/aux2 + 1;
                            }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                                aux2 += NR_PAGINAS;
                                aux1 = 0;
                                if(aux3%aux2 == 0)
                                    pag = aux3/aux2;
                                else pag = aux3/aux2 + 1;
                            }

                            if(system("stty cooked"));
                            if(system("clear"));

                            printQ6(dId, dName,aux1,aux2,aux3,pag);
                            if(system("stty raw"));
                }*/
                if(getQuery(q) == 7){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char** d = getList(q);

                    if (aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                    if(system("stty cooked"));
                    if(system("clear"));

                    printQ7(d,aux1,aux2,aux3,pag);

                    if(system("stty raw"));

                    while ((charAux = toupper(getchar())) != 13){
                        if(charAux == '2' && aux1 < pag-1)
                            aux1++;
                        else if(charAux == '1' && aux1 > 0)
                            aux1--;
                        else if(charAux == '3' && aux2 > NR_PAGINAS){
                            aux2 -= NR_PAGINAS;
                            aux1 = 0;
                            if(aux3 % aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                            aux2 += NR_PAGINAS;
                            aux1 = 0;
                            if(aux3%aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }

                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ7(d,aux1,aux2,aux3,pag);
                        if(system("stty raw"));
                    }
                }
                if(getQuery(q) == 8){aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char** d = getList(q);

                    if (aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ8(d,aux1,aux2,aux3,pag);

                        if(system("stty raw"));

                        while ((charAux = toupper(getchar())) != 13){
                            if(charAux == '2' && aux1 < pag-1)
                                aux1++;
                            else if(charAux == '1' && aux1 > 0)
                                aux1--;
                            else if(charAux == '3' && aux2 > NR_PAGINAS){
                                aux2 -= NR_PAGINAS;
                                aux1 = 0;
                                if(aux3 % aux2 == 0)
                                    pag = aux3/aux2;
                                else pag = aux3/aux2 + 1;
                            }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                                aux2 += NR_PAGINAS;
                                aux1 = 0;
                                if(aux3%aux2 == 0)
                                    pag = aux3/aux2;
                                else pag = aux3/aux2 + 1;
                            }

                            if(system("stty cooked"));
                            if(system("clear"));

                            printQ8(d,aux1,aux2,aux3,pag);
                            if(system("stty raw"));
                        }}
                if(getQuery(q) == 9){
                    aux1 = 0;
                    aux2 = NR_PAGINAS;
                    aux3 = getNumTotal(q);
                    char** d = getList(q);
                    if ( aux3 % aux2 == 0)
                        pag = aux3/aux2;
                    else pag = aux3 / aux2+1;

                    if(system("stty cooked"));
                    if(system("clear"));

                    printQ9(d, aux1, aux2, aux3, pag);

                    if(system("stty raw"));

                    while ((charAux = toupper(getchar())) != 13){
                        if(charAux == '2' && aux1 < pag-1)
                            aux1++;
                        else if(charAux == '1' && aux1 > 0)
                            aux1--;
                        else if(charAux == '3' && aux2 > NR_PAGINAS){
                            aux2 -= NR_PAGINAS;
                            aux1 = 0;
                            if(aux3 % aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }else if(charAux == '4' && aux2<3*NR_PAGINAS){
                            aux2 += NR_PAGINAS;
                            aux1 = 0;
                            if(aux3%aux2 == 0)
                                pag = aux3/aux2;
                            else pag = aux3/aux2 + 1;
                        }

                        if(system("stty cooked"));
                        if(system("clear"));

                        printQ9(d,aux1,aux2,aux3,pag);
                        if(system("stty raw"));
                    }
                }
                if(getQuery(q)==10){
                    TABLE t = getMemory(variaveis,variavel);
                    char *path = getPath(t);
                    FILE* fpt;
                    fpt = fopen(path, "r");
                    fclose(fpt);
                }
                printf("Press %s<Enter>%s to exit\n",RED,RESET);
                getchar();
            }else{
                char* temp = strtok(primeiraParte,"=");
                variavel = strdup(temp);
                primeiraParte = strtok(NULL,";");
                printf("%s\n",variavel );
                funcao = strdup(primeiraParte);
                printf("%s\n",funcao );
                segue = true;
            }
            
            if(SGRvalid(sgr) && segue){
                if(system("clear"));

                /* Querie 2 */
                if(strcmp(funcao,"businesses_started_by_letter")==0){
                    char* procuraLetra = strtok(segundaParte,"'");
                    char* letter = strdup(strtok(NULL,")"));
                    letra = letter[0];
                    q = businesses_started_by_letter(sgr, letra);
                    saveMemory(variaveis,variavel,q);
                    //freeTableList(q);
                }
                
                /* Querie 3 */
                if(strcmp(funcao,"business_info")==0){
                    char* procuraInput = strtok(segundaParte,"'");
                    printf("%s\n",procuraInput);
                    char* id = strdup(strtok(NULL,"'"));
                     printf("%s\n",id);
                    if(existBusinessSGR(sgr,id)){         
                        q = business_info(sgr,id);
                        saveMemory(variaveis, variavel, q);
                        //freeTableBusiness(q);
                    }else{
                        LOG_RED("Id de negócio inválido\n");
                    }
                }

                /* Querie 4 */
                if (strcmp(funcao,"businesses_reviewed")==0){
                    char* procuraLetra = strtok(segundaParte,"'");
                    char* idU = strdup(strtok(NULL,"'"));

                    if(existUserSGR(sgr,idU)){
                        q = businesses_reviewed(sgr, idU);
                        saveMemory(variaveis,variavel,q);
                        //freeTableLists(q);
                    }else{
                        LOG_RED("Id de utilizador inválido\n");
                    }
                }

                /* Querie 5 */
                if (strcmp(funcao,"business_by_star_and_city")==0){
                    
                    char* procuraStars = strtok(segundaParte,",");
                    printf("%s\n", procuraStars );
                    char* stars = strdup(strtok(NULL,","));
                    printf("%s\n", stars);
                    char* cidade = strdup(strtok(NULL,"'"));
                    printf("%s\n", cidade);

                    if(!isFloat(stars) || atof(stars)>5){
                        LOG_RED("\nNr de Estrelas inválido\n");

                    }else{
                        float star = atof(stars);
                        if(fgets(cidade,BUFFER,stdin));
                        cidade[strlen(cidade)-1] = '\0';
                        char *city = malloc(sizeof(char)*(strlen(cidade)+1));
                        strcpy(city,cidade);

                        q = business_by_star_and_city(sgr,star,city);
                        saveMemory(variaveis,variavel,q);

                        if(system("stty cooked"));
                        if(system("stty echo"));
                        //freeTableLists(q);
                    }
                }

                /* Querie 6 
                if (strcmp(funcao,"top_businesses_by_city")==0){

                    char* procuraInput = strtok(segundaParte,"'");
                    char* top = strdup(strtok(NULL,"'"));

                    if(!isdigit(top)){
                        LOG_RED("\nTop inválido\n");

                    }else{ 
                        int topInt = atoi(top);
                        q = top_businesses_by_city(sgr,top);
                        saveMemory(variaveis,variavel,q);
                        freeTableLists(q);
                    }
                }*/

                /* Querie 7 */
                if (strcmp(funcao,"international_users")==0){
                    q = international_users(sgr);
                    saveMemory(variaveis,variavel,q);
                    //freeTableList(q);
                }

                /* Querie 8 
                if (strcmp(funcao,"top_businesses_with_category")==0){

                    char* procuraInput = strtok(segundaParte,"'");
                    char* top = strdup(strtok(NULL,","));
                    char* category = strdup(strtok(NULL,"'"));

                    if(!isdigit(top)){
                        LOG_RED("\nTop inválido\n");

                    }else{
                        q = top_businesses_with_category(sgr,top,category);
                        saveMemory(variaveis,variavel,q);
                        freeTableList(q);
                    }
                }*/ 

                /* Querie 9 */
                if (strcmp(funcao,"reviews_with_word")==0){
                    char* procuraInput = strtok(segundaParte,"'");
                    char* palavra = (strtok(NULL,"'"));

                    q = reviews_with_word(sgr,palavra);
                    saveMemory(variaveis,variavel,q);
                    //freeTableList(q);
                }
                /* fromCSV */
                if(strcmp(funcao,"fromCSV")==0){
                    char* path = strdup(strtok(segundaParte,","));
                    q = fromCSV(path);                    
                }
            }

        }   
    }
    return RUN;
}


                
               

/**
 * @brief Função que carrega os dados presentes nos ficheiros  users.csv, businesses.csv, e reviews.csv
 * @param sgr SGR que contém as informações dos módulos
 */
 static SGR loadFromFiles(SGR sgr){

    printf("Press %s<enter>%s for default\n",YELLOW,RESET);
    char user_Path[BUFFER];
    char business_Path[BUFFER];
    char reviews_Path[BUFFER];

    FILE *ut,*n,*rev;

    printf("Users: ");

    while(1){
        user_Path[0] = getchar();
        if(user_Path[0] == '\n')
            strcpy (user_Path, user_Path_default);
        else if (fgets(user_Path+1, BUFFER-1, stdin));

        strtok(user_Path, "\n\r");

        ut = fopen(user_Path, "r");

        if(ut){
            if(system("clear"));
            printf("Press %s<enter>%s for default\n",YELLOW,RESET);
            LOG_GREEN("Users: check\n");
            break;
        }

        if(system("clear"));
        LOG_RED("Erro: Ficheiro não existente\n");
        printf("Press %s<enter>%s for default\n",YELLOW,RESET);
        LOG_RED("Users: ");
    }

    printf("Businesses: ");
    while(1){

        business_Path[0] = getchar();

        if(business_Path[0] == '\n') strcpy(business_Path,business_Path_default);
        else if(fgets(business_Path+1,BUFFER-1,stdin));

        strtok(business_Path,"\n\r");

        n = fopen(business_Path,"r");

        if(n){
            if(system("clear"));
            printf("Press %s<enter>%s for default\n",YELLOW,RESET);
            LOG_GREEN("Users: check\n");
            LOG_GREEN("Businesses: check\n");
            break;
        }
        if(system("cls"));
        LOG_RED("Erro: Ficheiro não existente\n");
        printf("Press %s<enter>%s for default\n",YELLOW,RESET);
        LOG_GREEN("Users: check\n");
        LOG_RED("Businesses : ");
    }

    printf("Reviews: ");
    while(1){
        reviews_Path[0] = getchar();
        if(reviews_Path[0] == '\n') strcpy(reviews_Path,reviews_Path_default);
        else if(fgets(reviews_Path+1,BUFFER-1,stdin));
        strtok(reviews_Path,"\n\r");
        rev = fopen(reviews_Path,"r");
        if (rev) {
            if(system("clear"));
            printf("Press %s<enter>%s for default\n",YELLOW,RESET);
            LOG_GREEN("Users: check\n");
            LOG_GREEN("Businesses: check\n");
            LOG_GREEN("Reviews: check\n");
            break;
        }
        if(system("clear"));
        LOG_RED("Erro: Ficheiro não existente\n");
        printf("Press %s<enter>%s for default\n",YELLOW,RESET);
        LOG_GREEN("Users: check\n");
        LOG_GREEN("Businesses : check\n");
        LOG_RED("Reviews: ");
    }
    printf("\e[5mLOADING ...\n\e[25m");
    t1 = clock();
    sgr = load_sgr(user_Path, business_Path, reviews_Path);
    t1 = clock() - t1;
    if(system("clear"));
    printf("Press %s<enter>%s for default\n",YELLOW,RESET);
    LOG_GREEN("Users: check\n");
    LOG_GREEN("Businesses: check\n");
    LOG_GREEN("Reviews: check\n");
    LOG_GREEN("LOADED\n");

    return sgr;
}

