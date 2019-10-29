package com.example.mddapp;

public class Diagnosis {

    private int id;
    private int diagnosis_id;
    private String profname;

    public Diagnosis(int id, int diagnosis_id, String profname) {
        this.id = id;
        this.diagnosis_id = diagnosis_id;
        this.profname = profname;
    }

    public int getId() {
        return id;
    }

    public int getDiagnosis_id() {
        return diagnosis_id;
    }

    public String getProfname() {
        return profname;
    }
}
