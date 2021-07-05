package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class Query5 implements Variables, Querie, Serializable {

    private LinkedHashMap<String,Integer> busineesReviedOrder;

    /**
     *  Método que inicializa uma estrutura Query5.
     */
    public Query5() {
        this.busineesReviedOrder = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Integer> getBusineesReviedOrder() {
        return busineesReviedOrder;
    }

    public void setBusineesReviedOrder(LinkedHashMap<String, Integer> busineesReviedOrder) {
        this.busineesReviedOrder = busineesReviedOrder;
    }



}
