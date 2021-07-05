/**
 * @file ReviewNotFoundException.java
 * Ficheiro que contém a API relativa à implementação da exceção ReviewNotFound.
 */

package Exceptions;

import java.io.Serializable;

public class ReviewNotFoundException extends Exception implements Serializable {

    /**
     * Método que retorna uma excepção quando um dado código de review não existe.
     */
    public ReviewNotFoundException() {
        super();
    }

    /**
     * Método que retorna uma mensagem de excepção quando um dado código de review não existe.
     * @param m String que é a mensagem.
     */
    public ReviewNotFoundException(String m) {
        super(m);
    }
}
