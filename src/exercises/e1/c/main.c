#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include <time.h>

// prototipi 
void launcher(int*, int);
int *randArray(int);
void printArray(int*, int);

void bubbleSort(int*, int);

int *randArray(int n) {
    srand(time(NULL));
    int *v = malloc(n*sizeof(int));
    for(int i = 0; i < n; i++) v[i] = rand();
    return v;
}

void launcher(int *a, int n) {
    printArray(a, n);
    printf("Lancio il Bubble Sort... ");
    clock_t begin = clock();
    bubbleSort(a, n);
    clock_t end = clock();
    printf("fatto. Tempo: %g msec.\n", (double)(end - begin) * 1000 / CLOCKS_PER_SEC);
    printArray(a, n);
    // t2-t1 è il tempo impiegato da bubbleSort
}

void printArray(int *a, int n) {
    printf("L'array ha %d elementi\n", n);
    for(int i = 0; i < n; i++) printf("array[%d] = %d\n", i, a[i]);
    printf("Fine array.\n");
}

// Esercizio 2
void e2(int argc, char *argv[]) {
    int n = argc;
    int *v = malloc(n * sizeof(int));
    if (!v) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }
    for (int i = 0; i < n; i++) {
        v[i] = atoi(argv[i]);
    }
    launcher(v, n);
    free(v);
}

// Esercizio 3
void e3(int n) {
    int *v = randArray(n);
    launcher(v, n);
    free(v);
}

int main(int argc, char *argv[]) {
    if (argc > 1 && strcmp(argv[1], "rnd") == 0) {
        if (argc < 3) {
            fprintf(stderr, "Uso: %s rnd <dimensione>\n", argv[0]);
            return 1;
        }
        int n = atoi(argv[2]);
        e3(n);
    } else if (argc > 1) {
        e2(argc-1, argv+1);
    } else {
        fprintf(stderr, "Uso:\n  %s rnd <n> -> genera array casuale\n  %s <num1> <num2> ... -> ordina array inserito\n", argv[0], argv[0]);
        return 1;
    }
    return 0;
}
