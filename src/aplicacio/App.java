package aplicacio;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
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
        int opcio = 0;
        llistaActivitats = carregarLlistaActivitats("data/activitats.bin");
        baseDadesUsuaris = carregarBaseDadesUsuaris("data/usuaris.csv");
        do {
            correcte = false;
            while (!correcte) {
                try {
                    mostrarMenuActivitats();
                    opcio = Integer.parseInt(teclat.nextLine());
                    correcte = true;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta, torna-ho a intentar." + e);
                } 
            }
            
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
                case 18:
                    opcio18();
                    break;
                case 19:
                    opcio19();
                    break;
                case 20:
                    opcio20();
                    break;
                case 21:
                    opcio21();
                    break;
                case 22:
                    break;
                default:
                    System.out.println("Entrada incorrecta. Escriu un nombre entre 1 i 22.");
                    break;
            }
            if (1 <= opcio && opcio <= 21 ){
                System.out.println("Prèm Enter per a continuar...");
                teclat.nextLine();
            }
        } while (opcio != 22);

        System.out.println("Vols sobreescriure les dades actuals per les anteriors? Y/N");
        correcte = false;
        while(!correcte){
            String resposta = teclat.next(); // Check if llegirText() is needed to cancel saving and return to the menu
            if(resposta.equals("Y")){ 
                guardarLlistaActivitats("data/activitats.bin");
                guardarBaseDadesUsuaris("data/usuaris.csv");
                correcte = true;
                System.out.println("Fitxer guardat");
            } else if (!resposta.equals("N")){
                System.out.println("Resposta incorrecta. Respon Y o N");
            } else correcte = true;
        }
        System.out.println("Tancant programa...");
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
        System.out.println("Introdueix la data d'avui:");
        avui = llegirData();
    }

    private static void opcio2(){
        String imprimir = null;
        do {
            try {
                
            
                System.out.println("De quina llista vols mostrar les dades?");
                System.out.println("1. Llista d'activitats");
                System.out.println("2. Llista d'usuaris");
                correcte = true;
                // String num = teclat.nextLine();
                String num = llegirText();
                if(num.equals("1")){
                    System.out.println("De quin tipus vols veure les activitats?");
                    System.out.println("1. Tots");
                    System.out.println("2. Un dia");
                    System.out.println("3. Periòdiques");
                    System.out.println("4. Online");
                    // num = teclat.nextLine();
                    num = llegirText();
                    
                    if(num.equals("1")) imprimir = llistaActivitats.toString();
                    else if(num.equals("2")) imprimir = llistaActivitats.obtenirPerTipus(Activitat.TIPUS_UNDIA).toString();
                    else if(num.equals("3")) imprimir = llistaActivitats.obtenirPerTipus(Activitat.TIPUS_PERIODICA).toString();
                    else if(num.equals("4")) imprimir = llistaActivitats.obtenirPerTipus(Activitat.TIPUS_ONLINE).toString();
                    else {
                        System.out.println("Entrada incorrecta. Torna-ho a intentar."); 
                    } 
                    
                } else if(num.equals("2")){
                    System.out.println("De quin col·lectiu vols veure els usuaris?");
                    System.out.println("1. Tots");
                    System.out.println("2. PTGAS");
                    System.out.println("3. PDI");
                    System.out.println("4. Estudiants");
                    // num = teclat.nextLine();
                    num = llegirText();
                    if(num.equals("1")) imprimir = baseDadesUsuaris.toString();
                    else if(num.equals("2")) imprimir = baseDadesUsuaris.obtenirPerColectiu(Usuari.COLECTIU_PTGAS).toString();
                    else if(num.equals("3")) imprimir = baseDadesUsuaris.obtenirPerColectiu(Usuari.COLECTIU_PDI).toString();
                    else if(num.equals("4")) imprimir = baseDadesUsuaris.obtenirPerColectiu(Usuari.COLECTIU_ESTUDIANTS).toString();
                    else {
                        System.out.println("Entrada incorrecta. Torna-ho a intentar.");
                    }
                } else {
                    System.out.println("Entrada incorrecta. Torna-ho a intentar.");
                }
            } catch (CancelarOperacioException e) {
                System.out.println("Creació d'activitat cancel·lada.");
                return; 
            }
        } while (imprimir == null);
        System.out.println(imprimir);
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
        try {
            System.out.println("Introdueix el nom de l'activitat a buscar:");
            // String nomActivitat = teclat.nextLine();
            String nomActivitat = llegirText();
            if (llistaActivitats.buscar(nomActivitat) == null) System.out.println("Activitat no trobada.");
            else System.out.println(llistaActivitats.buscar(nomActivitat));
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return; 
        }
        
    }
    
    private static void opcio8(){
        try {
            System.out.println("Introdueix el nom de l'usuari a buscar:");
            // String alies = teclat.nextLine();
            String alies = llegirText();
            Usuari usuari= baseDadesUsuaris.buscar(alies);

            if (usuari!= null) {
                System.out.println("Detalls de l'usuari:");
                System.out.println(usuari.toString());
            } else {
                System.out.println("No s'ha trobat cap usuari amb l'àlies: " + alies);
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
        
    }

    private static void opcio9(){
        try {
            System.out.println("Introdueix l'àlies de l'usuari per veure les seves activitats:");
            // String alies = teclat.nextLine();
            String alies = llegirText();
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
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
    }

    private static void opcio10(){
        System.out.println("--- INSCRIPCIÓ A UNA ACTIVITAT ---");
        
        try {
            System.out.print("Introdueix el nom de l'activitat: ");
            // String nomActivitat = teclat.nextLine();
            String nomActivitat = llegirText();
            Activitat activitatInscripcio = llistaActivitats.buscar(nomActivitat); 
            
            if (activitatInscripcio != null) {
                if (activitatInscripcio.estaEnPeriodeInscripcio(avui)) { 

                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    // String aliesInscripcio = teclat.nextLine();
                    String aliesInscripcio = llegirText();
                    Usuari usuariInscripcio = baseDadesUsuaris.buscar(aliesInscripcio); 
                    if (usuariInscripcio == null) usuariInscripcio = registrarUsuari(aliesInscripcio);
                    
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
                        System.out.println("Error: Activitat no trobada.");
                    }
                
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        } 
    }

    private static void opcio11(){
        try {
            System.out.print("Nom de l'activitat: ");
            // Activitat act = llistaActivitats.buscar(teclat.nextLine()); 
            Activitat act = llistaActivitats.buscar(llegirText());

            if (act != null) {
                LlistaInscripcions inscripcions = act.getLlistaInscripcions(); 
            
                System.out.println("\n--- INSCRIPCIONS DE: " + act.getNom() + " ---\n");
                System.out.println(inscripcions.getLlistaInscrits()); 
        
                if (inscripcions.getLlistaEspera() != null) {
                    System.out.println(inscripcions.getLlistaEspera());
                } else {
                    System.out.println("Llista d'espera: (Activitat Il·limitada)");
                }

            } else {
            System.out.println("Activitat no trobada.");
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
    }


    private static void opcio12() {
        System.out.println("--- BAIXA D'UNA ACTIVITAT ---");
        try {
            System.out.println("Introdueix el nom de l'activitat: ");
            // String nomActBaixa = teclat.nextLine();
            String nomActBaixa = llegirText();
            
            Activitat activitatBaixa = llistaActivitats.buscar(nomActBaixa); 
            
            System.out.println("Introdueix l'àlies de l'usuari a eliminar: ");
            // String aliesBaixa = teclat.nextLine();
            String aliesBaixa = llegirText();


            if (activitatBaixa != null) {
    
                LlistaInscripcions llista = activitatBaixa.getLlistaInscripcions(); 
                    
                int inscritsAbans = llista.getNumInscrits(); 
                boolean teniaEspera = (llista.getLlistaEspera() != null && !llista.getLlistaEspera().esBuida());
                llista.eliminar(aliesBaixa);

                int inscritsDespres = llista.getNumInscrits();
                
                if (inscritsDespres < inscritsAbans) {
                    System.out.println("L'usuari ha estat eliminat. S'ha alliberat una plaça.");
                } else if (teniaEspera && inscritsDespres == inscritsAbans) {
                    System.out.println("Usuari eliminat. La plaça lliure l'ha ocupat algú de la llista d'espera.");
                } else {
                    System.out.println("Usuari no trobat.");
                }

            } else {
                System.out.println("Activitat no trobada.");
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
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
        boolean datesValides = false;
        boolean dataIniciActivitatValida = false;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            // nom = teclat.nextLine();
            nom = llegirText();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            boolean validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[0] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[0] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per PTGAS?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[1] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[1] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per Estudiants?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[2] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[2] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }

            do {
                System.out.println("Inici d'inscripcions?");
                dataIniciInscripcio = llegirData();
                
                System.out.println("Fi d'inscripcions?");
                dataFiInscripcio = llegirData();
                
                if (!dataIniciInscripcio.esAnterior(dataFiInscripcio)) {
                    System.out.println("Error: La data de fi d'inscripció ha de ser posterior a la d'inici.");
                    System.out.println("Si us plau, torna a introduir les dates.");
                } else {
                    datesValides = true;
                }
                
            } while (!datesValides);
            
            System.out.println("Introdueix el nombre de places de l'activitat");
            // limitPlaces = Integer.parseInt(teclat.nextLine());
            limitPlaces = llegirEnter();
            
            System.out.println("Introdueix el preu de l'activitat");
            // preu = Double.parseDouble(teclat.nextLine());
            preu = llegirDouble();

            do {
                System.out.println("Quan es fa l'activitat?");
                dataActivitat = llegirData();
                
                if (!dataFiInscripcio.esAnterior(dataActivitat)) {
                    System.out.println("Error: La data quan es fa l'activitat ha de ser posterior a la de fi d'inscripció.");
                    System.out.println("Si us plau, torna a introduir la data.");
                } else {
                    dataIniciActivitatValida = true;
                }
                
            } while (!dataIniciActivitatValida);
    
            System.out.println("Introdueix l'horari de l'activitat");
            // horari = teclat.nextLine();
            horari = llegirText();
            
            System.out.println("Introdueix la ciutat de l'activitat");
            // ciutat = teclat.nextLine();
            ciutat = llegirText();
    
            activitat = new ActivitatUnDia(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 
                limitPlaces, preu, dataActivitat, horari, ciutat);
            
            llistaActivitats.afegir(activitat);
            
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return; 
        } catch (ActivitatDuplicadaException | DataFiInscripcioException e) {
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
        boolean datesValides = false;
        boolean dataIniciActivitatValida = false;
        
        try {
            System.out.println("Introdueix el nom de l'activitat");
            // nom = teclat.nextLine();
            nom = llegirText();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            boolean validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[0] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[0] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per PTGAS?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[1] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[1] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per Estudiants?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[2] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[2] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            do {
                System.out.println("Inici d'inscripcions?");
                dataIniciInscripcio = llegirData();
                
                System.out.println("Fi d'inscripcions?");
                dataFiInscripcio = llegirData();
                
                if (!dataIniciInscripcio.esAnterior(dataFiInscripcio)) {
                    System.out.println("Error: La data de fi d'inscripció ha de ser posterior a la d'inici.");
                    System.out.println("Si us plau, torna a introduir les dates.");
                } else {
                    datesValides = true;
                }
                
            } while (!datesValides);
            
            System.out.println("Introdueix el nombre de places de l'activitat");
            // limitPlaces = Integer.parseInt(teclat.nextLine);
            limitPlaces = llegirEnter();
            
            System.out.println("Introdueix el preu de l'activitat");
            // preu = Double.parseDouble(teclat.nextLine());
            preu = llegirDouble();

            do {
                System.out.println("Quin és el primer dia que es fa l'activitat?");
                dataInici = llegirData();
                
                if (!dataFiInscripcio.esAnterior(dataInici)) {
                    System.out.println("Error: La data del primer dia en què es fa l'activitat ha de ser posterior a la data de fi d'inscripció.");
                    System.out.println("Si us plau, torna a introduir la data.");
                } else {
                    dataIniciActivitatValida = true;
                }
                
            } while (!dataIniciActivitatValida);

            System.out.println("Introdueix el nombre de setmanes que es fa l'activitat");
            // numSetmanes = Integer.parseInt(teclat.nextLine());
            numSetmanes = llegirEnter();
    
            System.out.println("Introdueix l'horari de l'activitat");
            // horari = teclat.nextLine();
            horari = llegirText();
            
            System.out.println("Introdueix el nom del centre on es fa l'activitat");
            // nomCentre = teclat.nextLine();
            nomCentre = llegirText();

            System.out.println("Introdueix la ciutat de l'activitat");
            // ciutat = teclat.nextLine();
            ciutat = llegirText();
    
            activitat = new ActivitatPeriodica(nom, collectius, dataIniciInscripcio, dataFiInscripcio, limitPlaces, 
                preu, horari, dataInici, numSetmanes, nomCentre, ciutat);
            
            llistaActivitats.afegir(activitat);
            
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return; 
        } catch (ActivitatDuplicadaException | DataFiInscripcioException e) {
            System.out.println(e);
        }
    }

    private static void opcio15(){
        String nom;
        boolean[] collectius = new boolean[3];
        Activitat activitat = null;
        Data dataIniciInscripcio = null;
        Data dataFiInscripcio = null;
        Data dataActivitat = null;
        int periodeVisualitzacio;
        String enllac;
        boolean datesValides = false;
        boolean dataIniciActivitatValida = false;
       
        try {
            System.out.println("Introdueix el nom de l'activitat");
            // nom = teclat.nextLine();
            nom = llegirText();
            System.out.println("Colectius (respon 0 per false, 1 per true)"); 
            System.out.println("És per PDI?");
            boolean validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[0] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[0] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per PTGAS?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[1] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[1] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }
            
            System.out.println("És per Estudiants?");
            validInput = false;
            while (!validInput) {
                try {
                    int input = Integer.parseInt(llegirText());
                    if (input == 0) {
                        collectius[2] = false;
                        validInput = true;
                    } else if (input == 1) {
                        collectius[2] = true;
                        validInput = true;
                    } else {
                        System.out.println("Entrada incorrecta. Respon 0 o 1.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Respon 0 o 1.");
                }
            }

            do {
                System.out.println("Inici d'inscripcions?");
                dataIniciInscripcio = llegirData();
                
                System.out.println("Fi d'inscripcions?");
                dataFiInscripcio = llegirData();
                
                if (!dataIniciInscripcio.esAnterior(dataFiInscripcio)) {
                    System.out.println("Error: La data de fi d'inscripció ha de ser posterior a la d'inici.");
                    System.out.println("Si us plau, torna a introduir les dates.");
                } else {
                    datesValides = true;
                }
                
            } while (!datesValides);

            do {
                    System.out.println("Quan comença l'activitat?");
                    dataActivitat = llegirData();
                    
                    if (!dataFiInscripcio.esAnterior(dataActivitat)) {
                        System.out.println("Error: La data en què comença l'activitat ha de ser posterior a la data de fi d'inscripció");
                        System.out.println("Si us plau, torna a introduir la data.");
                    } else {
                        dataIniciActivitatValida = true;
                    }
                    
                } while (!dataIniciActivitatValida);
            
            System.out.println("Introdueix el nombre de dies que estarà disponible l'activitat");
            // periodeVisualitzacio = Integer.parseInt(teclat.nextLine());
            periodeVisualitzacio = llegirEnter();
            
            System.out.println("Introdueix l'enllac de l'activitat");
            // enllac = teclat.nextLine();
            enllac = llegirText();
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return; 
        }
        
        try {
            activitat = new ActivitatOnline(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 
                dataActivitat, periodeVisualitzacio, enllac);
        } catch (DataFiInscripcioException e) {
            System.out.println(e.getMessage());
        }
                
        try {
            llistaActivitats.afegir(activitat);
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e);
        }
    }

    
    
    public static void opcio16(){
        try {
            System.out.println("Introdueix el nom de l'activitat a valorar:");
            // String nomActivitat = teclat.nextLine();
            String nomActivitat = llegirText();
            Activitat activitat = llistaActivitats.buscar(nomActivitat);
            if (activitat == null) System.out.println("Activitat no trobada.");
            else {
                System.out.println("Introdueix el nom de l'usuari que valora:");
                // String nomUsuari = teclat.nextLine();
                String nomUsuari = llegirText();
                if (!activitat.teUsuariInscrit(nomUsuari)) System.out.println("Usuari no inscrit.");
                else {
                    if (activitat.haAcabat(avui)){
                        try {
                            Usuari usuari = activitat.getLlistaInscripcions().getLlistaInscrits().buscar(nomUsuari);
                            System.out.println("Introdueix la valoració (1-10 estrelles):");
                            int valoracio;
                            do {
                                correcte = false;
                                while (!correcte) {
                                    try {
                                        // valoracio = Integer.parseInt(teclat.nextLine());
                                        valoracio = llegirEnter();
                                        if (valoracio < 1 || valoracio > 10) {
                                            System.out.println("Valoració invàlida. Ha de ser entre 1 i 10. Torna-ho a intentar:");
                                        } else {
                                            correcte = true;
                                            activitat.afegirValoracio(avui, usuari, valoracio);
                                            System.out.println("Valoració afegida.");
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Entrada incorrecta. La valoració ha de ser un nombre entre 1 i 10. Torna-ho a intentar:");
                                    }
                                }
                            } while (!correcte);
                            
                            // activitat.afegirValoracio(avui, usuari, valoracio);
                            // System.out.println("Valoració afegida.");
                        } catch (UsuariDuplicatException e) {
                            System.out.println("L'usuari ja ha valorat aquesta activitat: " + e.getMessage());
                        }
                    } else {
                        System.out.println("L'activitat no ha acabat encara.");
                    }
                }
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
        
    }

    private static void opcio17(){
        Activitat[] activitatsAcabades = llistaActivitats.obtenirAcabades(avui);
        for (int i = 0; i < activitatsAcabades.length; i++){
            System.out.println("MITJANA DE VALORACIONS DE " + activitatsAcabades[i].getNom() + ":\n" + 
            activitatsAcabades[i].getLlistaValoracions().getMitjanaValoracions());
        }
    }

    private static void opcio18(){
        try {
            System.out.println("Introdueix l'àlies de l'usuari per veure les seves valoracions:");
            // String alies = teclat.nextLine();
            String alies = llegirText();
            Usuari usuari = baseDadesUsuaris.buscar(alies);

            if (usuari!= null) {
                System.out.println("Valoracions fetes per l'usuari " + alies + ":\n");
                boolean teValoracions = false;
                Activitat[] totesLesActivitats = llistaActivitats.obtenirTotes();
                for (Activitat activitat : totesLesActivitats) {
                    if (activitat.getLlistaValoracions().teValoracioDeUsuari(usuari)) { 
                        int valoracio = activitat.getLlistaValoracions().getValoracioDeUsuari(usuari);
                        System.out.println("- " + activitat.getNom() + ": " + valoracio + " estrelles");
                        teValoracions = true;
                    }
                }

                if (!teValoracions) {
                    System.out.println("No ha fet cap valoració encara.");
                }

            } else {
                System.out.println("Error: No existeix cap usuari amb l'àlies " + alies);
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
        

    }

    private static void opcio19(){
        try {
            System.out.println("Introdueix el nom del col·lectiu (Estudiants / PDI / PTGAS):");
            // String colectiu = teclat.nextLine();
            String colectiu = llegirText();
            double sumaValoracions = 0;
            int totalValoracions = 0;

            Activitat[] totesLesActivitats = llistaActivitats.obtenirTotes();
            for (Activitat activitat : totesLesActivitats) {
                LlistaValoracions llistaValoracions = activitat.getLlistaValoracions();
                for (int i = 0; i < llistaValoracions.getNumValoracions(); i++) {
                    Usuari usuari = llistaValoracions.getUsuari(i);
                    if (usuari.getColectiu().equalsIgnoreCase(colectiu)) {
                        sumaValoracions += llistaValoracions.getValoracioDeUsuari(usuari);
                        totalValoracions++;
                    }
                }
            }
            if (totalValoracions > 0) {
                double mitjana = sumaValoracions / totalValoracions;
                System.out.println("La mitjana de valoracions per al col·lectiu " + colectiu + " és: " + mitjana);
            } else {
                System.out.println("No hi ha valoracions per al col·lectiu " + colectiu);
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
    }

    private static void opcio20(){
        try {
            System.out.println("Introdueix el nom del col·lectiu (Estudiants / PDI / PTGAS):");
            // String colectiu = teclat.nextLine();
            String colectiu = llegirText();
            Usuari usuariMesActiu = null;
            int maxInscripcions = 0;

            for (int i = 0; i < baseDadesUsuaris.getNumUsuaris(); i++) {
                Usuari usuari = baseDadesUsuaris.getUsuari(i);
                if (usuari.getColectiu().equalsIgnoreCase(colectiu)) {
                    int comptadorInscripcions = 0;
                    Activitat[] totesLesActivitats = llistaActivitats.obtenirTotes();
                    for (Activitat activitat : totesLesActivitats) {
                        if (activitat.teUsuariInscrit(usuari.getAlies())) {
                            comptadorInscripcions++;
                        }
                    }
                    if (comptadorInscripcions > maxInscripcions) {
                        maxInscripcions = comptadorInscripcions;
                        usuariMesActiu = usuari;
                    }
                }
            }

            if (usuariMesActiu != null) {
                System.out.println("L'usuari més actiu del col·lectiu " + colectiu + " és " + 
                    usuariMesActiu.getAlies() + " amb " + maxInscripcions + " inscripcions.");
            } else {
                System.out.println("No s'han trobat usuaris per al col·lectiu " + colectiu);
            }
        } catch (CancelarOperacioException e) {
            System.out.println("Creació d'activitat cancel·lada.");
            return;
        }
        
    }

    private static void opcio21(){
        Activitat[] noPeriodeInscripcio = llistaActivitats.obtenirEnNoPeriodeInscripcio(avui);
        for (int i = 0; i < noPeriodeInscripcio.length; i++){
            if (noPeriodeInscripcio[i] instanceof ActivitatOnline && noPeriodeInscripcio[i].getLlistaInscripcions().getNumInscrits() < 20){
                llistaActivitats.eliminar(noPeriodeInscripcio[i].getNom());
                System.out.println("S'ha eliminat l'activitat online " + noPeriodeInscripcio[i].getNom() + " per tenir menys de 20 inscrits.");
            } else if (noPeriodeInscripcio[i].getPercentatgeOcupacio() < 10){
                llistaActivitats.eliminar(noPeriodeInscripcio[i].getNom());
                System.out.println("S'ha eliminat l'activitat " + noPeriodeInscripcio[i].getNom() + " per tenir menys del 10% d'ocupació.");
            }
        }
    }

    private static String llegirText() {
        String input = teclat.nextLine().trim();
        if (input.equalsIgnoreCase("q")) {
            throw new CancelarOperacioException();
        }
        return input;
    }

    private static int llegirEnter() {
        while (true) {
            String input = llegirText(); // ja controla 'q'
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada incorrecta. Introdueix un nombre.");
            }
        }
    }

    private static double llegirDouble() {
        while (true) {
            String input = llegirText();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada incorrecta. Introdueix un nombre decimal.");
            }
        }
    }

    private static Data llegirData(){
        boolean correcte = false;
        Data data = new Data();
        int dia, mes, any;
        while(!correcte){
            System.out.println("Introdueix la data en el següent format DD MM YYYY:");
            String input = llegirText();
            try {
                // String[] parts = teclat.nextLine().split(" ");
                String[] parts = input.split(" ");

                if (parts.length != 3)
                throw new IllegalArgumentException("Format correcte: DD MM YYYY");

                dia = Integer.parseInt(parts[0]);
                if (parts[1].equals("9") | parts[1].equals("09")) mes = 9;
                else if (parts[1].equals("10")) mes = 10;
                else if (parts[1].equals("11")) mes = 11;
                else if (parts[1].equals("12")) mes = 12;
                else throw new IllegalArgumentException("Mes incorrecte. Només de setembre a desembre (9-12).");
                any = Integer.parseInt(parts[2]);

                data = new Data(dia, mes, any);
                correcte = true;
            } catch (InputMismatchException e) {
                System.out.println("S'ha d'entrar la data en nombres enters. " + e);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (DataIncorrectaExcepction e){
                System.out.println("Data incorrecta, torna a intentar-ho. " + e);         
            } catch (Exception e) {
                System.out.println("Data incorrecta, torna a intentar-ho. " + e);
            }
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
            int colectiu;
            while (true) {
                // String input = teclat.nextLine();
                String input = llegirText();
                try {
                    colectiu = Integer.parseInt(input);
                    if (colectiu >= 1 && colectiu <= 3) {
                        break; // Entrada vàlida, sortir del bucle
                    } else {
                        System.out.println("Entrada incorrecta. Torna-ho a intentar.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada incorrecta. Torna-ho a intentar.");
                }
            }
            String adreca;
            switch (colectiu) {
                case 1:
                    try {
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        // adreca = teclat.nextLine();
                        adreca = llegirText();
                        String ensenyament;
                        int anyInici;
                        System.out.println("Introdueix el teu ensenyament");
                        // ensenyament = teclat.nextLine();
                        ensenyament = llegirText();
                        System.out.println("Introdueix el any d'inici del teu ensenyament");
                        // anyInici = Integer.parseInt(teclat.nextLine());
                        anyInici = llegirEnter();
                        usuari = new Estudiant(nom, adreca, ensenyament, anyInici);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    } catch (NumberFormatException e) {
                        System.out.println("Any d'inici invàlid. Torna-ho a intentar.");
                    } catch (Exception e) {
                        System.out.println("Error inesperat. " + e);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        // adreca = teclat.nextLine();
                        adreca = llegirText();
                        String departament, campus;
                        System.out.println("Introdueix el teu departament");
                        // departament = teclat.nextLine();
                        departament = llegirText();
                        System.out.println("Introdueix el teu campus");
                        // campus = teclat.nextLine();
                        campus = llegirText();
                        usuari = new Pdi(nom, adreca, departament, campus);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    } catch (Exception e) {
                        System.out.println("Error inesperat. " + e);
                    }
                    break;
                case 3:
                    try {
                        // System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        // adreca = teclat.nextLine();
                        System.out.println("Introdueix la teva adreça (només el d'abans de \"@\" )");
                        // adreca = teclat.nextLine();
                        adreca = llegirText();
                        String campus;
                        System.out.println("Introdueix el teu campus");
                        // campus = teclat.nextLine();
                        campus = llegirText();
                        usuari = new Ptgas(nom, adreca, campus);
                        baseDadesUsuaris.afegir(usuari);
                        correcte = true;
                    } catch (UsuariDuplicatException e) {
                        System.out.println("Error inesperat. " + e);    //Ja hem comprovat que no estarà duplicat
                    } catch (Exception e) {
                        System.out.println("Error inesperat. " + e);
                    }
                    break;
            
                default:
                    break;
            }
        }
        return usuari;
    
    }

    private static void guardarLlistaActivitats(String nomFitxer) {
        try{ 
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFitxer)); 
            oos.writeObject(llistaActivitats); // Guarda la lista completa
            oos.close();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void guardarBaseDadesUsuaris(String nomFitxer) {
        try{ 
            BufferedWriter w = new BufferedWriter(new FileWriter(nomFitxer));
            for (int i = 0; i < baseDadesUsuaris.getNumUsuaris(); i++){
                w.append(baseDadesUsuaris.getUsuari(i).toCSV());
            }
            w.close();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public static LlistaActivitats carregarLlistaActivitats(String nomFitxer) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFitxer))) {
            return (LlistaActivitats) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error carregant llista d'activitats, es crearà una llista buida. " + e);
            return new LlistaActivitats();
        }
    }

    public static LlistaUsuaris carregarBaseDadesUsuaris(String nomFitxer) {
        try{ 
            Scanner f = new Scanner(new File(nomFitxer));
            f.useDelimiter(";");
            LlistaUsuaris llistaUsuaris = new LlistaUsuaris();
            String[] linia;
            String tipusUsuari;
            while (f.hasNext()){
                try {
                    linia = f.nextLine().split(";");
                    tipusUsuari = linia[0];
                    if (tipusUsuari.equals(Usuari.COLECTIU_ESTUDIANTS)){
                        llistaUsuaris.afegir(new Estudiant(linia[1], linia[2], linia[3], Integer.parseInt(linia[4])));
                    } else if (tipusUsuari.equals(Usuari.COLECTIU_PDI)){
                        llistaUsuaris.afegir(new Pdi(linia[1], linia[2], linia[3], linia[4]));
                    } else if (tipusUsuari.equals(Usuari.COLECTIU_PTGAS)){
                        llistaUsuaris.afegir(new Ptgas(linia[1], linia[2], linia[3]));
                    } else{
                        System.out.println("Error afegint usuari: No existeix el col·lectiu " + tipusUsuari);
                    }
                } catch (UsuariDuplicatException e) {
                    System.out.println("Usuari duplicat en fitxer. " + e.getMessage());
                }
            }
            f.close();
            return llistaUsuaris;
        } catch (IOException e) {
            System.out.println("Error llegint base de usuaris." + e);
            return new LlistaUsuaris();
        }
    }
}