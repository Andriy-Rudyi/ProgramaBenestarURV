package dades.inscripcions;

import dades.excepcions.UsuariDuplicatException;
import dades.usuaris.Usuari;

public class LlistaValoracions {
    Usuari[] llistaUsuaris;
    int[] valoracions;
    int numValoracions;
    private static final int MIDA_INICIAL = 50;

    public LlistaValoracions(){
        llistaUsuaris = new Usuari[MIDA_INICIAL];
        valoracions = new int[MIDA_INICIAL];
        numValoracions = 0;
    }

    public void afegirValoracio(Usuari usuari, double valoracio){
            // null check
        if (usuari == null) {
            return;
        }
        
        // Comprovar si ja existeix
        for(int i = 0; i < numValoracions; i++){
            if (usuari.equals(llistaUsuaris[i])) return; //TODO què fem aquí?
        }
        
        // Redimensionar si cal
        if (numUsuaris == llista.length) {
            private void redimensionar() {
            Usuari[] novaLlista = new Usuari[llista.length * 2];
            for (int i = 0; i < numUsuaris; i++) {
                novaLlista[i] = llista[i];
            }
        llista = novaLlista;
    }
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
}
