package Geral;

import java.io.Serializable;


public class User implements Comparable, Serializable {
    private String code;
    private String name;
    private String friends;

    public User() {
        this.code = "";
        this.name = "";
        this.friends = "";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFriends() {
        return friends;
    }

    public void setFriends(String friends) {
        this.friends = friends;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
