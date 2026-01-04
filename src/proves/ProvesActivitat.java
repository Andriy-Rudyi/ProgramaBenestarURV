package proves;

import dades.Data;
import dades.activitats.*;
import dades.excepcions.DataIncorrectaExcepction;

public class ProvesActivitat {
    public static void main(String[] args) {
        try {
            provarActivitatOnline();
        } catch (DataIncorrectaExcepction e) {}
        
        try {
            provarActivitatUnDia();
        } catch (DataIncorrectaExcepction e) {}
        
        try {
            provarActivitatPeriodica();
        } catch (DataIncorrectaExcepction e) {}

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

    public static void provarActivitatUnDia() throws DataIncorrectaExcepction {
        boolean[] collectiuGeneral = new boolean []{true, true, true};

        ActivitatUnDia unDia = new ActivitatUnDia(
            "Visita al museu d'art",
            collectiuGeneral,
            new Data(5, 3, 2024),
            new Data(20, 3, 2024),
            30,
            15.0,
            new Data(25, 3, 2024),
            "10:00 - 14:00",
            "Barcelona"
        );

        System.out.println("Construcció ActivitatUnDia:");
        System.out.println(unDia.getInformacioEspecifica());
        System.out.println("Proves metodes:");
        System.out.println(" - Esta activa el 25/3/2024? " + unDia.estaActiva(new Data(25, 3, 2024)));
        System.out.println(" - Data: " + unDia.getData());
        System.out.println(" - Horari: " + unDia.getHorari());
        System.out.println(" - Ciutat: " + unDia.getCiutat());
        System.out.println(" - Informacio especifica: " + unDia.getInformacioEspecifica());
        System.out.println(" - Tipus: " + unDia.getTipus());
        System.out.println(" - Ha acabat el 26/3/2024? " + unDia.haAcabat(new Data(26, 3, 2024)));
        System.out.println(" - Hi ha places disponibles? " + unDia.hiHaPlacesDisponibles());
        System.out.println(" - Te classe avui (25/3/2024)? " + unDia.teClasseAvui(new Data(25, 3, 2024)));

        System.out.println(" - Fer copia");
        ActivitatUnDia copiaUnDia = unDia.copia();
        System.out.println(copiaUnDia);
    }
    
    public static void provarActivitatPeriodica() throws DataIncorrectaExcepction {
        boolean[] collectiuGeneral = new boolean []{true, true, true};

        ActivitatPeriodica periodica = new ActivitatPeriodica(
            "Classe de Catala", 
            collectiuGeneral, 
            new Data(1, 9, 2025),
            new Data(10, 9, 2025),
            25,
            100.0,
            "18:00 - 20:00",
            new Data(15, 9, 2025),
            10,
            "URV Campus Sescelades",
            "Tarragona"
        );
        
        System.out.println("Construcció ActivitatPeriodica:");
        System.out.println(periodica.getInformacioEspecifica());
        System.out.println("Proves metodes:");
        System.out.println(" - Esta activa el 20/9/2025? " + periodica.estaActiva(new Data(20, 9, 2025)));
        System.out.println(" - Data inici: " + periodica.getDataInici());
        System.out.println(" - Data fi: " + periodica.getDataFi());
        System.out.println(" - Horari: " + periodica.getHorari());
        System.out.println(" - Informacio especifica: " + periodica.getInformacioEspecifica());
        System.out.println(" - Tipus: " + periodica.getTipus());
        System.out.println(" - Ha acabat el 1/12/2025? " + periodica.haAcabat(new Data(1, 12, 2025)));
        System.out.println(" - Hi ha places disponibles? " + periodica.hiHaPlacesDisponibles());
        System.out.println(" - Te classe avui (20/9/2025)? " + periodica.teClasseAvui(new Data(20, 9, 2025)));
        
        System.out.println(" - Fer copia");
        ActivitatPeriodica copiaPeriodica = periodica.copia();
        System.out.println(copiaPeriodica);
    }


}

