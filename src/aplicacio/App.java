package aplicacio;
import java.util.Scanner;

import dades.activitats.*;
import dades.excepcions.*;
import dades.usuaris.*;
import dades.Data;

public class App {
    static Scanner teclat = new Scanner(System.in);
    static Data avui = new Data();
    static LlistaUsuaris baseDadesUsuaris = new LlistaUsuaris();
    static LlistaActivitats llistaActivitats = new LlistaActivitats();
    public static void main(String[] args){
        int opcio;
        
        do {
            mostrarMenuActivitats();
            opcio = teclat.nextInt();
            switch (opcio) {
                case 1:
                    opcio1(); 
                    break;
                case 2:
                    opcio2();
                    break;
                case 13:
                    opcio13();
                    break;
                default:
                    break;
            }
        } while (opcio != 22);

        teclat.close();
    }

    private static void mostrarMenuActivitats() {
        System.out.println("\n===== MENÚ PRINCIPAL ACTIVITATS =====");
        System.out.println("1. Indicar la data del dia d'avui");
        System.out.println("2. Mostrar les dades de les llistes");
        System.out.println("3. Mostrar activitats en període d'inscripció");
        System.out.println("4. Mostrar activitats amb classe avui");
        System.out.println("5. Mostrar activitats actives avui");
        System.out.println("6. Mostrar activitats amb places disponibles");
        System.out.println("7. Mostrar detall d'una activitat a partir del seu nom");
        System.out.println("8. Mostrar detall d'un usuari a partir del seu nom");
        System.out.println("9. Mostrar activitats a les que està apuntat un usuari");
        System.out.println("10. Inscriure's a una activitat");
        System.out.println("11. Mostrar usuaris apuntats a una activitat i llista d'espera");
        System.out.println("12. Eliminar un usuari d'una activitat");
        System.out.println("13. Afegir una nova activitat d'un dia");
        System.out.println("14. Afegir una nova activitat periòdica");
        System.out.println("15. Afegir una nova activitat en línia");
        System.out.println("16. Valorar una activitat per part d'un assistent");
        System.out.println("17. Mostrar resum de valoracions d'activitats acabades");
        System.out.println("18. Mostrar resum de valoracions fetes per un usuari");
        System.out.println("19. Calcular mitja de valoracions per col·lectiu");
        System.out.println("20. Calcular l'usuari més actiu d'un col·lectiu");
        System.out.println("21. Donar de baixa activitats amb poca participació");
        System.out.println("22. Sortir de l'aplicació");
        System.out.print("\nSelecciona una opció: ");
    }

    private static void opcio1(){  
        System.out.println("Introdueix la data d'avui, en el seguent format DD MM YYYY:");
        int dia, mes, any;
        boolean correcte = false;
        while(!correcte){
            try {
                dia = teclat.nextInt();
                mes = teclat.nextInt();
                any = teclat.nextInt();
                avui.setData(dia, mes, any);
                correcte = true;
            } catch (NumberFormatException e) {
                System.out.println("S'ha d'entrar la data en nombres enters" + e);
            } catch (DataIncorrectaExcepction e){
                System.out.println(e);
            }
        }
    }

    private static void opcio2(){
        System.out.println(llistaActivitats);   //TODO això és per poder fer testing abans de fer la opció completa
    }

    private static void opcio13(){
        String nom;
        boolean[] collectius = new boolean[3];
        Activitat activitat;
        int dia,mes,any;
        Data dataIniciInscripcio = null;
        Data dataFiInscripcio = null;
        Data dataActivitat = null;
        int limitPlaces;
        double preu;
        String horari, ciutat;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            nom = teclat.next();
    
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            collectius[0] = teclat.nextInt() == 1;
            System.out.println("És per PTGAS?");
            collectius[1] = teclat.nextInt() == 1;
            System.out.println("És per Estudiants?");
            collectius[2] = teclat.nextInt() == 1;
            
            boolean correcte = false;
            while(!correcte){
                System.out.println("Introdueix la data d'inici d'inscripcions, en el següent format DD MM YYYY:");
                try {
                    dia = teclat.nextInt();
                    mes = teclat.nextInt();
                    any = teclat.nextInt();
                    dataIniciInscripcio = new Data(dia, mes, any);
                    correcte = true;
                } catch (NumberFormatException e) {
                    System.out.println("S'ha d'entrar la data en nombres enters" + e);
                } /*catch (DataIncorrectaExcepction e){
                    System.out.println(e);              TREURE COMENTARI QUAN DATA LLENCI EXCEPCIO EN EL CONSTRUCTOR
                }*/ 
            }
    
            correcte = false;
            while(!correcte){
                System.out.println("Introdueix la data de fi d'inscripcions, en el següent format DD MM YYYY:");
                try {
                    dia = teclat.nextInt();
                    mes = teclat.nextInt();
                    any = teclat.nextInt();
                    dataFiInscripcio = new Data(dia, mes, any);
                    correcte = true;
                } catch (NumberFormatException e) {
                    System.out.println("S'ha d'entrar la data en nombres enters" + e);
                } /*catch (DataIncorrectaExcepction e){
                    System.out.println(e);              TREURE COMENTARI QUAN DATA LLENCI EXCEPCIO EN EL CONSTRUCTOR
                }*/ 
            }
    
            System.out.println("Introdueix el nombre de places de l'activitat");
            limitPlaces = teclat.nextInt();
            
            System.out.println("Introdueix el preu de l'activitat");
            preu = teclat.nextDouble();
    
            correcte = false;
            while(!correcte){
                System.out.println("Introdueix el dia en que es fa l'activitat, en el següent format DD MM YYYY:");
                try {
                    dia = teclat.nextInt();
                    mes = teclat.nextInt();
                    any = teclat.nextInt();
                    dataActivitat = new Data(dia, mes, any);
                    correcte = true;
                } catch (NumberFormatException e) {
                    System.out.println("S'ha d'entrar la data en nombres enters" + e);
                } /*catch (DataIncorrectaExcepction e){
                    System.out.println(e);              TREURE COMENTARI QUAN DATA LLENCI EXCEPCIO EN EL CONSTRUCTOR
                }*/ 
            }
    
            System.out.println("Introdueix l'horari de l'activitat");
            horari = teclat.next();
            
            System.out.println("Introdueix la ciutat de l'activitat");
            ciutat = teclat.next();
    
            activitat = new ActivitatUnDia(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 
                limitPlaces, preu, dataActivitat, horari, ciutat);
            
            llistaActivitats.afegir(activitat);
            
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e);
        }
    }
}