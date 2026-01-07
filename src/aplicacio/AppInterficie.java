package aplicacio;

import java.awt.*;
import javax.swing.*;

import dades.Data;
import dades.activitats.Activitat;
import dades.activitats.LlistaActivitats;
import dades.excepcions.DataIncorrectaExcepction;
import interficie.AccioActivitatClicada;
import interficie.AccioCanviarMes;
import interficie.AccioColectiuCanviat;
import interficie.AccioObrirDia;

public class AppInterficie extends JFrame {
    private static final long serialVersionUID=1L;
    private int mes, any;
    private JPanel panellCalendari = new JPanel(new GridLayout(0, 7));
    private JLabel etiquetaMesAny = new JLabel("", SwingConstants.CENTER);
    private String colectiu = "Totes";
    private AccioCanviarMes accioCanviarMes = new AccioCanviarMes(this);
    private AccioObrirDia accioObrirDia = new AccioObrirDia(this);
    private AccioActivitatClicada accioActivitatClicada = new AccioActivitatClicada(this);
    private AccioColectiuCanviat accioColectiuCanviat = new AccioColectiuCanviat(this);
    static LlistaActivitats llistaActivitats;
    
    public AppInterficie(String titol, int mes, int any) throws DataIncorrectaExcepction{
        super(titol); // Crida el constructor de la classe pare Jframe
        this.mes = mes;    
        this.any = any;

        this.setLocation(100, 200);
        this.setSize(1200,700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.add(panellSuperior(), BorderLayout.NORTH);
        this.add(panellCalendari, BorderLayout.CENTER);

        dibuixarCalendari(mes,any);
        
        this.setVisible(true);
    }

    public JPanel panellSuperior() {
        
        JPanel panellSuperior = new JPanel(new GridLayout(1, 5));

        // dos components buides per centrar panellMes
        panellSuperior.add(new JLabel()); 
        panellSuperior.add(new JLabel());
        
        JPanel panellMes = new JPanel(new BorderLayout());
        JButton botoAnterior = new JButton("<");
        botoAnterior.addActionListener(accioCanviarMes);
        JButton botoPosterior = new JButton(">");
        botoPosterior.addActionListener(accioCanviarMes);
        
        panellMes.add(botoAnterior, BorderLayout.WEST);
        panellMes.add(etiquetaMesAny, BorderLayout.CENTER);  //SwingConstants.CENTER per centrar el text dins del seu lloc
        panellMes.add(botoPosterior, BorderLayout.EAST);
        panellSuperior.add(panellMes);
        
        panellSuperior.add(new JLabel()); // component buida
        

        JPanel panellDreta = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel tipusActivitats = new JPanel();
        tipusActivitats.add(new JLabel("Tipus d'activitat:"));
        JComboBox<String> seleccioActivitats = new JComboBox<>(new String[]{"Totes", "Diàries", "Periòdiques", "Online"});
        seleccioActivitats.addActionListener(accioColectiuCanviat);
        tipusActivitats.add(seleccioActivitats);

        panellDreta.add(tipusActivitats);
        panellSuperior.add(panellDreta);
        panellSuperior.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30));
        return panellSuperior;
    }

    public int getMes(){return mes;}
    public int getAny(){return any;}

    public void augmentarMes(){ 
        if (1 <= mes && mes <= 11) mes++;
        else if(mes == 12){ 
            mes = 1; any++;
        }
    }

    public void disminuirMes(){ 
        if (2 <= mes && mes <= 12) mes--;
        else if(mes == 1){ 
            mes = 12; any--;
        }
    }

    public void setColectiu(String colectiu){ this.colectiu = colectiu; }

    public void dibuixarCalendari(int mes, int any) throws DataIncorrectaExcepction{
        panellCalendari.removeAll();
        etiquetaMesAny.setText(Data.getNomMes(mes) + " " + any);
        Data primerDeMes;
        primerDeMes = new Data(1, mes, any);
        int posicioPrimerDia = primerDeMes.calcularDiaSetmana();
        int dia = 1;
        for (int i = 1; i <= 6*7; i++){
            if (i-posicioPrimerDia >= 0 && dia <= Data.diesMes(mes, any)){
                JButton boto = new JButton(Integer.toString(dia));
                asignarColorBoto(boto, dia, mes, any);
                boto.addActionListener(accioObrirDia);
                panellCalendari.add(boto);
                dia++;
            } else panellCalendari.add(new JLabel());
        }
        panellCalendari.setBorder(BorderFactory.createEmptyBorder(30, 30, 0, 30));
        panellCalendari.revalidate();
        panellCalendari.repaint();
    }

    public void asignarColorBoto(JButton boto, int dia, int mes, int any) throws DataIncorrectaExcepction{
        boto.setOpaque(true);
        boto.setBorderPainted(true);
        if ((colectiu == "Totes" && llistaActivitats.obtenirActives(new Data(dia, mes, any)).length != 0 ) ||
        (llistaActivitats.obtenirPerTipus(colectiu).obtenirActives(new Data(dia, mes, any)).length != 0) ) boto.setBackground(new Color(255, 223, 0));
        else boto.setBackground(new Color(245, 245, 245));
    }

    public void obrirDialegDia(int dia) {
        try {
            Data dataSeleccionada = new Data(dia, this.mes, this.any);
            Activitat[] activitatsDia;
            if (colectiu != "Totes"){
                activitatsDia = llistaActivitats.obtenirPerTipus(colectiu).obtenirActives(dataSeleccionada);
            } else activitatsDia = llistaActivitats.obtenirActives(dataSeleccionada);
            
            JDialog dialeg = new JDialog(this, "Activitats del dia " + dataSeleccionada, true);
            dialeg.setLayout(new BorderLayout());
            dialeg.setSize(400, 300);
            dialeg.setLocationRelativeTo(this);
            JPanel llistaPanel = new JPanel();
            llistaPanel.setLayout(new BoxLayout(llistaPanel, BoxLayout.Y_AXIS));
            llistaPanel.setBackground(Color.WHITE);
            for (int i = 0; i < activitatsDia.length; i++){
                Activitat a = activitatsDia[i];
                JButton textBoto = new JButton(a.getNom());
                textBoto.setBorderPainted(false);
                textBoto.setContentAreaFilled(false);
                textBoto.setFocusPainted(false);
                textBoto.setOpaque(false);
                textBoto.setHorizontalAlignment(SwingConstants.LEFT);
                textBoto.setCursor(new Cursor(Cursor.HAND_CURSOR));
                textBoto.addActionListener(accioActivitatClicada);
                llistaPanel.add(textBoto);
            }
            dialeg.add(new JScrollPane(llistaPanel), BorderLayout.CENTER);

            JButton btnTancar = new JButton("Tancar");
            btnTancar.addActionListener(e -> dialeg.dispose());
            dialeg.add(btnTancar, BorderLayout.SOUTH);

            dialeg.setVisible(true);
            
        } catch (DataIncorrectaExcepction e) {
            JOptionPane.showMessageDialog(this, "Error en la data seleccionada");
        }
    }

    public void obrirActivitatClicada(String nomActivitat){
        JDialog dialeg = new JDialog(this, "Informació de l'activitat " + nomActivitat, true);
        dialeg.setSize(350, 250);
        dialeg.setLocationRelativeTo(this);
        JTextArea infoActivitat = new JTextArea(llistaActivitats.buscar(nomActivitat).toString());
        infoActivitat.setEditable(false);
        dialeg.add(infoActivitat);
        dialeg.setVisible(true);
    }

    public static void main(String[] args) {
        llistaActivitats = App.carregarLlistaActivitats("data/activitats.bin");
        try {
            new AppInterficie("Finestra principal", 9, 2025);
        } catch (DataIncorrectaExcepction e) {
            throw new IllegalStateException("Error intern usant constructor. " + e);
        }
    }
}
