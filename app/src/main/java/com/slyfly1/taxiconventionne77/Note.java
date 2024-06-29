package com.slyfly1.taxiconventionne77;

import com.google.firebase.firestore.Exclude;
public class Note {
    private String documentId;
    private String nom;
    private String prenom;
    private String email;
    private  String adresse;
    private String codepostal;
    private String ville;
    private String adresse2;
    private String codepostal2;
    private String ville2;

    public Note() {
        //public no-arg constructor needed
    }
    @Exclude
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public Note(String nom, String prenom,String email,String adresse,String codepostal,String ville,String adresse2,String codepostal2,String ville2) {
        this.nom = nom;
        this.prenom = prenom;
        this.email=email;
        this.adresse=adresse;
        this.codepostal=codepostal;
        this.ville=ville;
        this.adresse2=adresse2;
        this.codepostal2=codepostal2;
        this.ville2=ville2;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public String getEmail() {
        return email;
    }
    public String getAdresse() {
        return adresse;
    }
    public String getVille() {
        return ville;
    }
    public String getCodepostal() {
        return codepostal;
    }
    public String getAdresse2() {
        return adresse2;
    }
    public String getVille2() {
        return ville2;
    }
    public String getCodepostal2() {
        return codepostal2;
    }

}