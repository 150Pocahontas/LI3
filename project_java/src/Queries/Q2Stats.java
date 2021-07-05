package Queries;

import Geral.Variables;
import Interfaces.Querie;

import javax.management.ValueExp;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Q2Stats implements Variables, Querie, Serializable {
    private double classGlobalReviews;
    private int[] reviewsPorMes;
    private double[] mediaClasReview;
    private int[] userDifPorMes;

    /**
     *  Método que inicializa uma estrutura Q2Stats.
     */
    public Q2Stats(){
        this.classGlobalReviews = 0;
        this.reviewsPorMes = new int[MONTH];
        this.mediaClasReview = new double[MONTH];
        this.userDifPorMes = new int[MONTH];
    }

    public double getClassGlobalReviews() {
        return classGlobalReviews;
    }

    public void setClassGlobalReviews(double classGlobalReviews) {
        this.classGlobalReviews = classGlobalReviews;
    }

    public int getReviewsPorMes(int index) {
        return reviewsPorMes[index];
    }

    public void setReviewsPorMes(int[] reviewsPorMes) {
        this.reviewsPorMes = reviewsPorMes;
    }

    public double getMediaClasReview(int index) {
        return mediaClasReview[index];
    }

    public void setMediaClasReview(double[] mediaClasReview) {
        this.mediaClasReview = mediaClasReview;
    }

    public int getUserDifPorMes(int index) {
        return userDifPorMes[index];
    }

    public void setUserDifPorMes(int[] userDifPorMes) {
        this.userDifPorMes = userDifPorMes;
    }
}
