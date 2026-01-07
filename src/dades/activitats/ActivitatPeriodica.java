package dades.activitats;

import dades.Data;
import dades.excepcions.DataFiInscripcioException;
import dades.excepcions.DataIncorrectaExcepction;

/**
 * Representa una activitat que es repeteix periòdicament cada setmana
 * @author PROG3 - Andriy Rudyy
 */

public class ActivitatPeriodica extends Activitat {
    private int diaSetmana;
    private String horari;
    private int numSetmanes;
    private String nomCentre;
    private String ciutat;
    
    /**
     * Constructor d'ActivitatPeriodica
     * @param nom Nom de l'activitat
     * @param collectius Col·lectius als quals s'ofereix
     * @param dataIniciInscripcio Inici període inscripció
     * @param dataFiInscripcio Fi període inscripció
     * @param limitPlaces Límit de places
     * @param preu Preu total de l'activitat
     * @param horari Horari de l'activitat
     * @param dataIniciActivitat Data d'inici de l'activitat
     * @param numSetmanes Número de setmanes que dura
     * @param nomCentre Nom del centre
     * @param ciutat Ciutat on es realitza
     * @throws DataFiInscripcioException Si la data de fi d'inscripcions no és posterior a la d'inici d'inscripcions
     */
    public ActivitatPeriodica(String nom, boolean[] collectius, Data dataIniciInscripcio,
                              Data dataFiInscripcio, int limitPlaces, double preu,
                              String horari, Data dataIniciActivitat, 
                              int numSetmanes, String nomCentre, String ciutat) throws DataFiInscripcioException {
        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio, limitPlaces, preu, dataIniciActivitat);
        this.diaSetmana = dataIniciActivitat.calcularDiaSetmana();
        this.horari = horari;
        this.numSetmanes = numSetmanes;
        this.nomCentre = nomCentre;
        this.ciutat = ciutat;
    }

    // Getters
    public int getDiaSetmana() { return diaSetmana; }
    public String getHorari() { return horari; }
    public Data getDataInici() { return dataIniciActivitat; }
    public int getNumSetmanes() { return numSetmanes; }
    public String getNomCentre() { return nomCentre; }
    public String getCiutat() { return ciutat; }
    
    /**
     * Calcula la data de fi de l'activitat
     * @return Data de fi (dataInici + numSetmanes setmanes)
     */
    public Data getDataFi() {
        Data dataFi = null;
		try {
            dataFi = new Data(dataIniciActivitat.getDia(), dataIniciActivitat.getMes(), dataIniciActivitat.getAny());
		} catch (DataIncorrectaExcepction e) {
			throw new IllegalStateException("Error intern, invàlida copiant la data inicial " + e);
		}
        for (int i = 0; i < numSetmanes * 7; i++) {
            dataFi = dataFi.diaSeguent();
        }
        return dataFi;
    }
    
    @Override
    public boolean estaActiva(Data dataAvui) {
        Data dataFi = getDataFi();
        return !dataAvui.dataInferiorAltra(dataIniciActivitat) && !dataFi.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public boolean teClasseAvui(Data dataAvui) {
        if (!estaActiva(dataAvui)) return false;
        return dataAvui.calcularDiaSetmana() == diaSetmana;
    }
    
    @Override
    public boolean haAcabat(Data dataAvui) {
        Data dataFi = getDataFi();
        return dataFi.dataInferiorAltra(dataAvui);
    }
    
    @Override
    public String getTipus() {
        return TIPUS_PERIODICA;
    }
    
    @Override
    public String getInformacioEspecifica() {
        return "Dia: " + dataIniciActivitat.getNomDiaSetmana() + "\n" +
               "Horari: " + horari + "\n" +
               "Inici: " + dataIniciActivitat + "\n" +
               "Durada: " + numSetmanes + " setmanes\n" +
               "Fi: " + getDataFi() + "\n" +
               "Centre: " + nomCentre + ", " + "Ciutat: " + ciutat + "\n";
    }
    
    @Override
    public ActivitatPeriodica copia() {
        boolean[] col = new boolean[3];
        for (int i = 0; i < 3; i++) col[i] = collectius[i];
        
        ActivitatPeriodica copia = null;
        try {
            copia = new ActivitatPeriodica(nom, col, dataIniciInscripcio, dataFiInscripcio,
                                                              limitPlaces, preu, horari, dataIniciActivitat,
                                                              numSetmanes, nomCentre, ciutat);
        } catch (DataFiInscripcioException e) {
            System.out.println(e.getMessage());;
        }
        copia.llistaInscripcions = this.llistaInscripcions.copia();
        copia.llistaValoracions = this.llistaValoracions.copia();
        return copia;
    }
}