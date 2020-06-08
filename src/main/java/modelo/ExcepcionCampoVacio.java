package modelo;

public class ExcepcionCampoVacio extends Exception {
    public ExcepcionCampoVacio(){
        super("ERROR: Existe algún campo vacío");
    }

}
