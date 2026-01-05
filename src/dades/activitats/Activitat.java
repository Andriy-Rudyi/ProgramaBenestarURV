package dades.activitats;

import java.io.Serializable;

import dades.Data;
import dades.excepcions.ActivitatDuplicadaException;
import dades.excepcions.DataFiInscripcioException;
import dades.excepcions.UsuariDuplicatException;
import dades.inscripcions.*;
import dades.usuaris.*;

/**
 * Classe abstracta que representa una activitat del programa Benestar URV.
 * Conté els atributs i mètodes comuns a tots els tipus d'activitats.
 * @author PROG1 - Pau Font
 */
public abstract class Activitat implements Serializable{
    private static final long serialVersionUID = 1L;
    public static final String TIPUS_UNDIA = "UN DIA";
    public static final String TIPUS_PERIODICA = "PERIÒDICA";
    public static final String TIPUS_ONLINE = "ONLINE";

    protected String nom;
    protected boolean[] collectius; // [0]=PDI, [1]=PTGAS, [2]=Estudiants
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;
    protected int limitPlaces;
    protected double preu;
    protected Data dataIniciActivitat;
    protected LlistaInscripcions llistaInscripcions;
    protected LlistaValoracions llistaValoracions;
    
    /**
     * Constructor de la classe Activitat
     * @param nom Nom identificador de l'activitat
     * @param collectius Array de booleans indicant els col·lectius [PDI, PTGAS, Estudiants]
     * @param dataIniciInscripcio Data d'inici del període d'inscripció
     * @param dataFiInscripcio Data de fi del període d'inscripció
     * @param limitPlaces Límit de places (0 per il·limitat)
     * @param preu Preu de l'activitat
     */

    // mantenir el DataFiInscripcioException al constructor per si algú crea l'activitat des d'un altre lloc.
    public Activitat(String nom, boolean[] collectius, Data dataIniciInscripcio, 
                     Data dataFiInscripcio, int limitPlaces, double preu, Data dataIniciActivitat) throws DataFiInscripcioException {
        
        if (dataFiInscripcio.esAnterior(dataIniciInscripcio)) {
            throw new DataFiInscripcioException("Data de fi d'inscripció no pot ser anterior a la data d'inici d'inscripció");
        } else {
            this.nom = nom;
            // copia defensiva de l'array de collectius
            this.collectius = new boolean[3];
            for (int i = 0; i < 3; i++) {
                this.collectius[i] = collectius[i];
            }
            this.dataIniciInscripcio = dataIniciInscripcio;
            this.dataFiInscripcio = dataFiInscripcio;
            this.limitPlaces = limitPlaces;
            this.preu = preu;
            this.dataIniciActivitat = dataIniciActivitat;
            llistaInscripcions = new LlistaInscripcions(limitPlaces);
            llistaValoracions = new LlistaValoracions(limitPlaces);
        }
    }
    
    // Getters
    public String getNom() { return nom; }

    /**
     * Obté una còpia de l'array de col.lectius (protecció d'encapsulació)
     * @return Còpia de l'array de col.lectius
    */
   public boolean[] getCollectius() { 
       boolean[] copia = new boolean[3];
       for (int i = 0; i < 3; i++) {
           copia[i] = collectius[i];
        }
        return copia;
    }
    
