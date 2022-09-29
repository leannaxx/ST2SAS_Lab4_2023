/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package st2sas.lab4.data;

/**
 *
 * @author Jacques
 */
public class ProgrammeurBean {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String pseudo;
    private String responsable;
    private String hobby;
    private int anNaissance;
    private float salaire;
    private float prime;

    /**
     * Constructeur permettant d'initialiser tous les attributs de Programmeur
     * @param nom
     * @param prenom
     * @param adresse
     * @param pseudo
     * @param responsable
     * @param hobby
     * @param anNaissance
     * @param salaire
     * @param prime 
     */
    public ProgrammeurBean(String nom, String prenom, String adresse, String pseudo, String responsable, String hobby, int anNaissance, float salaire, float prime) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.pseudo = pseudo;
        this.responsable = responsable;
        this.hobby = hobby;
        this.anNaissance = anNaissance;
        this.salaire = salaire;
        this.prime = prime;
    }

    public ProgrammeurBean() {
    }

    @Override
    public String toString() {
        return           "Id          : " + this.getId()+ "\n"
                         + "Nom         : " + this.getNom() + "\n"
                        +  "Prénom      : " + this.getPrenom()+ "\n"
                        +  "Adresse     : " + this.getAdresse()+ "\n"
                        +  "Pseudo      : " + this.getPseudo() + "\n"
                        +  "Responsable : " + this.getResponsable()+ "\n"
                        +  "Hobby       : " + this.getHobby()+ "\n"
                        +  "Naissance   : " + this.getAnNaissance() + "\n"
                        +  "Salaire     : " + this.getSalaire() + "\n"
                        +  "Prime       : " + this.getPrime() + "\n"
                        + "------------------------------------ \n";
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

    public int getAnNaissance() {
        return anNaissance;
    }

    public void setAnNaissance(int anNaissance) {
        this.anNaissance = anNaissance;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    public float getPrime() {
        return prime;
    }

    public void setPrime(float prime) {
        this.prime = prime;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

}
