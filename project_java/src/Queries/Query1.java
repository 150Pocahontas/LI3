/**
 * @file Querie1
 *  Ficheiro que contém a API relativa à implementação da query 1.
 */

package Queries;

import Interfaces.Querie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Query1 implements Querie, Serializable{
    private Set<String> businesses_not_reviewed;
    private int total;

    /**
     *  Método que inicializa uma estrutura Querie1.
     */
    public Query1(){
        this.businesses_not_reviewed = new TreeSet<>();
        this.total = 0;
    }
    /**
     *  Método que inicializa uma estrutura Querie1 com passagem de parâmetros.
     * @param total int que é o total de produtos.
     * @param businesses_not_reviewed que contém os códigos de ñegócios.
     */
    public Query1(int total, Set<String> businesses_not_reviewed) {
        setBusinesses(businesses_not_reviewed);
        this.total = total;
    }

    /**
     *  Método que devolve o total de negócios não avaliados.
     * @return int que é o total de negócios.
     */
    public int getSizeQuerie1(){
        return this.businesses_not_reviewed.size();
    }
    /**
     *  Método que define os negócios não avaliados.
     * @param p que contém os códigos dos negócios.
     */
    public void setBusinesses(Set<String> p){
        this.businesses_not_reviewed = new TreeSet<>();
        this.businesses_not_reviewed.addAll(p);
    }

    /**
     *  Método que devolve o conjunto dos negócios não avaliados.
     * @return aux que contém os códigos dos negócios.
     */
    public Set<String> getBusinesses(){
        Set<String> aux = new TreeSet<>();
        this.businesses_not_reviewed.forEach(v -> aux.add(v));
        return aux;
    }

    /**
     *  Método que devolve uma lista com os negócios não avaliados.
     * @return aux que contém os códigos dos negócios.
     */
    public List<String> getBusinessesList(){
        List<String> aux = new ArrayList<>();
        aux.addAll(this.businesses_not_reviewed);
        return aux;
    }

    /**
     *  Método que define o total de negócios.
     * @param t int que é o total.
     */
    public void setTotal(int t){
        this.total = t;
    }

    /**
     *  Método que devolve o total de negócios.
     * @return int que é o total.
     */
    public int getTotal(){
        return this.total;
    }
}