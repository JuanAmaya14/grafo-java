import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;
    private List<Conexion> conexiones = new ArrayList<>();

    public Nodo() { }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarArco(Conexion arco) {
        conexiones.add(arco);
    }

    public List<Conexion> getConexiones() {
        return conexiones;
    }

    public List<Nodo> getNodosAdyacentes() {
        List<Nodo> nodos = new ArrayList<>();

        // Por cada conexion donde el nodo es origen
        for (Conexion conexion : conexiones) {
            // Si la conexion no es reflexiva
            if (conexion.getDestino() != this) {
                // Agrega el nodo destino a la lista de nodos adyacentes
                nodos.add(conexion.getDestino());
            }
        }

        // Retorna la lista de nodos adyacentes
        return nodos;
    }


}