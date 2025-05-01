package com.example.projet.Quiz;

public class Question {
    private final String question;
    private final String reponseA;
    private final String reponseB;
    private final String reponseC;
    private final String reponseD;
    private final String bonneReponse;

    public Question(String question, String a, String b, String c, String d, String bonneReponse) {
        this.question = question;
        this.reponseA = a;
        this.reponseB = b;
        this.reponseC = c;
        this.reponseD = d;
        this.bonneReponse = bonneReponse;
    }

    public String getQuestion() { return question; }
    public String getReponseA() { return reponseA; }
    public String getReponseB() { return reponseB; }
    public String getReponseC() { return reponseC; }
    public String getReponseD() { return reponseD; }
    public String getBonneReponse() { return bonneReponse; }
}
