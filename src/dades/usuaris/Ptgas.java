package dades.usuaris;

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
     * @param campus Campus on treballa
     */
    public Ptgas(String alies, String adreca, String campus){
        this.alies = alies;
        // if (!adrecaValida(adreca.split("@")[0])) {
        //     throw new IllegalArgumentException("Adreça de correu electrònic no vàlida per a PTGAS.");
        // } else {
        //     this.adreca = adreca.split("@")[0];
        // }
        this.adreca = adreca.split("@")[0];
        this.campus = campus;
    }
    
    /**
     * Obté el campus del PTGAS
     * @return Nom del campus
     */
    public String getCampus() { return campus; }
    
    @Override
    public String getColectiu() {
        return "PTGAS";
    }

    @Override
    public String toString() {
        return "PTGAS --- Alies: " + alies + ", Adreça: " + getCorreuComplet() + 
               ", Campus: " + campus + "\n";
    }

    @Override
    public String toCSV() {
        return (COLECTIU_PTGAS + ";" + alies + ";" + getCorreuComplet() + ";" + campus + "\n");
    }
    
    @Override
    public Usuari copia() {
        return new Ptgas(alies, adreca, campus);
    }
}