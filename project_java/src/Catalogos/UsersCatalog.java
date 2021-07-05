/**
 * @file UsersCatalog.java
 * Ficheiro que contém a API relativa à implementação do catálogo de users.
 */

package Catalogos;

import Geral.User;

import java.io.Serializable;
import java.util.*;

public class UsersCatalog implements Catalogs, Serializable {
    private Map<String, User> users;

    public UsersCatalog() {
        this.users = new HashMap<>();
    }

    /**
     * @param u user
     * Método que dado um User o adiciona ao catálogo de users.
     */
    public void addUser(User u) {
        String code = u.getCode();
        this.users.put(code,u);
    }

    /**
     * Método que verifica se um dado user existe no catálogo de users.
     * @param code String com o código do users.
     * @return boolean com o resultado.
     */
    public boolean checkCode(String code) {
        return users.containsKey(code);
    }

}

