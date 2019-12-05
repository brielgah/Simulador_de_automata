package vista;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import control.ControlDePeticion;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import control.ControlDibujarDiagrama;
import java.awt.Color;
import javax.swing.JOptionPane;

public class AutomataFinitoDeterministaPanel extends JPanel implements ActionListener{
    
    private JPanel paneles[];
    private JButton botones [];
    private JLabel tipo;
    private JTextArea descripcion;
    private JPanel panelPolimorfico;
    private ControlDePeticion control;
    private VisualizadorDeArchivosPanel visualizador;
    private JTextArea alfabeto;
    
    public AutomataFinitoDeterministaPanel(JPanel panelPolimorfico,ControlDePeticion control)
    {
        super();
        this.panelPolimorfico = panelPolimorfico;
        this.control = control;
        this.setLayout(null);
        iniciarComponentes();
    }
    
    public void iniciarComponentes()
    {
        iniciarPaneles();
        iniciarBotones();
        iniciarLabels();
        visualizador = new VisualizadorDeArchivosPanel(panelPolimorfico,control,"afd",descripcion);
    }
    
    private void iniciarLabels()
    {
        tipo = new JLabel("AUTOMATA FINITO DETERMINISTA", (int) CENTER_ALIGNMENT);
        tipo.setFont(new Font("",Font.BOLD,20));
        tipo.setBounds(0, 0, 570, 30);
        descripcion = new JTextArea();
        descripcion.setFont(new Font("",Font.BOLD,13));
        descripcion.setBounds(10, 40, 550, 50);
        descripcion.setOpaque(false);
        descripcion.setLineWrap(true);
        descripcion.setEditable(false);
        alfabeto = new JTextArea();
        alfabeto.setFont(new Font("",Font.BOLD,15));
        alfabeto.setBounds(0, 0, 570, 30);
        alfabeto.setEditable(false);
        alfabeto.setLineWrap(true);
        alfabeto.setOpaque(false);
        paneles[3].add(tipo);
        paneles[3].add(descripcion);
        paneles[1].add(alfabeto);
    }
    
    private void iniciarPaneles()
    {
        paneles = new JPanel[4];
        for (int i = 0; i < paneles.length; i++) 
        {
            paneles[i] = new JPanel();
            paneles[i].setLayout(null);
            paneles[i].setBackground(Color.red);
            this.add(paneles[i]);
        }
        paneles[0].setBounds(590, 70, 200, 500);//botones
        paneles[1].setBounds(10, 480, 570,80);//lenguaje
        paneles[2].setBounds(10, 120, 570, 350);//dibujo
        paneles[3].setBounds(10, 10, 570, 100);//descripcion
    }
    
    public void iniciarBotones()
    {
        botones = new JButton[6];
        for(int x = 0; x < botones.length; x++)
        {
            botones[x] = new JButton();
            botones[x].setBorder(BorderFactory.createRaisedBevelBorder());
            botones[x].setFont(new Font("",Font.BOLD,10));
            botones[x].addActionListener(this);
            paneles[0].add(botones[x]);
        }
        botones[0].setBounds(0,10,200,50);
        botones[1].setBounds(0,80,200,50);
        botones[2].setBounds(0,150,200,50);
        botones[3].setBounds(0,220,200,50);
        botones[4].setBounds(0,290,200,50);
        botones[5].setBounds(0,360,200,50);
        botones[0].setText("Generar AF Determinista");
        botones[1].setText("Evaluar cadena");
        botones[2].setText("Convertir AFD a AFP");
        botones[3].setText("Guardar/Cargar AFD de un archivo");
        botones[4].setText("Actualizar");
        botones[5].setText("Regresar al menu principal");
	
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == botones[0])
        {
		control.manejarPeticion("GAFD");
                actualizarInformacion();
        }
        else if(ae.getSource() == botones[1])
        {
            if(control.getAutomataFinitoDeterminista() != null)
                control.manejarPeticion("EAFD");
            else
                JOptionPane.showConfirmDialog(null, "No hay un automata generado", "ERROR", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }
        else if(ae.getSource() == botones[2])
        {
            control.manejarPeticion("CAFD-AFP");
        }
        else if(ae.getSource() == botones[3])
        {
            panelPolimorfico.add(visualizador, "archivos");
            ((CardLayout)panelPolimorfico.getLayout()).show(panelPolimorfico, "archivos");
        }
        else if(ae.getSource() == botones[4])
        {
            actualizarInformacion();
        }
        else if(ae.getSource() == botones[5])
        {
            ((CardLayout) panelPolimorfico.getLayout()).show(panelPolimorfico, "inicio");
        }
    }
    
    private void actualizarInformacion()
    {
        if(control.getAutomataFinitoDeterminista() != null)
        {
            descripcion.setText(control.getAutomataFinitoDeterminista().getDescripcion());
            alfabeto.setText(control.getAutomataFinitoDeterminista().getLenguaje());
            ControlDibujarDiagrama cd = new ControlDibujarDiagrama(control.getAutomataFinitoDeterminista(),null, new DibujadorDeDiagrama());
            cd.dibujarAutomata();
            cd.getDibujador().setBounds(0, 0, 570, 350);
            paneles[2].removeAll();
            paneles[2].add(cd.getDibujador());
            updateUI();
        }
        else
        {
            JOptionPane.showConfirmDialog(null, "No hay un automata generado", "ERROR", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
