#include <stdio.h>
#include <stdlib.h>
#include "sort.h"

void swap_value(int* array, int i, int j){
    int tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
}

void arraycopy(array *src, int srcPos, array *dest, int destPos, int length){
    for (int i = 0; i < length; i++){
        dest->arr[destPos + i] = src->arr[srcPos + i];
    }
}

void betterMerge(int *a, int left, int midm1, int right, int *tmp) {
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

void mergeSort(array *a) {
    int *arr = a->arr;
    int n = a->size;
    int runSize = 1;
    int *tmp = (int*)malloc(sizeof(int) * n);
    
    while(runSize < n) {
        int l = 0, mm1 = runSize - 1, r;
        
        while(mm1 < n - 1) {
            r = (mm1 + runSize < n - 1) ? mm1 + runSize : n - 1;
            betterMerge(arr, l, mm1, r, tmp);
            l = r + 1;
            mm1 = l + runSize - 1;
        }
        runSize <<= 1;
    }
    
    free(tmp);
}

void downHeap(int *a, int n, int i) {
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

void array2heap(int *a, int n) {
    if(n < 2) return;
    
    for(int i = (n - 2) / 2; i >= 0; i--) {
        downHeap(a, n, i);
    }
}

void heapSort(array *a) {
    int *arr = a->arr;
    int n = a->size;
    
    array2heap(arr, n);
    
    for(int i = n - 1; i >= 1; i--) {
        swap_value(arr, 0, i);
        downHeap(arr, i, 0);
    }
}

void insertionSort(array *a) {
    int n = a->size, j;
    for (int i = 1; i < n; i++){
        int temp = a->arr[i];
        for (j = i - 1; (j >= 0) && (a->arr[j] > temp); j--){
            a->arr[j + 1] = a->arr[j];
        }
        a->arr[j + 1] = temp;
    }
}

void selectionSort(array *a) {
    int n = a->size;
    for (int i = 0; i < n - 1; i++) {
        int min = i;
        for (int j = i + 1; j < n; j++){
            if(a->arr[j] < a->arr[min]) min = j;
        }
        swap_value(a->arr, i, min);
    }
}

int partition(int *a, int left, int right) {
    int l = left + 1, r = right;
    int p = right; // random pivot would be better
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

void quickSortRecursive(int *a, int first, int last) {
    if(first >= last) return;
    
    int p = partition(a, first, last);
    quickSortRecursive(a, first, p - 1);
    quickSortRecursive(a, p + 1, last);
}

void quickSort(array *a) {
    quickSortRecursive(a->arr, 0, a->size - 1);
}

void bucketSort2(int *a, int n, int bit, int *tmp0, int *tmp1) {
    int i0 = 0, i1 = 0;
    int mask = 0x1 << bit;
    
    for(int i = 0; i < n; i++) {
        if(a[i] & mask) {
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

void radixSort(array *a) {
    int n = a->size;
    int *tmp0 = (int*)malloc(n * sizeof(int));
    int *tmp1 = (int*)malloc(n * sizeof(int));
    
    for(int bit = 0; bit < (int)sizeof(int) * 8; bit++) {
        bucketSort2(a->arr, n, bit, tmp0, tmp1);
    }
    
    free(tmp0);
    free(tmp1);
}

void bucketSort(array *a) {
    int max = 0, min = 0, n = a->size;
    
    for(int i = 1; i < n; i++) {
        if(a->arr[i] > a->arr[max]) {
            max = i;
        } else if(a->arr[i] < a->arr[min]) {
            min = i;
        }
    }
    
    int minv = a->arr[min];
    int size = a->arr[max] - a->arr[min] + 1;
    
    int *tmp = (int*)malloc(sizeof(int) * size);
    for(int i = 0; i < size; i++) {
        tmp[i] = 0;
    }
    
    for(int i = 0; i < n; i++) {
        tmp[a->arr[i] - minv]++;
    }
    
    int k = 0;
    for(int i = 0; i < size; i++) {
        while((tmp[i])-- > 0) {
            a->arr[k++] = i + minv;
        }
    }
    
    free(tmp);
}