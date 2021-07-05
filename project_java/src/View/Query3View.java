package View;

import Interfaces.GereReviewsModel;
import Interfaces.GereReviewsView;
import Queries.Query3;
import Geral.Variables;

import java.io.Serializable;
import java.util.ArrayList;

public class Query3View implements GereReviewsView, Variables, Serializable {
    private Query3 q3;
    /**
     *  Método que inicializa uma estrutura Query3View.
     */
    public Query3View() {
        this.q3 = new Query3();
    }

    /**
     *  Método que inicializa uma estrutura Query3View por cópia.
     * @param q3 Querie3 que vai ser copiada.
     */
    public Query3View(Query3 q3) {
        this.q3 = q3;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**
     *  Método que apresenta ao utilizador os resultados da Query3.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        if (o instanceof ArrayList) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            for (int mes = 1; mes < MONTH + 1; mes++) {
                System.out.println("Mês : " + MONTHS.get(mes - 1));
                System.out.println(" Nr Reviews: " + this.q3.getNreviews(mes - 1)
                        + " Nr Negócios distintos: " + this.q3.getNbusinesses(mes - 1)
                        + " Avaliação Total: " + this.q3.getMedia_mes(mes - 1));
            }
        }
    }
}
