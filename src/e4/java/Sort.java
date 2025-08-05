import java.util.*;

public class Sort {

    public void quickSortDefault(int[] array) {
        Arrays.sort(array);
    }

    private void swap_value(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    void betterMerge(int[] a, int left, int midm1, int right, int[] tmp) {
        int i = left, j = midm1 + 1, k = 0;
        
        while((i <= midm1) && (j <= right)) {
            if(a[i] < a[j]) {
                tmp[k++] = a[i++];
            } else {
                tmp[k++] = a[j++];
            }
        }
        
        for(int h = 0; h <= midm1 - i; h++) {
            a[right - h] = a[midm1 - h];
        }
        
        for(int h = 0; h < k; h++) {
            a[left + h] = tmp[h];
        }
    }

    public void mergeSort(int[] array) {
        int n = array.length;
        int runSize = 1;
        int[] tmp = new int[n];
        
        while(runSize < n) {
            int l = 0, mm1 = runSize - 1, r;
            
            while(mm1 < n - 1) {
                r = (mm1 + runSize < n - 1) ? mm1 + runSize : n - 1;
                betterMerge(array, l, mm1, r, tmp);
                l = r + 1;
                mm1 = l + runSize - 1;
            }
            runSize <<= 1;
        }
    }

    private void downHeap(int[] a, int n, int i) {
        int p = 2 * i + 1;
        int q, t;
        
        while(p < n) {
            q = p + 1;
            
            if(q >= n) {
                t = p;
            } else {
                t = (a[p] > a[q]) ? p : q;
            }
            
            if(a[i] < a[t]) {
                swap_value(a, i, t);
                i = t;
                p = 2 * i + 1;
            } else {
                return;
            }
        }
    }

    private void array2heap(int[] a, int n) {
        if(n < 2) return;
        
        for(int i = (n - 2) / 2; i >= 0; i--) {
            downHeap(a, n, i);
        }
    }

    public void heapSort(int[] array) {
        int n = array.length;
        array2heap(array, n);
        
        for(int i = n - 1; i >= 1; i--) {
            swap_value(array, 0, i);
            downHeap(array, i, 0);
        }
    }

    public void insertionSort(int[] array) {
        int n = array.length, j;
        for (int i = 1; i < n; i++) {
            int temp = array[i];
            for (j = i - 1; (j >= 0) && (array[j] > temp); j--){
                array[j + 1] = array[j];
            }
            array[j + 1] = temp; 
        }
    }

    public void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++){
                if(array[j] < array[min]) min = j;
            }
            swap_value(array, i, min);
        }
    }

    private int partition(int[] a, int left, int right) {
        Random ran = new Random();
        int l = left + 1, r = right;
        int p = left + ran.nextInt(right - left);
        int pivot = a[p];
        
        swap_value(a, left, p);
        
        while(l < r) {
            while((l < r) && (a[l] <= pivot)) l++;
            while(a[r] > pivot) r--;
            if(l < r) swap_value(a, l, r);
        }
        
        if(a[left] > a[r]) swap_value(a, left, r);
        
        return r;
    }

    private void quickSortRecursive(int[] a, int first, int last) {
        if(first >= last) return;
        
        int p = partition(a, first, last);
        quickSortRecursive(a, first, p - 1);
        quickSortRecursive(a, p + 1, last);
    }

    public void quickSort(int[] array) {
        quickSortRecursive(array, 0, array.length - 1);
    }

    private void bucketSort2(int[] a, int n, int bit, int[] tmp0, int[] tmp1) {
        int i0 = 0, i1 = 0;
        int mask = 0x1 << bit;
        
        for(int i = 0; i < n; i++) {
            if((a[i] & mask) != 0) {
                tmp1[i1++] = a[i];
            } else {
                tmp0[i0++] = a[i];
            }
        }
        
        for(int i = 0; i < i0; i++) {
            a[i] = tmp0[i];
        }
        
        int idx1 = 0;
        for(int i = i0; i < n; i++) {
            a[i] = tmp1[idx1++];
        }
    }

    public void radixSort(int[] array) {
        int n = array.length;
        int[] tmp0 = new int[n];
        int[] tmp1 = new int[n];
        
        for(int bit = 0; bit < 4 * 8; bit++) {
            bucketSort2(array, n, bit, tmp0, tmp1);
        }
    }

    public void bucketSort(int[] array) {
        int max = 0, min = 0, n = array.length;
        
        for(int i = 1; i < n; i++) {
            if(array[i] > array[max]) {
                max = i;
            } else if(array[i] < array[min]) {
                min = i;
            }
        }
        
        int minv = array[min];
        int size = array[max] - array[min] + 1;
        int[] tmp = new int[size];
        
        for(int i = 0; i < n; i++) {
            tmp[array[i] - minv]++;
        }
        
        int k = 0;
        for(int i = 0; i < size; i++) {
            while((tmp[i])-- > 0) {
                array[k++] = i + minv;
            }
        }
    }
}