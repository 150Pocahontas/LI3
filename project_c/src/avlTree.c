#include "avlTree.h"

/**
 * @file avlTree.c
 * @brief Ficheiro que contém a API relativa à implementação de AVL's.
 */

struct nodeAvl {
	char* key;
	int height;
    void *cont;
    struct nodeAvl *left;
    struct nodeAvl *right;
};

struct avl{
	NodeAvl arvore;
	int avlSize;
};

/**
 * @brief Função que dado um NodeAvl retorna a sua altura.
 * @param n NodeAvl.
 * @return int altura.
 */
int height(NodeAvl n){
    if (n == NULL)
        return 0;
    return n->height;
}

/**
 * @brief Função que calcula qual o maior de dois inteiros.
 * @param int a.
 * @param int b.
 * @return int max.
 */
int max(int a, int b){
    return (a > b)? a : b;
}

/**
 * @brief Função que verifica se o NodeAvl está balanceado e, consequentemente, a árvore.
 * @param N NodeAvl.
 * @return int como Boolean.
 */
int getBalance(NodeAvl n){
    if (n == NULL)
        return 0;
    return height(n->left) - height(n->right);
}

/**
 * @brief Função que cria um new NodeAvl.
 * @param node NodeAvl.
 * @param info Valor.
 * @param v void*.
 * @return new NodeAvl.
 */
NodeAvl newNode(NodeAvl node, char* info, void* v){
    node = (NodeAvl) malloc(sizeof(struct nodeAvl));
    node->key = malloc((strlen(info)+1)*sizeof(char));
    strcpy(node->key,info);
    node->left  = NULL;
    node->right = NULL;
    node->height = 1;
    node->cont = v;  
    return(node);
}

/**
 * @brief Função que dado um NodeAvl roda para a direita.
 * @param y NodeAvl.
 * @return NodeAvl depois de uma rotação à direita.
 */
NodeAvl rightRotate(NodeAvl y){
    NodeAvl x = y->left;
    NodeAvl T2 = x->right;
 
    /* Perform rotation */
    x->right = y;
    y->left = T2;
 
    /* Update heights */
    y->height = max(height(y->left), height(y -> right))+1;
    x->height = max(height(x->left), height(x -> right))+1;
 
    /* Return new root */
    return x;
}

/**
 * @brief Função que dado um NodeAvl roda para a esquerda.
 * @param x NodeAvl.
 * @return NodeAvl depois de uma rotação à esquerda.
 */ 
NodeAvl leftRotate(NodeAvl x) {
    NodeAvl y = x -> right;
    NodeAvl T2 = y -> left;
 
    /* Perform rotation */
    y -> left = x;
    x -> right = T2;
 
    /*  Update heights */
    x -> height = max(height(x -> left), height(x -> right))+1;
    y -> height = max(height(y -> left), height(y -> right))+1;
 
    /* Return new root */
    return y;
}

/**
 * @brief Função que insere uma nova estrutura na arvore tree tendo como referência de posicionamento um char* value.
 * @param tree AVL onde insere.
 * @param value char* a inserir.
 * @param estrutura Conteúdo/Estrutura a inserir.
 * @return AVL atualizada.
 */
AVL updateAVL(AVL tree, char* value, void* estrutura) {
    tree->arvore = updateNode(tree->arvore,value,estrutura);
    return tree;
}


/**
 * @brief Função que insere um node NodeAvl com um info Valor e uma estrutura Estrutura.
 * @param node NodeAvl a inserir.
 * @param info char* a inserir.
 * @param estrutura void* a inserir.
 * @return new NodeAvl inserido.
 */
NodeAvl insertNode(NodeAvl node, char* info, void* estrutura) {
    int balance;

    if(node != NULL) {
        if (strcmp(info,node->key) < 0)
            node->left  = insertNode(node->left, info, estrutura);
        else if(strcmp(info,node->key) > 0)
            node->right = insertNode(node->right, info, estrutura);
        else node->cont = estrutura;

        /* Atualiza os pesos */
        node->height = max(height(node->left), height(node->right)) + 1;

        /* Varifica o balanceamento */
        balance = getBalance(node);

        /* Left Left Case */
        if (balance > 1 && strcmp(info,node->left->key) < 0) return rightRotate(node);

        /* Right Right Case */
        if (balance < -1 && strcmp(info,node->right->key) > 0) return leftRotate(node);

        /* Left Right Case */
        if (balance > 1 && strcmp(info,node->left->key) > 0) {
            node->left =  leftRotate(node->left);
            return rightRotate(node);
        }
         /* Right Left Case */
        if (balance < -1 && strcmp(info, node->right->key) < 0) {
            node->right = rightRotate(node->right);
            return leftRotate(node);
        }

    } else node = newNode(node,info,estrutura);

   return node;
}

