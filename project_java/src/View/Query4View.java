/**
 * @file Query4View.java
 *  Ficheiro que contém a API relativa à implementação da view da query 4.
 */

package View;



import Geral.Variables;

import Interfaces.GereReviewsView;
import Interfaces.Querie;
import Queries.Query4;

import java.io.Serializable;
import java.util.ArrayList;


public class Query4View implements Querie,GereReviewsView, Variables,Serializable {

    private Query4 q4;

    /**
     *  Método que inicializa uma estrutura Qery4View.
     */
    public Query4View() {
        this.q4 = new Query4();
    }

    /**
     *  Método que inicializa uma estrutura Query4View por cópia.
     * @param q4 Querie4 que vai ser copiada.
     */
    public Query4View(Query4 q4) {
        this.q4 = q4;
    }

    /**
     *  Método que define a estrutura Querie4 com a informaçaão a apresentar.
     * @param q4 Querie4 que vai ser copiada.
     */
    public void setQ4(Query4 q4) {
        this.q4 = q4;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**
     *  Método que apresenta ao utilizador os resultados da Query4.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        if (o instanceof ArrayList) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (int mes = 1; mes < MONTH + 1; mes++) {
                System.out.println("Mês : " + MONTHS.get(mes - 1));
                System.out.println("Número de vezes Avaliado : " + this.q4.getTimes_reviewed(mes-1)
                        + " Número de Utilizadores distintos : " + this.q4.getNUsersReviewed(mes-1)
                        + " Total Classificação : " + this.q4.getMediaClassMes(mes-1));
            }
        }
    }

}
