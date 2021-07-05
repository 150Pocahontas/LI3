package View;

import Geral.Variables;
import Interfaces.GereReviewsView;
import Interfaces.Querie;
import Queries.Query5;

import java.io.Serializable;
import java.util.ArrayList;

public class Query5View implements Querie, GereReviewsView, Variables, Serializable {

    private Query5 q5;

    /**
     *  Método que inicializa uma estrutura Qery5View.
     */
    public Query5View() {
        this.q5 = new Query5();
    }

    /**
     *  Método que inicializa uma estrutura Query5View por cópia.
     * @param q5 Querie5 que vai ser copiada.
     */
    public Query5View(Query5 q5) {
        this.q5 = q5;
    }

    /**
     *  Método que define a estrutura Querie5 com a informaçaão a apresentar.
     * @param q5 Querie5 que vai ser copiada.
     */
    public void setQ5(Query5 q5) {
        this.q5 = q5;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**
     *  Método que apresenta ao utilizador os resultados da Query5.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        if (o instanceof ArrayList){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            q5.getBusineesReviedOrder().forEach((k, v) -> System.out.println("Negócio: " + k + "        Quantidade de Reviews: " + v));

        }
    }
}
