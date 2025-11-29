package dades.activitats;

import dades.Data;
import dades.inscripcions.LlistaEspera;

/**
 * Classe abstracta que representa una activitat del programa Benestar URV.
 * Conté els atributs i mètodes comuns a tots els tipus d'activitats.
 * @author PROG1 - Pau Font
 */
public abstract class Activitat {
    protected String nom;
    protected boolean[] collectius; // [0]=PDI, [1]=PTGAS, [2]=Estudiants
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;
    protected int limitPlaces;
    protected double preu;
    protected int numInscripcions;
    protected double sumaValoracions;
    protected int numValoracions;
    protected LlistaEspera llistaEspera;
    
    /**
     * Constructor de la classe Activitat
     * @param nom Nom identificador de l'activitat
     * @param collectius Array de booleans indicant els col·lectius [PDI, PTGAS, Estudiants]
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data de fi del període d'inscripció
     * @param limitPlaces Límit de places (0 per il·limitat)
     * @param preu Preu de l'activitat
     */
    public Activitat(String nom, boolean[] collectius, Data dataIniciInscripcio, 
                     Data dataFiInscripcio, int limitPlaces, double preu) {
        this.nom = nom;
        this.collectius = collectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
        this.limitPlaces = limitPlaces;
        this.preu = preu;
        this.numInscripcions = 0;
        this.sumaValoracions = 0.0;
        this.numValoracions = 0;
        this.llistaEspera = new LlistaEspera();
    }
    
    // Getters
    public String getNom() { return nom; }
    public boolean[] getCollectius() { return collectius; }
    public Data getDataIniciInscripcio() { return dataIniciInscripcio; }
    public Data getDataFiInscripcio() { return dataFiInscripcio; }
    public int getLimitPlaces() { return limitPlaces; }
    public double getPreu() { return preu; }
    public int getNumInscripcions() { return numInscripcions; }
    public LlistaEspera getLlistaEspera() { return llistaEspera; }
    
    /**
     * Comprova si l'activitat està en període d'inscripció
     * @param dataAvui Data actual
     * @return true si estem en període d'inscripció
     */
    public boolean estaEnPeriodeInscripcio(Data dataAvui) {
        return !dataAvui.dataInferiorAltra(dataIniciInscripcio) && 
               !dataFiInscripcio.dataInferiorAltra(dataAvui);
    }
    
    /**
     * Comprova si hi ha places disponibles
     * @return true si hi ha places disponibles
     */
    public boolean hiHaPlacesDisponibles() {
        if (limitPlaces == 0) return true; // Il·limitat
        return numInscripcions < limitPlaces;
    }
    
    /**
     * Incrementa el número d'inscripcions
     */
    public void incrementarInscripcions() {
        numInscripcions++;
    }
    
    /**
     * Decrementa el número d'inscripcions
     */
    public void decrementarInscripcions() {
        if (numInscripcions > 0) numInscripcions--;
    }
    
    /**
     * Afegeix una valoració a l'activitat
     * @param valoracio Nota entre 0 i 10
     */
    public void afegirValoracio(double valoracio) {
        sumaValoracions += valoracio;
        numValoracions++;
    }
    
    /**
     * Obté la mitjana de valoracions
     * @return Mitjana de valoracions o 0 si no n'hi ha cap
     */
    public double getMitjanaValoracions() {
        if (numValoracions == 0) return 0.0;
        return sumaValoracions / numValoracions;
    }
    
    public int getNumValoracions() { return numValoracions; }
    
    /**
     * Comprova si l'activitat està oferida per a un col·lectiu concret
     * @param tipusCollectiu 0=PDI, 1=PTGAS, 2=Estudiants
     * @return true si l'activitat està oferida al col·lectiu
     */
    public boolean esPerCollectiu(int tipusCollectiu) {
        return collectius[tipusCollectiu];
    }
    
    /**
     * Calcula el percentatge d'ocupació de l'activitat
     * @return Percentatge d'ocupació (0-100)
     */
    public double getPercentatgeOcupacio() {
        if (limitPlaces == 0) return 100.0; // Online
        return (numInscripcions * 100.0) / limitPlaces;
    }
    
    // Mètodes abstractes que implementaran les subclasses
    public abstract boolean estaActiva(Data dataAvui);
    public abstract boolean teClasseAvui(Data dataAvui);
    public abstract boolean haAcabat(Data dataAvui);
    public abstract String getTipus();
    public abstract String getInformacioEspecifica();
    public abstract Activitat copia();
    
    @Override
    public String toString() {
        String info = "Nom: " + nom + "\n";
        info += "Col·lectius: ";
        if (collectius[0]) info += "PDI ";
        if (collectius[1]) info += "PTGAS ";
        if (collectius[2]) info += "Estudiants ";
        info += "\n";
        info += "Inscripcions: " + dataIniciInscripcio + " - " + dataFiInscripcio + "\n";
        info += "Preu: " + preu + "€\n";
        info += "Places: " + numInscripcions;
        if (limitPlaces > 0) info += "/" + limitPlaces;
        info += "\n";
        info += getInformacioEspecifica();
        return info;
    }
}