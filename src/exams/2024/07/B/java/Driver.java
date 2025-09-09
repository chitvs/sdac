public class Driver {
    
    private Driver() {}
    
    public static void main(String[] args) {
        Graph<String> gra = new Graph<String>();
    
        Node<String> a = new Node<String>(new String("a"));
        Node<String> b = new Node<String>(new String("b"));		
        Node<String> c = new Node<String>(new String("c"));		
        Node<String> d = new Node<String>(new String("d"));
        Node<String> e = new Node<String>(new String("e"));
        Node<String> f = new Node<String>(new String("f"));
        
        gra.insertNode(a);
        gra.insertNode(b);
        gra.insertNode(c);
        gra.insertNode(d);
        gra.insertNode(e);
        gra.insertNode(f);
        

        // Il grafo in input è indiretto e pesato
        gra.insertEdge(a, b, 2);
        gra.insertEdge(b, a, 2);
        gra.insertEdge(a, c, 1);
        gra.insertEdge(c, a, 1);
        gra.insertEdge(a, d, 5);
        gra.insertEdge(d, a, 5);
        gra.insertEdge(b, c, 4);
        gra.insertEdge(c, b, 4);
        gra.insertEdge(b, d, 3);
        gra.insertEdge(d, b, 3);
        gra.insertEdge(c, d, 3);
        gra.insertEdge(d, c, 3);
        gra.insertEdge(c, e, 1);		
        gra.insertEdge(e, c, 1);
        gra.insertEdge(e, d, 1);
        gra.insertEdge(d, e, 1);
        gra.insertEdge(d, f, 5);
        gra.insertEdge(f, d, 5);	
        gra.insertEdge(e, f, 2);
        gra.insertEdge(f, e, 2);

        // Test
        System.out.println("Gli archi (non diretti) appartenenti all'albero ricoprente minimo sono (l'ordine non è importante):");
        System.out.println("(a, b), (a, c), (c, e), (e, d), (e, f)");
        System.out.println("Il tuo programma stampa:");
        GraphServices.mst(gra);
    }
}
