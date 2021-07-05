package Geral;

import java.io.Serializable;

public class Business implements Comparable, Serializable {
    private String code;
    private String name;
    private String city;
    private String state;
    private String category;

    public Business() {
        this.code = "";
        this.name = "";
        this.city = "";
        this.state = "";
        this.category = "";
    }

    public Business(String code) {
        this.code = code;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
