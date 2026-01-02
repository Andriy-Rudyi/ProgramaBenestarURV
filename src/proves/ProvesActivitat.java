package proves;

import dades.Data;
import dades.activitats.*;
import dades.excepcions.DataIncorrectaExcepction;

public class ProvesActivitat {
    public static void main(String[] args) {
        try {
            provarActivitatOnline();
        } catch (DataIncorrectaExcepction e) {}
        // provarActivitatUnDia();
        // provarActivitatPeriodica();
        
        // provarLlistaActivitats();
        // provarLlistaEspera();

    }

    public static void provarActivitatOnline() throws DataIncorrectaExcepction{
        boolean[] collectiuEstudiants = new boolean []{false, false, true};
        
        ActivitatOnline online = new ActivitatOnline(
            "Classes de catala",
            collectiuEstudiants,
            

            new Data(2, 12, 2025), 
            new Data(11, 12, 2025),
            new Data(1, 1, 2026), 
            60, 
            "https://www.llengues.urv.cat/ca/");
            
        System.out.println("Construcció ActivitatOnline:");
        System.out.println(online.getInformacioEspecifica()); 
    
        System.out.println("Proves metodes:");
        System.out.println(" - Esta activa el 2/12/2025? " + online.estaActiva(new Data(2, 12, 2025)));
        System.out.println(" - Data fi: " + online.getDataFi());
        System.out.println(" - Data inici: " + online.getDataInici());
        System.out.println(" - Enllac: " + online.getEnllac());
        System.out.println(" - Informacio especifica: " + online.getInformacioEspecifica());
        System.out.println(" - Periodo visualitzacio: " + online.getPeriodeVisualitzacio() + " dies");
        System.out.println(" - Tipus: " + online.getTipus());
        System.out.println(" - Ha acabat el 2/12/2025? " + online.haAcabat(new Data(15, 2, 2026)));
        System.out.println(" - Hi ha places disponibles? " + online.hiHaPlacesDisponibles());
        System.out.println(" - Te classe avui (2/12/2025)? " + online.teClasseAvui(new Data(2, 12, 2025)));
        
        
        System.out.println(" - Fer copia");
        ActivitatOnline copiaOnline = online.copia();

        System.out.println(copiaOnline);

    }

}

