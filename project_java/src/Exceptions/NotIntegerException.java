/**
 * @file NotIntegerException.java
 * Ficheiro que contém a API relativa à implementação da exceção NotInteger.
 */

package Exceptions;

import java.io.Serializable;

public class NotIntegerException extends Exception implements Serializable {

    /**
     * Método que retorna uma excepção quando um dado inteiro não é válido.
     */
    public NotIntegerException(){
        super();
    }

    /**
     * Método que retorna uma mensagem de excepção quando um dado inteiro não é válido.
     * @param m String que é a mensagem.
     */
    public NotIntegerException(String m){
        super(m);
    }
}