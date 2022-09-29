package st2sas.lab4.exec;

import java.util.Scanner;

import static java.lang.Integer.*;

/**
 * Classe abstraite -> définit les caractéristiques communes du (ou des)
 * menus(s)
 *
 * @author JAA
 */
public abstract class AbsMenu {

    // Sélection d'attributs mutualisables
    // On n'est pas obligé de tous les déclarer ici 
    // Certains attributs seront déclarés dans la (les) classe(s) fille(s)
    protected Scanner input = new Scanner(System.in);
    protected String choix = "";
    protected int id = 0;
    protected String nom = "";
    protected String prenom = "";
    protected String adresse = "";
    protected String pseudo = "";
    protected String responsable = "";
    protected String hobby = "";
    protected int annaissance = 0;
    protected float salaire = 0;
    protected float prime = 0;
    protected int choixSaisi; // Choix saisi par l'utilisateur

    public abstract void gererMenu(); // méthode abstraite => déclaration uniquement

    /**
     * Les classes filles hériteront de cette méthode "déjà" implémentée
     * @return choixSaisi
     */
        protected int initMenu() { //permet d'afficher le menu et de retourner la valeur choisie par l'utilisateur
        System.out.println("§§§§§§§§§§ MENU §§§§§§§§§§");
        System.out.println("1. Afficher tous les programmeurs\n");
        System.out.println("2. Afficher un programmeur\n");
        System.out.println("3. Supprimer un programmeur\n");
        System.out.println("4. Ajouter un programmeur\n");
        System.out.println("5. Modifier le salaire\n");
        System.out.println("6. Quitter le programme\n");
        System.out.print("Quel est votre choix ? : ");

        choixSaisi = parseInt(input.nextLine());

        while (choixSaisi > 6) {
            System.out.println("ERREUR! Veuillez saisir un nombre entre 1 et 6.");
            initMenu();
        }

        return choixSaisi;
    }
}
