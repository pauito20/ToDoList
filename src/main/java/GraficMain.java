import controlador.Controlador;
import modelo.Modelo;
import vista.VistaGeneral;

import javax.swing.*;



public class GraficMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                    VistaGeneral vista = new VistaGeneral();
                    Modelo modelo = new Modelo();
                    Controlador controlador = new Controlador();
                    modelo.cargaDatos();

                    modelo.setVista(vista);
                    controlador.setVista(vista);
                    controlador.setModelo(modelo);
                    vista.setModelo(modelo);
                    vista.setControlador(controlador);
                    vista.creaInterfazVisual();
            }
        });
    }
}
