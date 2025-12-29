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
    static boolean correcte = false; //pels try catch
    public static void main(String[] args){
        int opcio;
        
        do {
            mostrarMenuActivitats();
            opcio = Integer.parseInt(teclat.nextLine());
            switch (opcio) {
                case 1:
                    opcio1(); 
                    break;
                case 2:
                    opcio2();
                    break;
                case 3:
                    opcio3();
                    break;
                case 4:
                    opcio4();
                    break;
                case 5:
                    opcio5();
                    break;
                case 6:
                    opcio6();
                    break;
                case 7:
                    opcio7();
                    break;
                case 8:
                    opcio8();
                    break;
                case 9:
                    opcio9();
                    break;
                case 10:
                    opcio10();
                    break;
                case 13:
                    opcio13();
                    break;
                case 14:
                    opcio14();
                    break;
                case 15:
                    opcio15();
                    break;
                case 16:
                    opcio16();
                    break;
                case 17:
                    opcio17();
                    break;
                case 21:
                    opcio21();
                    break;
                default:
                    break;
            }
        } while (opcio != 22);

        teclat.close();
    }

    private static void mostrarMenuActivitats() {
        System.out.println("\n===== MENÚ PRINCIPAL ACTIVITATS =====");
        System.out.println("1. Indicar la data del dia d'avui"); // fet
        System.out.println("2. Mostrar les dades de les llistes"); // fet
        System.out.println("3. Mostrar activitats en període d'inscripció"); // fet
        System.out.println("4. Mostrar activitats amb classe avui"); // fet
        System.out.println("5. Mostrar activitats actives avui"); // fet
        System.out.println("6. Mostrar activitats amb places disponibles");// fet
        System.out.println("7. Mostrar detall d'una activitat a partir del seu nom"); // fet
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

    private static void opcio3(){
        Activitat[] enInscripcio = llistaActivitats.obtenirEnPeriodeInscripcio(avui);
        for (int i = 0; i < enInscripcio.length; i++){
            int placesDisp = enInscripcio[i].getPlacesDisponibles();
            if (placesDisp == 0){
                System.out.println(enInscripcio[i].getNom() + ": No té places disponibles");
            } else if (placesDisp == -1){
                System.out.println(enInscripcio[i].getNom() + ": Té places infinites");
            } else System.out.println(enInscripcio[i].getNom() + ": Té " + placesDisp + " places disponibles");
        }
    }

    public static void opcio4(){
        Activitat[] ambClasseAvui = llistaActivitats.obtenirAmbClasseAvui(avui);
        for (int i = 0; i < ambClasseAvui.length; i++) System.out.println(ambClasseAvui[i]);
    }

    public static void opcio5(){
        Activitat[] activesAvui = llistaActivitats.obtenirActives(avui);
        for (int i = 0; i < activesAvui.length; i++) System.out.println(activesAvui[i].getNom());
    }

    public static void opcio6(){
        Activitat[] ambPlacesDisp = llistaActivitats.obtenirAmbPlaces();
        for (int i = 0; i < ambPlacesDisp.length; i++) System.out.println(ambPlacesDisp[i].getNom());
    }

    private static void opcio7(){
        System.out.println("Introdueix el nom de l'activitat a buscar:");
        String nomActivitat = teclat.nextLine();
        System.out.println(llistaActivitats.buscar(nomActivitat));
    }

    private static void opcio8(){
        System.out.println("Introdueix el nom de l'usuari a buscar:");
        String nomUsuari = teclat.nextLine();
        System.out.println(baseDadesUsuaris.buscar(nomUsuari));
    }

    private static void opcio9(){
        System.out.println("Introdueix el nom de l'usuari a buscar:");
        String nomUsuari = teclat.nextLine();
        System.out.println(baseDadesUsuaris.buscar(nomUsuari).getLlistaActivitats());
    }

    private static void opcio10(){
        System.out.println("Introdueix el nom de l'activitat a inscriure's:");
        Activitat activitat = llistaActivitats.buscar(teclat.nextLine());

        if (!activitat.hiHaPlacesDisponibles()){
            System.out.println("No hi ha places disponibles");
            return;
        }

        System.out.println("Introdueix el nom de l'usuari a registrar:");
        String nomUsuari = teclat.nextLine();
        Usuari usuari = baseDadesUsuaris.buscar(nomUsuari);
        if (usuari == null){
            registrarUsuari(nomUsuari);
            usuari = baseDadesUsuaris.buscar(nomUsuari);
        }
        if (activitat.esPerCollectiu(usuari.getColectiu())){
            correcte = false;
            while (!correcte) {
                try {
                    activitat.inscriureUsuari(usuari);
                    correcte = true;
                } catch (UsuariDuplicatException e) {
                    System.out.println(e);
                } catch (ActivitatDuplicadaException e) {
                    System.out.println("Inesperat. L'usuari ja està registrat en aquesta activitat. " + e);
                }
            }
        } else System.out.println("L'activitat no és per el teu col·lectiu!");

    }

    private static void registrarUsuari(String nom){
        System.out.println("Usuari no trobat. Començant registre...");
        System.out.println("Tria el numero del teu col·lectiu:\n");
        System.out.println("1. Estudiant"); 
        System.out.println("2. PDI"); 
        System.out.println("3. PTGAS");
        int colectiu = Integer.parseInt(teclat.nextLine());
        String adreca;
        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
        adreca = teclat.nextLine();
        correcte = false;
        switch (colectiu) {
            case 1:
                while (!correcte) {
                    try {
                        String ensenyament;
                        int anyInici;
                        System.out.println("Introdueix el teu ensenyament");
                        ensenyament = teclat.nextLine();
                        System.out.println("Introdueix el any d'inici del teu ensenyament");
                        anyInici = Integer.parseInt(teclat.nextLine());
                        Estudiant estudiant = new Estudiant(nom, adreca, ensenyament, anyInici);
                        baseDadesUsuaris.afegir(estudiant);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                }
                break;
            case 2:
                while (!correcte) {
                    try {
                        String departament, campus;
                        System.out.println("Introdueix el teu departament");
                        departament = teclat.nextLine();
                        System.out.println("Introdueix el teu campus");
                        campus = teclat.nextLine();
                        Pdi pdi = new Pdi(nom, adreca, departament, campus);
                        baseDadesUsuaris.afegir(pdi);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                }
                break;
            case 3:
                while (!correcte) {
                    try {
                        String campus;
                        System.out.println("Introdueix el teu campus");
                        campus = teclat.nextLine();
                        Ptgas ptgas = new Ptgas(nom, adreca, campus);
                        baseDadesUsuaris.afegir(ptgas);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                }
                break;
        
            default:
                break;
        }
    
    }

    private static void opcio13(){
        String nom;
        boolean[] collectius = new boolean[3];
        Activitat activitat;
        Data dataIniciInscripcio = null;
        Data dataFiInscripcio = null;
        Data dataActivitat = null;
        int limitPlaces;
        double preu;
        String horari, ciutat;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            nom = teclat.nextLine();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            collectius[0] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per PTGAS?");
            collectius[1] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per Estudiants?");
            collectius[2] = Integer.parseInt(teclat.nextLine()) == 1;
            
            System.out.println("Inici d'inscripcions?");
            dataIniciInscripcio = llegirData();
            System.out.println("Fi d'inscripcions?");
            dataFiInscripcio = llegirData();
            
            System.out.println("Introdueix el nombre de places de l'activitat");
            limitPlaces = Integer.parseInt(teclat.nextLine());
            
            System.out.println("Introdueix el preu de l'activitat");
            preu = Double.parseDouble(teclat.nextLine());
            
            System.out.println("Quan es fa l'activitat?");
            dataActivitat = llegirData();
    
            System.out.println("Introdueix l'horari de l'activitat");
            horari = teclat.nextLine();
            
            System.out.println("Introdueix la ciutat de l'activitat");
            ciutat = teclat.nextLine();
    
            activitat = new ActivitatUnDia(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 
                limitPlaces, preu, dataActivitat, horari, ciutat);
            
            llistaActivitats.afegir(activitat);
            
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e);
        }
    }

    private static void opcio14(){

        String nom;
        boolean[] collectius = new boolean[3];
        Activitat activitat;
        Data dataIniciInscripcio = null;
        Data dataFiInscripcio = null;
        Data dataInici = null;
        int limitPlaces, numSetmanes;
        double preu;
        String horari, nomCentre, ciutat;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            nom = teclat.nextLine();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            collectius[0] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per PTGAS?");
            collectius[1] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per Estudiants?");
            collectius[2] = Integer.parseInt(teclat.nextLine()) == 1;
            
            System.out.println("Inici d'inscripcions?");
            dataIniciInscripcio = llegirData();
            System.out.println("Fi d'inscripcions?");
            dataFiInscripcio = llegirData();
            
            System.out.println("Introdueix el nombre de places de l'activitat");
            limitPlaces = Integer.parseInt(teclat.nextLine());
            
            System.out.println("Introdueix el preu de l'activitat");
            preu = Double.parseDouble(teclat.nextLine());
            
            System.out.println("Quin és el primer dia que es fa l'activitat?");
            dataInici = llegirData();

            System.out.println("Introdueix el nombre de setmanes que es fa l'activitat");
            numSetmanes = Integer.parseInt(teclat.nextLine());
    
            System.out.println("Introdueix l'horari de l'activitat");
            horari = teclat.nextLine();
            
            System.out.println("Introdueix el nom del centre on es fa l'activitat");
            nomCentre = teclat.nextLine();

            System.out.println("Introdueix la ciutat de l'activitat");
            ciutat = teclat.nextLine();
    
            activitat = new ActivitatPeriodica(nom, collectius, dataIniciInscripcio, dataFiInscripcio, limitPlaces, 
                preu, horari, dataInici, numSetmanes, nomCentre, ciutat);
            
            llistaActivitats.afegir(activitat);
            
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e);
        }
    }

    private static void opcio15(){
        String nom;
        boolean[] collectius = new boolean[3];
        Activitat activitat;
        Data dataIniciInscripcio = null;
        Data dataFiInscripcio = null;
        Data dataActivitat = null;
        int periodeVisualitzacio;
        String enllac;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            nom = teclat.nextLine();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            collectius[0] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per PTGAS?");
            collectius[1] = Integer.parseInt(teclat.nextLine()) == 1;
            System.out.println("És per Estudiants?");
            collectius[2] = Integer.parseInt(teclat.nextLine()) == 1;
            
            System.out.println("Inici d'inscripcions?");
            dataIniciInscripcio = llegirData();
            System.out.println("Fi d'inscripcions?");
            dataFiInscripcio = llegirData();
            
            System.out.println("Quan comença l'activitat?");
            dataActivitat = llegirData();
            
            System.out.println("Introdueix el nombre de dies que estarà disponible l'activitat");
            periodeVisualitzacio = Integer.parseInt(teclat.nextLine());
            
            System.out.println("Introdueix l'enllac de l'activitat");
            enllac = teclat.nextLine();
    
            activitat = new ActivitatOnline(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 
                        dataActivitat, periodeVisualitzacio, enllac);
            
            llistaActivitats.afegir(activitat);
            
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e);
        }
    }

    
    
    public static void opcio16(){
        System.out.println("Introdueix el nom de l'activitat a valorar:");
        String nomActivitat = teclat.next();
        Activitat activitat = llistaActivitats.buscar(nomActivitat);
        
        System.out.println("Introdueix el nom de l'usuari que valora:");
        String nomUsuari = teclat.next();
        Usuari usuari = baseDadesUsuaris.buscar(nomUsuari); // restore lookup
        
        System.out.println("Introdueix la valoració (1-10 estrelles):");
        int valoracio = teclat.nextInt();
        
        if (activitat != null && usuari != null && activitat.haAcabat(avui) 
            && activitat.teUsuariInscrit(usuari.getAlies())){
        try {
                activitat.afegirValoracio(avui, usuari, valoracio);
                System.out.println("Valoració afegida.");
            } catch (UsuariDuplicatException e) {
                System.out.println("L'usuari ja ha valorat aquesta activitat: " + e.getMessage());
            }
        } else {
            System.out.println("Activitat no trobada, usuari no trobat, activitat no ha acabat o usuari no inscrit.");
        }
    }

    private static void opcio17(){
        Activitat[] activitatsAcabades = llistaActivitats.obtenirAcabades(avui);
        for (int i = 0; i < activitatsAcabades.length; i++){
            System.out.println("MITJANA DE VALORACIONS DE" + activitatsAcabades[i].getNom() + ":\n" + 
            activitatsAcabades[i].getLlistaValoracions().getMitjanaValoracions());
        }
    }

    private static void opcio21(){
        Activitat[] noPeriodeInscripcio = llistaActivitats.obtenirEnPeriodeInscripcio(avui);
        for (int i = 0; i < llistaActivitats.getNumActivitats(); i++){
            if (noPeriodeInscripcio[i] instanceof ActivitatOnline && noPeriodeInscripcio[i].getLlistaInscripcions().getNumInscrits() < 20){
                llistaActivitats.eliminar(noPeriodeInscripcio[i].getNom());
            } else if (noPeriodeInscripcio[i].getPercentatgeOcupacio() < 10){
                llistaActivitats.eliminar(noPeriodeInscripcio[i].getNom());
            }
        }
    }

    private static Data llegirData(){
        boolean correcte = false;
        Data data = new Data();
        int dia, mes, any;
        while(!correcte){
            System.out.println("Introdueix la data en el següent format DD MM YYYY:");
            try {
                String[] parts = teclat.nextLine().split(" ");

                if (parts.length != 3)
                throw new IllegalArgumentException("Format incorrecte");

                dia = Integer.parseInt(parts[0]);
                mes = Integer.parseInt(parts[1]);
                any = Integer.parseInt(parts[2]);

                data = new Data(dia, mes, any);
                correcte = true;
            } catch (NumberFormatException e) {
                System.out.println("S'ha d'entrar la data en nombres enters" + e);
            } catch (IllegalArgumentException e) {
                System.out.println("Format correcte: DD MM YYYY");
            } /*catch (DataIncorrectaExcepction e){
                System.out.println(e);              TREURE COMENTARI QUAN DATA LLENCI EXCEPCIO EN EL CONSTRUCTOR
            }*/ 
        }
        return data;
    }
}