package Queries;
import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;

public class Query3 implements Variables, Querie, Serializable {
    private int[] nreviews;
    private int[] nbusinesses;
    private double[] media_mes;

    public Query3() {
        this.nreviews = new int[MONTH];
        this.nbusinesses = new int[MONTH];
        this.media_mes = new double[MONTH];
    }

    public int getNreviews(int index) {
        return nreviews[index];
    }

    public void setNreviews(int[] nreviews) {
        this.nreviews = nreviews;
    }

    public int getNbusinesses(int index) {
        return nbusinesses[index];
    }

    public void setNbusinesses(int[] nbusinesses) { this.nbusinesses = nbusinesses;    }

    public double getMedia_mes(int index) {
        return media_mes[index];
    }

    public void setMedia_mes(double[] media_mes) {
        this.media_mes = media_mes;
    }


}
