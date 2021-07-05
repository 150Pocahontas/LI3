package Geral;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public interface Variables extends Serializable {

    int MONTH = 12;
    int YEAR = 17;

    List<String> MONTHS = Arrays.asList("JANEIRO", "FEVEREIRO","MARÇO","ABRIL","MAIO","JUNHO","JULHO","AGOSTO","SETEMBRO","OUTUBRO","NOVEMBRO","DEZEMBRO");
    List<String> YEARS = Arrays.asList("2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021");

    String usersFileDefault = "Resources/users_full.csv";
    String businessesFileDefault = "Resources/business_full.csv";
    String reviewsFileDefault = "Resources/reviews_1M.csv";
}
