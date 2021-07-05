package View;

import Interfaces.*;

import java.io.Serializable;

public class MainMenuView implements GereReviewsView, Serializable {

    /**
     *  Método que apresenta ao utilizador o menu inicial.
     */
    @Override
    public void show() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("          SGR          ");
        System.out.println("------- Main Menu ------");
        System.out.println("                       ");
        System.out.println("A -> Query 1 Statistics");
        System.out.println("B -> Query 2 Statistics");
        System.out.println("1 -> Query 1");
        System.out.println("2 -> Query 2");
        System.out.println("3 -> Query 3");
        System.out.println("4 -> Query 4");
        System.out.println("5 -> Query 5");
        System.out.println("6 -> Query 6");
        System.out.println("7 -> Query 7");
        System.out.println("8 -> Query 8");
        System.out.println("9 -> Query 9");
        System.out.println("10 -> Query 10");
        System.out.println("G -> Guardar Programa");
        System.out.println("C -> Carregar Programa");
        System.out.println("S -> Sair do Programa");
        System.out.println("           ----         ");
        System.out.print("> ");
    }

    public void show(Object o) {
        if(o instanceof String){
            System.out.print((String) o);
        }
    }
}
