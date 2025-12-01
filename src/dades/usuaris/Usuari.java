package dades.usuaris;

/**
 * Classe Pare Usuari
 * @author PROG4 - Tiago Amarelle Rodrigues
 */

public abstract class Usuari {
    protected String alies;
    protected String adreca;
    protected String colectiu;

    /**
     * Col·lectius vàlids de la URV
     */
    public static final String COLECTIU_PDI = "PDI";
    public static final String COLECTIU_PTGAS = "PTGAS";
    public static final String COLECTIU_ESTUDIANTS = "Estudiants";

    /**
     * Dominis de correu electrònic segons el col·lectiu
     */
    public static final String DOMINI_URV = "@urv.cat";
    public static final String DOMINI_ESTUDIANTS = "@estudiants.urv.cat";

    /**
     * Obté l'àlies de l'usuari
     * @return Àlies de l'usuari
     */
    public String getAlies(){
        return alies;
    }

    /**
     * Obté la part local de l'adreça de correu (abans del @)
     * @return Adreça local
     */
    public String getAdreca(){
        return adreca;
    }

    /**
     * Obté el col·lectiu al qual pertany l'usuari
     * @return Col·lectiu (PDI, PTGAS o Estudiants)
     */
    public String getColectiu(){
        return colectiu;
    }

    /**
     * Obté l'adreça de correu completa segons el tipus d'usuari
     * PDI i PTGAS: adreca@urv.cat
     * Estudiants: adreca@estudiants.urv.cat
     * 
     * @return Adreça de correu electrònic completa
     */
    public String getCorreuComplet() {
        if (this instanceof Estudiant) {
            return adreca + DOMINI_ESTUDIANTS;
        } else {
            return adreca + DOMINI_URV;
        }
    }
    
    /**
     * Valida que el col·lectiu sigui un dels valors permesos
     * @param colectiu Col·lectiu a validar
     * @return true si el col·lectiu és vàlid, false en cas contrari
     */
    public static boolean esColectiuValid(String colectiu) {
        return colectiu != null && 
               (colectiu.equals(COLECTIU_PDI) || 
                colectiu.equals(COLECTIU_PTGAS) || 
                colectiu.equals(COLECTIU_ESTUDIANTS));
    }

    /**
     * Representació en format text de l'usuari
     * @return Cadena amb la informació de l'usuari
     */
    public abstract String toString();

    /**
     * Compara si dos usuaris són iguals basant-se en el seu àlies
     * Dos usuaris es consideren iguals si tenen el mateix àlies,
     * ja que l'àlies és únic per a cada usuari
     * 
     * @param obj Objecte a comparar
     * @return true si els usuaris tenen el mateix àlies, false en cas contrari
     */
    @Override
    public boolean equals(Object obj) {
        // refence check
        if (this == obj) {
            return true;
        }

        // null and type check
        if (obj == null || !(obj instanceof Usuari)) {
            return false;
        }
        
        Usuari altre = (Usuari) obj;
        return this.alies != null && this.alies.equals(altre.alies);
    }

    /**
     * Crea una còpia de l'usuari
     * @return Nova instància amb les mateixes dades
     */
    public abstract Usuari copia();
}
