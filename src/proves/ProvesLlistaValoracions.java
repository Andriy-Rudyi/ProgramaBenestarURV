package proves;

import dades.excepcions.UsuariDuplicatException;
import dades.inscripcions.LlistaValoracions;
import dades.usuaris.Pdi;
import dades.usuaris.Ptgas;
import dades.usuaris.Estudiant;


public class ProvesLlistaValoracions {
    public static void main(String[] args) {
        System.out.println("ProvesLlistaValoracions");

        LlistaValoracions valoracions = new LlistaValoracions(3);
        
        System.out.println("Llista de valoracions inicialment: " + valoracions);


        // afegir valoracions
        try {
            valoracions.afegirValoracio(new Pdi("Alies1", "alies1", "Departament 1", "Campus 1"), 4);
            valoracions.afegirValoracio(new Ptgas("Alies4", "alies4", "Campus 4"), 5);
            valoracions.afegirValoracio(new Estudiant("Alies7", "alies7", "Ensenyament 7", 2025), 3);
        } catch (UsuariDuplicatException e) {
            System.out.println(e.getMessage());
        }

        // copia
        LlistaValoracions copiaValoracions = valoracions.copia();
        System.out.println("Còpia de les valoracions:" + copiaValoracions);

        // getMitjanaValoracions
        System.out.println("Mitjana de valoracions: " + copiaValoracions.getMitjanaValoracions());

        // getNumValoracions
        System.out.println("Número de valoracions: " + copiaValoracions.getNumValoracions());

        // getNumValoracions
        System.out.println("Número de valoracions: " + copiaValoracions.getNumValoracions());

        // getUsuari
        System.out.println("Usuari a l'índex 1: " + copiaValoracions.getUsuari(1));

        // getValoracioDeUsuari
        System.out.println("Valoració de l'usuari amb àlies 'alies4': " + copiaValoracions.getValoracioDeUsuari(copiaValoracions.getUsuari(1)));

        // teValoracioDeUsuari
        System.out.println("L'usuari amb àlies 'alies4' té valoració? " + copiaValoracions.teValoracioDeUsuari(copiaValoracions.getUsuari(1)));
    }


}
