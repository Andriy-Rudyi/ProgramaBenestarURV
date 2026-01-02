package dades.activitats;

import dades.Data;

/**
 * Representa una activitat que es realitza en un sol dia (taller o seminari)
 * @author PROG
 */
public class ActivitatUnDia extends Activitat {
    private Data data;
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
     * @param data Data en què es realitza l'activitat
     * @param horari Horari de l'activitat
     * @param ciutat Ciutat on es realitza
     */
    public ActivitatUnDia(String nom, boolean[] collectius, Data dataIniciInscripcio,
                          Data dataFiInscripcio, int limitPlaces, double preu,
                          Data data, String horari, String ciutat) {
        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio, limitPlaces, preu);
        this.data = data;
        this.horari = horari;
        this.ciutat = ciutat;
    }
    
    // Getters específics
    public Data getData() { return data; }
    public String getHorari() { return horari; }
    public String getCiutat() { return ciutat; }
    
    @Override
    public boolean estaActiva(Data dataAvui) {
        // Una activitat d'un dia està activa només el dia que es realitza
        return data.equals(dataAvui);
    }
    
    @Override
    public boolean teClasseAvui(Data dataAvui) {
        return data.equals(dataAvui);
    }
    
    @Override
    public boolean haAcabat(Data dataAvui) {
        return data.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public String getTipus() {
        return TIPUS_UNDIA;
    }
    
    @Override
    public String getInformacioEspecifica() {
        return "Data: " + data + "\n" +
               "Horari: " + horari + "\n" +
               "Ciutat: " + ciutat + "\n";
    }
    
    @Override
    public Activitat copia() {
        boolean[] col = new boolean[3];
        for (int i = 0; i < 3; i++) col[i] = collectius[i];
        
        ActivitatUnDia copia = new ActivitatUnDia(nom, col, dataIniciInscripcio, dataFiInscripcio,
                                                   limitPlaces, preu, data, horari, ciutat);
        copia.llistaInscripcions = this.llistaInscripcions.copia();
        copia.llistaValoracions = this.llistaValoracions.copia();
        return copia;
    }
}