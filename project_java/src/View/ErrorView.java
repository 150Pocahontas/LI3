package View;

import Interfaces.GereReviewsView;

import java.io.Serializable;

public class ErrorView implements GereReviewsView, Serializable {
    @Override
    public void show() {}

    /**
     *  Método que mostra um dado erro como uma mensagem.
     * @param o Object que é o erro a mostrar.
     */
    @Override
    public void show(Object o) {
        if (o instanceof String) {
            String a = (String) o;
            System.out.println(a);
        }
    }
}
