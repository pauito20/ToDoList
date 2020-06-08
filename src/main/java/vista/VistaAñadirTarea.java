package vista;

import modelo.ExcepcionCampoVacio;
import modelo.ModeloTabla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VistaAñadirTarea {
    private VistaGeneral vistaGeneral;
    JFrame ventanaAñadirTareas;

    JButton jbNuevaTarea;
    JRadioButton jbCompletada;
    JComboBox<String> cbPrioridad;
    JTextField jtTitulo;
    JTextArea jaDescrip;





    public void setVistaGeneral(VistaGeneral vistaGeneral){
        this.vistaGeneral = vistaGeneral;
    }

    public void setVisibleAñadirTareas(JFrame ventana){
        ventanaAñadirTareas.setLocationRelativeTo(ventana);
        ventanaAñadirTareas.pack();
        ventanaAñadirTareas.setVisible(true);
    }


    public void creaInterfazVisual() {
        ventanaAñadirTareas = new JFrame("Añadir Tarea");
        ventanaAñadirTareas.getContentPane().setBackground(Color.lightGray);
        Container container = new Container();
        container.setLayout(new BoxLayout(container,BoxLayout.PAGE_AXIS));
        JPanel jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        JLabel etiqueta =  new JLabel(
                "<html> <body>" +
                        " <h1> Nueva Tarea <h1/>  " +
                        " <hr> " +
                        "<body/> </html>");
        jpAux.add(etiqueta);
        ventanaAñadirTareas.getContentPane().add(jpAux, BorderLayout.NORTH);
        ventanaAñadirTareas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vistaGeneral.ventanaPrincipal.setLocationRelativeTo(ventanaAñadirTareas);
                vistaGeneral.ventanaPrincipal.setVisible(true);
                ventanaAñadirTareas.setVisible(false);
            }
        });

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        String[] eleccion = { "Alta","Normal","Baja"};
        cbPrioridad = new JComboBox<String>(eleccion);
        jpAux.add(new JLabel("Prioridad:   "));
        jpAux.add(cbPrioridad);

        container.add(jpAux,BorderLayout.WEST);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(Box.createRigidArea(new Dimension(30,0)));
        container.add(jpAux,BorderLayout.CENTER);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Copletada: "));
        jbCompletada = new JRadioButton();
        jpAux.add(jbCompletada);

        container.add(jpAux,BorderLayout.EAST);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Título:          "));
        jtTitulo = new JTextField();
        jtTitulo.setColumns(25);
        jpAux.add(jtTitulo);

        container.add(jpAux,BorderLayout.SOUTH);




        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Descripción:"));
        jaDescrip = new JTextArea();
        jaDescrip.setColumns(25);
        jaDescrip.setRows(5);
        jpAux.add(jaDescrip);

        container.add(jpAux,BorderLayout.SOUTH);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(Box.createRigidArea(new Dimension(30,10)));
        container.add(jpAux,BorderLayout.SOUTH);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jbNuevaTarea = new JButton("Añadir");
        jbNuevaTarea.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbNuevaTarea.setMaximumSize(new Dimension(200,200));
        container.add(jbNuevaTarea, BorderLayout.SOUTH);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(Box.createRigidArea(new Dimension(30,10)));
        container.add(jpAux,BorderLayout.SOUTH);


        ventanaAñadirTareas.add(container);

        jbNuevaTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    vistaGeneral.controlador.añadirTarea();
                } catch (ExcepcionCampoVacio excepcionCampoVacio) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "No pueden haber campos vacíos, por favor rellenelos. ",
                            "Campos vacíos",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
    }
}
