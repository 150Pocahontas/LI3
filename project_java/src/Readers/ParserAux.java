package Readers;


import Geral.*;
import Catalogos.*;
import Queries.Q1Stats;
import ReviewsStuff.ReviewUserBusiness;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ParserAux implements Variables, Serializable {

    /**
     *  Método que lê ficheiros de users, businesses e reviews e insere nas respetivas estruturas
     * @param usersCatalog UsersCatalog onde serão inseridos as informações de users lidos.
     * @param businessesCatalog BusinessesCatalog onde serão inseridos as informações de negócios lidos.
     * @param reviewsCatalog ReviewsCatalog onde serão inseridos as informações de reviews lidos.
    //* @param q1Stats Q1Stats onde serão guardados valores referentes à Query1 Estatística.
     * @throws FileNotFoundException exceção.
     */
    public void parser(UsersCatalog usersCatalog, BusinessesCatalog businessesCatalog, ReviewsCatalog reviewsCatalog, ReviewUserBusiness rub,Q1Stats q1Stats) throws FileNotFoundException {
        List<String> users = Collections.emptyList();
        List<String> businesses = Collections.emptyList();
        List<String> reviews = Collections.emptyList();
        String []buffer;

        int nusers = 0;
        int nbusiness = 0;
        int nreviewsValidas = 0;
        int nreviewsTotal=0;
        int reviewsSImp = 0;

        try{
            Crono.start();
            users = Files.readAllLines(Paths.get(usersFileDefault));
            for(String s: users) {
                buffer = s.split(";");
                User u = new User();
                u.setCode(buffer[0]);
                u.setName(buffer[1]);
                buffer = s.split(",");

                nusers++;
            }
            q1Stats.setTotalUsers(nusers);
            businesses = Files.readAllLines(Paths.get(businessesFileDefault));
            for(String s: businesses) {
                buffer = s.split(";");
                Business b = new Business();
                b.setCode(buffer[0]);
                b.setName(buffer[1]);
                b.setCity(buffer[2]);
                b.setState(buffer[3]);
                b.setCategory(buffer[4]);
                nbusiness++;
            }
            q1Stats.setTotalBusinesses(nbusiness);
            reviews = Files.readAllLines(Paths.get(reviewsFileDefault));
            for(String s : reviews) {
                buffer = s.split(";");
                nreviewsTotal++;
                if( buffer.length>=9 && usersCatalog.checkCode(buffer[1]) && businessesCatalog.checkCode(buffer[2])){
                    Review r = new Review();
                    r.setCode(buffer[0]);
                    r.setUser(buffer[1]);
                    rub.addUserCode(buffer[1]);
                    r.setBusiness(buffer[2]);
                    rub.addBusinessCode(buffer[2]);
                    r.setStars(Float.parseFloat(buffer[3]));
                    r.setUseful(Integer.parseInt(buffer[4]));
                    r.setFunny(Integer.parseInt(buffer[5]));
                    r.setCool(Integer.parseInt(buffer[6]));
                    String[] mes = s.split("-");
                    r.setMes(Integer.parseInt(mes[1]));
                    nreviewsValidas++;
                    reviewsCatalog.addReview(r);
                    if((Integer.parseInt(buffer[4]) + Integer.parseInt(buffer[5]) + Integer.parseInt(buffer[6])) == 0)
                        reviewsSImp++;
                }
            }
            q1Stats.setnReviewsErradas(nreviewsTotal-nreviewsValidas);
            q1Stats.setReviewsSemImpacto(reviewsSImp);
            q1Stats.setTotalBusinessNaoAvalidados(nbusiness-rub.lengthBusiness());
            q1Stats.setTotalBusinessesDif(rub.lengthBusiness());
            q1Stats.setTotalUsersFizeramReviews(rub.lengthUsers());
            q1Stats.setTotalUsersNaoAvaliaram(nusers- rub.lengthUsers());
            System.out.println("Loading : " + Crono.getTImeString());
        }
        catch (IOException e) {
            // do something
            System.out.println("Deu Errado");
        }
    }

    /**
     * Método que lê ficheiros de clientes, produtos e vendas e insere nas respetivas estruturas.
     * @param path List que contém os caminhos dos ficheiros a ler.
     * @param usersCatalog UsersCatalog onde serão inseridos os códigos de cliente lidos.
     * @param businessesCatalog BusinessesCatalog onde serão inseridos os códigos de produto lidos.
    //* @param q1Stats Q1Stats onde serão guardados valores referentes à Query1 Estatística.
     */
    public void parserDiffFiles(List<String> path, UsersCatalog usersCatalog, BusinessesCatalog businessesCatalog, ReviewsCatalog reviewsCatalog, Q1Stats q1Stats){
        int nusers = 0;
        int nbusiness = 0;
        int nreviewsValidas = 0;
        int nreviewsTotal=0;
        int reviewsSImp = 0;
        try {
            BufferedReader usersbr = new BufferedReader(new FileReader(path.get(0)));
            BufferedReader businessbr = new BufferedReader(new FileReader(path.get(1)));
            BufferedReader reviewsbr = new BufferedReader(new FileReader(path.get(2)));

            q1Stats.setUserFile(path.get(0));
            q1Stats.setBusinessFile(path.get(1));
            q1Stats.setReviewFile(path.get(2));

            String s;
            String[] buffer;

            usersbr.readLine(); // read the firstline
            while ((s = usersbr.readLine()) != null) {
                buffer = s.split(";");
                User u = new User();
                u.setCode(buffer[0]);
                u.setName(buffer[1]);
                buffer = s.split(",");

                nusers++;
            }
            usersbr.close();
            q1Stats.setTotalUsers(nusers);
            businessbr.readLine(); // read the firstline
            while((s = businessbr.readLine()) != null) {
                buffer = s.split(";");
                Business b = new Business();
                b.setCode(buffer[0]);
                b.setName(buffer[1]);
                b.setCity(buffer[2]);
                b.setState(buffer[3]);
                b.setCategory(buffer[4]);
                nbusiness++;
                businessesCatalog.addBusiness(b);
            }
            businessbr.close();
            q1Stats.setTotalBusinesses(nbusiness);
            reviewsbr.readLine(); // read the firstline
            ReviewUserBusiness rub2 = new ReviewUserBusiness();
            while ((s = reviewsbr.readLine()) != null) {
                buffer = s.split(";");
                nreviewsTotal++;
                if( buffer.length>=9 && usersCatalog.checkCode(buffer[1]) && businessesCatalog.checkCode(buffer[2])){
                    Review r = new Review();
                    r.setCode(buffer[0]);
                    r.setUser(buffer[1]);
                    rub2.addUserCode(buffer[1]);
                    r.setBusiness(buffer[2]);
                    rub2.addBusinessCode(buffer[2]);
                    r.setStars(Float.parseFloat(buffer[3]));
                    r.setUseful(Integer.parseInt(buffer[4]));
                    r.setFunny(Integer.parseInt(buffer[5]));
                    r.setCool(Integer.parseInt(buffer[6]));
                    String[] date = s.split("-");
                    r.setAno(Integer.parseInt(date[0]));
                    r.setMes(Integer.parseInt(date[1]));
                    nreviewsValidas++;
                    reviewsCatalog.addReview(r);
                    if((Integer.parseInt(buffer[4]) + Integer.parseInt(buffer[5]) + Integer.parseInt(buffer[6])) == 0)
                        reviewsSImp++;
                }
            }
            reviewsbr.close();
            q1Stats.setnReviewsErradas(nreviewsTotal-nreviewsValidas);
            q1Stats.setReviewsSemImpacto(reviewsSImp);
            q1Stats.setTotalBusinessNaoAvalidados(nbusiness-rub2.lengthBusiness());
            q1Stats.setTotalBusinessesDif(rub2.lengthBusiness());
            q1Stats.setTotalUsersFizeramReviews(rub2.lengthUsers());
            q1Stats.setTotalUsersNaoAvaliaram(nusers- rub2.lengthUsers());
            System.out.println(nreviewsValidas);
        } catch (IOException e) {
            System.out.println("Ficheiro(s) inexistente(s)");
            System.exit(-1);
        }
    }
}