import java.util.ArrayList;
import java.util.List;

public class Grafo {

    List<Nodo> nodos = new ArrayList<>();
    // Lista que almacena todas las conexiones en el grafo,
    // permitiendo una vista global de todas las relaciones entre nodos.
    // Esto incluye cada conexión entre nodos, tanto de origen a destino como viceversa.
    List<Conexion> todasLasConexiones = new ArrayList<>();

    public Grafo() {
    }

    // Metodo para agregar un nodo al grafo
    public void addNodo(String nombre) {
        Nodo nodo = new Nodo();
        nodo.setNombre(nombre);
        nodos.add(nodo); // Agregar el nodo a la lista de nodos
    }

    // Metodo para agregar una todasLasConexiones entre dos nodos, usando los nombres de los nodos
    public void addConexion(String origen, String destino) {

        // Buscar los nodos de origen y destino en la lista de nodos
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);

        // Validar que ambos nodos existen
        if (nodoOrigen == null) {
            System.out.println("Error: NodoOrigen no encontrado (" + origen + ")");
            return; // Termina si el nodo origen no se encuentra
        }
        if (nodoDestino == null) {
            System.out.println("Error: NodoDestino no encontrado (" + destino + ")");
            return; // Termina si el nodo destino no se encuentra
        }

        // Crear la primera todasLasConexiones (de origen a destino)
        Conexion conexion = new Conexion();
        conexion.setOrigen(nodoOrigen);
        conexion.setDestino(nodoDestino);
        this.todasLasConexiones.add(conexion); // Agregar la todasLasConexiones a la lista
        nodoOrigen.agregarConexion(conexion); // Asociar la todasLasConexiones al nodo de origen

        // Crear la todasLasConexiones inversa (de destino a origen)
        conexion = new Conexion();
        conexion.setOrigen(nodoDestino);
        conexion.setDestino(nodoOrigen);
        this.todasLasConexiones.add(conexion); // Agregar la todasLasConexiones a la lista
        nodoDestino.agregarConexion(conexion); // Asociar la todasLasConexiones al nodo destino
    }

    // Metodo para buscar un nodo por su nombre
    public Nodo buscarNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo; // Devolver el nodo si se encuentra
            }
        }
        return null; // Retornar null si no se encuentra
    }

    // Metodo para buscar una ruta entre dos nodos usando sus nombres
    public List<Nodo> buscarRuta(String origen, String destino) {

        // Buscar nodos de origen y destino
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);
        List<Nodo> nodosRuta = new ArrayList<>();

        // Verificar si se encuentran los nodos
        if (nodoOrigen == null) {
            System.out.println("Error: NodoOrigen no encontrado (" + origen + ")");
            return null; // Termina si el nodo origen no se encuentra
        }
        if (nodoDestino == null) {
            System.out.println("Error: NodoDestino no encontrado (" + destino + ")");
            return null; // Termina si el nodo destino no se encuentra
        }

        // Intentar encontrar una ruta usando DFS
        if (inicializaDFS(nodosRuta, nodoOrigen, nodoDestino)) {
            return nodosRuta; // Retornar la lista de nodos si se encuentra la ruta
        } else {
            return null; // Retornar null si no se encuentra la ruta
        }
    }

    /*
    *
    * DFS (Depth-First Search)
    *
    */
    // Metodo para buscar una ruta entre dos nodos usando DFS
    public boolean inicializaDFS(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino) {

        // Lista para controlar los nodos visitados
        List<Nodo> nodosVisitados = new ArrayList<>();

        // Iniciar la busqueda con DFS
        return dfs(nodoOrigen, nodoDestino, nodosRuta, nodosVisitados);
    }

    // Metodo DFS simple
    private boolean dfs(Nodo actual, Nodo destino, List<Nodo> nodosRuta, List<Nodo> visitados) {

        // Marcar el nodo actual como visitado y agregarlo a la ruta
        visitados.add(actual);
        nodosRuta.add(actual);

        // Si llegamos al destino, retornamos true
        if (actual.equals(destino)) {
            return true;
        }

        // Recorrer los nodos adyacentes
        for (Nodo adyacente : actual.getNodosAdyacentes()) {
            // Si no ha sido visitado, continuamos la busqueda
            if (!visitados.contains(adyacente)) {
                if (dfs(adyacente, destino, nodosRuta, visitados)) {
                    return true; // Ruta encontrada
                }
            }
        }

        // Si no encontramos el destino, eliminamos el nodo de la ruta y regresamos false
        nodosRuta.remove(actual);
        return false;
    }

}
