package dades.usuaris;

/**
 * Classe filla PDI de Pare Usuari
 * @author PROG4 - Tiago Amarelle Rodrigues
 */
public class Pdi extends Usuari{
    private String  departament;
    private String campus;

    public Pdi(String alies, String adreca, String departament, String campus){
        this.alies = alies;
        this.adreca = adreca;
        this.departament = departament;
        this.campus = campus;
    }

    public String getDepartament(){
        return departament;
    }

    public String getCampus(){
        return campus;
    }

    @Override
    public String toString() {
        return "PDI --- Alies: " + alies + ", Adreça: " + adreca + 
        "@urv.cat, Departament " + departament + ", Campus: " + campus + "\n";
    }

    @Override
    public Usuari copia() {
        Pdi duplicat = new Pdi(alies, adreca, departament, campus);
        return duplicat;
    }

    @Override
    public boolean equals(Object obj) {
        Pdi altre = (Pdi)obj;
        if (this.alies.equals(altre.alies)) return true;    // no hi haurà dues persones amb el mateix alies
        else return false;

    }
}
