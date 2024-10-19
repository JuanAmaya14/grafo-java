import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Crear una instancia del grafo
        Grafo grafo = new Grafo();

        // Agregar nodos al grafo
        grafo.addNodo("A");
        grafo.addNodo("B");
        grafo.addNodo("C");
        grafo.addNodo("D");
        grafo.addNodo("E");
        grafo.addNodo("F");

        // Agregar todasLasConexiones entre los nodos
        grafo.addConexion("A", "B");
        grafo.addConexion("B", "C");

        grafo.addConexion("A", "E");
        grafo.addConexion("E", "F");

        grafo.addConexion("B", "D");

        // Intentar buscar una ruta entre el nodo origen y el nodo destino
        List<Nodo> camino = grafo.buscarRuta("A", "F");

        if (camino != null) {
            System.out.println("Camino encontrado");

            for (Nodo nodo: camino) {
                System.out.println(nodo.getNombre());
            }
        } else {

            System.out.println("Camino no encontrado, no hay conexion con los dos nodos");

        }
    }

}

