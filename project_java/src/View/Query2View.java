/**
 * @file Query2View.java
 *  Ficheiro que contém a API relativa à implementação da view da query 2.
 */

package View;

import Geral.Variables;
import Interfaces.GereReviewsView;

import java.io.Serializable;
import java.util.ArrayList;


public class Query2View implements GereReviewsView, Variables, Serializable {

    private int nUsers;
    private int nReviews;

    /**
     *  Método que inicializa uma estrutura Query2View com passagem de parâmetros.
     */
    public Query2View(int nUsers, int nReviews) {
        this.nUsers = nUsers;
        this.nReviews = nReviews;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**
     *  Método que apresenta ao utilizador os resultados da Query2.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        if (o instanceof ArrayList) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            ArrayList<Object> lista = new ArrayList<>((ArrayList<Object>) o);
            int ano = (Integer) lista.get(1);
            int mes = (Integer) lista.get(0);
            System.out.println("ANO : " + ano + " | Mês : "  + MONTHS.get(mes - 1));
            System.out.printf("%s %d\n", "Número Total de Reviews : ", this.nReviews);
            System.out.printf("%s %d\n", "Número de Utilizadores Distintos: ", this.nUsers);
        }
    }
}
