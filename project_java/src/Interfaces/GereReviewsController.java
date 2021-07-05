package Interfaces;


import Exceptions.BusinessNotFoundException;
import Exceptions.ReviewNotFoundException;
import Exceptions.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface GereReviewsController {
    void setModel(GereReviewsModel m);

    void setView(GereReviewsView v);

    void start() throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException;

    List<String> loadFiles();

}