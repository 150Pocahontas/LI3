package View;

import Geral.Variables;
import Interfaces.GereReviewsView;
import Interfaces.Querie;
import Queries.Query7;

import java.io.Serializable;
import java.util.Map;

public class Query7View implements Querie, GereReviewsView, Variables, Serializable {

    private Query7 q7;

    /**  Método que inicializa uma estrtura Query7View.
     */
    public Query7View() {
        this.q7 = new Query7();
    }

    /**  Método que inicializa uma estrutura Query7View por cópia.
     * @param q7 Querie7 que vai ser copiada.
     */
    public Query7View(Query7 q7) {
        this.q7 = q7;
    }

    /**  Método que apresenta ao utilizador uma mensagem pré-definida.
     */
    @Override
    public void show() {
        System.out.println("Enter to Exit");
    }

    /**  Método que apresenta ao utilizador os resultados da Query7.
     * @param o Object que é um argumento.
     */
    @Override
    public void show(Object o) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("3 Melhores Negócios por Cidade: ");
        Map<String, Map<String, Integer>> x = q7.getBusinessMostFamose();
        System.out.println("aoiuwhdoiuaw");
        for(Map.Entry<String, Map<String, Integer>> a:x.entrySet()){
            System.out.println(a.getKey());
            for (Map.Entry<String, Integer> b:a.getValue().entrySet()){
                System.out.println("\t"+b.getKey()+": "+b.getValue());
            }
        }
    }
}