/**
 * @brief Função que inicializa uma nova AVL.
 * @return Nova AVL nula
 */

AVL initAVL() {
    AVL tree = malloc(sizeof(struct avl));
    tree-> arvore = NULL;
    tree-> avlSize = 0;
    return tree;
}

/**
 * @brief Função que insere na arvore tree tendo como referência de posicionamento um char*.
 * @param tree AVL onde insere.
 * @param key char* a inserir.
 * @param estrutura Conteúdo/Estrutura a inserir.
 * @return AVL com o new NodeAvl adicionado.
 */
AVL avlInsert(AVL tree, char* key, void* estrutura) {
    tree->arvore = insertNode(tree->arvore,key,estrutura);
    tree->avlSize ++;
    return tree;
}

/**
 * @brief Função que devolve um Boolean referente a ter encontrado ou não na AVL o Valor value.
 * @param tree AVL onde é efectuada a procura.
 * @param value valor a procurar na AVL.
 * @return Boolean com o resultado.
 */
Boolean avlLookUp(AVL tree, char* value) {
    if(tree == NULL) return false;
    return nodeLookUp(tree->arvore,value);
}

/**
 * @brief Função que devolve o tamanho (quantidade de nodos) de uma AVL passada como argumento.
 * @param tree AVL da qual se pretende o tamanho.
 * @return Int com tamanho da AVL.
 */
int avlCount(AVL tree) {
    return tree->avlSize;
}

/**
 * @brief Função que devolve a estrutura associada a um NodeAvl de uma AVL passada como argumento.
 * @param node AVL de onde será devolvida a estrutura.
 * @param value char* indicando o NodeAvl a procurar onde estará a estrutura associada.
 * @return void* com apontador para a estrutura ou NULL caso a mesma nao se encontre lá.
 */
void* avlGetStruct(AVL node, char* value) {
    return nodeGetStruct(node->arvore,value);
}

/**
 * @brief Função que devolve o tamanho de uma AVL.
 * @param tree AVL da qual se calcula o tamanho.
 * @return int com o tamanho da AVL.
 */
int avlGetSize(AVL tree) {
  return tree->avlSize;
}

/**
 * @brief Função que dada uma AVL retorna o NodeAvl da sua raiz.
 * @param a AVL de onde se pretende o NodeAvl.
 * @return NodeAvl.
 */
NodeAvl getNodo(AVL a) {
    NodeAvl new;
    if(a -> arvore) {
        new = (NodeAvl) malloc(sizeof(struct nodeAvl));
        new->key = malloc((strlen(a -> arvore -> key) + 1) * sizeof(char));
        strcpy(new->key, a->arvore->key);
        new -> cont = a->arvore->cont;
        new -> left = a->arvore->left;
        new -> right = a->arvore->right;
    }else {
        new = NULL;
    }

    return new;
}

/**
 * @brief Função que dado um NodeAvl retorna o NodeAvl á sua esquerda.
 * @param n NodeAvl.
 * @return NodeAvl à esquerda.
 */
NodeAvl getNodoEsq(NodeAvl n) {
    NodeAvl new;
    if(n->left) {
        new = (NodeAvl) malloc(sizeof(struct nodeAvl));
        new->key = malloc((strlen(n->left->key) +1) * sizeof(char));
        strcpy(new->key, n->left->key);
        new->cont = n->left->cont;
        new->left = n->left->left;
        new->right = n->left->right;
    }else {
        new = NULL;
    }
    return new;
}

/**
 * @brief Função que dado um NodeAvl retorna o NodeAvl á sua direita.
 * @param n NodeAvl.
 * @return NodeAvl à direita.
 */
NodeAvl getNodoDir(NodeAvl n) {
    NodeAvl new;
    if(n -> right) {
        new = (NodeAvl) malloc(sizeof(struct nodeAvl));
        new->key = malloc((strlen(n->right->key) +1) * sizeof(char));
        strcpy(new->key, n->right->key);
        new->cont = n->right->cont;
        new->left = n->right->left;
        new->right = n->right->right;
    }else {
        new = NULL;
    }
    return new;
}

