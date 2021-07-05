package View;

import Interfaces.GereReviewsView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListView implements GereReviewsView, Serializable {

    private int index;
    private List<String> l;
    private int pageTotal;
    private int infoPPag;

    /**
     *  Método que inicializa uma estrutura ListView.
     */
    public ListView() {
        this.l = new ArrayList<>();
        index = 0;
    }

    /**
     * @param list que é a lista a copiar.
     *  Método que inicializa uma estrutura ListView com passagem de parâmetros.
     */
    public ListView(List<String> list) {
        this.l = new ArrayList<>(list);
    }

    /**
     * @param i    int que é a posição a averiguar.
     * @param list que contém a informação.
     *  Método que inicializa uma estrutura ListView.
     */
    public ListView(int i, List<String> list) {
        index = i;
        this.l = new ArrayList<>(list);
        pageTotal = (l.size() % infoPPag == 0) ? l.size() / infoPPag : l.size() / infoPPag + 1;
    }

    /**
     * @param index    int que é a posição a averiguar.
     * @param infoPPag int que é a página a averiguar.
     * @param list     que contém a informação.
     *  Método que inicializa uma estrutura ListView numa dada página.
     */
    public ListView(int index, int infoPPag, List<String> list) {
        this.index = index;
        l = new ArrayList<>(list);
        this.infoPPag = infoPPag;
        this.pageTotal = (l.size() / infoPPag % 2 == 0) ? l.size() / infoPPag : l.size() / infoPPag + 1;
    }

    /**
     * @param infoPPag int que é a página a averiguar.
     *  Método que define a página a visualizar.
     */
    public void setInfoPPag(int infoPPag) {
        this.infoPPag = infoPPag;
        this.pageTotal = (this.l.size() / infoPPag % 2 == 0) ? this.l.size() / infoPPag % 2 : this.l.size() / infoPPag % 2 + 1;
    }

    /**
     * @return index int que é a posição.
     *  Método que devolve uma posição da lista.
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index int que é a posição
     *  Método que define uma posição da lista.
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return pageTotal int que é o total.
     *  Método que devolve o total de páginas.
     */
    public int getPageTotal() {
        return pageTotal;
    }

    /**
     *  Método que mostra uma mensagem ao utilizador.
     */
    @Override
    public void show() {
        System.out.println("Insert letter: ");
    }

    /**
     * @param o Object que é a posição da lista a averiguar.
     *  Método que imprime a visualização da página.
     */
    @Override
    public void show(Object o) {
        if (o instanceof Integer && ((Integer) o) >= 0 && ((Integer) o) <= pageTotal) {
            index = (Integer) o;
            int size = l.size();
            int indiceAtual = index * infoPPag;
            for (int i = indiceAtual; i < indiceAtual + infoPPag && i < size; ) {
                for (int j = 0; i < indiceAtual + infoPPag && i < size && j < 6; j++, i++)
                    System.out.print(" │ " + l.get(i) + " │ ");
                System.out.println();
            }

            System.out.println("\n");
            System.out.println("Pag " + (index + 1) + " of " + (pageTotal + 1) + " Pages");
            System.out.println("Insira Opção:");
            System.out.println("A = <- | D = -> | J = jump to N | S = Quit");
        }
        else if (o instanceof String)
            System.out.print((String) o);
    }
}
