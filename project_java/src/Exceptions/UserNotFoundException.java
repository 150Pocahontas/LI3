/**
 * @file UserNotFoundException.java
 * Ficheiro que contém a API relativa à implementação da exceção UserNotFound.
 */

package Exceptions;

import java.io.Serializable;

public class UserNotFoundException extends Exception implements Serializable {

    /**
     * Método que retorna uma excepção quando um dado código de utilizador não existe.
     */
    public UserNotFoundException() {
        super();
    }

    /**
     * Método que retorna uma mensagem de excepção quando um dado código de utilizador não existe.
     * @param m String que é a mensagem.
     */
    public UserNotFoundException(String m) {
        super(m);
    }
}
