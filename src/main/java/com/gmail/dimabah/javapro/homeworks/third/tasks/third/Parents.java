package com.gmail.dimabah.javapro.homeworks.third.tasks.third;

public class Parents {
    private String motherName;
    @Save private String fatherName;

    public Parents(String motherName, String fatherName) {
        this.motherName = motherName;
        this.fatherName = fatherName;
    }

    public Parents() {
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                '}';
    }
}
