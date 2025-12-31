package dades.activitats;

import java.io.Serializable;

import dades.Data;
import dades.excepcions.ActivitatDuplicadaException;

/**
 * Llista d'activitats ordenada per nom per facilitar les cerques
 * @author PROG3 - Andriy Rudyy
 */

public class LlistaActivitats implements Serializable{
    private Activitat[] llista;
    private int numActivitats;
    private static final int MIDA_INICIAL = 50;
    
    /**
     * Constructor de LlistaActivitats
     */
    public LlistaActivitats() {
        llista = new Activitat[MIDA_INICIAL];
        numActivitats = 0;
    }
    
    /**
     * Redimensiona l'array quan està ple
     */
    private void redimensionar() {
        Activitat[] novaLlista = new Activitat[llista.length * 2];
        for (int i = 0; i < numActivitats; i++) {
            novaLlista[i] = llista[i];
        }
        llista = novaLlista;
    }
    
    /**
     * Afegeix una activitat a la llista mantenint l'ordre alfabètic per nom
     * @param activitat Activitat a afegir
     * @throws ActivitatDuplicadaException si l'activitat ja existeix
     */
    public void afegir(Activitat activitat) throws ActivitatDuplicadaException {
        if (activitat == null) return;
        
        // Comprovar si ja existeix
        if (buscar(activitat.getNom()) != null) {
            throw new ActivitatDuplicadaException("L'activitat " + activitat.getNom() + " ja existeix");
        }
        
        // Redimensionar si cal
        if (numActivitats == llista.length) {
            redimensionar();
        }
        
        // Trobar posició d'inserció mantenint ordre alfabètic
        int pos = 0;
        while (pos < numActivitats && llista[pos].getNom().compareTo(activitat.getNom()) < 0) {
            pos++;
        }
        
        // Desplaçar elements cap a la dreta
        for (int i = numActivitats; i > pos; i--) {
            llista[i] = llista[i - 1];
        }
        
        // Inserir l'activitat
        llista[pos] = activitat.copia();
        numActivitats++;
    }
    
    /**
     * Busca una activitat per nom (cerca binària)
     * @param nom Nom de l'activitat
     * @return Activitat trobada o null si no existeix
     */
    public Activitat buscar(String nom) {
        int esquerra = 0;
        int dreta = numActivitats - 1;
        
        while (esquerra <= dreta) {
            int mig = (esquerra + dreta) / 2;
            int comparacio = llista[mig].getNom().compareTo(nom);
            
            if (comparacio == 0) {
                return llista[mig];
            } else if (comparacio < 0) {
                esquerra = mig + 1;
            } else {
                dreta = mig - 1;
            }
        }
        return null;
    }
    
    /**
     * Elimina una activitat de la llista
     * @param nom Nom de l'activitat a eliminar
     * @return true si s'ha eliminat, false si no existia
     */
    public boolean eliminar(String nom) {
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].getNom().equals(nom)) {
                // Desplaçar elements cap a l'esquerra
                for (int j = i; j < numActivitats - 1; j++) {
                    llista[j] = llista[j + 1];
                }
                llista[numActivitats - 1] = null;
                numActivitats--;
                return true;
            }
        }
        return false;
    }
    
    /**
     * Obté totes les activitats
     * @return Array d'activitats
     */
    public Activitat[] obtenirTotes() {
        Activitat[] resultat = new Activitat[numActivitats];
        for (int i = 0; i < numActivitats; i++) {
            resultat[i] = llista[i];
        }
        return resultat;
    }
    
    /**
     * Obté activitats que estan en període d'inscripció
     * @param dataAvui Data actual
     * @return Array d'activitats en període d'inscripció
     */
    public Activitat[] obtenirEnPeriodeInscripcio(Data dataAvui) {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].estaEnPeriodeInscripcio(dataAvui)) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].estaEnPeriodeInscripcio(dataAvui)) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }
    
    /**
     * Obté activitats que no estan en període d'inscripció
     * @param dataAvui Data actual
     * @return Array d'activitats que no estan en període d'inscripció
     */
    public Activitat[] obtenirEnNoPeriodeInscripcio(Data dataAvui) {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (!llista[i].estaEnPeriodeInscripcio(dataAvui)) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (!llista[i].estaEnPeriodeInscripcio(dataAvui)) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }

    /**
     * Obté activitats que tenen classe avui
     * @param dataAvui Data actual
     * @return Array d'activitats amb classe avui
     */
    public Activitat[] obtenirAmbClasseAvui(Data dataAvui) {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].teClasseAvui(dataAvui)) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].teClasseAvui(dataAvui)) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }
    
    /**
     * Obté activitats actives
     * @param dataAvui Data actual
     * @return Array d'activitats actives
     */
    public Activitat[] obtenirActives(Data dataAvui) {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].estaActiva(dataAvui)) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].estaActiva(dataAvui)) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }
    
    /**
     * Obté activitats amb places disponibles
     * @return Array d'activitats amb places
     */
    public Activitat[] obtenirAmbPlaces() {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].hiHaPlacesDisponibles()) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].hiHaPlacesDisponibles()) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }
    
    /**
     * Obté activitats que ja han acabat
     * @param dataAvui Data actual
     * @return Array d'activitats acabades
     */
    public Activitat[] obtenirAcabades(Data dataAvui) {
        int count = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].haAcabat(dataAvui)) count++;
        }
        
        Activitat[] resultat = new Activitat[count];
        int index = 0;
        for (int i = 0; i < numActivitats; i++) {
            if (llista[i].haAcabat(dataAvui)) {
                resultat[index++] = llista[i];
            }
        }
        return resultat;
    }
    
    public int getNumActivitats() { return numActivitats; }
    
    public boolean esBuida() { return numActivitats == 0; }
    
    @Override
    public String toString() {
        String info = "LLISTA ACTIVITATS amb " + numActivitats + " activitats:\n";
        for (int i = 0; i < numActivitats; i++) {
            info += llista[i].toString() + "\n";
        }
        return info;
    }
}