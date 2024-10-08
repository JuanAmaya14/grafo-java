import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Grafo {

    List<Nodo> nodos = new ArrayList<>();
    List<Arco> arcos = new ArrayList<>();

    public Grafo() {
    }

    // Metodo para agregar un nodo al grafo
    public void addNodo(String nombre) {
        Nodo nodo = new Nodo();
        nodo.setNombre(nombre);
        nodos.add(nodo);         // Agregar el nodo a la lista de nodos
    }

    // Metodo para agregar un arco entre dos nodos, usando los nombres de los nodos
    public void addArco(String origen, String destino){

        // Buscar los nodos de origen y destino en la lista de nodos
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);

        // Lanzar excepciones si no se encuentran los nodos
        if (nodoOrigen == null) {
            throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
        }
        if (nodoDestino == null) {
            throw new RuntimeException("Error en la Búsqueda: NodoDestino no encontrado");
        }

        // Agregar el arco entre los nodos
        addArco(nodoOrigen, nodoDestino);
    }

    // Metodo para agregar un arco, usando los objetos Nodo
    public void addArco(Nodo nodoOrigen, Nodo nodoDestino) {
        // Crear un arco entre origen y destino
        Arco arco = new Arco();
        arco.setOrigen(nodoOrigen);
        arco.setDestino(nodoDestino);
        arcos.add(arco);              // Añadir el arco a la lista de arcos
        nodoOrigen.agregarArco(arco); // Asociar el arco al nodo de origen

        // Crear el arco inverso (destino a origen)
        arco = new Arco();
        arco.setOrigen(nodoDestino);
        arco.setDestino(nodoOrigen);
        arcos.add(arco);
        nodoDestino.agregarArco(arco);
    }

    // Metodo para buscar un nodo por su nombre
    public Nodo buscarNodo(String nombre) {
        for (Nodo nodo : nodos) {
            if (nodo.getNombre().equals(nombre)) {
                return nodo;  // Devolver el nodo si se encuentra
            }
        }
        return null;  // Retornar null si no se encuentra
    }

    // Metodo para buscar una ruta entre dos nodos, usando sus nombres
    public List<Nodo> buscarRuta(String origen, String destino) {

        // Buscar nodos de origen y destino
        Nodo nodoOrigen = buscarNodo(origen);
        Nodo nodoDestino = buscarNodo(destino);
        List<Nodo> nodosRuta = new ArrayList<>();

        // Lanzar excepciones si no se encuentran los nodos
        if (nodoOrigen == null) {
            throw new RuntimeException("Error en la Búsqueda: NodoOrigen no encontrado");
        }
        if (nodoDestino == null) {
            throw new RuntimeException("Error en la Búsqueda: NodoDestino no encontrado");
        }

        // Intentar encontrar una ruta usando DFS (Depth First Search)
        if (buscarRutaDFS(nodosRuta, nodoOrigen, nodoDestino)) {
            return nodosRuta;  // Retornar la lista de nodos si se encuentra la ruta
        } else {
            return null;  // Retornar null si no se encuentra la ruta
        }
    }

    // Metodo privado que implementa la búsqueda de rutas con DFS
    private boolean buscarRutaDFS(List<Nodo> nodosRuta, Nodo nodoOrigen, Nodo nodoDestino) {

        // Lista para controlar los nodos visitados
        List<Nodo> nodosVisitados = new ArrayList<>();

        // Llamada recursiva a DFS
        return dfs(nodoOrigen, nodoDestino, nodosRuta, nodosVisitados);
    }

    // DFS en una sola función
    private boolean dfs(Nodo actual, Nodo destino, List<Nodo> nodosRuta, List<Nodo> visitados) {

        // Agregar el nodo actual a la ruta y marcarlo como visitado
        visitados.add(actual);
        nodosRuta.add(actual);

        // Si el nodo actual es el destino, retornar true
        if (actual.equals(destino)) {
            return true;
        }

        // Recorrer los nodos adyacentes
        for (Nodo adyacente : actual.getNodosAdyacentes()) {
            // Continuar la búsqueda si el nodo adyacente no ha sido visitado
            if (!visitados.contains(adyacente)) {
                if (dfs(adyacente, destino, nodosRuta, visitados)) {
                    return true; // Ruta encontrada
                }
            }
        }

        // Si no se encuentra el destino, remover el nodo actual de la ruta y retornar false
        nodosRuta.remove(actual);
        return false;
    }


}
