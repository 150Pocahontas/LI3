package View;

import Interfaces.GereReviewsView;


import java.io.Serializable;

public class LoadFilesView implements GereReviewsView, Serializable {
    @Override
    public void show() {
        System.out.println("         SGR          ");
        System.out.println("    -- Load Menu --   ");
        System.out.println("______________________");
        System.out.println("  1 -> Default Files  ");
        System.out.println("  2 -> Custom Files   ");
        System.out.println("  3 -> Object File    ");
        System.out.println("______________________");
        System.out.print("> ");
    }

    /**
     * Método que apresenta ao utilizador Informações úteis
     * @param o Objeto a apresentar ao Utilizador
     */
    @Override
    public void show(Object o) {
        if(o instanceof Integer){
            if((int) o == 2){
                System.out.println("              SGR              ");
                System.out.println("       Custom Files Menu       ");
                System.out.println("_______________________________");
                System.out.println("    <ENTER> to Default File    ");
            }
        }
        else if(o instanceof String){
            System.out.print((String) o);
        }
    }
}
