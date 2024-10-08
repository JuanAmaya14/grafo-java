import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Crear una instancia del grafo
            Grafo g = new Grafo();

            // Agregar nodos al grafo
            g.addNodo("A");
            g.addNodo("B");
            g.addNodo("C");

            // Agregar arcos entre los nodos
            g.addArco("A", "B");
            g.addArco("B", "C");
            g.addArco("A", "C");

            // Intentar buscar una ruta entre el nodo origen y el nodo destino
            List<Nodo> camino = g.buscarRuta("A", "C");

            if (camino != null) {
                System.out.println("Camino encontrado");

                for (Nodo nodo: camino) {
                    System.out.println(nodo.getNombre());
                }
            } else {

                System.out.println("Camino no encontrado");

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

