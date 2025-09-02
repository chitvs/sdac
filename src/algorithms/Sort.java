import java.util.ArrayList;
import java.util.Collections;

/*
 * Class containing the main sorting algorithms.
 * Each method sorts the input array in ascending order.
 */
public class Sort {
    
    /* ====================== INSERTION SORT ====================== */
    // Time Complexity: O(n^2) in the worst and average case, O(n) in the best case (already sorted array).
    // Space Complexity: O(1) (in-place algorithm).
    public static void insertionSort(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            
            // Shift elements greater than key to the right
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            
            // Place key at the correct position
            array[j + 1] = key;
        }
    }
    
    /* ====================== MERGE SORT ====================== */
    // Time Complexity: O(n log n) in all cases.
    // Space Complexity: O(n) (uses temporary arrays for merging).
    private static void merge(int[] array, int p, int q, int r) {
        int nL = q - p + 1;   // length of left half
        int nR = r - q;       // length of right half
        
        // Temporary arrays
        int[] L = new int[nL];
        int[] R = new int[nR];
        
        // Copy data into temp arrays
        for (int i = 0; i < nL; i++) {
            L[i] = array[p + i];
        }
        for (int j = 0; j < nR; j++) {
            R[j] = array[q + 1 + j];
        }
        
        // Merge the temp arrays back into array[p..r]
        int i = 0, j = 0, k = p;
        while (i < nL && j < nR) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements, if any
        while (i < nL) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < nR) {
            array[k] = R[j];
            j++;
            k++;
        }
    }
    
    public static void mergeSort(int[] array, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSort(array, p, q);       // Sort left half
        mergeSort(array, q + 1, r);   // Sort right half
        merge(array, p, q, r);        // Merge the two halves
    }
    
    /* ====================== HEAP SORT ====================== */
    // Time Complexity: O(n log n) in all cases.
    // Space Complexity: O(1) (in-place algorithm).
    private static int parent(int i) { return (i - 1) / 2; }
    private static int left(int i) { return 2 * i + 1; }
    private static int right(int i) { return 2 * i + 2; }
    
    private static void maxHeapify(int[] array, int heapSize, int i) {
        int l = left(i);
        int r = right(i);
        int largest = i;
        
        if (l < heapSize && array[l] > array[largest]) largest = l;
        if (r < heapSize && array[r] > array[largest]) largest = r;
        
        if (largest != i) {
            swap(array, i, largest);
            maxHeapify(array, heapSize, largest);
        }
    }
    
    private static void buildMaxHeap(int[] array) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(array, n, i);
        }
    }
    
    public static void heapSort(int[] array) {
        int n = array.length;
        buildMaxHeap(array);
        
        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            maxHeapify(array, i, 0);
        }
    }
    
    /* ====================== QUICK SORT ====================== */
    // Time Complexity: O(n log n) on average, O(n^2) in worst case (already sorted array with poor pivot).
    // Space Complexity: O(log n) due to recursion stack.
    private static int partition(int[] array, int p, int r) {
        int x = array[r]; // pivot
        int i = p - 1;
        
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                swap(array, i, j);
            }
        }
        
        swap(array, i + 1, r); // put pivot into correct position
        return i + 1;
    }
    
    public static void quickSort(int[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            quickSort(array, q + 1, r);
        }
    }
    
    /* ====================== COUNTING SORT ====================== */
    // Time Complexity: O(n + k)
    // Space Complexity: O(n + k)
    public static void countingSort(int[] A, int n, int k) {
        int[] B = new int[n];      // output array
        int[] C = new int[k + 1];  // counting array
        
        // Initialize
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }
        
        // Count occurrences
        for (int j = 0; j < n; j++) {
            C[A[j]] = C[A[j]] + 1;
        }
        
        // Cumulative sum
        for (int i = 1; i <= k; i++) {
            C[i] = C[i] + C[i - 1];
        }
        
        // Build output
        for (int j = n - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]] = C[A[j]] - 1;
        }
        
        // Copy back
        for (int i = 0; i < n; i++) {
            A[i] = B[i];
        }
    }
    
    /* ====================== RADIX SORT ====================== */
    // Time Complexity: O(d * (n + k)) where d = number of digits, k = radix (base, usually 10).
    // Space Complexity: O(n + k).
    private static void countingSortByDigit(int[] A, int n, int exp) {
        int[] B = new int[n + 1];   // output (1..n)
        int[] C = new int[10];      // digits 0..9
        
        for (int i = 0; i < 10; i++) C[i] = 0;
        
        for (int j = 1; j <= n; j++) {
            int digit = (A[j - 1] / exp) % 10;
            C[digit] = C[digit] + 1;
        }
        
        for (int i = 1; i < 10; i++) {
            C[i] = C[i] + C[i - 1];
        }
        
        for (int j = n; j >= 1; j--) {
            int digit = (A[j - 1] / exp) % 10;
            B[C[digit]] = A[j - 1];
            C[digit] = C[digit] - 1;
        }
        
        for (int i = 1; i <= n; i++) {
            A[i - 1] = B[i];
        }
    }
    
    public static void radixSort(int[] A, int n, int d) {
        int exp = 1; // 10^0
        for (int i = 1; i <= d; i++) {
            countingSortByDigit(A, n, exp);
            exp = exp * 10;
        }
    }
    
    /* ====================== BUCKET SORT ====================== */
    // Time Complexity: O(n + n^2) in the worst case, O(n) on average if input is uniformly distributed.
    // Space Complexity: O(n).
    public static void bucketSort(float[] A, int n) {
        // Create n empty buckets
        ArrayList<Float>[] B = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            B[i] = new ArrayList<>();
        }
        
        // Insert A[i] into the right bucket
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (n * A[i]); // assume A[i] ∈ [0,1)
            B[bucketIndex].add(A[i]);
        }
        
        // Sort each bucket (using insertion sort or here Collections.sort)
        for (int i = 0; i < n; i++) {
            Collections.sort(B[i]);
        }
        
        // Concatenate buckets
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (float val : B[i]) {
                A[index++] = val;
            }
        }
    }
    
    /* ====================== SWAP ====================== */
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}