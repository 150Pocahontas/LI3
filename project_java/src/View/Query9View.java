package View;

import Geral.Variables;
import Interfaces.GereReviewsView;
import Interfaces.Querie;
import Queries.Query9;

import java.io.Serializable;

public class Query9View implements Querie, GereReviewsView, Variables, Serializable {
    private Query9 q9;

    /**  Método que inicializa uma estrtura Query9View.
     */
    public Query9View() {
        this.q9 = new Query9();
    }

    /**  Método que inicializa uma estrutura Query9View por cópia.
     * @param q9 Querie9 que vai ser copiada.
     */
    public Query9View(Query9 q9) {
        this.q9 = q9;
    }

    /**  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**  Método que apresenta ao utilizador os resultados da Query9.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(q9.getNmrs_reviews());
    }
}
