package modelo;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class ModeloTabla extends AbstractTableModel {

    final List<String> cabeceras = Arrays.asList("Terminada", "Prioridad", "Título", "Descripción", "Código" );
    List<Tarea> datos;

    public ModeloTabla(final List<Tarea> datos){
        this.datos = datos;
    }

    public void setDatos(final  List<Tarea> datos){
        this.datos = datos;
    }

    @Override
    public int getRowCount() {
        return datos.size();
    }

    @Override
    public int getColumnCount() {
        return cabeceras.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) return datos.get(rowIndex).isCompletada();
        if (columnIndex == 1) return datos.get(rowIndex).getPrioridad();
        if(columnIndex == 2) return datos.get(rowIndex).getTitulo();
        if (columnIndex == 3) return datos.get(rowIndex).descripcion;
        if (columnIndex == 4) return datos.get(rowIndex).cod_tarea;
        else return "";
    }

    @Override
    public String getColumnName(int colum){
        return cabeceras.get(colum);
    }


}
