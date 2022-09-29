package st2sas.lab4.exec;


import st2sas.lab4.data.ProgrammeurBean;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jacques
 * Interface contenant l'ensemble des services 
 * nécessaires pour interagir avec la base de données (BDD)
 * 
 */
public interface ActionsBDD {

    /**
     * Méthode qui, à partir d'un objet Statement et d'une requête 
     * permet de construire le resultset qui contient les données
     * retournées par la base
     * @param stmt
     * @param req
     * @return 
     */
    ResultSet getResultSet(Statement stmt, String req);

    /**
     * Méthode qui retourne tous les programmeurs présents en base
     * @return 
     */
    ArrayList getProgrammeurs();

    /**
     * Méthode qui permet de récupérer un seul progammeur
     * à partir de son id
     * @param id
     * @return 
     */
    ProgrammeurBean getProgrammeur(int id);

    /**
     * Cette méthode affiche les programmeurs une fois qu'ils ont été 
     * retournés par la base et transférés du resultset à une liste 
     */
    public void afficherProgrammeurs();

    /**
     * Cette méthode affiche un seul programmeur à partir de son id
     * @param id 
     */
    public void afficherUnSeulProgrammeur(int id);

    public int supprimerProgrammeur(int id);

    public int modifierSalaireProgrammeur(Float salaire, int id);

    public int ajouterProgrammeur(ProgrammeurBean prog);

    /**
     * Cette méthode ferme les 4 principaux objets 
     * utilisés pour interagir avec la BDD
     * Puis indique au garbage collector que ces objets 
     * sont éligibles pour être détruits
     */
    public void libererRessources();
}
