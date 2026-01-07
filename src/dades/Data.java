package dades;

import java.io.Serializable;

import dades.excepcions.DataIncorrectaExcepction;

/**
 * Classe per guardar dates.
 * @author Professores de programació.
 */

public class Data implements Serializable{
	private static final long serialVersionUID = 1L;
    private int dia;		//atributs, sempre privats
    private int mes;
    private int any;

    /**
	 * Constructor per defecte
	 */
	public Data() {
		this.dia = 1;
		this.mes = 9;
		this.any = 2025;
	}


    /**
	 * Constructor que rep la data per paràmetre
	 * Ha de validar que la data és correcta. Si rep una data incorrecta inicialitza la instància
	 * amb la data de referència.
	 * @param dia
	 * @param mes
	 * @param any
	 */
    public Data(int dia, int mes, int any) throws DataIncorrectaExcepction{
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que és una data valida
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		} else throw new DataIncorrectaExcepction(dia, mes, any);
    }

	
	// Getters

	public int getDia(){
		return dia;
	}

	public int getMes(){
		return mes;
	}

	public int getAny(){
		return any;
	}

	/**
	 * Setter que posa la data només si és una data correcta.
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public void setData(int dia, int mes, int any) throws DataIncorrectaExcepction{
		if(esDataCorrecta(dia, mes, any)){
			this.dia = dia;
			this.mes = mes;
			this.any = any;
		} else throw new DataIncorrectaExcepction(dia, mes, any);
	}

	/**
	 * Mètode que retorna un booleà true si les dues dates son iguals. 
	 * Si no, retorna false.
	 * @param data2
	 * @return si les dues dates son iguals
	 */
	public boolean equals(Data data2){
		if ((this.dia == data2.dia) && (this.mes == data2.mes) && (this.any == data2.any)) return true;
		else return false;
	}

	/**
	 * Mètode que calcula i retorna una instància amb el valor del dia següent
	 * @return la data del dia seguent
	 */
    public Data diaSeguent(){
		Data novaData = null;
		try {
			novaData = new Data(dia, mes, any);
		} catch (DataIncorrectaExcepction e) {
			throw new IllegalStateException("Error intern, invàlida copiant la data inicial " + e);
		}
		if(novaData.dia == diesMes(novaData.mes, novaData.any)){
			novaData.dia = 1;
			if(novaData.mes != 12) novaData.mes = novaData.mes + 1;
			else {
				novaData.mes = 1;
				novaData.any = novaData.any + 1;
			}
		}
		else {
			novaData.dia = novaData.dia + 1;
		}

		return novaData;
    }

	/**
	 * Mètode que calcula i retorna una instància amb el valor del dia anterior
	 * @return la data del dia anterior
	 */
	public Data diaAnterior() {
		Data novaData = null;
		try {
			novaData = new Data(dia, mes, any);
		} catch (DataIncorrectaExcepction e) {
			throw new IllegalStateException("Error intern, invàlida copiant la data inicial " + e);
		}
		if(novaData.dia == 1){
			if (novaData.mes == 1){
				novaData.mes = 12;
				novaData.any = novaData.any - 1;
			} 
			else {
				novaData.mes = novaData.mes - 1;
			}
			novaData.dia = diesMes(novaData.mes, novaData.any);	//Assignem el dia del mes (sent ja el mes del dia anterior)
		} 
		else {
			novaData.dia = novaData.dia - 1;
		}

		return novaData;
	}

	/**
	 * Mètode que calcula la diferencia de dies entre dues dates
	 * @param data2
	 * @return dies entre dos dates
	 */
	public int diesEntreDuesDates(Data data2){
		int diferenciaDies = 0;
		Data diaMajor, diaMenor;
		if (esInferiorAltra(this, data2)){
			diaMenor = this;
			diaMajor = data2;
		} 
		else {
			diaMenor = data2;
			diaMajor = this;
		}

		while(esInferiorAltra(diaMenor, diaMajor)){
			diaMenor = diaMenor.diaSeguent();
			diferenciaDies++;
		}
		return diferenciaDies; 
	}

	/**
	 * Mètode que comprova si la data és inferior a la data cridada 
	 * @param data2
	 * @return si la data és inferior a la data cridada
	 */
	public boolean dataInferiorAltra(Data data2){
		return esInferiorAltra(this, data2);
	}

	/**
	 * Mètode que comprova si la data actual correspon a un any de traspas
	 * @return si és any de traspas
	 */
    public boolean esDataAnyDeTraspas() {
        return esAnyTraspas(any);
    }

	/**
	 * Mètode que transforma el contingut d'un objecte en una cadena de caracters per ser
	 * mostrat per pantalla
	 */
	public String toString() {
		return(dia+"/"+mes+"/"+any);
	}

	/**
	 * Calcula el número de dies que té un mes en un any determinat
	 * @param mes Número del mes (1-12)
	 * @param any Any per comprovar si és de traspàs
	 * @return Nombre de dies del mes (28, 29, 30 o 31)
	 */
	public static int diesMes(int mes, int any) { // per saber quants dies te un mes d'un any
	int diesMes;
	if (mes == 2) {
			if (esAnyTraspas(any)) {
				diesMes = 29;
			} 
			else {
				diesMes = 28;
			}
		} 
		else {
			if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
				diesMes = 30;
			} 
			else {
				diesMes = 31;
			}
		}
		return diesMes;
	}

	/**
	 * Comprova si una data és anterior a una altra
	 * @param data1 Primera data a comparar
	 * @param data2 Segona data a comparar
	 * @return true si data1 és anterior a data2, false en cas contrari
	 */
	private static boolean esInferiorAltra(Data data1, Data data2){ //Retorna true si data1 es inferior a data2 
		if (data1.any < data2.any) return true; 
		else if ((data1.any == data2.any) && (data1.mes < data2.mes)) return true; 
		else if ((data1.any == data2.any) && (data1.mes == data2.mes) && (data1.dia < data2.dia)) return true;
		else return false;	// Casos: any2 > any1, any1==any2 i mes2 > mes1, any1==any2, mes1==mes2 i dia2>=dia1
	}

	/**
	 * Calcula el dia de la setmana d'una data (1=Dilluns, ..., 7=Diumenge)
	 * Algorisme de Zeller modificat
	*/
    public int calcularDiaSetmana() {
		if (mes < 3) {
            mes += 12;
            any--;
        }
        int k = any % 100;
        int j = any / 100;
        int h = (dia + (13 * (mes + 1)) / 5 + k + k / 4 + j / 4 - 2 * j) % 7;
        
        // Convertir: 0=Dissabte, 1=Diumenge, 2=Dilluns,...
        int diaSetmana = ((h + 5) % 7) + 1;
        return diaSetmana;
    }
	
	/**
     * Retorna el nom del dia de la setmana
	 * @return Nom del dia
     */
	public String getNomDiaSetmana() {
        String[] dies = {"", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"};
        if (calcularDiaSetmana() >= 1 && calcularDiaSetmana() <= 7) {
			return dies[calcularDiaSetmana()];
        }
        return "Desconegut";
    }
	
	/**
	 * Retorna el nom del mes
	 * @param mes Enter que representa el mes [1-12]
	 * @return Nom del mes
	*/
    public static String getNomMes(int mes){
        String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
        if (mes >= 1 && mes <= 12) {
            return mesos[mes-1];
        }
        return "Desconegut";
    }

	/**
	 * Compara si una data es anterior de l'altra
	 * @param altra
	 * @return true si una data es anterior de l'altre, false si no ho es
	*/
	public boolean esAnterior(Data altra) {
		if (any != altra.getAny()) return any < altra.getAny();
    	if (mes != altra.getMes()) return mes < altra.getMes();
    	return dia < altra.getDia();
	}

	/**
	 * Retorna una copia de la data
	 * @return copia de la data
	 */
	public Data copia(){
		try {
			return new Data(dia, mes, any);
		} catch (DataIncorrectaExcepction e) {
			throw new IllegalStateException("Error intern copiant data. " + e);
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	/// 
	// Mètodes de classe (STATIC).
	// no s'apliquen sobre el contingut d'una instància de data sinó sobre valors
	// que es reben per paràmetre.
	// són mètodes auxiliars i per això estan definits com a private dins la classe
	// no són accessibles des de la classe Aplicacio (App.java)

	/**
	 * Valida si una data és correcta
	 * @param dia Dia del mes (1-31)
	 * @param mes Mes de l'any (1-12)
	 * @param any Any
	 * @return true si la data és vàlida, false en cas contrari
	 */
	private static boolean esDataCorrecta(int dia, int mes, int any) {
		boolean hoEs=true;
		if (dia < 1 || dia > 31) { // dia incorrecte
			hoEs= false;
		}
		else if (mes < 1 || mes > 12) { // mes incorrecte
			hoEs= false;
		}
		else if (dia > diesMes(mes, any)) { // dia del mes incorrecte
			hoEs= false;
		}
		return hoEs;
	}

	/**
	 * Determina si un any és de traspàs
	 * @param any Any a comprovar
	 * @return true si l'any és de traspàs, false en cas contrari
	 */
	private static boolean esAnyTraspas(int any) { // ens estalviem crear una instancia de data
		if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
			return true;
		} 
		else {
			return false;
		}
	}

}
