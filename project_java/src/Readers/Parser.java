package Readers;

import Catalogos.*;
import Geral.*;
import Queries.Q1Stats;

import Geral.Variables;
import ReviewsStuff.ReviewUserBusiness;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Parser implements Variables, Serializable {

    /**
     * Método que lê ficheiros de clientes, produtos e vendas e insere nas respetivas estruturas
     * @param usersCatalog     UsersCatalog onde serão inseridos as informações de users lidos.
     * @param businessesCatalog BusinessesCatalog onde serão inseridos as informações de negócios lidos.
     * @param reviewsCatalog   ReviewsCatalog onde serão inseridos as informações de reviews lidos.
    // * @param q1Stats          Q1Stats onde serão guardados valores referentes à Query1 Estatística.
     */
    public void parser(UsersCatalog usersCatalog, BusinessesCatalog businessesCatalog, ReviewsCatalog reviewsCatalog, ReviewUserBusiness rub, Q1Stats q1Stats) {
        int nusers = 0;
        int nbusiness = 0;
        int nreviewsValidas = 0;
        int nreviewsTotal=0;
        int reviewsSImp = 0;

        try {
            BufferedReader usersbr = new BufferedReader(new FileReader(usersFileDefault));
            BufferedReader businessbr = new BufferedReader(new FileReader(businessesFileDefault));
            BufferedReader reviewsbr = new BufferedReader(new FileReader(reviewsFileDefault));

            q1Stats.setUserFile(usersFileDefault);
            q1Stats.setBusinessFile(businessesFileDefault);
            q1Stats.setReviewFile(reviewsFileDefault);

            String s;
            String[] buffer;
            usersbr.readLine(); // read the firstline
            while ((s = usersbr.readLine()) != null) {
                buffer = s.split(";");
                User u = new User();
                u.setCode(buffer[0]);
                u.setName(buffer[1]);
                u.setFriends(buffer[2]);
                usersCatalog.addUser(u);
                nusers++;
            }
            usersbr.close();
            rub.setCatUser(usersCatalog);
            q1Stats.setTotalUsers(nusers);

            businessbr.readLine(); // read the firstline
            while ((s = businessbr.readLine()) != null) {
                buffer = s.split(";");
                Business b = new Business();
                b.setCode(buffer[0]);
                b.setName(buffer[1]);
                b.setCity(buffer[2]);
                b.setState(buffer[3]);
                if((buffer.length)>4) b.setCategory(buffer[4]);
                businessesCatalog.addBusiness(b);
                nbusiness++;
            }
            businessbr.close();
            rub.setCatBusiness(businessesCatalog);
            q1Stats.setTotalBusinesses(nbusiness);
            reviewsbr.readLine(); // read the firstline
            while ((s = reviewsbr.readLine()) != null) {
                buffer = s.split(";");
                nreviewsTotal++;
                if(buffer.length>8 && usersCatalog.checkCode(buffer[1]) && businessesCatalog.checkCode(buffer[2])){
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
                    String[] dat = buffer[7].split("-");
                    r.setAno(Integer.parseInt(dat[0]));
                    r.setMes(Integer.parseInt(dat[1]));
                    if((Integer.parseInt(buffer[4]) + Integer.parseInt(buffer[5]) + Integer.parseInt(buffer[6])) == 0)
                        reviewsSImp++;
                    nreviewsValidas++;
                    reviewsCatalog.addReview(r);
                }
            }
            reviewsbr.close();
            rub.setCatReviews(reviewsCatalog);
            q1Stats.setReviewsTotal(nreviewsTotal);
            q1Stats.setReviewsValidas(nreviewsValidas);
            q1Stats.setReviewsSemImpacto(reviewsSImp);
            q1Stats.setTotalBusinessesDif(rub.lengthBusiness());
            q1Stats.setTotalUsersFizeramReviews(rub.lengthUsers());
            q1Stats.setnReviewsErradas(nreviewsTotal-nreviewsValidas);
            q1Stats.setTotalBusinessNaoAvalidados(nbusiness-rub.lengthBusiness());
            q1Stats.setTotalUsersNaoAvaliaram(nusers- rub.lengthUsers());
        } catch (IOException e) {
            System.out.println("Ficheiro(s) inexistente(s)");
            System.exit(-1);
        }
    }

    /**
     * Método que lê ficheiros de clientes, produtos e vendas e insere nas respetivas estruturas.
     * @param path           que contém os caminhos dos ficheiros a ler.
     * @param usersCatalog   UsersCatalog onde serão inseridos as informações de users lidos.
     * @param businessesCatalog BusinessesCatalog onde serão inseridos as informações de negócios lidos.
     * @param reviewsCatalog  ReviewsCatalog onde serão inseridos as informações de reviews lidos.
    //* @param q1Stats         Q1Stats onde serão guardados valores referentes à Query1 Estatística.
     */
    public void parserDiffFiles(List<String> path, UsersCatalog usersCatalog, BusinessesCatalog businessesCatalog, ReviewsCatalog reviewsCatalog,ReviewUserBusiness rub,Q1Stats q1Stats) {
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
                nusers++;
            }
            usersbr.close();
            q1Stats.setTotalUsers(nusers);

            businessbr.readLine(); // read the firstline
            while ((s = businessbr.readLine()) != null) {
                buffer = s.split(";");
                Business b = new Business();
                b.setCode(buffer[0]);
                b.setName(buffer[1]);
                b.setCity(buffer[2]);
                b.setState(buffer[3]);
                b.setCategory(buffer[4]);
                businessesCatalog.addBusiness(b);
                nbusiness++;
            }
            businessbr.close();
            q1Stats.setTotalBusinesses(nbusiness);

            reviewsbr.readLine(); // read the firstline
            //ReviewUserBusiness rub = new ReviewUserBusiness();
            while ((s = reviewsbr.readLine()) != null) {
                buffer = s.split(";");
                nreviewsTotal++;
                if(buffer.length>=9 && usersCatalog.checkCode(buffer[1]) && businessesCatalog.checkCode(buffer[2])){
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
                    r.setAno(Integer.parseInt(mes[0]));
                    r.setMes(Integer.parseInt(mes[1]));
                    //r.setText(buffer[8]);
                    nreviewsValidas++;
                    reviewsCatalog.addReview(r);
                    if((Integer.parseInt(buffer[4]) + Integer.parseInt(buffer[5]) + Integer.parseInt(buffer[6])) == 0)
                        reviewsSImp++;
                }
            }
            reviewsbr.close();
            q1Stats.setReviewsTotal(nreviewsTotal);
            q1Stats.setReviewsValidas(nreviewsValidas);
            q1Stats.setnReviewsErradas(nreviewsTotal-nreviewsValidas);
            q1Stats.setReviewsSemImpacto(reviewsSImp);
            System.out.println(nreviewsValidas);
        } catch (IOException e) {
            System.out.println("Ficheiro(s) inexistente(s)");
            System.exit(-1);
        }
    }
}