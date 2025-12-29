package dades.inscripcions;
import dades.usuaris.LlistaEspera;
import dades.usuaris.LlistaUsuaris;
import dades.usuaris.Usuari;
import dades.excepcions.UsuariDuplicatException;

/**
 * Llista d'inscripcions que s'inclourà dins de cada activitat. 
 * @author PROG4 - Tiago Amarelle Rodrigues
 */
public class LlistaInscripcions{
    private LlistaUsuaris inscrits;
    private LlistaEspera espera;
    int numPlaces;

    public LlistaInscripcions(){    // Constructor amb infinites places
        inscrits = new LlistaUsuaris();
        numPlaces = 0;
    }

    public LlistaInscripcions(int numPlaces){   // Constructor amb places finites
        inscrits = new LlistaUsuaris();
        this.numPlaces = numPlaces;
        espera = new LlistaEspera();
    }
    
    public void afegir(Usuari usuari) throws UsuariDuplicatException{
        if (usuari != null && (inscrits.getNumUsuaris() < numPlaces || numPlaces == 0)){
            inscrits.afegir(usuari);
        }
        else if (!espera.estaPlena()){
            espera.afegir(usuari);
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
