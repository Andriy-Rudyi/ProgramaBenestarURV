package dades.usuaris;

import dades.excepcions.ColectiuInvalidException;

/**
 * Representa un estudiant de la URV
 * @author PROG1 - Pau Font
 */
public class Estudiant extends Usuari {
    private String ensenyament;
    private int anyInici;
    
    /**
     * Constructor d'Estudiant
     * @param alies Àlies de l'estudiant
     * @param adreca Part del correu abans de @estudiants.urv.cat
     * @param colectiu Col·lectiu (ha de ser "Estudiants")
     * @param ensenyament Ensenyament on està matriculat
     * @param anyInici Any d'inici dels estudis
     * @throws ColectiuInvalidException si el col·lectiu no és "Estudiants"
     */
    public Estudiant(String alies, String adreca, String colectiu, String ensenyament, int anyInici) 
            throws ColectiuInvalidException {
        if (!COLECTIU_ESTUDIANTS.equals(colectiu)) {
            throw new ColectiuInvalidException(
                "El col·lectiu ha de ser '" + COLECTIU_ESTUDIANTS + "' per a Estudiant. S'ha rebut: " + colectiu);
        }
        
        this.alies = alies;
        this.adreca = adreca;
        this.colectiu = colectiu;
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }
    
    /**
     * Obté l'ensenyament de l'estudiant
     * @return Nom de l'ensenyament
     */
    public String getEnsenyament() { return ensenyament; }

    /**
     * Obté l'any d'inici dels estudis
     * @return Any d'inici
     */
    public int getAnyInici() { return anyInici; }
    
    @Override
    public String toString() {
        return "ESTUDIANT --- Alies: " + alies + ", Adreça: " + getCorreuComplet() + 
               ", Ensenyament: " + ensenyament + ", Any inici: " + anyInici + "\n";
    }
    
    @Override
    public Usuari copia() {
        try {
            return new Estudiant(alies, adreca, colectiu, ensenyament, anyInici);
        } catch (ColectiuInvalidException e) {
            // No hauria de passar mai perquè estem copiant un objecte vàlid
            throw new RuntimeException("Error inesperat copiant Estudiant", e);
        }
    }
}
