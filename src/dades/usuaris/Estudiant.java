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
    public Estudiant(String alies, String adreca, String ensenyament, int anyInici) {
        this.alies = alies;
        this.adreca = adreca;
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }
    
    public String getEnsenyament() { return ensenyament; }
    public int getAnyInici() { return anyInici; }
    
    /**
     * Retorna el tipus de col·lectiu (2 = Estudiant)
     */
    public int getTipusCollectiu() {
        return 2;
    }
    
    @Override
    public String toString() {
        return "ESTUDIANT --- Alies: " + alies + ", Adreça: " + adreca + 
               "@estudiants.urv.cat, Ensenyament: " + ensenyament + 
               ", Any inici: " + anyInici + "\n";
    }
    
    @Override
    public Usuari copia() {
        return new Estudiant(alies, adreca, ensenyament, anyInici);
    }
}
