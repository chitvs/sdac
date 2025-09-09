import java.util.*;

public class GraphServices {

	public static <V> void distances(Graph<V> g) {
		String dist = "";
		for (Node<V> u : g.getNodes())
			dist += sssp(g, u) + "\n";
		System.out.println(dist);
	} 

	public static <V> String sssp(Graph<V> g, Node<V> source) {
		MinHeap<Node<V>> pqueue = new MinHeap<Node<V>>();
		HashMap<Node<V>, HeapEntry<Node<V>>> dist = new HashMap<Node<V>, HeapEntry<Node<V>>>();
		String toPrint = "";

		final int INFINITY = 100000; // = "Infinito"
		// (NB.: deve essere maggiore della somma di tutti i pesi del grafo, altrimenti e' scorretto)

		// Inizializzazione
		for(Node<V> u : g.getNodes()) {
			HeapEntry<Node<V>> hu = pqueue.insert(u == source ? 0 : INFINITY, u);
			dist.put(u, hu);
		}

		// Ciclo principale
		while (!pqueue.isEmpty()) {
			HeapEntry<Node<V>> hu = pqueue.removeMin();
			Node<V> u = hu.getValue();

			for(Edge<V> e : g.getOutEdges(u)) {
				Node<V> v = e.getTarget();
				if (dist.get(u).getKey() + e.getWeight() < dist.get(v).getKey()) {
					pqueue.replaceKey(dist.get(v), dist.get(u).getKey() + e.getWeight());
				}
			}
		}

		// Realizzazione della stringa da restituire
		toPrint = "Distanze dal nodo " + source.toString() + " [";
		for(Node<V> u : g.getNodes()) {
			toPrint += u.toString() + ":" + dist.get(u).getKey() + ", ";
		}
		toPrint = toPrint.substring(0, toPrint.length()-2);
		toPrint += "]";
		return toPrint;
	}

}
