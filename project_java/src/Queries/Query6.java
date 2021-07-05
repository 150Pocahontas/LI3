package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class Query6 implements Variables, Querie, Serializable {
    Map<Integer, Map<String, Set<Integer>>> Times_reviewed;

    public Map<Integer, Map<String, Set<Integer>>> getTimes_reviewd() {
        return Times_reviewed;
    }

    public void setTimes_reviewed(Map<Integer, Map<String, Set<Integer>>> times_reviewd) {
        Times_reviewed = times_reviewd;
    }
}
