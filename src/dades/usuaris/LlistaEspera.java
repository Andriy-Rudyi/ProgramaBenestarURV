package dades.usuaris;

import java.io.Serializable;

import dades.excepcions.UsuariDuplicatException;

/**
 * Llista d'espera per a una activitat
 * @author PROG4 - Tiago Amarelle Rodrigues
 */

public class LlistaEspera implements Serializable{
    Usuari[] llista;
    int numUsuaris;
    private static final int MAX_ESPERA = 10;

    /**
     * Constructor de LlistaEspera
     */
    public LlistaEspera(){
        numUsuaris = 0;
        llista = new Usuari[MAX_ESPERA]; 
    }

    /**
     * Afegeix un usuari a la cua d'espera (FIFO)
     * @param usuari Usuari a afegir (no nul)
     * @return true si s'ha afegit, false si la llista està plena o usuari és null
     */
    public boolean afegir(Usuari usuari) throws UsuariDuplicatException{
        if (usuari == null || numUsuaris >= MAX_ESPERA) {
            return false;
        }
        if (buscar(usuari.getAlies()) != null){ 
            throw new UsuariDuplicatException("L'usuari amb alias " + usuari.getAlies() + " ja existeix");
        } else {
            llista[numUsuaris] = usuari.copia();
            numUsuaris++;
            return true;
        }
    }

    /**
     * Busca un usuari per alias (cerca binària aprofitant l'ordenació)
     * @param alies Alias a buscar
     * @return Usuari trobat o null si no existeix
     */
    public Usuari buscar(String alies) {
        // null check
        if (alies == null) {
            return null;
        }

        int esquerra = 0;
        int dreta = numUsuaris - 1;
        
        while (esquerra <= dreta) {
            int mig = (esquerra + dreta) / 2;
            int comparacio = llista[mig].getAlies().compareTo(alies);
            
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
     * Retorna i treu el primer de la cua
     * @return Primer usuari de la cua (o null si està buida)
     */
    public Usuari treurePrimer() {
        if (numUsuaris == 0) {
            return null;
        }
        
        Usuari primer = llista[0];

        for (int i = 0; i < numUsuaris - 1; i++) {
            llista[i] = llista[i + 1];
        }
        llista[numUsuaris - 1] = null;
        numUsuaris--;
        
        return primer;
    }

    /**
     * Elimina un usuari concret de la llista d'espera
     * @param usuari Usuari a eliminar
     * @return true si s'ha eliminat, false si no existia
     */
    public boolean eliminar(String alies) {
        if (alies == null) return false;
        
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i].getAlies().equals(alies)) {
                // Desplaçar elements cap a l'esquerra
                for (int j = i; j < numUsuaris - 1; j++) {
                    llista[j] = llista[j + 1];
                }
                llista[numUsuaris - 1] = null;
                numUsuaris--;
                return true;
            }
        }
        return false;
    }

    /**
     * Obté una còpia de la llista d'espera
     * @return Array amb els usuaris en espera
     */
    public Usuari[] getLlistaEspera() {
        Usuari[] copia = new Usuari[numUsuaris];
        for (int i = 0; i < numUsuaris; i++) {
            copia[i] = llista[i];
        }
        return copia;
    }
    
    /**
     * Obté el nombre d'usuaris en espera
     * @return Nombre d'usuaris
     */
    public int getNumUsuaris() {
        return numUsuaris;
    }
    
    /**
     * Comprova si la llista d'espera està plena
     * @return true si està plena (10 persones)
     */
    public boolean estaPlena() {
        return numUsuaris >= MAX_ESPERA;
    }

    /**
     * Comprova si la llista d'espera està buida
     * @return true si està buida
     */
    public boolean esBuida() {
        return numUsuaris == 0;
    }

    /**
     * Comprova si un usuari està a la llista d'espera
     * @param usuari Usuari a buscar
     * @return true si l'usuari hi és
     */
    public boolean hiEs(Usuari usuari) {
        if (usuari == null) return false;
        
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i].equals(usuari)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obté la posició d'un usuari a la llista d'espera
     * @param usuari Usuari a buscar
     * @return Posició (1-10) o -1 si no hi és
     */
    public int getPosicio(Usuari usuari) {
        if (usuari == null) return -1;
        
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i].equals(usuari)) {
                return i + 1;
            }
        }
        return -1;
    }

    /**
     * Crea una còpia de la llista espera
     * @return Nova instància amb les mateixes dades
     */
    public LlistaEspera copia() {
        LlistaEspera copia = new LlistaEspera();
        try {
            for (int i = 0; i < numUsuaris; i++){
            copia.afegir(llista[i].copia());
            }
        } catch (UsuariDuplicatException e) {
            throw new IllegalStateException("Error intern copiant LlistaEspera");
        }
        
        return copia;
    }
    
    @Override
    public String toString() {
        if (numUsuaris == 0) {
            return "Llista d'espera buida";
        }
        
        String info = "LLISTA ESPERA amb " + numUsuaris + " usuaris:\n";
        for (int i = 0; i < numUsuaris; i++) {
            info += "  " + (i + 1) + ". " + llista[i].getAlies() + "\n";
        }
        return info;
    }

}