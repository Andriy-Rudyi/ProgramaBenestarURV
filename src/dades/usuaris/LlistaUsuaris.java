package dades.usuaris;

import dades.excepcions.UsuariDuplicatException;

/**
 * Llista d'usuaris ordenada per alias per facilitar les cerques
 * @author PROG1 - Pau Font
 */
public class LlistaUsuaris {
    private Usuari[] llista;
    private int numUsuaris;
    private static final int MIDA_INICIAL = 50;
    
    /**
     * Constructor de LlistaUsuaris
     */
    public LlistaUsuaris() {
        llista = new Usuari[MIDA_INICIAL];
        numUsuaris = 0;
    }
    
    /**
     * Redimensiona l'array quan està ple
     */
    private void redimensionar() {
        Usuari[] novaLlista = new Usuari[llista.length * 2];
        for (int i = 0; i < numUsuaris; i++) {
            novaLlista[i] = llista[i];
        }
        llista = novaLlista;
    }
    
    /**
     * Afegeix un usuari a la llista mantenint l'ordre alfabètic per alias
     * @param usuari Usuari a afegir
     * @throws UsuariDuplicatException si l'usuari ja existeix
     */
    public void afegir(Usuari usuari) throws UsuariDuplicatException {
        if (usuari == null) return;
        
        // Comprovar si ja existeix
        if (buscar(usuari.getAlies()) != null) {
            throw new UsuariDuplicatException("L'usuari amb alias " + usuari.getAlies() + " ja existeix");
        }
        
        // Redimensionar si cal
        if (numUsuaris == llista.length) {
            redimensionar();
        }
        
        // Trobar posició d'inserció mantenint ordre alfabètic
        int pos = 0;
        while (pos < numUsuaris && llista[pos].getAlies().compareTo(usuari.getAlies()) < 0) {
            pos++;
        }
        
        // Desplaçar elements cap a la dreta
        for (int i = numUsuaris; i > pos; i--) {
            llista[i] = llista[i - 1];
        }
        
        // Inserir l'usuari
        llista[pos] = usuari.copia();
        numUsuaris++;
    }
    
    /**
     * Busca un usuari per alias (cerca binària aprofitant l'ordenació)
     * @param alies Alias a buscar
     * @return Usuari trobat o null si no existeix
     */
    public Usuari buscar(String alies) {
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
     * Obté tots els usuaris
     * @return Array d'usuaris
     */
    public Usuari[] obtenirTots() {
        Usuari[] resultat = new Usuari[numUsuaris];
        for (int i = 0; i < numUsuaris; i++) {
            resultat[i] = llista[i];
        }
        return resultat;
    }
    
    /**
     * Obté usuaris d'un tipus concret
     * @param tipus 0=PDI, 1=PTGAS, 2=Estudiant
     * @return Array d'usuaris del tipus especificat
     */
    public Usuari[] obtenirPerTipus(int tipus) {
        // Primer contar quants hi ha
        int count = 0;
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i] instanceof Pdi && tipus == 0) count++;
            else if (llista[i] instanceof Ptgas && tipus == 1) count++;
            else if (llista[i] instanceof Estudiant && tipus == 2) count++;
        }
        
        // Crear array i omplir-lo
        Usuari[] resultat = new Usuari[count];
        int index = 0;
        for (int i = 0; i < numUsuaris; i++) {
            if (llista[i] instanceof Pdi && tipus == 0) resultat[index++] = llista[i];
            else if (llista[i] instanceof Ptgas && tipus == 1) resultat[index++] = llista[i];
            else if (llista[i] instanceof Estudiant && tipus == 2) resultat[index++] = llista[i];
        }
        return resultat;
    }
    
    public int getNumUsuaris() { return numUsuaris; }
    
    public boolean esBuida() { return numUsuaris == 0; }
    
    @Override
    public String toString() {
        String info = "LLISTA USUARIS amb " + numUsuaris + " usuaris:\n";
        for (int i = 0; i < numUsuaris; i++) {
            info += llista[i].toString();
        }
        return info;
    }
}

