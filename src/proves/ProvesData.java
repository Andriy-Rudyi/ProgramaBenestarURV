package proves;

import dades.Data;
import dades.excepcions.DataIncorrectaExcepction;

public class ProvesData {
    public static void main(String[] args){
        System.out.println("ProvesData");
        Data[] dates = null;
        try {
            dates = new Data[]{
                new Data(9, 9, 2025),
                new Data(10, 9 , 2025)
            };
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }

        // dataInferiorAltra()
        System.out.println("Comprovar si la data " + dates[0] + " es inferior de l'altra data " + dates[1] + " : " + dates[0].dataInferiorAltra(dates[1]));

        // diaAnterior()
        System.out.println("Dia anterior de la data " + dates[0] + " es " + dates[0].diaAnterior());

        // diaSeguent()
        System.out.println("Dia seguent de la data " + dates[1] + " es " + dates[1].diaSeguent());

        // diesEntreDuesDates()
        System.out.println("Comptar dies entre la data1 " + dates[0] + " i la data2 " + dates[1] + " : " + dates[0].diesEntreDuesDates(dates[1]));

        // equals()
        System.out.println("Comprovar si la data1 i la data2 son iguals: " + dates[0].equals(dates[1]));  
        
        // getAny()
        System.out.println("Any de la data es: " + dates[0].getAny());

        // getDia()
        System.out.println("Dia de la data es: " + dates[0].getDia());

        // getMes()
        System.out.println("Mes de la data es: " + dates[0].getMes());

        // calcularDiaSetmana()
        System.out.println("Calcula el dia de la setmana (utilitzant l'algoritsme de Zeller): " + dates[0].calcularDiaSetmana());
        
        // getNomDiaSetmana()
        System.out.println("Obtindre el nom del dia de la setmana: " + dates[0].getNomDiaSetmana());
        
        // toString()
        System.out.println("La data es: " + dates[0]);

        // data incorrecta
        System.out.println("Intent crear una data incorrecta: 32/12/2025");
        try {
            Data dataIncorrecta = new Data(32, 12, 2025);
            System.out.println(dataIncorrecta);
        } catch (DataIncorrectaExcepction e) {
            System.out.println(e.getMessage());
        }
    }
}
