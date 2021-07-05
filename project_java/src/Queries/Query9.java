package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Query9 implements Variables, Querie, Serializable {
    private List<Map.Entry<String,Integer>> nmrs_reviews; //Lista de pares que tem o ID do user e o nmr de reviews, ordenado pelo nmr
    private List<Map.Entry<String,Float>> user_reviews;//Lista de pares ID/Classificacao com a mesma ordem que o anterior

    public Query9() {
        this.nmrs_reviews = new ArrayList<>();
        this.user_reviews = new ArrayList<>();
    }

    public List<Map.Entry<String, Integer>> getNmrs_reviews() {
        return nmrs_reviews;
    }

    public List<Map.Entry<String, Float>> getUser_reviews() {
        return user_reviews;
    }

    public void insereNrmsReviews(String id, int nmr) {
        nmrs_reviews.add(new AbstractMap.SimpleEntry<>(id, nmr));
    }

    public void insereUserReviews(String id, float c) {
        user_reviews.add(new AbstractMap.SimpleEntry<>(id, c));
    }

    public void sortAndTruncate(int n){
        this.nmrs_reviews.sort((a,b)->-a.getValue().compareTo(b.getValue()));
        if (n<=nmrs_reviews.size())
            this.nmrs_reviews.subList(0, n);
    }
}