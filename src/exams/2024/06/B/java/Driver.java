public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
		Graph<String> gra = new Graph<String>();
			
		Graph.GraphNode<String> a = gra.addNode("A");
		Graph.GraphNode<String> b = gra.addNode("B");
		Graph.GraphNode<String> c = gra.addNode("C");
		Graph.GraphNode<String> d = gra.addNode("D");
		Graph.GraphNode<String> e = gra.addNode("E");
		Graph.GraphNode<String> f = gra.addNode("F");
		Graph.GraphNode<String> g = gra.addNode("G");
		Graph.GraphNode<String> h = gra.addNode("H");
		Graph.GraphNode<String> i = gra.addNode("I");
		Graph.GraphNode<String> l = gra.addNode("L");
	
		gra.addEdge(a, e);
		gra.addEdge(b, a);
		gra.addEdge(b, d);
		gra.addEdge(c, e);		
		gra.addEdge(d, g);
		gra.addEdge(d, i);
		gra.addEdge(e, b);
		gra.addEdge(e, d);
		gra.addEdge(f, c);		
		gra.addEdge(f, e);		
		gra.addEdge(f, h);		
		gra.addEdge(f, l);		
		gra.addEdge(g, h);		
		gra.addEdge(h, l);		
		gra.addEdge(l, i);		
		
		System.out.println("Grafo:");
		System.out.println(gra);
		
				
		
		// Test per componenti fortemente connesse
		System.out.println("Verifica ordinamento topologico ...");
		System.out.println();
		System.out.println("Il tuo programma dovrebbe segnalare che non esiste un ordinamento topologico\n");
		System.out.println("Il tuo programma stampa:");
		GraphServices.tsort(gra); 
		System.out.println();
		System.out.println("Modifichiamo il grafo rimuovendo l'arco (B, A)");
		gra.removeEdge(b, a);
		System.out.println();
		System.out.println("Verifica ordinamento topologico ...");
		System.out.println();
		System.out.println("Stavolta il tuo programma dovrebbe stampare un possibile ordinamento topologico dei vertici, ad esempio:\n");
		System.out.println("F C A E B D G H L I");
		System.out.println();
		System.out.println("Il tuo programma stampa:");
		GraphServices.tsort(gra); 
		
		
	}
}
