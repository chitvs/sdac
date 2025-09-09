import java.util.*;

public class GraphServices {


    public static <V> void mst(Graph<V> G) {
        Partition<V> P;
        MinHeap<Edge<V>> Q;

        int i = 0;
        for(Node<V> n : G.getNodes())
            n.map = i++;

        P = new Partition<V>(G.getNodes());
        Q = new MinHeap<Edge<V>>();
        for(Edge<V> e : G.getEdges())
            Q.insert(e.getWeight(), e);

        while(!Q.isEmpty())	{
            Edge<V> e = Q.removeMin().getValue();
            Node<V> u = e.getSource(), v = e.getTarget();
            
            if(P.find(u.map) != P.find(v.map)) {
                System.out.println(u.getValue() + " " + v.getValue());
                P.union(u.map, v.map);
            }
        }
    }
}
