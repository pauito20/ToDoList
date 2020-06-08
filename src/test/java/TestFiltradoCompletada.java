import modelo.Tarea;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestFiltradoCompletada {

    Tarea tarea1 = new Tarea("Primera","","Alta",true,1);
    Tarea tarea2 = new Tarea("Segunda","","Alta",true,1);
    Tarea tarea3 = new Tarea("Tercera","","Normal",false,1);
    Tarea tarea4 = new Tarea("Cuarta","","Baja",true,1);

    List<Tarea> tareas = new LinkedList<Tarea>();


    @Test
    public void TestFiltraCompletadas(){
        tareas.add(tarea1);
        tareas.add(tarea2);
        tareas.add(tarea3);
        tareas.add(tarea4);

        int tareas_comp = 0;

        for (Tarea ta : tareas){
            if (ta.isCompletada())
                tareas_comp++;
        }
        assertEquals(tareas_comp,3);
    }
    @Test
    public void TestFiltraNoCompletadas(){
        tareas.add(tarea1);
        tareas.add(tarea2);
        tareas.add(tarea3);
        tareas.add(tarea4);

        int tareas_comp = 0;

        for (Tarea ta : tareas){
            if (!ta.isCompletada())
                tareas_comp++;
        }
        assertEquals(tareas_comp,1);
    }

}
