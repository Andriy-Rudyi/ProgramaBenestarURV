package dades.inscripcions;
import dades.usuaris.LlistaEspera;
import dades.usuaris.LlistaUsuaris;
import dades.usuaris.Usuari;

import java.io.Serializable;

import dades.excepcions.UsuariDuplicatException;

/**
 * Llista d'inscripcions que s'inclourà dins de cada activitat. 
 * @author PROG4 - Tiago Amarelle Rodrigues
 */
public class LlistaInscripcions implements Serializable{
    private static final long serialVersionUID = 1L;
    private LlistaUsuaris inscrits;
    private LlistaEspera espera;
    int numPlaces;

    // public LlistaInscripcions(){    
    //     inscrits = new LlistaUsuaris();
    //     numPlaces = 0;
    // }

    public LlistaInscripcions(int numPlaces){
        if (numPlaces > 0){                                     // Constructor amb places limitades
            inscrits = new LlistaUsuaris();         
            this.numPlaces = numPlaces;
            espera = new LlistaEspera();
        } else if (numPlaces == 0) {                            // Constructor amb infinites places
            inscrits = new LlistaUsuaris();
            this.numPlaces = 0;
        } else {
            throw new IllegalArgumentException("El número de places no pot ser negatiu");
        }
    }

    public boolean afegir(Usuari usuari) throws UsuariDuplicatException{
        if (inscrits.buscar(usuari.getAlies()) != null ) // Per evitar inscriure a la llista d'espera si ja es troba a inscrits
            throw new UsuariDuplicatException("L'usuari amb alias " + usuari.getAlies() + " ja existeix");
        if (usuari != null && (inscrits.getNumUsuaris() < numPlaces || numPlaces == 0)){
            inscrits.afegir(usuari);
            return true;
        } else if (!espera.estaPlena()){     
            espera.afegir(usuari);
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminar(String nom){
        if (!inscrits.eliminar(nom)){ 
            if (espera.eliminar(nom)) return true;
            return false;
        } else {
            try {
                if(inscrits.afegir(espera.treurePrimer())) return true;
            } catch (UsuariDuplicatException e) {
                throw new IllegalStateException("Error intern, s'intenta passar usuari a inscrits quan ja s'hi trobava.");
            }
        }
        return false;
    }

    public int getNumInscrits(){
        return inscrits.getNumUsuaris();
    }

    public int getNumPlaces(){
        return numPlaces;
    }

    public int getUsuarisEnEspera(){
        return espera.getNumUsuaris();
    }

    public boolean teUsuarisEnEspera(){
        if (numPlaces == 0) return false; //És online
        return !espera.esBuida();
    }

    public boolean teUsuariInscrit(String nom){
        return inscrits.buscar(nom) != null;
    }

    public LlistaUsuaris getLlistaInscrits() {
    return inscrits;
    }


    public LlistaEspera getLlistaEspera() {
    return espera;
    }

    public LlistaInscripcions copia() {
        LlistaInscripcions duplicat = new LlistaInscripcions(numPlaces);
        duplicat.inscrits = inscrits.copia();
        if (numPlaces != 0) duplicat.espera = espera.copia();
        return duplicat;
    }

}
