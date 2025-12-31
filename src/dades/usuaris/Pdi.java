package dades.usuaris;

/**
 * Representa un membre del Personal Docent i Investigador (PDI) de la URV
 * @author PROG4 - Tiago Amarelle Rodrigues
 */
public class Pdi extends Usuari{
    private String departament;
    private String campus;

    /**
     * Constructor de PDI
     * @param alies Àlies de l'usuari
     * @param adreca Part del correu abans de @urv.cat
     * @param departament Departament on treballa
     * @param campus Campus on treballa
     * @throws ColectiuInvalidException si el col·lectiu no és "PDI"
     */
    public Pdi(String alies, String adreca, String departament, String campus){
        this.alies = alies;
        this.adreca = adreca.split("@")[0];
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
    public String toCSV() {
        return (PDI + ";" + alies + ";" + getCorreuComplet() + ";" + departament + ";" + campus + "\n");
    }

    @Override
    public String getColectiu() {
        return "PDI";
    }

    @Override
    public Usuari copia() {
        return new Pdi(alies, adreca, departament, campus);
    }
}
