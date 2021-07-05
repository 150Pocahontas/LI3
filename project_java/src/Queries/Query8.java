package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Query8 implements Variables, Querie, Serializable {
    private List<Map.Entry<String,Integer>> utilizadores_negocio; //Lista de pares que tem o codigo do user e o nmr de reviews, ordenado pelo nmr

    public Query8() {
        this.utilizadores_negocio = new ArrayList<>();
    }

    public List<Map.Entry<String, Integer>> getUtilizadores_negocio() {
        return utilizadores_negocio;
    }

    public void insereUtilizadores_negocio(String id, int nmr) {
        utilizadores_negocio.add(new AbstractMap.SimpleEntry<>(id, nmr));
    }

    public void sortAndTruncate(int n){
        this.utilizadores_negocio.sort((a,b)->-a.getValue().compareTo(b.getValue()));
        if (n<=utilizadores_negocio.size())
            this.utilizadores_negocio.subList(0, n);
    }
}
