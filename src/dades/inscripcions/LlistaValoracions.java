package dades.inscripcions;

import dades.excepcions.UsuariDuplicatException;
import dades.usuaris.Usuari;

public class LlistaValoracions {
    Usuari[] llistaUsuaris;     /*no usem LlistaUsuaris perquè aquí no volem que les ordeni per alies
                                ja que si no, els índex de LlistaUsuaris no pertanyeran als de int[] */ 
    int[] valoracions;
    int numValoracions;

    public LlistaValoracions(int numMaximInscripcions){
        llistaUsuaris = new Usuari[numMaximInscripcions];
        valoracions = new int[numMaximInscripcions];
        numValoracions = 0;
    }

    public int getNumValoracions(){ return numValoracions; }

    public double getMitjanaValoracions(){
        int sumaValoracions = 0;
        for (int i = 0; i < numValoracions; i++){
            sumaValoracions = sumaValoracions + valoracions[i];
        }
        return ( (double) sumaValoracions ) / numValoracions ;
    }

    public void afegirValoracio(Usuari usuari, int valoracio) throws UsuariDuplicatException{
            // null check
        if (usuari == null) {
            return;
        }
        
        // Comprovar si ja existeix
        for(int i = 0; i < numValoracions; i++){
            if (usuari.equals(llistaUsuaris[i])) throw new UsuariDuplicatException("Aquest usuari ja ha fet una valoració");
        }
        
        // Inserir (sense cap ordre)
        llistaUsuaris[numValoracions] = usuari;
        valoracions[numValoracions] = valoracio;
    }

    public LlistaValoracions copia() {
        LlistaValoracions copia = new LlistaValoracions(llistaUsuaris.length);
        copia.numValoracions = numValoracions;
        copia.valoracions = valoracions.clone();
        return copia;
    }
}
