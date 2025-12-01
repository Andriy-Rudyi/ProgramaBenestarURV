package dades.usuaris;

import dades.excepcions.ColectiuInvalidException;

/**
 * Representa un membre del Personal Docent i Investigador (PDI) de la URV
 * @author PROG4 - Tiago Amarelle Rodrigues
 */
public class Pdi extends Usuari{
    private String  departament;
    private String campus;

    /**
     * Constructor de PDI
     * @param alies Àlies de l'usuari
     * @param adreca Part del correu abans de @urv.cat
     * @param colectiu Col·lectiu (ha de ser "PDI")
     * @param departament Departament on treballa
     * @param campus Campus on treballa
     * @throws ColectiuInvalidException si el col·lectiu no és "PDI"
     */
    public Pdi(String alies, String adreca, String colectiu, String departament, String campus) 
            throws ColectiuInvalidException {
        if (!COLECTIU_PDI.equals(colectiu)) {
            throw new ColectiuInvalidException(
                "El col·lectiu ha de ser '" + COLECTIU_PDI + "' per a PDI. S'ha rebut: " + colectiu);
        }
        
        this.alies = alies;
        this.adreca = adreca;
        this.colectiu = colectiu;
        this.departament = departament;
        this.campus = campus;
    }

    /**
     * Obté el departament del PDI
     * @return Nom del departament
     */
    public String getDepartament(){
        return departament;
    }

    /**
     * Obté el campus del PDI
     * @return Nom del campus
     */
    public String getCampus(){
        return campus;
    }

    @Override
    public String toString() {
        return "PDI --- Alies: " + alies + ", Adreça: " + getCorreuComplet() + 
               ", Departament: " + departament + ", Campus: " + campus + "\n";
    }

    @Override
    public Usuari copia() {
        try {
            return new Pdi(alies, adreca, colectiu, departament, campus);
        } catch (ColectiuInvalidException e) {
            // no hauria de passar mai perquè estem copiant un objecte vàlid
            throw new RuntimeException("Error inesperat copiant PDI", e);
        }
    }


    @Override
    public boolean equals(Object obj) {
        Pdi altre = (Pdi)obj;
        if (this.alies.equals(altre.alies)) return true;    // no hi haurà dues persones amb el mateix alies
        else return false;

    }
}
