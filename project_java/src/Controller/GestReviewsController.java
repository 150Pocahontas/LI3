/**
 * @file GestreviewsController.java
 * Ficheiro que contém a API relativa à implementação do controlador.
 */

package Controller;


import Exceptions.UserNotFoundException;
import Exceptions.BusinessNotFoundException;
import Exceptions.ReviewNotFoundException;
import Geral.Crono;
import Interfaces.*;
import Models.GestReviews;

import Queries.*;
import Queries.Q1Stats;
import Queries.Q2Stats;
import Readers.Input;
import View.*;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static Geral.Variables.*;


public class GestReviewsController implements GereReviewsController, Serializable {
    private GereReviewsModel model;
    private GereReviewsView view;

    /**
     * Método que define o modelo a utilizar.
     * @param m GereReviewsModel que é quem tem acesso à informação e funções necessárias para efetuar os pedidos do utilizador.
     */
    @Override
    public void setModel(GereReviewsModel m) {
        this.model = m;
    }

    /**
     * Método que define a view a utilizar.
     * @param v GereReviewsView que é quem tem apresenta a informação ao utilizador.
     */
    @Override
    public void setView(GereReviewsView v) {
        this.view = v;
    }

    /**
     * Método que comunica com o utilizador e obtém os ficheiros desejados.
     * @return Lista com os paths dos ficheiros.
     */
    @Override
    public List<String> loadFiles() {
        boolean flag = false;
        List<String> paths = new ArrayList<>();
        int opcao;
        do {
            this.view = new LoadFilesView();
            this.view.show("\033[H\033[2J");
            System.out.flush();
            this.view.show();
            opcao = Input.lerInt();
            if (opcao != 1 && opcao != 2 && opcao != 3) {
                this.view = new ErrorView();
                this.view.show("Opção inválida");
            }
        } while (opcao != 1 && opcao != 2 && opcao != 3);
        if (opcao == 1) {
            return null;
        }
        else if(opcao == 2) {
            String users, business, reviews;
            this.view.show(2);
            do {
                this.view.show("Users: ");
                users = Input.lerString();
                if (users.isEmpty()) {
                    users = usersFileDefault;
                    paths.add(users);
                    flag = true;
                } else if (Files.exists(Paths.get(users))) {
                    paths.add(users);
                    flag = true;
                } else {
                    this.view.show("Ficheiro de Users inválido\n");
                }
            } while (!flag);
            flag = false;
            do {
                this.view.show("Business: ");
                business = Input.lerString();
                if (business.isEmpty()) {
                    business = businessesFileDefault;
                    paths.add(business);
                    flag = true;
                } else if (Files.exists(Paths.get(business))) {
                    paths.add(business);
                    flag = true;
                } else {
                    this.view.show("Ficheiro de Business inválido\n");
                }
            } while (!flag);
            flag = false;
            do {
                this.view.show("Reviews: ");
                reviews = Input.lerString();
                if (reviews.isEmpty()) {
                    reviews = reviewsFileDefault;
                    paths.add(reviews);
                    flag = true;
                } else if (Files.exists(Paths.get(reviews))) {
                    paths.add(reviews);
                    flag = true;
                } else {
                    this.view.show("Ficheiro de Reviews inválido\n");
                }
            } while (!flag);
        }
        else {
            do {
                view.show("Insira o nome do ficheiro objeto: ");
                String filename = Input.lerString();
                if(filename.isEmpty()) {
                    return null;
                }
                else if(Files.exists(Paths.get(filename))){
                    paths.add(filename);
                    flag = true;
                }
                else{
                    this.view.show("Ficheiro Objeto inválido\n");
                }
            }while(!flag);
        }
        return paths;
    }

