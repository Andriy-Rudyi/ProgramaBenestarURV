package proves;

import dades.excepcions.UsuariDuplicatException;
import dades.inscripcions.LlistaInscripcions;
import dades.usuaris.Pdi;
import dades.usuaris.Ptgas;
import dades.usuaris.Estudiant;
import dades.usuaris.Usuari;

public class ProvesLlistaInscripcions {
    public static void main(String[] args) {
        System.out.println("ProvesLlistaInscripcions");

        LlistaInscripcions inscripcions = new LlistaInscripcions(3);

        System.out.println("Inscripcions: " + inscripcions);


        Usuari[] llistaUsuaris = {
            new Pdi("Alies1", "alies1", "Departament 1", "Campus 1"),
            new Ptgas("Alies4", "alies4", "Campus 4"),
            new Estudiant("Alies7", "alies7", "Ensenyament 7", 2025)
        };

        // afegir
        for (Usuari u : llistaUsuaris) {
            try {
                inscripcions.afegir(u);
            } catch (UsuariDuplicatException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            inscripcions.afegir(new Estudiant("Alies8", "alies8", "Ensenyament 8", 2025));
            inscripcions.afegir(new Pdi("Alies2", "alies2", "Departament 2", "Campus 2"));
            inscripcions.afegir(new Ptgas("Alies5", "alies5", "Campus 5"));
        } catch (UsuariDuplicatException e) {
            System.out.println(e.getMessage());
        } // a la llista d'espera

        // copia
        LlistaInscripcions copiaInscripcions = inscripcions.copia();
        System.out.println("Còpia de les inscripcions:" + copiaInscripcions);

        // eliminar (per nom)
        if (inscripcions.eliminar("Alies1")) {
            System.out.println("S'ha eliminat l'usuari amb àlies Alies1");
        } else {
            System.out.println("No s'ha trobat l'usuari amb àlies Alies1 per eliminar");
        }

        // getLlistaEspera
        System.out.println("Llista d'espera: " + copiaInscripcions.getLlistaEspera());

        // getLlistaInscrits
        System.out.println("Llista d'inscrits: " + copiaInscripcions.getLlistaInscrits());

        // getNumInscrits
        System.out.println("Número d'inscrits: " + copiaInscripcions.getNumInscrits());

        // getNumPlaces
        System.out.println("Número de places: " + copiaInscripcions.getNumPlaces());

        // getUsuarisEnEspera
        System.out.println("Usuaris en espera: " + copiaInscripcions.getUsuarisEnEspera());
        
        // teUsuariEnEspera
        System.out.println("Hi ha usuaris en espera? " + copiaInscripcions.teUsuarisEnEspera());

        // teUsuariInscrit
        System.out.println("L'usuari amb àlies Alies4 està inscrit? " + copiaInscripcions.teUsuariInscrit("Alies4"));

    }    
}