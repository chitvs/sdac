import java.util.*;

public class GraphServices {

    public static <V> void tsort(Graph<V> g) {

        g.resetStatus();
        
        LinkedList<Graph.GraphNode<V>> res = new LinkedList<Graph.GraphNode<V>>();

        System.out.println();

        for (Graph.GraphNode<V> node : g.getNodes()){
            
            if (node.state == Graph.GraphNode.Status.UNEXPLORED){
                if (!dfs(node, res)){
                    System.out.println("Il grafo dato non ammette un ordinamento topologico.");
                    return;
                }    
            }
        }
        

        for (Graph.GraphNode<V> r : res) System.out.print(r.value + " ");

        System.out.println();
            
    }

    public static <V> boolean dfs(Graph.GraphNode<V> node, LinkedList<Graph.GraphNode<V>> list){
        if(node.state == Graph.GraphNode.Status.EXPLORING)
            return false;
        
        if(node.state == Graph.GraphNode.Status.EXPLORED)
            return true;

        node.state = Graph.GraphNode.Status.EXPLORING;
        boolean b = true;

        for (Graph.GraphNode<V> n : node.outEdges){
            b = b && dfs(n, list);
        }

        node.state = Graph.GraphNode.Status.EXPLORED;
        list.addFirst(node);
        return b;
    }

}