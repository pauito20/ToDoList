package modelo;

import modelo.filtrado.completada.*;
import modelo.filtrado.prioridad.*;
import vista.InterfaceVista;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Modelo implements InterfaceModelo, Serializable {

    private InterfaceVista vista;
    public Map<Integer, Tarea> toDoList;
    int cod_tarea;


    public Modelo(){
        toDoList = new HashMap<Integer, Tarea>();
    }

    @Override
    public void setVista(InterfaceVista vista) {
        this.vista = vista;
    }

    //METODOS GESTIÓN LISTA DE TAREAS

    //Añade nueva Tarea
    @Override
    public void añadeTarea(String titulo, String descripcion, String prioridad, boolean completada){
        if (toDoList.isEmpty()){
            cod_tarea = 1;
        }else {
            cod_tarea = toDoList.size()+1;
        }
        Tarea tarea = new Tarea(titulo,descripcion,prioridad,completada,cod_tarea);

        toDoList.put(cod_tarea,tarea);
        cod_tarea++;
        vista.setTareaAñadida();
        vista.actualizaTabla();
    }

    //Borra una Tarea
    @Override
    public void borraTarea(int cod_tarea){
        String devolver;
        if (!toDoList.isEmpty()) {
            if (toDoList.containsKey(cod_tarea)) {
                toDoList.remove(cod_tarea);
                devolver = "La Tarea ha sido eliminada";
            }
        }
        vista.actualizaTabla();
    }

    //Actualiza Tarea
    @Override
    public void actualizaTarea(String titulo, String descripcion, String prioridad, boolean completada, int cod_tarea){

        String devolver;
        if (!toDoList.isEmpty()) {
            if (toDoList.containsKey(cod_tarea)) {
                System.out.println(prioridad+titulo+descripcion+cod_tarea);
                toDoList.get(cod_tarea).setPrioridad(prioridad);
                toDoList.get(cod_tarea).setTitulo(titulo);
                toDoList.get(cod_tarea).setDescripcion(descripcion);
                toDoList.get(cod_tarea).setCompletada(completada);
                vista.actualizaTabla();
            }
        }


    }
    //Genera Lista con las Tareas del mapa
    @Override
    public List<Tarea> generaListaTareas(){
        List<Tarea> lista = new LinkedList<Tarea>();
        lista.addAll(toDoList.values());
        return lista;
    }

    //Serializa guardando los datos
    @Override
    public void guardaDatos()
    {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("lista_tareas.bin");
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(toDoList);
            oos.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void cargaDatos() {
        //Carga los datos del fichero pero no se ejecuta la primera vez si no hay fichero
        FileInputStream fis = null;
        File fich = new File("lista_tareas.bin");
        if (fich.exists()){
            try {
                fis = new FileInputStream("lista_tareas.bin");
                ObjectInputStream ois = null;
                ois = new ObjectInputStream(fis);
                toDoList = (HashMap<Integer, Tarea>) ois.readObject();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String mostrarTituloTarea(int cod_tarea) {
        return toDoList.get(cod_tarea).getTitulo();
    }

    @Override
    public String mostrarDescripcionTarea(int cod_tarea) {
        return toDoList.get(cod_tarea).getDescripcion();
    }

    @Override
    public boolean mostrarSeleccionada(int cod_tarea) {
        return toDoList.get(cod_tarea).isCompletada();
    }

    @Override
    public String mostararPrioridadSeleccionada(int cod_tarea) {
        return toDoList.get(cod_tarea).getPrioridad();
    }

    @Override
    public int getSize() {
        return toDoList.size();
    }

    @Override
    public void  filtrar(String completadas, String prioridad) {

        List<Tarea> dev;

        //En Cuanto si esta o no Completada o elegimos Todas
        if (completadas.equals("Completadas")){
            FiltroCompletadas filtroCompletadas = new FiltroCompletadas();
            dev = filtroCompletadas.listaTareasCompletadas(generaListaTareas());
        }else if (completadas.equals("NoCompletadas")){
            FiltroNoCompletadas filtroNoCompletadas = new FiltroNoCompletadas();
            dev = filtroNoCompletadas.listaTareasCompletadas(generaListaTareas());
        }else {
            //Por defecto las tomaremos todas
            FiltroTodasCompletadas filtroTodasCompletadas = new FiltroTodasCompletadas();
            dev = filtroTodasCompletadas.listaTareasCompletadas(generaListaTareas());
        }

        //En cuanto a su prioridad
        if (prioridad.equals("Alta")){
            FiltroPrioridadAlta filtroPrioridadAlta = new FiltroPrioridadAlta();
            dev.retainAll(filtroPrioridadAlta.listaTareasPrioridad(generaListaTareas()));

        }else if (prioridad.equals("Baja")){
            FiltroPrioridadBaja filtroPrioridadBaja = new FiltroPrioridadBaja();
            dev.retainAll(filtroPrioridadBaja.listaTareasPrioridad(generaListaTareas()));

        }else if (prioridad.equals("Normal")){
            FiltroPrioridadNormal filtroPrioridadNormal = new FiltroPrioridadNormal();
            dev.retainAll(filtroPrioridadNormal.listaTareasPrioridad(generaListaTareas()));
        }else {
            FiltroPrioridadTodas filtroPrioridadTodas = new FiltroPrioridadTodas();
            dev.retainAll(filtroPrioridadTodas.listaTareasPrioridad(generaListaTareas()));
        }

        vista.actualizaFiltrado(dev);

    }


}
