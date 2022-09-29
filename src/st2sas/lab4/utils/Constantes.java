package st2sas.lab4.utils;

/**
 * Classe qui définit toutes les constantes utilisées dans l'ensemble du
 * programme
 * @author Jacques
 */
public class Constantes {

    public static final String OCCUPATION = "Programmeur";

    /**
     * Contient l'url de la BDD
     */
    public static final String URL = "jdbc:derby://localhost:1527/APTN61_BD";

    /**
     * Contient le nom d'utilisateur de connexion à la BDD
     */
    public static final String USER = "adm";

    /**
     * Contient le mot de passe de connexion à la BD
     */
    public static final String MDP = "adm";

    /**
     * Contient la requête pour afficher tous les programmeurs
     */
    public static final String REQ_SEL_TOUS = "SELECT * from PROGRAMMEUR";

    /**
     * Contient la requête pour afficher les information d'un programmeur en
     * fonction de son ID
     */
    public static final String REQ_SEL_UN = "SELECT * from PROGRAMMEUR WHERE id = ?";

    /**
     * Contient la requête pour supprimer un utilisateur en fonction de son ID
     */
    public static final String REQ_SUPPR = "DELETE from PROGRAMMEUR WHERE id = ?";

    /**
     * Contient la requête pour ajouter un utilisateur
     */
    public static final String REQ_AJOUT = "INSERT INTO PROGRAMMEUR(NOM,PRENOM,"
            + "ADRESSE,PSEUDO,RESPONSABLE,HOBBY,"
            + "ANNAISSANCE,SALAIRE,PRIME) VALUES\n"
            + "(?,?,?,?,?,?,?,?,?)";

    /**
     * Contient la requête pour modifier le salaire d'un utilisateur
     */
    public static final String REQ_MODIF_SALAIRE = "UPDATE PROGRAMMEUR "
            + "SET salaire =?"
            + "WHERE id = ?";
}
