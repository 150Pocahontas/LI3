package Interfaces;

import Exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public interface GereReviewsModel extends Serializable {
    void loadDefault() throws FileNotFoundException;
    void loadFiles(List<String> paths);
    Querie interpreter(int n, Collection<Object> l) throws UserNotFoundException, BusinessNotFoundException, ReviewNotFoundException, IOException, ClassNotFoundException;
}