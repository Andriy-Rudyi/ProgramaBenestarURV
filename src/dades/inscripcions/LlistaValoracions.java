package dades.inscripcions;

import java.io.Serializable;

import dades.excepcions.UsuariDuplicatException;
import dades.usuaris.Usuari;

public class LlistaValoracions implements Serializable{
    private static final long serialVersionUID = 1L;
    Usuari[] llistaUsuaris;     /*no usem LlistaUsuaris perquè aquí no volem que les ordeni per alies
                                ja que si no, els índex de LlistaUsuaris no pertanyeran als de int[] */ 
    int[] valoracions;
    int numValoracions;

    public LlistaValoracions(int numPlaces){
        if (numPlaces > 0) {
            llistaUsuaris = new Usuari[numPlaces];
            valoracions = new int[numPlaces];
            numValoracions = 0;
        } else if (numPlaces == 0) {
            llistaUsuaris = new Usuari[1];
            valoracions = new int[1];
            numValoracions = 0;
        } else {
            System.out.println("El número de places no pot ser negatiu");
        }
    }

    public int getNumValoracions(){ return numValoracions; }

    public double getMitjanaValoracions(){
        int sumaValoracions = 0;
        for (int i = 0; i < numValoracions; i++){
            sumaValoracions = sumaValoracions + valoracions[i];
        }
        return ( (double) sumaValoracions ) / numValoracions ;
    }
    
    public Usuari getUsuari(int index) {
        if (index < 0 || index >= numValoracions) return null;
        return llistaUsuaris[index];
    }

    public boolean teValoracioDeUsuari(Usuari usuari) {
        for(int i = 0; i < numValoracions; i++){
            if (usuari.equals(llistaUsuaris[i])) return true;
        }
        return false;
    }

    public int getValoracioDeUsuari(Usuari usuari) {
        for(int i = 0; i < numValoracions; i++){
            if (usuari.equals(llistaUsuaris[i])) return valoracions[i];
        }
        return -1; // No té valoració
    }

    /**
     * Afegeix una valoracio a la llista valoracions
     * @param usuari
     * @param valoracio
     * @throws UsuariDuplicatException
     */
    public void afegirValoracio(Usuari usuari, int valoracio) throws UsuariDuplicatException{
        // null check
        if (usuari == null) {
            return;
        }

        // Redimensionar si cal
        if (numValoracions == llistaUsuaris.length) {
            Usuari[] novaLlistaUsuaris = new Usuari[llistaUsuaris.length * 2];
            int[] novaValoracions = new int[valoracions.length * 2];
            for (int i = 0; i < numValoracions; i++) {
                novaLlistaUsuaris[i] = llistaUsuaris[i];
                novaValoracions[i] = valoracions[i];
            }
            llistaUsuaris = novaLlistaUsuaris;
            valoracions = novaValoracions;
        }
        
        // Comprovar si ja existeix
        for(int i = 0; i < numValoracions; i++){
            if (usuari.equals(llistaUsuaris[i])) throw new UsuariDuplicatException("Aquest usuari ja ha fet una valoració");
        }
        
        // Inserir (sense cap ordre)
        llistaUsuaris[numValoracions] = usuari;
        valoracions[numValoracions] = valoracio;
        
        numValoracions++;
    }

    /**
     * Crea una còpia de la llista valoracions
     * @return Nova instància amb les mateixes dades
     */
    public LlistaValoracions copia() {
        LlistaValoracions copia = new LlistaValoracions(llistaUsuaris.length);
        copia.numValoracions = numValoracions;
        copia.valoracions = valoracions.clone();
        return copia;
    }
}
