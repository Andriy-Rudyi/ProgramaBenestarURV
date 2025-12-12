package aplicacio;
import java.util.Scanner;

import dades.*;
import dades.excepcions.DataIncorrectaExcepction;

public class App {
    static Scanner teclat = new Scanner(System.in);
    public static void main(String[] args) {
        mostrarMenuActivitats();
        int opcio;
        Data avui = null;


        do {
            opcio = Integer.parseInt(teclat.nextLine());
            switch (opcio) {
                case 1:
                    opcio1(avui); 
                    break;
                case 2:
                    
                    break;
                default:
                    break;
            }
        } while (opcio != 22);
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

    private static void opcio1(Data avui){  
        System.out.println("Introdueix la data d'avui, en el seguent format DD MM YYYY:");
        int dia, mes, any;
        boolean correcte = false;
        while(!correcte){
            try {
                dia = Integer.parseInt(teclat.nextLine());
                mes = Integer.parseInt(teclat.nextLine());
                any = Integer.parseInt(teclat.nextLine());
                avui.setData(dia, mes, any);
                correcte = true;
            } catch (NumberFormatException e) {
                System.out.println("S'ha d'entrar la data en nombres enters" + e);
            } catch (DataIncorrectaExcepction e){
                System.out.println(e);
            }
        }
    }
}