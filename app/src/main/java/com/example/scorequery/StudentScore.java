package com.example.scorequery;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class StudentScore {
    private String number;
    private String name;
    private String chinese;
    private String math;
    private String english;
    private String physics;
    private String politics;
    private String history;
    private String biology;
    private String geography;
    private String sum;
    private String rank;
    private String gradeRank;


    public StudentScore(
            //String number,String name,String chinese,String math,String english,String physics,String politics,String history,String biology,String geography,String sum,String rank,String gradeRank
            ){
        super();
        this.number="";
        this.name="";
        this.chinese="";
        this.math="";
        this.english="";
        this.physics="";
        this.politics="";
        this.history="";
        this.biology="";
        this.geography="";
        this.sum="";
        this.rank="";
        this.gradeRank="";
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPhysics() {
        return physics;
    }

    public void setPhysics(String physics) {
        this.physics = physics;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getBiology() {
        return biology;
    }

    public void setBiology(String biology) {
        this.biology = biology;
    }

    public String getGeography() {
        return geography;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGradeRank() {
        return gradeRank;
    }

    public void setGradeRank(String gradeRank) {
        this.gradeRank = gradeRank;
    }

    public static void write2Excel(){}

    HSSFWorkbook excel=new HSSFWorkbook();

}
