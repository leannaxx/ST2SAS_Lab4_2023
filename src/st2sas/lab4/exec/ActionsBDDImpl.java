package st2sas.lab4.exec;

import st2sas.lab4.data.ProgrammeurBean;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static st2sas.lab4.utils.Constantes.*;

/**
 * Classe permettant d'exécuter tous les services de transaction avec la BD
 *
 * @author Jacques
 */
public class ActionsBDDImpl implements ActionsBDD {

    //------------ VARIABLES GLOBALES / ENCAPSULATION TOTALE------- //
    private Connection dbConn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ArrayList<ProgrammeurBean> listeProgrammeurs;
    private ProgrammeurBean prog;
    private int resultatReq;
    private String dbUrl;
    private String dbUser;
    private String dbMdp;
    private boolean testId;

    //Bloc anonyme 
    {
        Properties prop = new Properties();
        InputStream input;

        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        input = cl.getResourceAsStream("st2sas/lab4/utils/db.properties");

        try {
            prop.load(input);
        } catch (IOException ex) {
            // Instruction équivalente à "ex.printStackTrace();" pour générer un log d'erreur 
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        dbUrl = prop.getProperty("dbUrl");
        dbUser = prop.getProperty("dbUser");
        dbMdp = prop.getProperty("dbMdp");
    }

    public ActionsBDDImpl() {
        if (dbConn == null) {  // Pour éviter de créer inutilement n connexions
            try {
                dbConn = DriverManager.getConnection(dbUrl, dbUser, dbMdp);
            } catch (SQLException sqle) {
                Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }

    }

    public PreparedStatement getPreparedStatement(String req) {
        if (pstmt == null) {
            try {
                pstmt = dbConn.prepareStatement(req);
            } catch (SQLException ex) {
                Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return pstmt;
    }

    public Statement getStatement() {
        if (stmt == null) {
            try {
                stmt = dbConn.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return stmt;
    }

    @Override
    public ResultSet getResultSet(Statement stmt, String req) {
        try {
            rs = stmt.executeQuery(req);
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, sqle);
        }
        return rs;
    }

    @Override
    public ArrayList getProgrammeurs() {
        listeProgrammeurs = new ArrayList<>();

        try {
            stmt = this.getStatement();
            rs = stmt.executeQuery(REQ_SEL_TOUS);

            while (rs.next()) {
                prog = new ProgrammeurBean();
                prog.setId(rs.getInt("ID"));
                prog.setPrenom(rs.getString("PRENOM"));
                prog.setNom(rs.getString("NOM"));
                prog.setAdresse(rs.getString("ADRESSE"));
                prog.setPseudo(rs.getString("PSEUDO"));
                prog.setResponsable(rs.getString("RESPONSABLE"));
                prog.setHobby(rs.getString("HOBBY"));
                prog.setAnNaissance(rs.getInt("ANNAISSANCE"));
                prog.setSalaire(rs.getFloat("SALAIRE"));
                prog.setPrime(rs.getFloat("PRIME"));

                listeProgrammeurs.add(prog);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return listeProgrammeurs;
    }

    @Override
    public ProgrammeurBean getProgrammeur(int id) {
        prog = null;
        try {

            pstmt = dbConn.prepareStatement(REQ_SEL_UN);
            pstmt.setString(1, Integer.toString(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                prog = new ProgrammeurBean();
                prog.setId(rs.getInt("ID"));
                prog.setPrenom(rs.getString("PRENOM"));
                prog.setNom(rs.getString("NOM"));
                prog.setAdresse(rs.getString("ADRESSE"));
                prog.setPseudo(rs.getString("PSEUDO"));
                prog.setResponsable(rs.getString("RESPONSABLE"));
                prog.setHobby(rs.getString("HOBBY"));
                prog.setAnNaissance(rs.getInt("ANNAISSANCE"));
                prog.setSalaire(rs.getFloat("SALAIRE"));
                prog.setPrime(rs.getFloat("PRIME"));
            }
        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return prog;
    }

    /**
     * Vérifie l'existence de l'ID saisi dans la BD
     *
     * @param id
     * @param req
     * @return boolean
     */
    public boolean verifierId(int id, String req) {
        try {
            pstmt = dbConn.prepareStatement(req);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            testId = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return testId;
    }

    @Override
    public int supprimerProgrammeur(int id) {
        try {
            pstmt = dbConn.prepareStatement(REQ_SUPPR);
            pstmt.setString(1, Integer.toString(id));
            resultatReq = pstmt.executeUpdate();
            
            if (resultatReq > 0){
                System.out.println("SUPPRESSION REUSSIE!");
            }

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return resultatReq;
    }

    @Override
    public int modifierSalaireProgrammeur(Float salaire, int id) {
        try {
            pstmt = dbConn.prepareStatement(REQ_MODIF_SALAIRE);
            pstmt.setFloat(1, salaire);
            pstmt.setString(2, Integer.toString(id));

            resultatReq = pstmt.executeUpdate();
            
            if (resultatReq > 0){
                System.out.println("MODIFICATION REUSSIE!");
            }

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return resultatReq;
    }

    @Override
    public int ajouterProgrammeur(ProgrammeurBean prog) {
        int resultatAjout = 0;

        try {
            pstmt = dbConn.prepareStatement(REQ_AJOUT);
            pstmt.setString(1, prog.getNom());
            pstmt.setString(2, prog.getPrenom());
            pstmt.setString(3, prog.getAdresse());
            pstmt.setString(4, prog.getPseudo());
            pstmt.setString(5, prog.getResponsable());
            pstmt.setString(6, prog.getHobby());
            pstmt.setString(7, Integer.toString(prog.getAnNaissance()));
            pstmt.setString(8, Float.toString(prog.getSalaire()));
            pstmt.setString(9, Float.toString(prog.getPrime()));

            resultatAjout = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, sqle);
        }
        return resultatAjout;

    }

    @Override
    public void afficherProgrammeurs() {
        listeProgrammeurs = this.getProgrammeurs();
        for (ProgrammeurBean prog : listeProgrammeurs) {
            System.out.println(prog);
        }
    }

    @Override
    public void afficherUnSeulProgrammeur(int idProg) {
        prog = this.getProgrammeur(idProg);
        System.out.println(prog);
    }

    @Override
    public void libererRessources() {
        try {
            if (dbConn != null) {
                dbConn.close();
                dbConn = null; // OK pour destruction par le garbage collector 

                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

                if (pstmt != null) {
                    pstmt.close();
                    pstmt = null;
                }

                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActionsBDDImpl.class.getName()).log(Level.FINE, null, ex);
        }

    }
}
