package proves;

import dades.activitats.*;
import dades.excepcions.DataIncorrectaExcepction;
import dades.excepcions.ActivitatDuplicadaException;
import dades.Data;

public class ProvesLlistaActivitat {
    public static void main(String[] args) {
        System.out.println("ProvesLlistaActivitat");
        
        LlistaActivitats llistaActivitats = new LlistaActivitats();
        System.out.println("Llista d'activitats inicialment:");
        System.out.println(llistaActivitats);
        Activitat[] activitats = null;
        boolean[] collectiuGeneral = {true, true, true};
        try {
            activitats = new Activitat[] {
                new ActivitatOnline(
                    "Classe Catala Online",
                    collectiuGeneral, 
                    new Data(9, 9, 2025),
                    new Data(10, 9, 2025),
                    new Data(15, 9, 2025),
                    30,
                    "https://meet.google.com/liliput"),

                new ActivitatOnline(
                    "Classe Catala Online",
                    new boolean[]{true, false, true}, 
                    new Data(9, 10, 2025),
                    new Data(10, 10, 2025),
                    new Data(15, 10, 2025),
                    30,
                    "https://meet.google.com/goblin-Griphook"),

                new ActivitatPeriodica(
                    "Entrenament Futbol", 
                    collectiuGeneral,
                    new Data(1, 9, 2025),
                    new Data(10, 9, 2025),
                    20,
                    100.0,
                    "10:00-12:00",
                    new Data(15, 9, 2025),
                    3,
                    "URV",
                    "Tarragona"),

                new ActivitatPeriodica(
                    "Classe de Angles", 
                    collectiuGeneral,
                    new Data(1, 9, 2025),
                    new Data(10, 9, 2025),
                    20,
                    100.0,
                    "10:00-12:00",
                    new Data(15, 9, 2025),
                    11,
                    "URV",
                    "Tarragona"),

                new ActivitatUnDia(
                    "Pataquers al campus Catalunya",
                    collectiuGeneral,
                    new Data(5, 9, 2025),
                    new Data(20, 9, 2025),
                    30,
                    10.0,
                    new Data(25, 9, 2025),
                    "10:00 - 14:00",
                    "Tarragona"),

                new ActivitatUnDia(
                    "Visita al museu d'art",
                    collectiuGeneral,
                    new Data(5, 10, 2025),
                    new Data(20, 10, 2025),
                    30,
                    15.0,
                    new Data(25, 10, 2025),
                    "10:00 - 14:00",
                    "Barcelona")

            };

        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Afegeix una activitat a la llista mantenint l'ordre alfabètic per nom desde activitat
        for (Activitat act : activitats) {
            try {
                llistaActivitats.afegir(act);
            } catch (ActivitatDuplicadaException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // Afegeix una activitat a la llista mantenint l'ordre alfabètic per nom des de paràmetres
        try {
            llistaActivitats.afegir(new ActivitatOnline(
                "Classe Castella Online",
                collectiuGeneral, 
                new Data(9, 9, 2025),
                new Data(10, 9, 2025),
                new Data(15, 9, 2025),
                30,
                "https://meet.google.com/adabakedabra"));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        } catch (ActivitatDuplicadaException e) {
            System.out.println(e.getMessage());
        }

        // Busca una activitat per nom (cerca binària)
        Activitat activitatBuscada = llistaActivitats.buscar("Classe Catala Online");
        if (activitatBuscada != null) {
            System.out.println("Activitat trobada: " + activitatBuscada.getNom());
        } else {
            System.out.println("Activitat no trobada");
        }

        
        // Elimina una activitat de la llista
        if (llistaActivitats.eliminar("Classe Castella Online")) {
            System.out.println("Activitat eliminada correctament");
        } else {
            System.out.println("No s'ha pogut eliminar l'activitat");
        }

        // Comprova si la llista està buida
        if (llistaActivitats.esBuida()) {
            System.out.println("La llista està buida");
        } else {
            System.out.println("La llista no està buida");
        }
        
        // Obte el nombre d'activitats a la llista
        System.out.println("Nombre d'activitats: " + llistaActivitats.getNumActivitats());

        // Obté activitats que ja han acaba
        try {
            System.out.println("Activitats que ja han acabat:" + llistaActivitats.obtenirAcabades(new Data(30, 9, 2025)));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Obté activitats actives
        try {
            System.out.println("Activitats actives:" + llistaActivitats.obtenirActives(new Data(10, 9, 2025)));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Obté activitats que tenen classe avui
        try {
            System.out.println("Activitats que tenen classe avui:" + llistaActivitats.obtenirAmbClasseAvui(new Data(10, 9, 2025)));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Obté activitats amb places disponibles
        System.out.println("Activitats amb places disponibles:" + llistaActivitats.obtenirAmbPlaces());

        // Obté activitats que no estan en període d'inscripció
        try {
            System.out.println("Activitats fora de període d'inscripció:" + llistaActivitats.obtenirEnNoPeriodeInscripcio(new Data(20, 9, 2025)));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Obté activitats que estan en període d'inscripció
        try {
            System.out.println("Activitats en període d'inscripció:" + llistaActivitats.obtenirEnPeriodeInscripcio(new Data(10, 9, 2025)));
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // Retorna subllista per tipus d'activitat
        System.out.println("Subllista d'activitats Online:" + llistaActivitats.obtenirPerTipus("Online"));
        
        // Obté totes les activitats
        System.out.println("Totes les activitats:" + llistaActivitats.obtenirTotes());
        
        // Mostra la llista d'activitats
        System.out.println("Llista d'activitats final:");
        System.out.println(llistaActivitats);

    }
}
