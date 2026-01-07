package dades.activitats;

import dades.Data;
import dades.excepcions.DataFiInscripcioException;

/**
 * Representa una activitat que es realitza en un sol dia (taller o seminari)
 * @author PROG
 */
public class ActivitatUnDia extends Activitat {
    private String horari;
    private String ciutat;
    
    /**
     * Constructor d'ActivitatUnDia
     * @param nom Nom de l'activitat
     * @param collectius Col·lectius als quals s'ofereix [PDI, PTGAS, Estudiants]
     * @param dataIniciInscripcio Inici del període d'inscripció
     * @param dataFiInscripcio Fi del període d'inscripció
     * @param limitPlaces Límit de places
     * @param preu Preu de l'activitat
     * @param dataIniciActivitat Data en què es realitza l'activitat
     * @param horari Horari de l'activitat
     * @param ciutat Ciutat on es realitza
     * @throws DataFiInscripcioException Si la data de fi d'inscripcions no és posterior a la d'inici d'inscripcions
     */
    public ActivitatUnDia(String nom, boolean[] collectius, Data dataIniciInscripcio,
                          Data dataFiInscripcio, int limitPlaces, double preu,
                          Data dataIniciActivitat, String horari, String ciutat) throws DataFiInscripcioException {
        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio, limitPlaces, preu, dataIniciActivitat);
        this.horari = horari;
        this.ciutat = ciutat;
    }
    
    // Getters específics
    public Data getData() { return dataIniciActivitat.copia(); }
    public String getHorari() { return horari; }
    public String getCiutat() { return ciutat; }
    
    @Override
    public boolean estaActiva(Data dataAvui) {
        // Una activitat d'un dia està activa només el dia que es realitza
        return dataIniciActivitat.equals(dataAvui);
    }
    
    @Override
    public boolean teClasseAvui(Data dataAvui) {
        return dataIniciActivitat.equals(dataAvui);
    }
    
    @Override
    public boolean haAcabat(Data dataAvui) {
        return dataIniciActivitat.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public String getTipus() {
        return TIPUS_UNDIA;
    }
    
    @Override
    public String getInformacioEspecifica() {
        return "Data: " + dataIniciActivitat + "\n" +
               "Horari: " + horari + "\n" +
               "Ciutat: " + ciutat + "\n";
    }
    
    @Override
    public ActivitatUnDia copia() {
        boolean[] col = new boolean[3];
        for (int i = 0; i < 3; i++) col[i] = collectius[i];
        
        ActivitatUnDia copia = null;
        try {
            copia = new ActivitatUnDia(nom, col, dataIniciInscripcio, dataFiInscripcio,
                                                       limitPlaces, preu, dataIniciActivitat, horari, ciutat);
        } catch (DataFiInscripcioException e) {
            throw new IllegalStateException("Error intern. " + e);
        }
        copia.llistaInscripcions = this.llistaInscripcions.copia();
        copia.llistaValoracions = this.llistaValoracions.copia();
        return copia;
    }
}