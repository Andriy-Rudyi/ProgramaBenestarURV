package dades.usuaris;

import dades.excepcions.ColectiuInvalidException;

/**
 * Representa un membre del Personal Tècnic i de Gestió i Administració (PTGAS) de la URV
 * @author PROG3 - Andriy Rudyy
 */

public class Ptgas extends Usuari {
    private String campus;
    
    /**
     * Constructor de PTGAS
     * @param alies Àlies de l'usuari
     * @param adreca Part del correu abans de @urv.cat
     * @param colectiu Col·lectiu (ha de ser "PTGAS")
     * @param campus Campus on treballa
     * @throws ColectiuInvalidException si el col·lectiu no és "PTGAS"
     */
    public Ptgas(String alies, String adreca, String colectiu, String campus) throws ColectiuInvalidException {
        if (!COLECTIU_PTGAS.equals(colectiu)) {
            throw new ColectiuInvalidException(
                "El col·lectiu ha de ser '" + COLECTIU_PTGAS + "' per a PTGAS. S'ha rebut: " + colectiu);
        }
        
        this.alies = alies;
        this.adreca = adreca;
        this.colectiu = colectiu;
        this.campus = campus;
    }
    
    /**
     * Obté el campus del PTGAS
     * @return Nom del campus
     */
    public String getCampus() { return campus; }
    
    @Override
    public String toString() {
        return "PTGAS --- Alies: " + alies + ", Adreça: " + getCorreuComplet() + 
               ", Campus: " + campus + "\n";
    }
    
    @Override
    public Usuari copia() {
        try {
            return new Ptgas(alies, adreca, colectiu, campus);
        } catch (ColectiuInvalidException e) {
            // No hauria de passar mai perquè estem copiant un objecte vàlid
            throw new RuntimeException("Error inesperat copiant PTGAS", e);
        }
    }
}