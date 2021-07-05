package Queries;


import Interfaces.Querie;

import java.io.Serializable;

import static Geral.Variables.MONTH;

public class Query2 implements Querie, Serializable {
    private int nUsers;
    private int nReviews;

    /**
     *  Método que inicializa uma estrutura Querie2.
     */
    public Query2(){

        this.nUsers = 0;
        this.nReviews = 0;
    }

    /**
     *  Método que inicializa uma estrutura Querie2 com passagem de porâmetros.
     * @param nUsers int que é o número de utilizadores-
     * @param nReviews int que é o número de reviews.
     */
    public Query2(int nUsers, int nReviews) {
        this.nUsers = nUsers;
        this.nReviews = nReviews;
    }

    public int getnUsers() {
        return nUsers;
    }

    public void setnUsers(int nUsers) {
        this.nUsers = nUsers;
    }

    public int getnReviews() {
        return nReviews;
    }

    public void setnReviews(int nReviews) {
        this.nReviews = nReviews;
    }
}
