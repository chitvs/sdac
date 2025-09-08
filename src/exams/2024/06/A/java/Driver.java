public class Driver {
	
	private Driver() {}
	
	public static void main(String[] args) {
			
		Graph<String> gra = new Graph<String>();
			
		Graph.Node<String> a = gra.addNode("A");
		Graph.Node<String> b = gra.addNode("B");
		Graph.Node<String> c = gra.addNode("C");
		Graph.Node<String> d = gra.addNode("D");
		Graph.Node<String> e = gra.addNode("E");
		Graph.Node<String> f = gra.addNode("F");
		Graph.Node<String> g = gra.addNode("G");
		Graph.Node<String> h = gra.addNode("H");
		Graph.Node<String> i = gra.addNode("I");
		Graph.Node<String> l = gra.addNode("L");
	
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
		
				
		
		System.out.println("Il tuo programma dovrebbe stampare:");
				
		System.out.println("Nodo A --> {A:0,E:1,B:2,D:2,G:3,I:3,H:4,L:5}");
		System.out.println("Nodo B --> {B:0,A:1,D:1,E:2,G:2,I:2,H:3,L:4}");
		System.out.println("Nodo C --> {C:0,E:1,B:2,D:2,A:3,G:3,I:3,H:4,L:5}");
		System.out.println("Nodo D --> {D:0,G:1,I:1,H:2,L:3}");
		System.out.println("Nodo E --> {E:0,B:1,D:1,A:2,G:2,I:2,H:3,L:4}");
		System.out.println("Nodo F --> {F:0,C:1,E:1,H:1,L:1,B:2,D:2,I:2,A:3,G:3}");
		System.out.println("Nodo G --> {G:0,H:1,L:2,I:3}");
		System.out.println("Nodo H --> {H:0,L:1,I:2}");
		System.out.println("Nodo I --> {I:0}");
		System.out.println("Nodo L --> {L:0,I:1}");

		
		System.out.println();
		System.out.println("Il tuo programma stampa: ");
		GraphServices.distances(gra); 
		System.out.println();

		
	}
}
