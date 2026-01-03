package proves;
import dades.usuaris.*;
public class ProvesUsuari {
    public static void main(String[] args) {
        Usuari[] llista = {
        new Pdi("Alies1", "alies1", "Departament 1", "Campus 1"),
        new Pdi("Alies2", "alies2", "Departament 2", "Campus 2"),
        new Pdi("Alies3", "alies3", "Departament 3", "Campus 3"),

        new Ptgas("Alies4", "alies4", "Campus 4"),
        new Ptgas("Alies5", "alies5", "Campus 5"),
        new Ptgas("Alies6", "alies6", "Campus 6"),

        new Estudiant("Alies7", "alies7", "Ensenyament 7", 2017),
        new Estudiant("Alies8", "alies8", "Ensenyament 8", 2018),
        new Estudiant("Alies9", "alies9", "Ensenyament 9", 2019),
        };
        
        for (int i = 0; i < 9; i++){
            System.out.println(llista[i]);
        }
        
        // Proves generals/abstractes (Usuari)
        Usuari duplicat = llista[2].copia();
        System.out.println("Usuari duplicat (copia): " + duplicat);
        System.out.println("És igual l'usuari original i el duplicat? " + llista[2].equals(duplicat));
        System.out.println(llista[3].getAdreca() + " és l'adreça de " + llista[3].getAlies());
        System.out.println(llista[6].getCorreuComplet() + " és el correu complet de " + llista[6].getAlies());
        System.out.println("El col·lectiu de " + llista[0].getAlies() + " és " + llista[0].getColectiu());
        System.out.println("Usuari en format CSV:" + llista[5].toCSV());
        System.out.println("Usuari en format String (NullPointerException inclos):" + llista[2]);

        // Proves específiques
        // PDI
        Pdi pdi = (Pdi) llista[1];
        System.out.println("El departament de " + pdi.getAlies() + " és " + pdi.getDepartament());
        System.out.println("El campus de " + pdi.getAlies() + " és " + pdi.getCampus());

        // PTGAS
        Ptgas ptgas = (Ptgas) llista[4];
        System.out.println("El campus de " + ptgas.getAlies() + " és " + ptgas.getCampus());
        
        // Estudiant
        Estudiant estudiant = (Estudiant) llista[7];
        System.out.println("L'ensenyament de " + estudiant.getAlies() + " és " + estudiant.getEnsenyament());
        System.out.println("L'any d'ingrés de " + estudiant.getAlies() + " és " + estudiant.getAnyInici());
    }

}
