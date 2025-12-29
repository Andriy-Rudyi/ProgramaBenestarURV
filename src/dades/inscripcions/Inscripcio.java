
package dades.inscripcions;

import dades.activitats.Activitat;
import dades.usuaris.Usuari;

/**
 * Representa una inscripció d'un usuari a una activitat
 * 
 * @author PROG2 - Felipe
 */
public class Inscripcio {
    private Usuari usuari;
    private Activitat activitat;
    private boolean estaEnLlistaEspera;

    /**
     * Constructor d'Inscripcio
     * @param usuari Usuari que es vol inscriure
     * @param activitat Activitat a la qual s'inscriu
     */
    public Inscripcio(Usuari usuari, Activitat activitat) {
        this.usuari = usuari;
        this.activitat = activitat;
        this.estaEnLlistaEspera = false;
    }
    
    /**
     * Constructor per inscripcions en llista d'espera
     * @param usuari Usuari en espera
     * @param activitat Activitat
     * @param enEspera true si està en llista d'espera
     */
    public Inscripcio(Usuari usuari, Activitat activitat, boolean enEspera) {
        this.usuari = usuari;
        this.activitat = activitat;
        this.estaEnLlistaEspera = enEspera;
    }

    // Getters
    public Usuari getUsuari() {
        return usuari;
    }

    public Activitat getActivitat() {
        return activitat;
    }
    
    public boolean estaEnLlistaEspera() {
        return estaEnLlistaEspera;
    }

    // Setters
    public void setEstaEnLlistaEspera(boolean enEspera) {
        this.estaEnLlistaEspera = enEspera;
    }

    @Override
    public String toString() {
        if (estaEnLlistaEspera) {
            return usuari.getAlies() + " -> " + activitat.getNom() + " [EN LLISTA D'ESPERA]";
        } else {
            return usuari.getAlies() + " -> " + activitat.getNom();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Inscripcio)) return false;
        
        Inscripcio altra = (Inscripcio) obj;
        return this.usuari.equals(altra.usuari) && 
               this.activitat.getNom().equals(altra.activitat.getNom());
    }
}