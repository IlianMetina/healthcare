package com.myapp.assessment.enums;

public enum TriggerTerms {

    HEMOGLOBINE_A1C("Hémoglobine A1C"),
    MICROALBUMINE("Microalbumine"),
    TAILLE("Taille"),
    POIDS("Poids"),
    FUMEUR("Fumeur"),
    FUMEUSE("Fumeuse"),
    ANORMAL("Anormal"),
    CHOLESTEROL("Cholestérol"),
    VERTIGE("Vertige"),
    RECHUTE("Rechute"),
    REACTION("Réaction"),
    ANTICORPS("Anticorps");

    private final String terms;

    TriggerTerms(String terms) {
        this.terms = terms;
    }

    public String getTerms(){
        return terms;
    }
}
