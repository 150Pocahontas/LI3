/**
 * @file BusinessNotFoundException.java
 * Ficheiro que contém a API relativa à implementação da exceção BusinessNotFound.
 */

package Exceptions;

import java.io.Serializable;

public class BusinessNotFoundException extends Exception implements Serializable {

    /**
     * Método que retorna uma excepção quando um dado código de negócio não existe.
     */
    public BusinessNotFoundException() {
        super();
    }

    /**
     * Método que retorna uma mensagem de excepção quando um dado código de negócio não existe.
     *
     * @param m String que é a mensagem.
     */
    public BusinessNotFoundException(String m) {
        super(m);
    }
}
