/**
 * @file Q1Stats.java
 *  Ficheiro que contém a API relativa à implementação da query 1 estatística.
 */

package Queries;

import Interfaces.Querie;

import java.io.Serializable;

public class Q1Stats implements Querie, Serializable {
    private String userFile;
    private String businessFile;
    private String reviewFile;

    private int reviewsTotal;
    private int reviewsValidas;
    private int nReviewsErradas;
    private int reviewsSemImpacto;

    private int totalBusinesses;
    private int totalBusinessesAvaliados;
    private int totalBusinessNaoAvalidados;

    private int totalUsers;
    private int totalUsersFizeramReviews;
    private int totalUsersInativos;
    private int totalUsersNaoAvaliaram;

    public Q1Stats() {
        this.userFile = "";
        this.businessFile = "";
        this.reviewFile = "";

        this.reviewsTotal = 0;
        this.reviewsValidas = 0;
        this.nReviewsErradas = 0;
        this.reviewsSemImpacto = 0;

        this.totalBusinesses = 0;
        this.totalBusinessesAvaliados = 0;
        this.totalBusinessNaoAvalidados = 0;

        this.totalUsers = 0;
        this.totalUsersFizeramReviews = 0;
        this.totalUsersInativos = 0;
        this.totalUsersNaoAvaliaram = 0;
    }


    public int getReviewsTotal() {
        return reviewsTotal;
    }

    public void setReviewsTotal(int reviewsTotal) {
        this.reviewsTotal = reviewsTotal;
    }

    public int getReviewsValidas() {
        return reviewsValidas;
    }

    public void setReviewsValidas(int reviewsValidas) {
        this.reviewsValidas = reviewsValidas;
    }

    public String getUserFile() {
        return userFile;
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }

    public String getReviewFile() {
        return reviewFile;
    }

    public void setReviewFile(String reviewFile) {
        this.reviewFile = reviewFile;
    }

    public int getReviewsSemImpacto() {
        return reviewsSemImpacto;
    }

    public void setReviewsSemImpacto(int reviewsSemImpacto) {
        this.reviewsSemImpacto = reviewsSemImpacto;
    }

    public String getBusinessFile() {
        return businessFile;
    }

    public void setBusinessFile(String businessFile) {
        this.businessFile = businessFile;
    }

    public int getnReviewsErradas() {
        return (getReviewsTotal()-getReviewsValidas());
    }

    public void setnReviewsErradas(int nReviewsErradas) {
        this.nReviewsErradas = nReviewsErradas;
    }


    public int getTotalBusinesses() {
        return totalBusinesses;
    }

    public void setTotalBusinesses(int totalBusinesses) {
        this.totalBusinesses = totalBusinesses;
    }

    public int getTotalBusinessesAvaliados() { return totalBusinessesAvaliados; }

    public void setTotalBusinessesDif(int totalBusinessesDif) {
        this.totalBusinessesAvaliados = totalBusinessesDif;
    }

    public int getTotalBusinessNaoAvalidados() {
        return totalBusinessNaoAvalidados;
    }

    public void setTotalBusinessNaoAvalidados(int totalBusinessNaoAvalidados) {
        this.totalBusinessNaoAvalidados = totalBusinessNaoAvalidados;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public int getTotalUsersFizeramReviews() {
        return totalUsersFizeramReviews;
    }

    public void setTotalUsersFizeramReviews(int totalUsersFizeramReviews) {
        this.totalUsersFizeramReviews = totalUsersFizeramReviews;
    }

    public int getTotalUsersInativos() {
        return totalUsersInativos;
    }

    public void setTotalUsersInativos(int totalUsersInativos) {
        this.totalUsersInativos = totalUsersInativos;
    }

    public int getTotalUsersNaoAvaliaram() {
        return totalUsersNaoAvaliaram;
    }

    public void setTotalUsersNaoAvaliaram(int totalUsersNaoAvaliaram) {
        this.totalUsersNaoAvaliaram = totalUsersNaoAvaliaram;
    }
}
