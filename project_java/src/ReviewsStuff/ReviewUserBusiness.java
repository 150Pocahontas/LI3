package ReviewsStuff;

import Exceptions.*;
import Geral.*;
import Queries.*;
import Catalogos.*;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class ReviewUserBusiness implements Variables, Serializable {
    private Set<String> users;
    private Set<String> business;
    private UsersCatalog catUser;
    private BusinessesCatalog catBusiness;
    private ReviewsCatalog catReviews;

    public ReviewUserBusiness() {
        users = new TreeSet<>();
        business = new TreeSet<>();
        catReviews = new ReviewsCatalog();
        catUser = new UsersCatalog();
        catBusiness = new BusinessesCatalog();
    }

    public void setCatUser(UsersCatalog catUser) { this.catUser = catUser;}

    public void setCatBusiness(BusinessesCatalog catBusiness) {
        this.catBusiness = catBusiness;
    }

    public void setCatReviews(ReviewsCatalog catReviews) {
        this.catReviews = catReviews;
    }

    public void addUserCode(String code) { users.add(code); }

    public void addBusinessCode(String code) {
        business.add(code);
    }

    public int lengthUsers() {
        return this.users.size();
    }

    public int lengthBusiness() {
        return this.business.size();
    }

    public int[] getUsersDifMes() {
        Map<Integer, Set<String>> us = new HashMap<>();
        int[] aux = new int[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            String u = r.getUser();
            int mes = r.getMes();
            us.putIfAbsent(mes-1, new TreeSet<>());
            us.get(mes-1).add(u);
        }
        for (Integer i : us.keySet()) {
            aux[i] = us.get(i).size();
        }
        return aux;
    }

    public int getNReviewsMesEspecifico(int m) {
        int nrev = 0;
        for (Review r : this.catReviews.getReviewsList()) {
            if (r.getMes() == m)
                nrev++;
        }
        return nrev;
    }

    public double[] getClass_total_mes() {
        Map<Integer, Set<Double>> s = new HashMap<>();
        double[] aux = new double[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            double clf = r.getStars();
            int mes = r.getMes();
            s.putIfAbsent(mes-1, new TreeSet<>());
            s.get(mes-1).add(clf);
        }

        for (Integer i : s.keySet()) {
            aux[i] = (s.get(i).stream().mapToDouble(Double::doubleValue).sum()) / (s.get(i).size());
        }
        return aux;
    }

    public double getClass_global(){
        return (Arrays.stream(getClass_total_mes()).sum())/getClass_total_mes().length;
    }

    public int[] getNReviews_mes(){
        int[] aux = new int[MONTH];
        for (int i = 0; i < MONTH; i++) {
            aux[i] = this.getNReviewsMesEspecifico(i+1);
        }
        return aux;
    }

    /*

    public int[] getBusDiffMes(){
        int[] aux = new int[MONTH];
        for(Review r: this.catBusiness.getBusinessList()) {
            for (int i = 0; i < MONTH; i++) {
                aux[i]=this.lengthBusiness();
            }
        }
        return aux;
    }


     */

    /*--------------------------------------------------QUERIE2STATS--------------------------------------------------*/

    /**
     * Método que adiciona à Q2Stats a informação relativa aos utilizadores.
     *
     * @return q2Stats Q2Stats que é a estrutura onde é inserida a informação.
     */

    public Q2Stats getUserValuesQ2Stats() {
        Q2Stats q2Stats = new Q2Stats();
        q2Stats.setUserDifPorMes(getUsersDifMes());
        q2Stats.setReviewsPorMes(getNReviews_mes());
        q2Stats.setMediaClasReview(getClass_total_mes());
        q2Stats.setClassGlobalReviews(getClass_global());
        return q2Stats;
    }


    /*------------------------------------------------------QUERY1-------------------------------------------------------*/
    /**
     * Método que dado um conjunto com os códigos de negócios, verifica quais nunca foram avaliados.
     * @return q1 Querie1 onde serão guardados os negócios, bem como a quantidade total, que não foram avaliados.
     */

    public Query1 comparaQ1() {
        Query1 q1 = new Query1();
        Set<String> res = new TreeSet<>();
            for(Business b : this.catBusiness.getBusinessList()){
                String c = b.getCode();
                if(!this.business.contains(c)) {
                    res.add(c);
                }
        }
        q1.setBusinesses(res);
        q1.setTotal(res.size());
        return q1;
    }

    /*------------------------------------------------------QUERY2-------------------------------------------------------*/

    public int getNReviews_Data_Especifica(int m, int a){
        Map<Integer,Set<String>>rev = new HashMap<>();
        int aux = 0;
        for (Review r : this.catReviews.getReviewsList()) {
            String re = r.getCode();
            m = r.getMes();
            rev.putIfAbsent(m, new TreeSet<>());
            rev.get(m).add(re);
        }
        for(Integer i: rev.keySet()){
            aux = rev.get(i).size();
        }
        return aux;
    }

    public int getUsersDif(int m, int a) {
        Map<Integer, Set<String>> users = new HashMap<>();
        int aux = 0;
        for (Review r : this.catReviews.getReviewsList()) {
            String u = r.getUser();
            m = r.getMes();
            users.putIfAbsent(m, new TreeSet<>());
            users.get(m).add(u);
        }
        for(Integer i: users.keySet()){
           aux = users.get(i).size();
        }
        return aux;
    }

    public Query2 countReviewsAndUsersQ2(int mes, int ano){
        Query2 q2 = new Query2();
        q2.setnUsers(getUsersDif(mes,ano));
        q2.setnReviews(getNReviews_Data_Especifica(mes,ano));
        return q2;

    }

    /*------------------------------------------------------QUERY3-------------------------------------------------------*/

    public int[] getNReviews_mesU(String userCode) {
        Map<Integer, Set<Integer>> review = new HashMap<>();
        int[] aux = new int[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if (r.getUser().equals(userCode)) {
                int mes = r.getMes();
                review.putIfAbsent(mes-1, new TreeSet<>());
                review.get(mes-1).add(1);
            }
            for (Integer i : review.keySet()) {
                aux[i] = review.get(i).size();
            }
        }
        return aux;
    }

    public int[] getTotalBusinessesReviewed(String userCode){
        Map<Integer, Set<String>> business = new HashMap<>();
        int[] aux = new int[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getUser().equals(userCode)) {
                String b = r.getBusiness();
                int mes = r.getMes();
                business.putIfAbsent(mes-1, new TreeSet<>());
                business.get(mes-1).add(b);
            }
        }
        for (Integer i : business.keySet()) {
            aux[i] = business.get(i).size();
        }
        return aux;
    }

    public double[] getClass_total_mesU(String userCode) {
        Map<Integer, Set<Double>> s = new HashMap<>();
        double[] aux = new double[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getUser().equals(userCode)) {
                double clf = r.getStars();
                int mes = r.getMes();
                s.putIfAbsent(mes-1, new TreeSet<>());
                s.get(mes-1).add(clf);
            }
        }
        for (Integer i : s.keySet()) {
            aux[i] =  (s.get(i).size());
        }
        return aux;
    }

    /**
     * Método que, dado um código de utilizador, calcula o número de reviews realizadas,  a média total de classificações e o total de negócios avaliados por mês.
     * @param userCode String que é o user a averiguar.
     * @throws UserNotFoundException exceção.
     * @return q3 Querie3 que é a estrutura a inserir a informação.
     */

    public Query3 userMonthlyValuesQ3(String userCode) throws UserNotFoundException {
        Query3 q3 = new Query3();
        if (this.users.contains(userCode)){
           q3.setNreviews(getNReviews_mesU(userCode));
           q3.setMedia_mes(getClass_total_mesU(userCode));
           q3.setNbusinesses(getTotalBusinessesReviewed(userCode));
        }else{
            throw new UserNotFoundException(userCode);
        }
        return q3;
    }


    /*------------------------------------------------------QUERY4-------------------------------------------------------*/

    /**
     * Função que determina o numero de utilizadores que avaliaram o negócio
     * @param businessCode codigo do negócio
     * @return int[] com o numero de vezes que o negócio foi avalido por mes
     */
    public int[] get_Users_reviewed(String businessCode) {
        Map<Integer, Set<String>> business = new HashMap<>();
        int[] aux = new int[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getBusiness().equals(businessCode)) {
                int mes = r.getMes();
                business.putIfAbsent(mes-1, new TreeSet<>());
                business.get(mes-1).add(r.getUser());
            }
        }
        for (Integer i : business.keySet()) {
            aux[i] = business.get(i).size();
        }
        return aux;
    }

    /**
     * Função que determina o numero de vezes que um negócio foi avalidado mes a mes
     * @param businessCode codigo do negócio
     * @return int[] com o numero de vezes que o negócio foi avalido por mes
     */
    public int[] getNVezesAvaliadoMes(String businessCode){
        Map<Integer, Set<Integer>> business = new HashMap<>();
        int[] aux = new int[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getBusiness().equals(businessCode)) {
                int mes = r.getMes();
                business.putIfAbsent(mes-1, new TreeSet<>());
                business.get(mes-1).add(1);
            }
        }
        for (Integer i : business.keySet()) {
            aux[i] = business.get(i).size();
        }
        return aux;
    }

    public double[] getClass_total_mesB(String businessCode) {
        Map<Integer, Set<Double>> s = new HashMap<>();
        double[] aux = new double[MONTH];
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getBusiness().equals(businessCode)) {
                double clf = r.getStars();
                int mes = r.getMes();
                s.putIfAbsent(mes-1, new TreeSet<>());
                s.get(mes-1).add(clf);
            }
        }
        for (Integer i : s.keySet()) {
            aux[i] =  (s.get(i).size());
        }
        return aux;
    }

    public Query4 businessMonthlyValuesQ4(String businessCode) throws BusinessNotFoundException{
        Query4 q4 = new Query4();
        q4.setTimes_reviewed(getNVezesAvaliadoMes(businessCode));
        q4.setUsers_reviewed(get_Users_reviewed(businessCode));
        q4.setMedia_class_mes(getClass_total_mesB(businessCode));

        return q4;
    }


    /*------------------------------------------------------QUERY5-------------------------------------------------------*/

    public LinkedHashMap<String,Integer> getBusinessReviwedOrder(String userCode){
        Map<String,Set<Integer>> s = new HashMap<>();
        LinkedHashMap<String,Integer> aux = new LinkedHashMap<>();
        for (Review r : this.catReviews.getReviewsList()) {
            if(r.getUser().equals(userCode)) {
                String b = r.getBusiness();
                s.putIfAbsent(b, new TreeSet<>());
                s.get(b).add(1);
            }
        }

        Map<String, Integer> news = new HashMap<>();
        for(String i: s.keySet()){
            news.put(i,s.get(i).size());
        }

         news.entrySet()
                .stream()
                 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                 .sorted(Map.Entry.comparingByKey())
                 .forEachOrdered(x -> aux.put(x.getKey(), x.getValue()));

        return aux;
    }

    /**
     * Método que, dado um código de utilizador, calcula o número de reviews realizadas,  a média total de classificações e o total de negócios avaliados por mês.
     * @param userCode String que é o user a averiguar.
     * @throws UserNotFoundException exceção.
     * @return q5 Querie5 que é a estrutura a inserir a informação.
     */

    public Query5 mostReviewedBusinessQ5(String userCode) throws UserNotFoundException {
        Query5 q5 = new Query5();
        if (this.users.contains(userCode)){
            q5.setBusineesReviedOrder(getBusinessReviwedOrder(userCode));
        }else{
            throw new UserNotFoundException(userCode);
        }
        return q5;
    }

    /*------------------------------------------------------QUERY6-------------------------------------------------------*/

    public Map<Integer, Map<String,Set<Integer>>> businessMostReviewed(int n){
        Map<Integer, Map<String,Set<Integer>>> aux = new HashMap<>();
        for(Review r : this.catReviews.getReviewsList()){
            String b = r.getBusiness();
            int ano = r.getAno();
            aux.putIfAbsent(ano, new HashMap<>());
            aux.get(ano).putIfAbsent(b,new TreeSet<>());
            aux.get(ano).get(b).add(1);
        }
        return aux;
    }

    /**
     * Método que, dado um numero de negocios, retorna a lista dos negócios mais avaliados e o numero de utilizadores que avaliaram.
     * //@param n int que é o numero de negocios a averiguar.
     * @return q6 Query6 que é a estrutura a inserir a informação.
     */

    public Query6 topXBusinessesReviewed(int n) {
        Query6 q6 = new Query6();
        q6.setTimes_reviewed(businessMostReviewed(n));
        return q6;
    }


    /*------------------------------------------------------QUERY7-------------------------------------------------------*/

    public Query7 businessMaisFamososQ7 () {
        Query7 q7 = new Query7();
        Map<String,Map<String,Integer>> aux = new HashMap<>();
        //pega em todas as reviews
        List<Review> reviews = new ArrayList<>(catReviews.getReviewsList());
        //cria set c/ nome de todas as cidades
        Set<String> cidades = catBusiness.getBusinessList().stream().map(Business::getCity).collect(Collectors.toSet());
        //p/ cada cidade cria uma lista dos ids dos businesses
        for (String c : cidades){
            List<String> businessCity = catBusiness.getBusinessList().stream().filter(a -> a.getCity().equals(c)).map(Business::getCode).collect(Collectors.toList());
            List<Map.Entry<String,Integer>> aux2 = new ArrayList<>();
            for (String b :businessCity){
                //p/ cada business, pega nas reviews e ve o numero (i)
                int i = (int) reviews.stream().filter(a -> a.getBusiness().equals(b)).count();
                //adiciona uma entry a lista
                aux2.add(new AbstractMap.SimpleEntry<>(catBusiness.getBusinessByID(b).getName(),i));
            }
            //ordena a lista por ordem de reviews
            aux2.sort((a,b)->-a.getValue().compareTo(b.getValue()));
            //ve se tem mais de 3 reviews para truncar
            if(aux2.size()>3)
                //pega nas 3 maiores e adiciona ao aux
                aux2 = aux2.subList(0,3);
            Map<String,Integer> temp = new HashMap<>();
            for(int i = 0;i<aux2.size();i++){
                temp.put(aux2.get(i).getKey(),aux2.get(i).getValue());
            }
            aux.put(c,temp);
        }
        q7.setBusinessMostFamose(aux);
        return q7;
    }

    /*------------------------------------------------------QUERY8-------------------------------------------------------*/

    public Query8 usersMoreBusinessQ8 (int n) {
        Query8 q8 = new Query8();
        //cria lista de todas as reviews
        List<Review> reviews = new ArrayList<>(this.catReviews.getReviewsList());
        //pega nas reviews e cria um set com ids dos users
        Set<String> users = reviews.stream().map(Review::getUser).collect(Collectors.toSet());
        for(String s : users) {
            //p/ cada user, pega nas reviews e cria uma  lista
            List<Review> temp = reviews.stream().filter(a->a.getUser().equals(s)).collect(Collectors.toList());
            //cria um set c/ os businesses todos das reviews
            Set<String> b = temp.stream().map(Review::getBusiness).collect(Collectors.toSet());
            //adiciona um par a lista
            q8.insereUtilizadores_negocio(s,b.size());
        }
        //ordena a lista
        q8.sortAndTruncate(n);
        return q8;
    }

    /*------------------------------------------------------QUERY9-------------------------------------------------------*/

    public Query9 usersBusinessQ9(String business, int n) throws BusinessNotFoundException {
        Query9 q9 = new Query9();
        //lista das reviews c/ businesses dado no input
        List<Review> reviews = this.catReviews.getReviewsList().stream().filter(a->a.getBusiness().equals(business)).collect(Collectors.toList());
        if (reviews.size() == 0) throw new BusinessNotFoundException();
        //set c/ todos os users da lista de reviews
        Set<String> users = reviews.stream().map(Review::getUser).collect(Collectors.toSet());
        //p/users ve as reviews que fez e conta-as
        for(String u: users){
            List<Review> r = reviews.stream().filter(a->a.getUser().equals(u)).collect(Collectors.toList());
            //adiciona o par a lista
            q9.insereNrmsReviews(u,r.size());
        }
        //organiza pelo numero de reviews
        q9.sortAndTruncate(n);
        //pega nos pares anteriores
        for (Map.Entry<String, Integer> e : q9.getNmrs_reviews()) {
            //para cada user dos pares, cria uma lista c/ as reviews que fez
            List<Review> r = reviews.stream().filter(a->a.getUser().equals(e.getKey())).collect(Collectors.toList());
            //poe o nr total de stars dadas no total
            float total = r.stream().map(Review::getStars).reduce(0.0f,Float::sum);
            //adiciona a média à lista
            q9.insereUserReviews(e.getKey(),total/r.size());
        }
        return q9;
    }

}












