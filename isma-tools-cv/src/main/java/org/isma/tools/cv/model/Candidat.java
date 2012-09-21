package org.isma.tools.cv.model;

public class Candidat {
    private final String prenom;
    private final String nom;
    private final int age;
    private final String statut;
    private final String nationalite;
    private final Contact contact;
    private final Adresse adresse;
    private final Profil profil;

    public Candidat(String prenom, String nom, int age, String statut, String nationalite, Contact contact, Adresse adresse, Profil profil) {
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.statut = statut;
        this.nationalite = nationalite;
        this.contact = contact;
        this.adresse = adresse;
        this.profil = profil;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public int getAge() {
        return age;
    }

    public String getStatut() {
        return statut;
    }

    public String getNationalite() {
        return nationalite;
    }

    public Contact getContact() {
        return contact;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Profil getProfil() {
        return profil;
    }
}
