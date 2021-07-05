/**
 * @file GestReviewsAppMVC.java
 *  Ficheiro que contém a API relativa à implementação do MVC da aplicação.
 */

import Controller.*;
import Exceptions.*;
import Geral.Crono;
import Interfaces.*;
import Models.GestReviews;
import View.ErrorView;
import View.MainMenuView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class GestReviewsAppMVC {

    /**
     *  Método que cria o modelo inicial do programa e o carrega com as informações default.
     * @throws FileNotFoundException exceção.
     * @return gvcm GereReviewsModel que é o modelo carregado com as informações lidas.
     */
    private static GereReviewsModel createData() throws FileNotFoundException {
        GereReviewsModel grm = new GestReviews();
        Crono.start();
        grm.loadDefault();
        System.out.println(Crono.getTImeString());
        return grm;
    }

    /**
     *  Método que cria o modelo inicial do programa e o carrega com as informações do Utilizador.
     * @param paths lista com os caminhos dos ficheiros a serem lidos
     * @return grcm GereReviewsModel que é o modelo carregado com as informações lidas.
     */
    private static GereReviewsModel createData(List<String> paths){
        GereReviewsModel grm = new GestReviews();
        Crono.start();
        grm.loadFiles(paths);
        System.out.println(Crono.getTImeString());
        return grm;
    }


    /**
     * @param args String[] aos possiveis argumentos passados passados ao Método
     */
    public static void main(String[] args) throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException {
        GereReviewsController control = new GestReviewsController();
        GereReviewsView view;

        List<String> paths = control.loadFiles();
        GereReviewsModel model = null;
        System.out.println("Loading ...");
        if(paths == null){
            model = createData();
        }
        else if(paths.size() == 1) {
            try {
                model = GestReviews.loadGestReviews(paths.get(0));
            }catch (IOException | ClassNotFoundException e){
                view = new ErrorView();
                view.show("Error while loading Object");
                System.exit(-1);
            }
        }
        else{
            model = createData(paths);
        }

        if (model == null) {
            view = new ErrorView();
            view.show("Error while loading model");
            System.exit(-1);
        }

        view = new MainMenuView();
        control.setModel(model);
        control.setView(view);
        control.start();
        System.exit(0);
    }
}