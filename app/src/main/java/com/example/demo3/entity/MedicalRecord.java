package com.example.demo3.entity;

public class MedicalRecord {


    /**
     * mrItemID : 24
     * name : 住院病案首页
     * pdf : ftp://192.168.199.93:21/DHCEPRFS2019/08/07/113047-1002056.pdf
     */

    private String mrItemID;
    private String name;
    private String pdf;

    public String getMrItemID() {
        return mrItemID;
    }

    public void setMrItemID(String mrItemID) {
        this.mrItemID = mrItemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}
