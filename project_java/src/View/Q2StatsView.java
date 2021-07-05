package View;

import Geral.Variables;
import Interfaces.GereReviewsView;
import Queries.Q2Stats;

import java.io.Serializable;
import java.time.Month;

public class Q2StatsView implements GereReviewsView, Variables , Serializable {

    private Q2Stats q2Stats;

    /**
     *  Método que inicializa uma estrutura Q2StatsView por cópia.
     * @param q2Stats Q2Stats que vai ser copiada.
     */
    public Q2StatsView(Q2Stats q2Stats){
        this.q2Stats = q2Stats;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to exit");
    }

    /**
     *  Método que apresenta ao utilizador os resultados da Query2 Estatística.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        int m;
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\nClassificção Global:  " + this.q2Stats.getClassGlobalReviews() + "\n");

        for(m=0;m<MONTH;m++) {
            System.out.println();
            System.out.println(MONTHS.get(m));
            System.out.println("Número de Reviews: " + this.q2Stats.getReviewsPorMes(m));
            System.out.println("Média de Classificação de reviews: " + this.q2Stats.getMediaClasReview(m));
            System.out.println("Número de Utilizadores que Avaliaram: " + this.q2Stats.getUserDifPorMes(m));
        }
    }
}
