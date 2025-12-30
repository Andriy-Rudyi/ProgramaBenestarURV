package aplicacio;
import java.util.Scanner;

import dades.activitats.*;
import dades.excepcions.*;
import dades.inscripcions.*;
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
                case 11:
                    opcio11();
                    break;
                case 12:
                    opcio12();
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
        System.out.println("Introdueix la data d'avui:");
        avui = llegirData();
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
        String alies = teclat.next();
        Usuari usuari= baseDadesUsuaris.buscar(alies);

        if (usuari!= null) {
        System.out.println("Detalls de l'usuari:");
        System.out.println(usuari.toString());
        } else {
        System.out.println("No s'ha trobat cap usuari amb l'àlies: " + alies);
        }
    }
    private static void opcio9(){
        System.out.println("Introdueix l'àlies de l'usuari per veure les seves activitats:");
        String alies = teclat.nextLine();
        Usuari usuari = baseDadesUsuaris.buscar(alies);

        if (usuari!= null) {
        System.out.println("L'usuari " + alies + " està inscrit a:");
        boolean teInscripcions = false;
        Activitat[] totesLesActivitats = llistaActivitats.obtenirTotes();
        for (Activitat activitat : totesLesActivitats) {
            if (activitat.teUsuariInscrit(alies)) { // NOU Metode creat a llista inscripcions
                System.out.println("- " + activitat.getNom() + " (" + activitat.getTipus() + ")");
                teInscripcions = true;
            }
        }

        if (!teInscripcions) {
            System.out.println("No està inscrit a cap activitat actualment.");
        }

    } else {
        System.out.println("Error: No existeix cap usuari amb l'àlies " + alies);
    }



    }
    private static void opcio10(){
        System.out.println("--- INSCRIPCIÓ A UNA ACTIVITAT ---");
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String aliesInscripcio = teclat.nextLine();
        System.out.print("Introdueix el nom de l'activitat: ");
        String nomActivitat = teclat.nextLine();

        Usuari usuariInscripcio = baseDadesUsuaris.buscar(aliesInscripcio); 
        Activitat activitatInscripcio = llistaActivitats.buscar(nomActivitat); 

        if (usuariInscripcio == null) usuariInscripcio = registrarUsuari(aliesInscripcio);
        if (activitatInscripcio != null) {
            if (activitatInscripcio.estaEnPeriodeInscripcio(avui)) { 
                if (activitatInscripcio.esPerCollectiu(usuariInscripcio.getColectiu())) { //
                    try {
                  
                    LlistaInscripcions llista = activitatInscripcio.getLlistaInscripcions();
                    int inscritsAbans = llista.getNumInscrits(); 
                    llista.afegir(usuariInscripcio); 
                    if (llista.getNumInscrits() > inscritsAbans) {
                        System.out.println("Usuari inscrit oficialment.");
                    } 
                    else {
                        
                        System.out.println("Activitat plena: L'usuari ha anat a la LLISTA D'ESPERA.");
                    }

                } catch (UsuariDuplicatException e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else {
                System.out.println("Error: Aquesta activitat no és per al col·lectiu " + usuariInscripcio.getColectiu());
            }
        } else {
            System.out.println("Error: Fora de termini d'inscripció.");
        }
    } else {
        System.out.println("Error: Usuari o activitat no trobats.");
    }
        
    }

    private static void opcio11(){
        System.out.print("Nom de l'activitat: ");
        Activitat act = llistaActivitats.buscar(teclat.nextLine()); 

        if (act != null) {
            LlistaInscripcions inscripcions = act.getLlistaInscripcions(); 
        
            System.out.println("\n--- DADES DE: " + act.getNom() + " ---\n");
            System.out.println(inscripcions.getLlistaInscrits()); 
       
       
        if (inscripcions.getLlistaEspera() != null) {
            System.out.println(inscripcions.getLlistaEspera());
        } else {
            System.out.println("Llista d'espera: (Activitat Il·limitada)");
        }

        } else {
        System.out.println("Activitat no trobada.");
        }

    }


    private static void opcio12() {
        System.out.println("--- BAIXA D'UNA ACTIVITAT ---");

        System.out.println("Introdueix l'àlies de l'usuari a eliminar: ");
        String aliesBaixa = teclat.nextLine();
        System.out.println("Introdueix el nom de l'activitat: ");
        String nomActBaixa = teclat.nextLine();

        Activitat activitatBaixa = llistaActivitats.buscar(nomActBaixa); 

        if (activitatBaixa != null) {
  
            LlistaInscripcions llista = activitatBaixa.getLlistaInscripcions(); 

        try {
 
            int inscritsAbans = llista.getNumInscrits(); 
            boolean teniaEspera = (llista.getLlistaEspera() != null && !llista.getLlistaEspera().esBuida());
            llista.eliminar(aliesBaixa);

            int inscritsDespres = llista.getNumInscrits();
            
            if (inscritsDespres < inscritsAbans) {
                System.out.println("L'usuari ha estat eliminat. S'ha alliberat una plaça.");
            } else if (teniaEspera && inscritsDespres == inscritsAbans) {
                System.out.println("Usuari eliminat. La plaça lliure l'ha ocupat algú de la llista d'espera.");
            } else {
                System.out.println("Operació realitzada (si l'usuari existia a la llista d'espera o inscrits).");
            }

        } catch (Exception e) {
            System.out.println("Error durant l'eliminació: " + e.getMessage());
        }

    } else {
        System.out.println("Activitat no trobada.");
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
        String nomActivitat = teclat.nextLine();
        Activitat activitat = llistaActivitats.buscar(nomActivitat);
        
        System.out.println("Introdueix el nom de l'usuari que valora:");
        String nomUsuari = teclat.nextLine();
        Usuari usuari = baseDadesUsuaris.buscar(nomUsuari); // restore lookup
        
        System.out.println("Introdueix la valoració (1-10 estrelles):");
        int valoracio = Integer.parseInt(teclat.nextLine());
        
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
            System.out.println("MITJANA DE VALORACIONS DE " + activitatsAcabades[i].getNom() + ":\n" + 
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

    private static Usuari registrarUsuari(String nom){
        boolean correcte = false;
        System.out.println("Usuari no trobat. Començant registre...");
        Usuari usuari = null;
        while (!correcte) {
            System.out.println("Tria el numero del teu col·lectiu:\n");
            System.out.println("1. Estudiant"); 
            System.out.println("2. PDI"); 
            System.out.println("3. PTGAS");
            int colectiu = Integer.parseInt(teclat.nextLine());
            String adreca;
            switch (colectiu) {
                case 1:
                    try {
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        adreca = teclat.nextLine();
                        String ensenyament;
                        int anyInici;
                        System.out.println("Introdueix el teu ensenyament");
                        ensenyament = teclat.nextLine();
                        System.out.println("Introdueix el any d'inici del teu ensenyament");
                        anyInici = Integer.parseInt(teclat.nextLine());
                        usuari = new Estudiant(nom, adreca, ensenyament, anyInici);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                    break;
                case 2:
                    try {
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        adreca = teclat.nextLine();
                        String departament, campus;
                        System.out.println("Introdueix el teu departament");
                        departament = teclat.nextLine();
                        System.out.println("Introdueix el teu campus");
                        campus = teclat.nextLine();
                        usuari = new Pdi(nom, adreca, departament, campus);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                    break;
                case 3:
                    try {
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        adreca = teclat.nextLine();
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        adreca = teclat.nextLine();
                        String campus;
                        System.out.println("Introdueix el teu campus");
                        campus = teclat.nextLine();
                        usuari = new Ptgas(nom, adreca, campus);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    }   //més excepcions
                    break;
            
                default:
                    break;
            }
        }
        return usuari;
    
    }
}