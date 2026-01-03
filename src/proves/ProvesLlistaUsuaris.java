package proves;
import dades.excepcions.UsuariDuplicatException;
import dades.usuaris.*;

public class ProvesLlistaUsuaris {
    public static void main(String[] args) {
        
        LlistaUsuaris usuaris = new LlistaUsuaris();

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
                usuaris.afegir(llista[i]);
            } catch (UsuariDuplicatException e){
                System.out.println(e);
            }
        }

        try {
                usuaris.afegir(llista[0]);
            } catch (UsuariDuplicatException e){
                System.out.println(e);
            }

        System.out.println(usuaris);
        

        LlistaUsuaris usuaris2 = new LlistaUsuaris();
        System.out.println("Inserció amb àlies desordenats i intent de afegir duplicat");
        for (int i = 0; i < 9; i++){
            try {
                usuaris2.afegir(llista[9-i-1]);
            } catch (UsuariDuplicatException e){
                System.out.println(e);
            }
        }

        try {
            usuaris2.afegir(llista[0]);
        } catch (UsuariDuplicatException e){
            System.out.println(e);
        }

        System.out.println(usuaris2);


        System.out.println("Probem a eliminar usuari");
        
        usuaris2.eliminar("Alies4");
        System.out.println(usuaris2);
        

        System.out.println("Probem a eliminar usuari inexistent");

        usuaris2.eliminar("test1234");
        System.out.println(usuaris2);


        System.out.println("Probem a buscar un usuari");

        System.out.println(usuaris2.buscar("Alies2"));
        
        
        System.out.println("Probem a buscar un usuari inexistent");
        
        System.out.println(usuaris2.buscar("Tiago"));


        System.out.println("Obtenim del colectiu PDI");

        System.out.println(usuaris2.obtenirPerColectiu("PDI"));
        
        System.out.println("Obtenim del colectiu PTGAS");

        System.out.println(usuaris2.obtenirPerColectiu("PTGAS"));
        
        System.out.println("Obtenim del colectiu Estudiants");

        System.out.println(usuaris2.obtenirPerColectiu("Estudiants"));
        
        System.out.println("Obtenim de colectiu inexistent");

        System.out.println(usuaris2.obtenirPerColectiu("test1234"));


        System.out.println("Total usuaris: " + usuaris2.obtenirTots());
        System.out.println("Obtenir l'usuari per índex:" + usuaris2.getUsuari(3));
        System.out.println("Obtenir el nombre d'usuaris: " + usuaris2.getNumUsuaris());
        System.out.println("Comprovar si la llista està buida: " + usuaris2.esBuida());

        LlistaUsuaris copiaUsuaris2 = new LlistaUsuaris();
        copiaUsuaris2 = usuaris2.copia();
        System.out.println("Còpia de la llista d'usuaris (I tambe la comprovació de toString() ):");
        System.out.println(copiaUsuaris2);

    }
}
