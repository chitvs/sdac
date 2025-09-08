import java.util.*;

public class GraphServices {

	public static <V> void distances(Graph<V> g) {
		
		for (Graph.Node<V> source: g.getNodes()) {
			g.resetStatus();
			LinkedList<Graph.Node<V>> ll = new LinkedList();
			bfs(g, source, ll);
			String s = "Nodo " + source.value + " --> {";
			for (Graph.Node<V> v: ll) 
				s = s + v.value + ":" + v.dist + ",";
			s = s.substring(0, s.length() - 1) + "}";
			System.out.println(s);
		}
			
	}

	public static <V> void bfs(Graph<V> g, Graph.Node<V> source, LinkedList<Graph.Node<V>> ll) {
		
		Queue<Graph.Node<V>> queue = new ArrayDeque<Graph.Node<V>>();
		source.state = Graph.Node.Status.EXPLORED;
		source.dist = 0;
		queue.add(source);
		ll.add(source);
		while(!queue.isEmpty()) {
			Graph.Node<V> u = queue.remove();
			for(Graph.Node<V> v : g.getOutNeighbors(u)) {
				if(v.state == Graph.Node.Status.UNEXPLORED){
					v.state = Graph.Node.Status.EXPLORED;
					v.dist = u.dist + 1;
					queue.add(v);
					ll.add(v);
				}
			}
		}
	}

}
