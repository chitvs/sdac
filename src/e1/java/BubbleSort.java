import java.util.Random;

// Implementazione del Bubble Sort in Java 
class BubbleSort { 
	
    void bubbleSort(int a[]) { 
        int n = a.length; 
        for (int i = 0; i < n-1; i++) 
            for (int j = 0; j < n-i-1; j++) 
				// Se a[j] > a[j+1] allora scambia
                if (a[j] > a[j+1]) { 
                    int temp = a[j]; 
                    a[j] = a[j+1]; 
                    a[j+1] = temp; 
                } 
    } 
  
    // Stampa array
    void printArray(int arr[]) { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 

    // Esercizio 2
    static void e2(String args[]) {
        int arr[] = new int[args.length];
        for (int i = 0; i < args.length; i++){
            arr[i] = Integer.parseInt(args[i]);
        } 
        BubbleSort ob = new BubbleSort(); 
        ob.bubbleSort(arr); 
        System.out.println("Sorted array"); 
        ob.printArray(arr); 
    }

    // Esercizio 3
    static void e3(int n) {
        int arr[] = new int[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++){
            arr[i] = rnd.nextInt(100);
        } 
        BubbleSort ob = new BubbleSort(); 
        ob.bubbleSort(arr); 
        System.out.println("Sorted array"); 
        ob.printArray(arr); 
    }
  
    // Driver per provare la classe 
    public static void main(String args[]) { 
        if (args[0].equals("rnd")) {
            long starting = System.currentTimeMillis();
            e3(Integer.parseInt(args[1]));
            long ending = System.currentTimeMillis();
            System.out.println("Elapsed time: " + (ending-starting) + "ms");
        }
        else {
            long starting = System.currentTimeMillis();
            e2(args);
            long ending = System.currentTimeMillis();
            System.out.println("Elapsed time: " + (ending-starting) + "ms");
        }
    } 
} 