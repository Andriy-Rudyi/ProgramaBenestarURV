package proves;

import dades.excepcions.UsuariDuplicatException;
import dades.usuaris.LlistaEspera;
import dades.usuaris.Pdi;
import dades.usuaris.Ptgas;
import dades.usuaris.Usuari;
import dades.usuaris.Estudiant;

public class ProvesLlistaEspera {
    public static void main(String[] args) {
        System.out.println("ProvesLlistaEspera");

        LlistaEspera espera = new LlistaEspera();

        System.out.println("Comprovar si es buida inicialment: " + espera.esBuida());

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

        System.out.println("Inserció amb àlies ordenats i intent de afegir duplicat");
        for (int i = 0; i < 9; i++){
            try {
                espera.afegir(llista[i]);
            } catch (UsuariDuplicatException e){
                System.out.println(e);
            }
        }

        try {
                espera.afegir(llista[0]);
            } catch (UsuariDuplicatException e){
                System.out.println(e);
            }

        System.out.println("Comprovar si esta buida després d'afegir usuaris: " + espera.esBuida());
        
        System.out.println(espera);

        LlistaEspera espera2 = espera.copia();
        System.out.println("Còpia de la llista d'espera:");
        System.out.println(espera2);

        System.out.println("Buscar usuari amb Alies2: " + espera2.buscar("Alies2"));
        
        System.out.println("Comprovar si llista esta plena: " + espera2.estaPlena());

        System.out.println("Obtindre la llista d'espera: " + espera2.getLlistaEspera());

        System.out.println("Obtindre el numero d'usuaris a la llista d'espera: " + espera2.getNumUsuaris());

        Usuari usuariBuscar = new Ptgas("Alies5", "alies5", "Campus 5");
        if (espera2.hiEs(usuariBuscar)){
            System.out.println("L'usuari amb Alies5 està a la llista d'espera.");
            System.out.println("Obtindre posicio d'usuari amb Alies5: " + espera2.getPosicio(usuariBuscar));
        } else {
            System.out.println("L'usuari amb Alies5 NO està a la llista d'espera.");
        }

        Usuari usuariAfegir = new Estudiant("Alies10", "alies10", "Ensenyament 10", 2020);
        try {
            espera2.afegir(usuariAfegir);
            System.out.println("S'ha afegit l'usuari amb Alies10 a la llista d'espera.");
        } catch (UsuariDuplicatException e){
            System.out.println(e);
        }

        System.out.println("Comprovar si esta plena: " + espera2.estaPlena());

        System.out.println("Treure primer usuari de la llista d'espera." + espera2.treurePrimer());
        System.out.println(espera2);
        System.out.println("Eliminar usuari amb Alies3 de la llista d'espera." + espera2.eliminar("Alies3"));
        System.out.println(espera2);

        System.out.println("Eliminar usuari amb Alies3 de la llista d'espera." + espera2.eliminar("Alies3"));
        System.out.println(espera2);
    }
}
