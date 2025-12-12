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
    public LlistaInscripcions (){
        inscrits = new LlistaUsuaris();
        numPlaces = 0;
    }

    public LlistaInscripcions(int numPlaces){
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

    

}
