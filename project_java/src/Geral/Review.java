package Geral;

import java.io.Serializable;

public class Review implements Comparable, Serializable {
    private String code;
    private String user;
    private String business;
    private float stars;
    private int useful;
    private int funny;
    private int cool;
    private int ano;
    private int mes;

    public Review() {
        this.code = "";
        this.user = "";
        this.business = "";
        this.stars = 0;
        this.useful = 0;
        this.funny = 0;
        this.cool = 0;
        this.mes = 0;
        this.ano = 0;
    }

    public Review(String code, String user, String business, float stars, int useful, int funny, int cool, int ano, int mes) {
        this.code = code;
        this.user = user;
        this.business = business;
        this.stars = stars;
        this.useful = useful;
        this.funny = funny;
        this.cool = cool;
        this.ano = ano;
        this.mes = mes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public int getUseful() {
        return useful;
    }

    public void setUseful(int useful) {
        this.useful = useful;
    }

    public int getFunny() {
        return funny;
    }

    public void setFunny(int funny) {
        this.funny = funny;
    }

    public int getCool() {
        return cool;
    }

    public void setCool(int cool) {
        this.cool = cool;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
