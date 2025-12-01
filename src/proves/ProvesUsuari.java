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
        
        Usuari duplicat = llista[2].copia();
        System.out.println(duplicat);
    }

}
