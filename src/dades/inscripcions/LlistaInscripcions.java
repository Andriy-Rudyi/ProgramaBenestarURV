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
        } else {                                                // TODO Excecio
            System.out.println("El número de places no pot ser negatiu");
        }
    }

    public boolean afegir(Usuari usuari) throws UsuariDuplicatException{
        if (usuari != null && (inscrits.getNumUsuaris() < numPlaces || numPlaces == 0)){
            inscrits.afegir(usuari);
            return true;
        }
        else if (!espera.estaPlena()){
            espera.afegir(usuari);
            return true;
        } else {
            return false;
        }
    }

    public void eliminar(String nom) throws UsuariDuplicatException{
        if (!inscrits.eliminar(nom)){ 
            espera.eliminar(nom);
            inscrits.afegir(espera.treurePrimer());
        }
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

}
