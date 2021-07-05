/**
 * @file ReviewsCatalog.java
 * Ficheiro que contém a API relativa à implementação do catálogo de reviews.
 */

package Catalogos;

import Geral.Review;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ReviewsCatalog implements Catalogs, Serializable {
    private Map<String, Review> reviews;

    public ReviewsCatalog() {
        this.reviews = new HashMap<>();
    }
    /**
     * @param r Review.
     * Método que dado um código de um business o adiciona ao catálogo de reviews.
     */
    public void addReview(Review r){
        String code = r.getCode();
        this.reviews.put(code,r);
    }

    /**
     * Método que verifica se um dado code existe no catálogo de reviews.
     * @param code String com o código do reviews.
     * @return boolean com o resultado.
     */
    public boolean checkCode(String code) {
        return reviews.containsKey(code);
    }

    public List<Review> getReviewsList(){
        return this.reviews.values().stream().collect(Collectors.toList());
    }

}

