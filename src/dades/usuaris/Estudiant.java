package dades.usuaris;

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
     * @param ensenyament Ensenyament on està matriculat
     * @param anyInici Any d'inici dels estudis
     */
    public Estudiant(String alies, String adreca, String ensenyament, int anyInici){
        this.alies = alies;
        this.adreca = adreca.split("@")[0];
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
    public String getColectiu() {
        return "Estudiants";
    }
    
    @Override
    public String toString() {
        return "ESTUDIANT --- Alies: " + alies + ", Adreça: " + getCorreuComplet() + 
               ", Ensenyament: " + ensenyament + ", Any inici: " + anyInici + "\n";
    }

    @Override
    public String toCSV() {
        return (COLECTIU_ESTUDIANTS + ";" + alies + ";" + getCorreuComplet() + ";" + ensenyament + ";" + anyInici + "\n");
    }
    
    @Override
    public Usuari copia() {
        return new Estudiant(alies, adreca, ensenyament, anyInici);
    }
}
