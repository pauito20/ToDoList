package modelo;

public enum Prioridad {
    ALTA("Prioridad Alta"),
    BAJA("Prioridad Baja"),
    NORMAL("Prioridad Normal");

   private String prioridad;

   Prioridad(String prioridad){
       this.prioridad = prioridad;
   }

   public static Prioridad enteroATipo(int pos){
       return values()[pos];
   }

   public static String options(){
       StringBuilder sb = new StringBuilder();
       for (Prioridad tipo: values())
           sb.append("\t" + tipo.ordinal() + ". " + tipo.prioridad + "\n");
       return sb.toString();
   }


}
