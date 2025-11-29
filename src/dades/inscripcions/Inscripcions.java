package dades.inscripcions;

import dades.activitats.Activitat;
import dades.usuaris.Usuari;

public class Inscripcions { 
    private Usuari usuari;
    private Activitat activitat;
    private double valoracio; 

    // public Inscripcions(Usuari usuari, Activitat activitat, double valoracio) {
    //     this.usuari = usuari;
    //     this.activitat = activitat;
    //     this.valoracio = -1; // -1 indica que encara no s'ha valorat
    // }

    public Inscripcions(Usuari usuari, Activitat activitat) {
        this.usuari = usuari;
        this.activitat = activitat;
        this.valoracio = -1; // -1 indica que encara no s'ha valorat
    }

    public Usuari getUsuari() {
        return usuari;
    }

    public Activitat getActivitat() {
        return activitat;
    }

    public double getValoracio() {
        return valoracio;
    }

   
    public boolean setValoracio(double nota) {
        if (nota >= 0 && nota <= 10) {
            this.valoracio = nota;
            return true;
        } else {
            return false; 
        }
    }

    @Override
    public String toString() {
        String estat = (valoracio == -1) ? " (Sense valorar)" : " (Nota: " + valoracio + ")";
        return usuari.getAlies() + " inscrit a " + activitat.getNom() + estat;
    }
}