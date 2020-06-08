package vista;

import controlador.InterfaceControlador;
import modelo.InterfaceModelo;
import modelo.ModeloTabla;
import modelo.Tarea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.List;

public class VistaGeneral implements InterfaceVista{

    InterfaceModelo modelo;
    InterfaceControlador controlador;
    VistaAñadirTarea vistaAñadirTarea = new VistaAñadirTarea();
    VistaListarTareas vistaListarTareas = new VistaListarTareas();
    //Ventana
    JFrame ventanaPrincipal;
    //Botones Ventana Principal
    JButton jbVentanaCrear;
    JButton jbVentanaListar;


    public VistaGeneral(){
    }

    @Override
    public void setControlador(InterfaceControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void setModelo(InterfaceModelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void creaInterfazVisual() {
        vistaAñadirTarea.setVistaGeneral(this);
        vistaAñadirTarea.creaInterfazVisual();
        vistaListarTareas.setVistaGeneral(this);
        vistaListarTareas.creaInterfazVisual();

        // ----------------------------------- VENTANA INICIAL ------------------------------------------//
        ventanaPrincipal = new JFrame("Menú Principal");

        //Llama a la pestaña listar Tareas
        jbVentanaListar = new JButton("Actualizar tareas");
        jbVentanaListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaListarTareas.setVisibleListarTareas(ventanaPrincipal);
                ventanaPrincipal.setVisible(false);
            }
        });

