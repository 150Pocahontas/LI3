package View;

import Interfaces.GereReviewsView;
import Queries.Q1Stats;

import java.io.Serializable;


public class Q1StatsView implements GereReviewsView, Serializable {

    private Q1Stats q1Stats;

    /**
     *  Método que inicializa uma estrutura Q1StatsView por cópia.
     * @param q1Stats Q1Stats que é a estrutura a copiar.
     */
    public Q1StatsView(Q1Stats q1Stats){
        this.q1Stats = q1Stats;
    }

    /**
     *  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() { System.out.println("Enter para Sair");}

    /**
     *  Método que apresenta ao utilizador os resultados da Query1 Estatística.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\nFicheiros Lidos: \n        " + this.q1Stats.getUserFile() +
                "\n        " + this.q1Stats.getBusinessFile() + "\n        " + this.q1Stats.getReviewFile());
        System.out.println("\nTotal de Reviews Validas: " + this.q1Stats.getReviewsValidas());
        System.out.println("Total de Reviews Errados: " + this.q1Stats.getnReviewsErradas());
        System.out.println("Total de reviews Sem impacto: " + this.q1Stats.getReviewsSemImpacto());
        System.out.println("\nTotal de negócios: " + this.q1Stats.getTotalBusinesses());
        System.out.println("Total de negócios Avaliados: " + this.q1Stats.getTotalBusinessesAvaliados());
        System.out.println("Total de negócios não avaliados: " + this.q1Stats.getTotalBusinessNaoAvalidados());
        System.out.println("\nTotal de Users: " + this.q1Stats.getTotalUsers());
        System.out.println("Total de users que Avaliaram: " + this.q1Stats.getTotalUsersFizeramReviews());
        System.out.println("Total de users que Não Avaliaram: " + this.q1Stats.getTotalUsersNaoAvaliaram());
    }
}
