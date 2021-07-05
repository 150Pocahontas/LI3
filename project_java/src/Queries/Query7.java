package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;
import java.util.*;

public class Query7 implements Variables, Querie, Serializable {

    private Map<String,Map<String,Integer>> businessMostFamose;

    /**
     *  Método que inicializa uma estrutura Query7.
     */
    public Query7() {
        this.businessMostFamose = new HashMap<>();
    }

    public Map<String, Map<String, Integer>> getBusinessMostFamose() {
        return businessMostFamose;
    }

    public void setBusinessMostFamose(Map<String, Map<String, Integer>> businessMostFamose) {
        this.businessMostFamose = businessMostFamose;
    }

}