    /**
     * Método que interpreta a opção do utilizador e reencaminha para o pretendido.
     * @throws BusinessNotFoundException exceção.
     * @throws UserNotFoundException exceção.
     * @throws ReviewNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    @Override
    public void start() throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException {
        String opcao = "";
        do {
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            switch (opcao) {
                case "A":
                    statisticsQuery1();
                    break;
                case "B":
                    statisticsQuery2();
                    break;
                case "1":
                    businessesNeverReviewed();
                    break;
                case "2":
                    totalUsersOfReviewsByDate();
                    break;
                case "3":
                    usersReportPerMonth();
                    break;
                case "4":
                    businessAverageOfReviews();
                    break;
                case "5":
                    businessesReviewedByUser();
                    break;
                case "6":
                    //topXBusinessesReviewedByYear();
                    break;
                case "7":
                    famousBusinessesByCity();
                    break;
                case "8":
                    xUsersMaisAvaliaram();
                    break;
                case "9":
                    topUsersReviewedABusiness();
                    break;
                case "10":
                    break;
                case "G":
                    guardarGestReviews();
                    break;
                case "C":
                    loadGestReviews();
                case "S":
                    break;


            }
        } while (!opcao.equals("S"));
    }

    /**
     * Método que obtém os valores da Query1 Estatística e apresenta ao utilizador
     * @throws BusinessNotFoundException exceção.
     * @throws UserNotFoundException exceção.
     * @throws ReviewNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    private void statisticsQuery1() throws BusinessNotFoundException, UserNotFoundException, ReviewNotFoundException,IOException, ClassNotFoundException {
        Crono.start();
        Q1Stats q1Stats = (Q1Stats) model.interpreter(11, new ArrayList<>());
        System.out.println(Crono.getTImeString());
        this.view = new Q1StatsView(q1Stats);
        view.show(0);
        view.show();
        Input.lerString();
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query2 Estatística e a apresenta ao utilizador.
     * @throws BusinessNotFoundException exceção.
     * @throws UserNotFoundException exceção.
     * @throws ReviewNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    private void statisticsQuery2() throws BusinessNotFoundException, UserNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException {
        Crono.start();
        Q2Stats q2Stats = (Q2Stats) model.interpreter(12, new ArrayList<>());
        System.out.println(Crono.getTImeString());
        this.view = new Q2StatsView(q2Stats);
        view.show(0);
        view.show();
        Input.lerString();
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query1 e a apresenta ao utilizador.
     * @throws BusinessNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    private void businessesNeverReviewed() throws BusinessNotFoundException, IOException, ClassNotFoundException, UserNotFoundException, ReviewNotFoundException{
        int index = 0;
        String opcao = "";
        Crono.start();
        Query1 q1 = (Query1) model.interpreter(1, new ArrayList<>());
        System.out.println(Crono.getTImeString()+ "\n");
        System.out.println(q1.getTotal() + "\n");
        view = new ListView(0, 30,q1.getBusinessesList());
        int maxPag = q1.getSizeQuerie1();
        maxPag = (maxPag % 30 == 0) ? maxPag / 30 : maxPag / 30 + 1;
        do {
            view.show("\033[H\033[2J");
            System.out.flush();
            view.show(index);
            view.show();
            opcao = Input.lerString();
            opcao = opcao.toUpperCase();
            String[] args = opcao.split(" ");
            try {
                switch (args[0]) {
                    case "A":
                        if (index > 0)
                            index--;
                        break;
                    case "D":
                        if (index < maxPag - 1)
                            index++;
                        break;
                    case "J":
                        try {
                            int j = Integer.parseInt(args[1]);
                            if (j > 0 && j <= maxPag)
                                index = j - 1;
                            else
                                view.show("Página inválida\n");
                        } catch (NumberFormatException | NullPointerException e) {
                            view.show("Insira um número válido\n");
                        }
                        break;
                    default:
                        break;
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(e.getMessage() + "\nthats a no no");
            }
        } while (!opcao.equals("S"));
        view = new MainMenuView();
    }


    /**
     * Método que obtém a informação da Query2 e a apresenta ao utilizador.
     * @throws BusinessNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    private void totalUsersOfReviewsByDate() throws  IOException, ClassNotFoundException, UserNotFoundException, ReviewNotFoundException, BusinessNotFoundException {
        int mes = 0;
        int ano = 0;
        boolean verify = false;
        do {
            try {
                do {
                    view.show("Mês: ");
                    mes = Input.lerInt();
                    if (mes > 0 && mes < MONTH + 1) verify = true;
                    else view.show("Mês inválido | 0 < MES <= 12\n");

                } while (!verify);
                verify = false;

                do {
                    view.show("Ano: ");
                    ano = Input.lerInt();
                    if (ano >= 0 && ano < YEAR + 1) verify = true;
                    else view.show("Ano Inválido \n");

                } while (!verify);

            } catch (NumberFormatException | NullPointerException e) {
                view.show("Insira num número válido\n");
            }
        } while (!verify);

        List<Object> arg = new ArrayList<>();
        arg.add(mes);
        arg.add(ano);
        Crono.start();
        Query2 q2 = (Query2) model.interpreter(2, arg);
        System.out.println(Crono.getTImeString());
        this.view = new Query2View(q2.getnUsers(),q2.getnReviews());
        view.show(arg);
        view.show();
        Input.lerString();
        view = new MainMenuView();

    }

    /**
     * Método que obtém a informação da Query3 e a apresenta ao utilizador.
     */
    private void usersReportPerMonth() {
        String opcao = "";
        view.show("User: ");
        opcao = Input.lerString();
        if (opcao.isEmpty()) return;
        List<Object> arg = new ArrayList<>();
        arg.add(opcao);
        try {
            Crono.start();
            this.view = new Query3View((Query3) model.interpreter(3, arg));
            System.out.println(Crono.getTImeString());
            view.show(arg);
            view.show();
            Input.lerString();
        } catch (UserNotFoundException | BusinessNotFoundException | IOException | ClassNotFoundException | ReviewNotFoundException m) {
            System.out.println(m.getMessage() + " não existente");
        }
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query4 e a apresenta ao utilizador.
     */
    private void businessAverageOfReviews(){
        String opcao = "";
        view.show("Business: ");
        opcao = Input.lerString();
        if (opcao.isEmpty()) return;
        List<Object> arg = new ArrayList<>();
        arg.add(opcao);
        try {
            Crono.start();
            this.view = new Query4View((Query4) model.interpreter(4, arg));
            System.out.println(Crono.getTImeString());
            view.show(arg);
            view.show();
            Input.lerString();
        } catch (BusinessNotFoundException | UserNotFoundException | IOException | ClassNotFoundException | ReviewNotFoundException m) {
            this.view.show(m.getMessage() + " não existente.\n<Enter> to continue\n");
            Input.lerString();
        }
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query5 e a apresenta ao utilizador.
     */
    private void businessesReviewedByUser(){
        String opcao = "";
        view.show("User: ");
        opcao = Input.lerString();
        if (opcao.isEmpty()) return;
        List<Object> arg = new ArrayList<>();
        arg.add(opcao);
        try {
            Crono.start();
            this.view = new Query5View((Query5) model.interpreter(5, arg));
            System.out.println(Crono.getTImeString());
            view.show(arg);
            view.show();
            Input.lerString();
        } catch (BusinessNotFoundException | UserNotFoundException | IOException | ClassNotFoundException | ReviewNotFoundException m) {
            this.view.show(m.getMessage() + " não existente.\n<Enter> to continue\n");
            Input.lerString();
        }
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query6 e a apresenta ao utilizador.
     */

     //topXBusinessesReviewedByYear

    /**
     * Método que obtém a informação da Query7 e a apresenta ao utilizador.
     */
    private void famousBusinessesByCity() throws UserNotFoundException, ReviewNotFoundException, BusinessNotFoundException, IOException, ClassNotFoundException {
        Crono.start();
        this.view = new Query7View((Query7) model.interpreter(7,new ArrayList<>()));
        System.out.println(Crono.getTImeString());
        int n = 1;
        view.show(n);
        view.show();
        Input.lerString();
        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query8 e a apresenta ao utilizador.
     */
    private void xUsersMaisAvaliaram() throws UserNotFoundException, ReviewNotFoundException, BusinessNotFoundException, IOException, ClassNotFoundException {
        view.show("Número de Utilizadores: ");
        int n;
        do {
            n = Input.lerInt();
            if (n < 0) view.show("Número inválido\nNúmero de clientes: ");
        } while (n < 0);
        List<Object> arg = new ArrayList<>();
        arg.add(n);
        Crono.start();
        this.view = new Query8View((Query8) model.interpreter(8, arg));
        System.out.println(Crono.getTImeString());
        view.show(n);
        view.show();
        Input.lerString();

        view = new MainMenuView();
    }

    /**
     * Método que obtém a informação da Query9 e a apresenta ao utilizador.
     */
    private void topUsersReviewedABusiness() throws UserNotFoundException, ReviewNotFoundException, BusinessNotFoundException, IOException, ClassNotFoundException {
        String business;
        int n;
        view.show("Business: ");
        business = Input.lerString();
        view.show("Número de utilizadores: ");
        do {
            n = Input.lerInt();
            if (n < 0) view.show("Número inválido\nNúmero de clientes: ");
        } while (n < 0);
        List<Object> arg = new ArrayList<>();
        arg.add(business);
        arg.add(n);
        try {
            Crono.start();
            this.view = new Query9View((Query9) model.interpreter(9, arg));
            System.out.println(Crono.getTImeString());
            view.show(n);
            view.show();
            Input.lerString();

        } catch (BusinessNotFoundException | IOException | ClassNotFoundException m) {
            System.out.println(m.getMessage() + " não existente.\n<Enter> to continue\n");
            Input.lerString();
        }
        view = new MainMenuView();
    }

    /**
     * Método que permite guardar o modelo que gere o programa como um ficheiro.
     * @throws UserNotFoundException exceção.
     * @throws IOException exceção.
     * @throws BusinessNotFoundException exceção.
     * @throws ClassNotFoundException exceção.
     */
    public void guardarGestReviews() throws UserNotFoundException, IOException, BusinessNotFoundException, ReviewNotFoundException, ClassNotFoundException {
        List<Object> aux = new ArrayList<>();
        String defaultFileName = "gestReviews.dat";
        aux.add(defaultFileName);
        model.interpreter(13, aux);
    }

    /**
     * Método que permite carregar o modelo que gere o programa com um ficheiro.
     * @throws BusinessNotFoundException exceção.
     * @throws UserNotFoundException exceção.
     * @throws ReviewNotFoundException exceção.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     */
    public void loadGestReviews() throws BusinessNotFoundException, IOException, UserNotFoundException, ReviewNotFoundException, ClassNotFoundException {
        boolean valid = false;
        String filename;
        do {
            view.show("Insira o nome do ficheiro objeto: ");
            filename = Input.lerString();
            if (filename.isEmpty()) return;
            else if (Files.exists(Paths.get(filename))) {
                valid = true;
            } else {
                this.view.show("Ficheiro Objeto inválido\n");
            }
        } while (!valid);
        this.model = GestReviews.loadGestReviews(filename);
    }
}