    public Data getDataIniciInscripcio() { return dataIniciInscripcio; }
    public Data getDataFiInscripcio() { return dataFiInscripcio; }
    public int getLimitPlaces() { return limitPlaces; }
    public double getPreu() { return preu; }
    public int getNumInscripcions() { return llistaInscripcions.getNumInscrits(); }
    public LlistaInscripcions getLlistaInscripcions() { return llistaInscripcions; }
    public LlistaValoracions getLlistaValoracions() { return llistaValoracions; }

    

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
        return getNumInscripcions() < limitPlaces;
    }

    /**
     * Obté el nombre de places disponibles
     * @return Nombre de places disponibles, o -1 si és il·limitat
     */
    public int getPlacesDisponibles() {
        if (limitPlaces == 0) return -1; // Il·limitat
        return limitPlaces - getNumInscripcions();
    }
    
    /**
     * Afegeix una valoració a l'activitat
     * @param valoracio Nota entre 0 i 10
     * @return true si la valoració s'ha afegit correctament
     */
    public boolean afegirValoracio(Data avui, Usuari usuari, int valoracio) throws UsuariDuplicatException{
        if (valoracio < 0 || valoracio > 10 || this.estaActiva(avui)) {     //CAL COMPROVAR USUARI INSCRIT.
            return false;
        }
        llistaValoracions.afegirValoracio(usuari, valoracio);
        return true;
    }

    /**
     * Afegeix un usuari a la llista inscripcions
     * @param usuari
     * @throws UsuariDuplicatException
     * @throws ActivitatDuplicadaException
     */
    public void inscriureUsuari(Usuari usuari) throws UsuariDuplicatException, ActivitatDuplicadaException{
        llistaInscripcions.afegir(usuari);
    }
    
    /**
     * Comprova si l'activitat està oferida per a un col·lectiu concret (per nom String)
     * @param nomCollectiu "PDI", "PTGAS" o "Estudiants"
     * @return true si l'activitat està oferida al col·lectiu
     */
    public boolean esPerCollectiu(String nomCollectiu) {
        if (nomCollectiu == null) return false;
        if (nomCollectiu.equals(Usuari.COLECTIU_PDI)) return collectius[0];
        if (nomCollectiu.equals(Usuari.COLECTIU_PTGAS)) return collectius[1];
        if (nomCollectiu.equals(Usuari.COLECTIU_ESTUDIANTS)) return collectius[2];
        return false;
    }
    
    /**
     * Calcula el percentatge d'ocupació de l'activitat
     * @return Percentatge d'ocupació (0-100)
     */
    public double getPercentatgeOcupacio() {
        if (llistaInscripcions.getNumPlaces() == 0) return 100.0; // Online
        return (llistaInscripcions.getNumInscrits() * 100.0) / llistaInscripcions.getNumPlaces();
    }

    /**
     * Obté els col·lectius com a String
     * @return String amb els col·lectius separats per espai
     */
    public String getCollectiusString() {
        String resultat = "";
        if (collectius[0]) resultat += "PDI ";
        if (collectius[1]) resultat += "PTGAS ";
        if (collectius[2]) resultat += "Estudiants ";
        return resultat.trim();
    }

    // Delegate check to the internal list (preferred for encapsulation)
    /**
     * Comprova si un usuari està inscrit
     * @param nom
     * @return true si està inscrit
     */
    public boolean teUsuariInscrit(String nom) {
        return llistaInscripcions != null && llistaInscripcions.teUsuariInscrit(nom);
    }    

    // Mètodes abstractes que implementaran les subclasses
    /**
     * Comprova si l'activitat està activa en una data
     * @param dataAvui Data a comprovar
     * @return true si l'activitat està activa
     */
    public abstract boolean estaActiva(Data dataAvui);

    /**
     * Comprova si l'activitat té classe en una data
     * @param dataAvui Data a comprovar
     * @return true si hi ha classe
     */
    public abstract boolean teClasseAvui(Data dataAvui);

    /**
     * Comprova si l'activitat ha acabat
     * @param dataAvui Data actual
     * @return true si l'activitat ja ha acabat
     */
    public abstract boolean haAcabat(Data dataAvui);

    /**
     * Obté el tipus d'activitat
     * @return "UN DIA", "PERIÒDICA" o "ONLINE"
     */
    public abstract String getTipus();

    /**
     * Obté la informació específica del tipus d'activitat
     * @return String amb la informació específica
     */
    public abstract String getInformacioEspecifica();

    /**
     * Crea una còpia de l'activitat
     * @return Nova instància amb les mateixes dades
     */
    public abstract Activitat copia();
    
    @Override
    public String toString() {
        String info = "Nom: " + nom + "\n";
        info += "Tipus: " + getTipus() + "\n";
        info += "Col·lectius: " + getCollectiusString() + "\n";
        info += "Període inscripció: " + dataIniciInscripcio + " - " + dataFiInscripcio + "\n";
        info += "Preu: " + preu + "€\n";
        info += "Places: " + getNumInscripcions();
        if (limitPlaces > 0) {
            info += "/" + limitPlaces;
        } else {
            info += " (il·limitades)";
        }
        info += "\n";
        if (llistaValoracions.getNumValoracions() > 0) {
            info += "Valoració: " + String.format("%.1f", llistaValoracions.getMitjanaValoracions()) + "/10";
            info += " (" + llistaValoracions.getNumValoracions() + " valoracions)\n";
        }
        if (llistaInscripcions.teUsuarisEnEspera()) {
            info += "Llista espera: " + llistaInscripcions.getUsuarisEnEspera() + " persones\n";
        }
        info += getInformacioEspecifica();
        return info;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Activitat)) return false;
        Activitat altra = (Activitat) obj;
        return this.nom != null && this.nom.equals(altra.nom);
    }    
}