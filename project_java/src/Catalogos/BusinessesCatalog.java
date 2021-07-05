/**
 * @file BusinessesCatalog.java
 * Ficheiro que contém a API relativa à implementação do catálogo de businesses.
 */

package Catalogos;

import Geral.Business;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class BusinessesCatalog implements Catalogs, Serializable {
    private Map<String, Business> businesses;


    public BusinessesCatalog() {
        this.businesses = new HashMap<>();
    }

    /**
     * @param b businesse
     * Método que dado um código de um business o adiciona ao catálogo de businesses.
     */
    public void addBusiness(Business b) {
        String code = b.getCode();
        this.businesses.put(code,b);
    }

    /**
     * Método que verifica se um dado business existe no catálogo de businesses.
     * @param code String com o código do businesses.
     * @return boolean com o resultado.
     */
    public boolean checkCode(String code) {
        return this.businesses.containsKey(code);
    }

    public List<Business> getBusinessList(){
        return this.businesses.values().stream().collect(Collectors.toList());
    }

    public Business getBusiness(String code){
        return businesses.get(code);
    }

    public Business getBusinessByID(String b) {
        return this.businesses.get(b);
    }
}

