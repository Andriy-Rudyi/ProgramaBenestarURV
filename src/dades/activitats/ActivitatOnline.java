package dades.activitats;

import dades.Data;
import dades.excepcions.DataIncorrectaExcepction;

/**
 * Representa una activitat online (asíncrona, gratuïta i sense límit de places)
 * @author PROG
 */
public class ActivitatOnline extends Activitat {
    private Data dataInici;
    private int periodeVisualitzacio; // en dies
    private String enllac;
    
    /**
     * Constructor d'ActivitatOnline
     * @param nom Nom de l'activitat
     * @param collectius Col·lectius als quals s'ofereix [PDI, PTGAS, Estudiants]
     * @param dataIniciInscripcio Inici del període d'inscripció
     * @param dataFiInscripcio Fi del període d'inscripció
     * @param dataInici Data d'inici de l'activitat
     * @param periodeVisualitzacio Període de visualització en dies
     * @param enllac Enllaç per accedir a l'activitat
     */
    public ActivitatOnline(String nom, boolean[] collectius, Data dataIniciInscripcio,
                           Data dataFiInscripcio, Data dataInici, 
                           int periodeVisualitzacio, String enllac) {
        // Les activitats online són gratuïtes (preu = 0) i sense límit de places (limitPlaces = 0)
        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio, 0, 0.0);
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac;
    }
    
    // Getters específics
    public Data getDataInici() { return dataInici; }
    public int getPeriodeVisualitzacio() { return periodeVisualitzacio; }
    public String getEnllac() { return enllac; }
    
    /**
     * Calcula la data de fi de l'activitat
     * @return Data de fi (dataInici + periodeVisualitzacio dies)
     */
    public Data getDataFi() {
        Data dataFi = null;
		try {
            dataFi = new Data(dataInici.getDia(), dataInici.getMes(), dataInici.getAny());
		} catch (DataIncorrectaExcepction e) {
			throw new IllegalStateException("Error intern, invàlida copiant la data inicial");
		}
        for (int i = 0; i < periodeVisualitzacio; i++) {
            dataFi = dataFi.diaSeguent();
        }
        return dataFi;
    }
    
    @Override
    public boolean estaActiva(Data dataAvui) {
        Data dataFi = getDataFi();
        return !dataAvui.dataInferiorAltra(dataInici) && !dataFi.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public boolean teClasseAvui(Data dataAvui) {
        // Les activitats online són asíncrones, "tenen classe" durant tot el període
        return estaActiva(dataAvui);
    }
    
    @Override
    public boolean haAcabat(Data dataAvui) {
        Data dataFi = getDataFi();
        return dataFi.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public String getTipus() {
        return TIPUS_ONLINE;
    }
    
    @Override
    public String getInformacioEspecifica() {
        return "\nData inici: " + dataInici + "\n" +
               "Període visualització: " + periodeVisualitzacio + " dies\n" +
               "Data fi: " + getDataFi() + "\n" +
               "Enllaç: " + enllac + "\n";
    }
    
    @Override
    public boolean hiHaPlacesDisponibles() {
        return true; // Sempre hi ha places (il·limitat)
    }
    
    @Override
    public ActivitatOnline copia() {
        boolean[] col = new boolean[3];
        for (int i = 0; i < 3; i++) col[i] = collectius[i];
        
        ActivitatOnline copia = new ActivitatOnline(nom, col, dataIniciInscripcio, dataFiInscripcio,
                                                     dataInici, periodeVisualitzacio, enllac);
        //TODO FALTA COPIA LES LLISTES D'INSCRITS
        return copia;
    }
}