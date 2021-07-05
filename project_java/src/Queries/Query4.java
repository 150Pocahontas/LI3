package Queries;

import Geral.Variables;
import Interfaces.Querie;

import java.io.Serializable;

public class Query4 implements Variables, Querie, Serializable {

    private int[] times_reviewed;
    private int[] users_reviewed;
    private double[] media_class_mes;

    /**
     *  Método que inicializa uma estrutura Query4.
     */
    public Query4() {
        this.times_reviewed = new int[MONTH];
        this.users_reviewed = new int[MONTH];
        this.media_class_mes = new double[MONTH];
    }

    public Query4(int[] times_reviewed, int[] users_reviewed, double[] media_class_mes) {
        this.times_reviewed = times_reviewed;
        this.users_reviewed = users_reviewed;
        this.media_class_mes = media_class_mes;
    }

    /**
     *  Método que devolve o número de vezes avaliado num dado mês.
     * @param index int que é o mês a averiguar.
     * @return int que é o número de vezes comprado.
     */
    public int getTimes_reviewed(int index) {
        return times_reviewed[index];
    }

    /**
     *  Método que devolve o número de clientes que avaliaram num dado mês.
     * @param index int que é o mês a averiguar.
     * @return int que é o número de users que avaliaram.
     */
    public int getNUsersReviewed(int index) {
        return users_reviewed[index];
    }

    /**
     *  Método que devolve o total avaliado num dado mês.
     * @param index int que é o mês a averiguar
     * @return double que é a classificação.
     */
    public double getMediaClassMes(int index) {
        return media_class_mes[index];
    }

    /**
     *  Método que define o número de vezes alaviado em cada mês.
     * @param times_reviewed int[] que contém o número de vezes avaliado em cada mês.
     */
    public void setTimes_reviewed(int[] times_reviewed) {
        this.times_reviewed = times_reviewed;
    }


    /**
     *  Método que devolve o número de users por mês.
     * @return int[] que contém o número de users em cada mês.
     */
    public int[] getUsers_reviewed() {
        return this.users_reviewed;
    }

    /**
     *  Método que define o total de users por mês.
     * @param users_reviewed int[] que contém o total de users em cada mês.
     */
    public void setUsers_reviewed(int[] users_reviewed) {
        this.users_reviewed = users_reviewed;
    }


    /**
     *  Método que devolve o total classificado por mês.
     * @return double[] que contém o total classificado em cada mês.
     */
    public double[] getMedia_class_mes() {
        return this.media_class_mes;
    }

    /**
     *  Método que define o total classificado por mês.
     * @param media_class_mes double[] que contém o total classificado em cada mês.
     */
    public void setMedia_class_mes(double[] media_class_mes) {
        this.media_class_mes = media_class_mes;
    }



}
