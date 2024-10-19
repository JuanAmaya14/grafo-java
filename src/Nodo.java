import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;
    // Lista que almacena las conexiones adyacentes a este nodo.
    // Estas conexiones representan las relaciones directas desde este nodo a otros nodos.
    // permitiendo acceder rápidamente a los nodos adyacentes.
    private List<Conexion> adyacencias = new ArrayList<>();

    public Nodo() { }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarConexion(Conexion arco) {
        adyacencias.add(arco);
    }

    public List<Conexion> getAdyacencias() {
        return adyacencias;
    }

    // Un nodo es adyacente a otro cuando hay una conexion que los une directamente
    public List<Nodo> getNodosAdyacentes() {
        List<Nodo> nodos = new ArrayList<>();

        // Recorre todas las conexiones asociadas a este nodo (adyacencias)
        for (Conexion conexion : adyacencias) {
            // Si la conexion no es reflexiva (es decir, el nodo de destino no es el mismo que el nodo actual)
            if (conexion.getDestino() != this) {
                // Agrega el nodo de destino a la lista de nodos adyacentes (nodos directamente conectados)
                nodos.add(conexion.getDestino());
            }
        }

        // Retorna la lista completa de nodos que estan conectados directamente a este nodo
        return nodos;
    }
}