        //Llama Ventana crear Tareas
        jbVentanaCrear = new JButton("Crear Tareas");
        jbVentanaCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vistaAñadirTarea.setVisibleAñadirTareas(ventanaPrincipal);
                ventanaPrincipal.setVisible(false);
            }
        });


        //Panel Principal
        JPanel jpContenido = new JPanel();
        jpContenido.setBackground(Color.lightGray);
        jpContenido.setLayout(new BoxLayout(jpContenido, BoxLayout.Y_AXIS));
        jbVentanaCrear.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbVentanaCrear.setMaximumSize(new Dimension(200,50));
        jbVentanaListar.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbVentanaListar.setMaximumSize(new Dimension(200,50));
        jpContenido.add(jbVentanaCrear);
        jpContenido.add(Box.createRigidArea(new Dimension(0, 20))); //espacio entre Botones
        jpContenido.add(jbVentanaListar);

        ventanaPrincipal.getContentPane().add(jpContenido, BorderLayout.CENTER);

        //Ventana
        JPanel jpDatos = new JPanel();
        jpDatos.setBackground(Color.lightGray);
        ventanaPrincipal.setSize(800, 700);
        ventanaPrincipal.setLocationRelativeTo(null);
        String html = "<html> <body>" +
                "<h2>  <h2/>" +
                " <h1>  Bienvenido a tu Planificador de Tareas  <h1/>  " +
                " <h2>  Seleccione una opción  <h2/> <hr> <br/>" +
                "<h4>  <h4/>" +
                "<body/> </html>";

        final JLabel[] etiqueta = {new JLabel(html)};
        jpDatos.add(etiqueta[0]);
        ventanaPrincipal.getContentPane().add(jpDatos, BorderLayout.NORTH);
        ventanaPrincipal.addWindowListener(new EscuchadorCerrarVentana());
        //ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);


    }

    //Getters y actualizadores de Campos GUI

    //AÑADIR TAREA
    @Override
    public String getDescripcionAñadir() {
        return vistaAñadirTarea.jaDescrip.getText();
    }

    @Override
    public String getTituloAñadir() {
        return vistaAñadirTarea.jtTitulo.getText();
    }

    @Override
    public String getPrioridadAñadir() {
        return (String) vistaAñadirTarea.cbPrioridad.getSelectedItem();
    }

    @Override
    public boolean geatCompletadaAñadir() {
        return vistaAñadirTarea.jbCompletada.isSelected();
    }

    @Override
    public void setTareaAñadida() {
        vistaAñadirTarea.jaDescrip.setText("");
        vistaAñadirTarea.jtTitulo.setText("");
    }

    //LISTAR TAREA
    @Override
    public String getDescripcionListar(){
        return vistaListarTareas.jaDescrip.getText();
    }
    @Override
    public String getTituloListar(){
        return vistaListarTareas.jtTitulo.getText();
    }
    @Override
    public boolean getCompletadaListar(){
        return vistaListarTareas.jbCompletada.isSelected();
    }
    @Override
    public boolean getCompletadaModificar(){
        return vistaListarTareas.jbCompletadaModificar.isSelected();
    }

    @Override
    public String getPrioridadListar(){

        return (String) vistaListarTareas.cbPrioridad.getSelectedItem();
    }
    @Override
    public String getPrioridadModificar(){
        return (String) vistaListarTareas.cbPrioridadModificar.getSelectedItem();
    }


    @Override
    public int getCodTareaSeleccionada() {
        int cod_tarea = (Integer) vistaListarTareas.tabla.getValueAt(vistaListarTareas.tabla.getSelectedRow(),4);
        return cod_tarea;
    }
    @Override
    public List<Tarea> getListaTareas(){
        return modelo.generaListaTareas();
    }

    @Override
    public void actualizaTabla() {
        ModeloTabla modelo_tabla = new ModeloTabla(modelo.generaListaTareas());
        vistaListarTareas.tabla.setModel(modelo_tabla);
        modelo_tabla.fireTableDataChanged();
    }
    @Override
    public void actualizaFiltrado(List<Tarea> filtradas){
        ModeloTabla modelo_tabla = new ModeloTabla(filtradas);
        vistaListarTareas.tabla.setModel(modelo_tabla);
        modelo_tabla.fireTableDataChanged();
    }

    @Override
    public void setDatosTarea(int cod_tarea){
        System.out.printf("Linea:" + vistaListarTareas.tabla.getSelectedRow() + "size: "+modelo.getSize());
        if (vistaListarTareas.tabla.getSelectedRow() <= modelo.getSize()) {
            //Actualizamos titulo
            vistaListarTareas.jtTitulo.setText(modelo.mostrarTituloTarea(cod_tarea));
            //Actualizamos Descripción
            vistaListarTareas.jaDescrip.setText(modelo.mostrarDescripcionTarea(cod_tarea));
            //Actualizaos completada
            if (modelo.mostrarSeleccionada(cod_tarea)) {
                vistaListarTareas.jbCompletadaModificar.setSelected(true);
            } else {
                vistaListarTareas.jbCompletadaModificar.setSelected(false);
            }
            //Actualizamos Prioridad
            if (modelo.mostararPrioridadSeleccionada(cod_tarea).equals("Alta")) {
                vistaListarTareas.cbPrioridadModificar.setSelectedIndex(0);

            } else if (modelo.mostararPrioridadSeleccionada(cod_tarea).equals("Baja")) {
                vistaListarTareas.cbPrioridadModificar.setSelectedIndex(2);
            } else {
                vistaListarTareas.cbPrioridadModificar.setSelectedIndex(1);
            }
        }

    }


    //Metodos Para Filtrar Tareas
    @Override
    public String isListarCompletadas(){
        String filtroCompletas;
        if (vistaListarTareas.jbCompletada.isSelected())
            filtroCompletas = "Completadas";
        else if (vistaListarTareas.jbNoCompletada.isSelected())
            filtroCompletas = "NoCompletadas";
        else
            filtroCompletas = "Todas";

        return filtroCompletas;
    }

    @Override
    public String listarTipoPrioridad(){
        return (String) vistaListarTareas.cbPrioridad.getSelectedItem();
    }


    //Cerrar Ventana
    private class EscuchadorCerrarVentana extends WindowAdapter implements Serializable{
        @Override
        public void windowClosing(WindowEvent e){
            final JFrame ventanaSalida = new JFrame("Cerrar aplicación");
            ventanaSalida.getContentPane().setBackground(Color.lightGray);
            JButton jbSalir = new JButton("Cerrar");
            jbSalir.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    modelo.guardaDatos();
                    System.exit(0);
                }
            });

            JButton jbCancelar = new JButton("Cancelar");
            jbCancelar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    creaInterfazVisual();
                    ventanaSalida.setVisible(false);

                }
            });

            JPanel jpSalida = new JPanel();
            jpSalida.setBackground(Color.lightGray);
            jpSalida.add(jbSalir);
            jpSalida.add(jbCancelar);
            ventanaSalida.setSize(330, 75);
            ventanaSalida.getContentPane().add(jpSalida, BorderLayout.CENTER);
            JLabel etiqueta = new JLabel("   ¿Está seguro de que desea cerrar la aplicación?");
            etiqueta.setBackground(Color.lightGray);
            ventanaSalida.getContentPane().add(etiqueta, BorderLayout.NORTH);
            ventanaSalida.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    creaInterfazVisual();
                }
            });
            ventanaSalida.setLocationRelativeTo(null);
            ventanaSalida.setVisible(true);

        }

    }





}


