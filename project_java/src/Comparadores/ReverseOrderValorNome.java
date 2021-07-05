package Comparadores;

import java.util.Comparator;
import java.util.Map;

public class ReverseOrderValorNome implements Comparator<Map.Entry<String, Integer>> {

    /**
     * Método que comparada duas Map.Entry pelo seu valor, para ordenação. Caso sejam iguais compara as keys.
     * @param stringIntegerEntry a ser comparado.
     * @param t1 a ser comparado.
     * @return int com o resultado da comparação.
     */
    @Override
    public int compare(Map.Entry<String, Integer> stringIntegerEntry, Map.Entry<String, Integer> t1) {
        if(t1.getValue() > stringIntegerEntry.getValue()) return 1;
        else if(t1.getValue() < stringIntegerEntry.getValue()) return -1;
        else return stringIntegerEntry.getKey().compareTo(t1.getKey());
    }
}
