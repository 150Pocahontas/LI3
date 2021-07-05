/**
 * @file GestReviews.java
 *  Ficheiro que contém a API relativa à implementação do model.
 */

package Models;

import Catalogos.*;
import Exceptions.*;
import Interfaces.*;
import Readers.Parser;
import Interfaces.Querie;
import Queries.*;
import ReviewsStuff.ReviewUserBusiness;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GestReviews implements GereReviewsModel, Serializable {

    private UsersCatalog users;
    private BusinessesCatalog business;
    private ReviewsCatalog reviews;
    private Q1Stats q1Stats;
    private ReviewUserBusiness rub;

    /**
     *  Método que inicializa o modelo do SGR.
     */
    public GestReviews() {
        this.users = new UsersCatalog();
        this.business = new BusinessesCatalog();
        this.reviews = new ReviewsCatalog();
        this.rub = new ReviewUserBusiness();
    }

    /**
     *  Método que retorna a informação de uma query pedida pelo utilizador.
     * @param n int que é a opção do utilizador.
     * @param l que contém, caso necessário, os argumentos para a execução da query.
     * @return Querie que contém o resultado da query pedida pelo utilizador.
     */
    public Querie interpreter(int n, Collection<Object> l) throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException {
        List<Object> lista = new ArrayList<>(l);
        switch (n) {
            case 1:
                return querie1();
            case 2:
               return query2((Integer) lista.get(0), (Integer) lista.get(1));
            case 3:
                return query3((String) lista.get(0));
            case 4:
                return query4((String) lista.get(0));
            case 5:
                return query5((String) lista.get(0));
            case 6:
                return query6((Integer) lista.get(0));
            case 7:
                return query7();
            case 8:
                return query8((Integer) lista.get(0));
            case 9:
                return query9((String) lista.get(0), (Integer) lista.get(1));
            case 10:
                //return query10();
            case 11:
                return querie1stats();
            case 12:
                return querie2stats();
            case 13:
                gravarGestReviews((String) lista.get(0));
            default:
                return null;
        }
    }

    /*------------------------------------QUERIES------------------------------------------*/

    /**
     * Método que retorna a informação pedida na Query1.
     * @return Querie1 que contém a informação relativa à Query1.
     */
    public Query1 querie1() {
        return this.rub.comparaQ1();
    }

    /**
     * Método que retorna a informação pedida na Query2.
     * @param mes int, mês.
     * @return Query2 que contém a informação relativa à Query2.
     */
    private Query2 query2(int mes, int ano) throws BusinessNotFoundException, IOException, ClassNotFoundException, UserNotFoundException, ReviewNotFoundException{
        return this.rub.countReviewsAndUsersQ2(mes, ano);
    }

    /**
     * Método que retorna a informação pedida na Query3.
     * @param userCode String, user code.
     * @return Query3 que contém a informação relativa à Query3.
     */

    private Query3 query3(String userCode) throws UserNotFoundException{
        return this.rub.userMonthlyValuesQ3(userCode);
    }

    /**
     * Método que retorna a informação pedida na Query4.
     * @param businessCode String, business code.4
     * @return Query4 que contém a informação relativa à Query4.
     */

    public Query4 query4(String businessCode) throws BusinessNotFoundException {
        return this.rub.businessMonthlyValuesQ4(businessCode);
    }

    /**
     * Método que retorna a informação pedida na Query5.
     * @param userCode String, user code.
     * @return Query5 que contém a informação relativa à Query5.
     */
    public Query5 query5(String userCode) throws UserNotFoundException {
        return this.rub.mostReviewedBusinessQ5(userCode);
    }

    /**
     * Método que retorna a informação pedida na Query6.
     * @param n int, numero de negocios.
     * @return Query6 que contém a informação relativa à Query6.
     */
    public Query6 query6(int n)   {
        return this.rub.topXBusinessesReviewed(n);
    }

    /**
     * Método que retorna a informação pedida na Query7.
     * @return Query7 que contém a informação relativa à Query7.
     */
    private Query7 query7() {
        return this.rub.businessMaisFamososQ7();
    }

    /**  Método que retorna a informação pedida na Query8.
     * @return Query8 que contém a informação relativa à Query8.
     */
    private Query8 query8(int n) {
        return this.rub.usersMoreBusinessQ8(n);
    }

    /**
     *  Método que retorna a informação pedida na Query9.
     * @param business String, business code code.
     */
    private Query9 query9(String business, int n) throws BusinessNotFoundException {
        return this.rub.usersBusinessQ9(business,n);
    }

    /**
     *  Método que retorna a informação pedida na Query1 Estatística.
     * @return Q1Stats que contém a informação relativa à Query1 Etatística.
     */
    public Q1Stats querie1stats(){
        return this.q1Stats;
    }

    /**
     *  Método que retorna a informação pedida na Query2 Estatística.
     * @return Q2Stats que contém a informação relativa à Query2 Etatística.
     */

     public Q2Stats querie2stats() {
         return this.rub.getUserValuesQ2Stats();

    }

    /**
     *  Método que carrega com os caminhos pré-definidos o modelo do programa.
     * @throws FileNotFoundException exceção.
     */
    @Override
    public void loadDefault() throws FileNotFoundException {
        Parser p = new Parser();
        this.q1Stats = new Q1Stats();
        p.parser(this.users, this.business, this.reviews,this.rub,q1Stats);
    }
    /**
     * Método que carrega com os caminhos dados pelo Utiilizador, o modelo do programa.
     * @param paths Lista com os caminhos inseridos pelo Utilizador.
     */
    @Override
    public void loadFiles(List<String> paths){
        Parser p = new Parser();
        this.q1Stats = new Q1Stats();
        p.parserDiffFiles(paths,this.users,this.business,this.reviews,this.rub,q1Stats);

    }

    /**
     *  Método que permite guardar o modelo do programa como um objeto .dat.
     * @param filename String que será o nome do ficheiro.
     * @throws IOException exceção.
     */
    public void gravarGestReviews(String filename) throws IOException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
        o.writeObject(this);
        o.flush();
        o.close();
    }

    /**
     *  Método que permite carregar um modelo do programa com um objeto .dat.
     * @param filename String que será o nome do ficheiro.
     * @throws IOException exceção.
     * @throws ClassNotFoundException exceção.
     * @return GestReviews gestor de Reviews.
     */
    public static GestReviews loadGestReviews(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
        GestReviews d = (GestReviews) o.readObject();
        o.close();
        return d;
    }
}