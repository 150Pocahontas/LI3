/**
 * @file GestReviews.java
 *  Ficheiro que contém a API relativa à implementação dos testes da aplicação.
 */

import Catalogos.*;
import Exceptions.*;
import Geral.*;
import Queries.*;
import Readers.Parser;
import ReviewsStuff.ReviewUserBusiness;

import javax.swing.*;
import java.util.List;


public class GestReviewsTests implements Variables {


    public static void main(String[] args) throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException{
        Crono.start();
        int i;
        UsersCatalog users = new UsersCatalog();
        BusinessesCatalog business = new BusinessesCatalog();
        ReviewsCatalog reviews = new ReviewsCatalog();
        ReviewUserBusiness rub = new ReviewUserBusiness();

        Q1Stats q1stats = new Q1Stats();

        Parser p = new Parser();
        p.parser(users,business,reviews,rub,q1stats);
        System.out.println(Crono.getTImeString());

        Crono.start();
        //querie1Stats(q1stats);
        System.out.println("Q1Stats " + Crono.getTImeString());

        Crono.start();
        //Querie1 q1int = querie1();
        System.out.println("Q1 " + Crono.getTImeString());

    }


}