package dades.inscripcions;

import dades.usuaris.Usuari;

/**
 * Classe Pare Usuari
 * @author PROG4 - Tiago Amarelle Rodrigues
 */

public class LlistaEspera {
    Usuari[] llista;
    int numUsuaris;

    public LlistaEspera(){
        numUsuaris = 0;
        llista = new Usuari[10]; 
    }

    /**
     * Mètode que afegeix un usuari a la cua d'espera
     * @param usuari no nul
     */
    public void afegir(Usuari usuari){
        if (usuari != null && numUsuaris <= llista.length){ 
            llista[numUsuaris] = usuari.copia();
            numUsuaris++;
        } 
    }

    /**
     * Mètode que retorna (i treu) al primer de la cua 
     * @return primer de la cua
     */
    public Usuari treurePrimer(){
        if (numUsuaris != 0){
            Usuari temp = llista[0];
            for (int i = 0; i < numUsuaris; i++){
                llista[i] = llista[i+1];
            }
            llista[numUsuaris-1] = null;    //no cal, però es posa per cura
            numUsuaris--;
            return temp;
        }
        else return null; //temporal, encara no sé fer exceptions
    }

    @Override
    public String toString() {
        String temp = "LLISTA ESPERA amb " + numUsuaris + " usuaris:" + "\n";
        for(int i = 0; i < numUsuaris; i++){
            temp = temp + llista[i].toString();
        }
        return temp;
    }
}