import java.util.List;

public class Main {

    public static void main(String[] args) {
            // Crear una instancia del grafo
            Grafo grafo = new Grafo();

            // Agregar nodos al grafo
            grafo.addNodo("A");
            grafo.addNodo("B");
            grafo.addNodo("C");

            // Agregar conexion entre los nodos
            grafo.addConexion("A", "B");
            grafo.addConexion("B", "C");


            // Intentar buscar una ruta entre el nodo origen y el nodo destino
            List<Nodo> camino = grafo.buscarRuta("A", "B");

            if (camino != null) {
                System.out.println("Camino encontrado");

                for (Nodo nodo: camino) {
                    System.out.println(nodo.getNombre());
                }
            }
    }

}

