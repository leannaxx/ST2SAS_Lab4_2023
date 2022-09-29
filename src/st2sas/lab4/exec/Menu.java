package st2sas.lab4.exec;


import st2sas.lab4.data.ProgrammeurBean;

import static st2sas.lab4.utils.Constantes.REQ_SEL_UN;

/**
 *
 * @author Jacques
 */
public class Menu extends AbsMenu {

    private ActionsBDDImpl dt;
    private int idSaisi; // id prog saisi -> choix 2,3 et 5 du menu
//    private int choixSaisi; // Choix saisi par l'utilisateur
    private boolean testId; // "true" si l'utilisateur existe dans la BDD
    
    /**
     * Constructeur -> init de dt qui sera par la suite utilisée partout dans le code 
     */
    public Menu(){
        dt = new ActionsBDDImpl();
    }

    @Override
    public void gererMenu() {

        while (this.initMenu() < 6) {
            //choixSaisi = this.initMenu(); //On récupère la valeur saisie par l'utilisateur
            switch (choixSaisi) {
                case 1:
                    dt.afficherProgrammeurs();
                    //dt.deconnect(co);
                    break;
                case 2:
                    if (this.verifierSiIdExiste()) {
                        dt.afficherUnSeulProgrammeur(idSaisi);
                    }
                    break;
                case 3:
                    if (this.verifierSiIdExiste()) {
                        dt.supprimerProgrammeur(idSaisi);
                    }
                    break;
                case 4:
                    this.ajouterProgrammeur();
                    break;
                case 5:
                    if (this.verifierSiIdExiste()) {
                        System.out.println("Nouveau salaire de ce programmeur :");
                        salaire = new Float(input.nextLine());
                        dt.modifierSalaireProgrammeur(salaire, idSaisi);
                    }
                    break;
                case 6:
                    dt.libererRessources();
                    System.exit(0);
                    break;
                default:
                    //L'utilisateur a fait un mauvais choix, retour à l'affichage + choix de la réponse ?
                    break;
            }
        }
    }

    public boolean verifierSiIdExiste() {
        System.out.print("Id du programmeur :");
        idSaisi = Integer.valueOf(input.nextLine());

        //testId = vrai si l'ID passé en paramètre de la méthode trouverID à été trouvé dans la base de donné, faux sinon.
        testId = dt.verifierId(idSaisi, REQ_SEL_UN);

        //Tant que l'ID n'est pas trouvé dans la base de donnée le programme redemande à l'utilisateur de saisir un nouvel ID avec le message approprié.
        while (!testId) {
            switch (choixSaisi) {
                case 2:
                    System.out.print("Recherche KO. Saisissez à nouveau l'id : ");
                    idSaisi = Integer.valueOf(input.nextLine());
                    testId = dt.verifierId(idSaisi, REQ_SEL_UN);
                    break;
                case 3:
                    System.out.print("Suppresion KO. Saisissez à nouveau l'id : ");
                    idSaisi = Integer.valueOf(input.nextLine());
                    testId = dt.verifierId(idSaisi, REQ_SEL_UN);
                    break;
                case 5:
                    System.out.print("Programmeur introuvable. Saisissez à nouveau l'id : ");
                    idSaisi = Integer.valueOf(input.nextLine());
                    testId = dt.verifierId(idSaisi, REQ_SEL_UN);
                    break;
                default:
                    break;
            }
        }
        return testId;
    }

    public void ajouterProgrammeur() {
        System.out.print("Nom du programmeur : ");
        nom = input.nextLine();
        System.out.print("Prénom du programmeur : ");
        prenom = input.nextLine();
        System.out.print("Adresse du programmeur : ");
        adresse = input.nextLine();
        System.out.print("Pseudo du programmeur : ");
        pseudo = input.nextLine();
        System.out.print("Responsable du programmeur : ");
        responsable = input.nextLine();
        System.out.print("Hobby du programmeur : ");
        hobby = input.nextLine();
        System.out.print("Année de naissance du programmeur : ");
        annaissance = Integer.valueOf(input.nextLine());
        System.out.print("Salaire du programmeur : ");
        salaire = Float.valueOf(input.nextLine());
        System.out.print("Prime du programmeur : ");
        prime = Float.valueOf(input.nextLine());
        ProgrammeurBean nouveauProgr = new ProgrammeurBean(nom, prenom, adresse,
                pseudo, responsable, hobby,
                annaissance, salaire, prime);
        if (dt.ajouterProgrammeur(nouveauProgr) != 0) {
            System.out.print("AJOUT REUSSI ! \n \n");
        }
    }
}
