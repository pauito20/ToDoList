package vista;

import modelo.Modelo;
import modelo.ModeloTabla;
import modelo.Tabla;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.StreamHandler;

public class VistaListarTareas {
    private VistaGeneral vistaGeneral;
    JFrame ventanaListarTareas;

    JButton jbActualizaTarea;
    JButton jbBorraTarea;

    JRadioButton jbCompletada;
    JRadioButton jbNoCompletada;
    JRadioButton jbTodasCompl;

    JRadioButton jbCompletadaModificar;


    JButton jbFiltrar;

    JComboBox<String> cbPrioridad;
    JComboBox<String> cbPrioridadModificar;

    JTextField jtTitulo;
    JTextArea jaDescrip;

    ModeloTabla modeloTabla;
    Tabla tabla;




    public void setVistaGeneral(VistaGeneral vistaGeneral){
        this.vistaGeneral = vistaGeneral;
    }


    public void setVisibleListarTareas(JFrame ventana){
        ventanaListarTareas.setLocationRelativeTo(ventana);
        ventanaListarTareas.pack();
        ventanaListarTareas.setVisible(true);
    }

    public void creaInterfazVisual() {
        ventanaListarTareas = new JFrame("Lista Tareas");
        ventanaListarTareas.getContentPane().setBackground(Color.lightGray);
        JPanel jpListarTareas = new JPanel();
        jpListarTareas.setBackground(Color.lightGray);
        ventanaListarTareas.getContentPane().add(jpListarTareas,BorderLayout.NORTH);
        ventanaListarTareas.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                vistaGeneral.ventanaPrincipal.setLocationRelativeTo(ventanaListarTareas);
                vistaGeneral.ventanaPrincipal.setVisible(true);
                ventanaListarTareas.setVisible(false);
            }
        });

        //Panel Central Filtros busqueda y la tabla
        JPanel jpFiltrosBusqueda = new JPanel(new GridLayout(1,3));
        jpFiltrosBusqueda.setBackground(Color.lightGray);
        JPanel jpAux = new JPanel(new FlowLayout());
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Prioridad "));
        String [] elec = {"Alta", "Normal", "Baja","Todas"};
        cbPrioridad = new JComboBox<String>(elec);
        jpAux.add(cbPrioridad);

        jpFiltrosBusqueda.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jbTodasCompl = new JRadioButton();
        jbCompletada = new JRadioButton();
        jbNoCompletada = new JRadioButton();
        JPanel jpBotonesCompletada = new JPanel();
        jpBotonesCompletada.setLayout(new GridLayout(3,2));
        jpBotonesCompletada.setBackground(Color.lightGray);
        jpBotonesCompletada.add(new JLabel("Completada"));
        jpBotonesCompletada.add(jbCompletada);
        jpBotonesCompletada.add(new JLabel("No completada"));
        jpBotonesCompletada.add(jbNoCompletada);
        jpBotonesCompletada.add(new JLabel("Todas"));
        jpBotonesCompletada.add(jbTodasCompl);
        jpAux.add(jpBotonesCompletada, BorderLayout.CENTER);

        jpFiltrosBusqueda.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jbFiltrar = new JButton("Filtrar");
        jpAux.add(jbFiltrar);

        jpFiltrosBusqueda.add(jpAux);

        //Tabla con lista de Tareas
        modeloTabla = new ModeloTabla(vistaGeneral.getListaTareas());
        tabla = new Tabla(modeloTabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Panel tareas seleccionadas en la tabla y campos a actualizar
        JPanel jpTareaSeleccionada = new JPanel();
        jpTareaSeleccionada.setBackground(Color.lightGray);
        jpTareaSeleccionada.setLayout(new BoxLayout(jpTareaSeleccionada, BoxLayout.Y_AXIS));


        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("DETALLES DE LA TAREA" ));

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Título:          "));
        jtTitulo = new JTextField();
        jtTitulo.setColumns(25);
        jpAux.add(jtTitulo);

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Descripción:"));
        jaDescrip = new JTextArea();
        jaDescrip.setColumns(25);
        jaDescrip.setRows(5);
        jpAux.add(jaDescrip);

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(new JLabel("Copletada: "));
        jbCompletadaModificar = new JRadioButton();
        jpAux.add(jbCompletadaModificar);

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        String[] eleccion = { "Alta","Normal","Baja"};
        cbPrioridadModificar = new JComboBox<String>(eleccion);
        jpAux.add(new JLabel("Prioridad:   "));
        jpAux.add(cbPrioridadModificar);

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jbActualizaTarea = new JButton("Actualizar");
        jbBorraTarea = new JButton("Borrar");

        jpAux.add(jbBorraTarea);
        jpAux.add(jbActualizaTarea);

        jpTareaSeleccionada.add(jpAux);

        jpAux = new JPanel();
        jpAux.setBackground(Color.lightGray);
        jpAux.add(Box.createRigidArea(new Dimension(1,10)));
        jpTareaSeleccionada.add(jpAux);


        jpListarTareas.setLayout(new BoxLayout(jpListarTareas, BoxLayout.Y_AXIS));
        //Añadimos los paneles al Principal
        jpListarTareas.add(jpFiltrosBusqueda );
        jpListarTareas.add(new JScrollPane(tabla));
        jpListarTareas.add(jpTareaSeleccionada);

        ventanaListarTareas.getContentPane().add(jpListarTareas,BorderLayout.SOUTH);

        jbFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //filtrar
            }
        });


        tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.getMinSelectionIndex() == -1)
                    return;
                //int fila = tabla.convertRowIndexToModel(tabla.getSelectedRow());
                int cod_tarea = vistaGeneral.getCodTareaSeleccionada();
                //int cod_tarea = modeloTabla.getCodifoTareaModelo(fila);
                vistaGeneral.setDatosTarea(cod_tarea);

            }
        });


        //Botones Borrar y Actualizar
        jbActualizaTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGeneral.controlador.actualizaTarea();
            }
        });

        jbBorraTarea.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaGeneral.controlador.borraTarea();
            }
        });

    }

}