/**
 * @brief Função que dado um NodeAvl retorna uma copia da sua key.
 * @param n NodeAvl.
 * @return char* com a cópia.
 */
char* getString(NodeAvl n){
    char* new;
    new = malloc((strlen(n->key) +1) * sizeof(char));
    strcpy(new, n->key);
    return new;
}

/**
 * @brief Função que dado um NodeAvl retorna o seu contéudo.
 * @param n NodeAvl.
 * @return void*.
 */
void* getCont(NodeAvl n){
    return n->cont;
}

/**
 * @brief Função com o objetivo de limpar da memória uma dada AVL.
 * @param NodeAvl AVL a limpar da memória.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void avlFree(AVL NodeAvl, void(*f) (void*)) {
    treeFree(NodeAvl->arvore,f);
    free(NodeAvl);
}

/**
 * @brief Função que verifica se existe um NodeAvl com um certo valor.
 * @param node NodeAvl a procurar.
 * @param value Valor a procurar.
 * @return Boolean com o resultado.
 */
int nodeLookUp(NodeAvl node, char* value) {
    int r;
    if(node == NULL) return 0;
    else {
        r = strcmp(value,node->key);
        if(r == 0) return 1;
        else if(r < 0) return nodeLookUp(node->left, value);
        else return nodeLookUp(node->right, value);
    }
}

/**
 * @brief Função que verifica se existe um NodeAvl com um certo valor.
 * @param node NodeAvl a procurar.
 * @param value Valor a procurar.
 * @return cont com a estrutura.
 */
NodeAvl nodeLookUpKey(NodeAvl node, char* key) {
    int r;
    if(node == NULL) return NULL;
    else {
        r = strcmp(key,node->key);
        if(r == 0) return node;
        else if(r < 0) return nodeLookUpKey(node->left, key);
        else return nodeLookUpKey(node->right, key);
    }
}

/**
 * @brief Função que atualiza um NodeAvl.
 * @param node NodeAvl atualizado
 * @param value char* utilizado como comparação para saber onde atualizar.
 * @param estrutura Conteúdo/Estrutura a inserir quando atualizar.
 * @return node NodeAvl atualizado.
 */
NodeAvl updateNode(NodeAvl node, char* value, void* estrutura) {
    int r;
    r = strcmp(value,node -> key);
    if(r == 0) {
        node -> cont = estrutura;
        return node;
    }
    else if(r < 0) updateNode(node->left, value,estrutura);
    else updateNode(node->right,value,estrutura);

    return node;
}


/**
 * @brief Função que devolve a estrutura associada a um NodeAvl que lhe é passado como argumento.
 * @param node NodeAvl de onde será devolvida a estrutura.
 * @param value Valor utilizado como referência de procura.
 * @return Estrutura ou NULL caso a mesma nao se encontre lá.
 */
void* nodeGetStruct(NodeAvl node, char* value) {
    int r;
    if(node == NULL) return NULL;
    else {
        r = strcmp(value,node -> key);
        if(r == 0) return node -> cont;
        else if(r < 0) return nodeGetStruct(node -> left, value);
        else return nodeGetStruct(node -> right,value);
    }
}

/**
 * @brief Função que limpa o espaço de memória ocupado por uma árvore.
 * @param node NodeAvl a libertar.
 * @param f Funcao que liberta a memória da estrutura associada.
 */
void treeFree(NodeAvl node, void(*f) (void*)) {
    if(node != NULL) {
        treeFree(node -> left,f);
        treeFree(node -> right,f);
        if(node -> cont != NULL) {
            f(node -> cont);
        }
        free(node -> key);
        free(node);
    }
}

/**
 * @brief Função que limpa da memória um dado NodeAvl.
 * @param node NodeAvl a remover.
 */
void freeNode(NodeAvl node) {
    if(node != NULL) {
        free(node -> key);
        free(node);
    }
}

/**
 * @brief Função que imprime os nodos da AVL.
 * @param tree NodeAvl a imprimir.
 */ 
void printAvlContent(NodeAvl tree) {
    if (tree == NULL) return;
    printAvlContent(tree -> left);
    printf("%s\n",tree -> key);
    printAvlContent(tree -> right);
}

/**
 * @brief Função que imprime todas as Strings da AVL
 * @param tree AVL a imprimir
 */
void printAVLString(AVL tree) {
    printAvlContent(tree->arvore);
}

