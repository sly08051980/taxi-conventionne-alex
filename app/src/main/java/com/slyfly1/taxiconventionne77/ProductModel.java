package com.slyfly1.taxiconventionne77;

public class ProductModel {

    private String item_Id;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String codepostal;
    private  String ville;
    private  String adresse2;
    private  String codepostal2;
    private  String ville2;
    private  String personne;
private String chauffeurs;

    private ProductModel(){}
    private ProductModel(String nom, String prenom,String telephone,String adresse,String codepostal,String ville,String adresse2,String codepostal2, String ville2,String personne,String chauffeurs,String item_Id){
        this.item_Id= item_Id;
        this.nom=nom;
        this.prenom=prenom;
        this.telephone=telephone;
        this.adresse=adresse;
        this.codepostal=codepostal;
        this.ville=ville;
        this.adresse2=adresse2;
        this.codepostal2=codepostal2;
        this.ville2=ville2;
        this.personne=personne;
        this.chauffeurs=chauffeurs;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(String adresse2) {
        this.adresse2 = adresse2;
    }

    public String getCodepostal2() {
        return codepostal2;
    }

    public void setCodepostal2(String codepostal2) {
        this.codepostal2 = codepostal2;
    }

    public String getVille2() {
        return ville2;
    }

    public void setVille2(String ville2) {
        this.ville2 = ville2;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPersonne() {
        return personne;
    }

    public void setPersonne(String personne) {
        this.personne = personne;
    }

    public String getChauffeurs() {
        return chauffeurs;
    }

    public void setChauffeurs(String chauffeurs) {
        this.chauffeurs = chauffeurs;
    }

    public String getItem_Id() {
        return item_Id;
    }

    public void setItem_Id(String item_Id) {
        this.item_Id = item_Id;
    }
}
