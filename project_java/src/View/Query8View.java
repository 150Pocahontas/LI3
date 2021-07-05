package View;

import Geral.Variables;
import Interfaces.GereReviewsView;
import Interfaces.Querie;
import Queries.Query8;

import java.io.Serializable;

public class Query8View implements Querie, GereReviewsView, Variables, Serializable {
    private Query8 q8;

    /**  Método que inicializa uma estrtura Query8View.
     */
    public Query8View() {
        this.q8 = new Query8();
    }

    /**  Método que inicializa uma estrutura Query8View por cópia.
     * @param q8 Querie8 que vai ser copiada.
     */
    public Query8View(Query8 q8) {
        this.q8 = q8;
    }

    /**  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**  Método que apresenta ao utilizador os resultados da Query8.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println(q8.getUtilizadores_negocio());
    }
}
