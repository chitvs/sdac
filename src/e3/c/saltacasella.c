#include <stdio.h>
#include <stdlib.h>

/* #define DBG
*/
int saltacasella(int** mat, int i, int j, int n, int N);
static int driver(int** mat, int N);
static void stampamatrice(int** mat, int N);

long Cont = 0;

int saltacasella(int** mat, int i, int j, int n, int N) {
    static int max = 0;
    if((i < 0) || (i >= N) || (j < 0) || (j >= N)) return 2; /* fuori */
    if(mat[i][j] != 0) return 1; /* occupato */
    mat[i][j] = n;
    Cont++;
    if(n > max) {
        max = n;
        printf("max = %4d\tCont = %ld\n", max, Cont);
    }
    if(Cont % 500000000 == 0 ) printf("Cont = %ld\n", Cont);
    if(n == N*N) {
        printf("\nvittoria - Cont = %ld\n", Cont);
        return 0;
    }
    #ifdef DBG
        stampamatrice(mat, N);
        getchar();
        int h = (int)((double)n*100/(N*N));
        printf("%4d%%\tn = % 4d, i = %2d, j = %2d, %4d\r", h, n, i, j, max);
    #endif
    n++;
    if(saltacasella(mat, i+3, j, n, N) == 0) return 0;
    if(saltacasella(mat, i+2, j+2, n, N) == 0) return 0;
    if(saltacasella(mat, i, j+3, n, N) == 0) return 0;
    if(saltacasella(mat, i-2, j+2, n, N) == 0) return 0;
    if(saltacasella(mat, i-3, j, n, N) == 0) return 0;
    if(saltacasella(mat, i-2, j-2, n, N) == 0) return 0;
    if(saltacasella(mat, i, j-3, n, N) == 0) return 0;
    if(saltacasella(mat, i+2, j-2, n, N) == 0) return 0;
    mat[i][j] = 0;
    return 3; /* no soluzione */
}


static int driver(int** mat, int N) {
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++) {
            #ifdef DBG
                getchar();
            #endif
            if(saltacasella(mat, i, j, 1, N) == 0) return 0;
        }
    return(3);
}

static void stampamatrice(int** mat, int N) {
    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) printf("%4d", mat[i][j]);
        printf("\n");
    }
}

int main(int argc, char **argv) {
    if(argc != 2) {
        printf("./saltacasella <size>");
        exit(3);
    }
    int N = atoi(argv[1]);
    int **mat = malloc(N * sizeof(int*));
    for(int i = 0; i < N; i++) mat[i] = calloc(N, sizeof(int));
    if(driver(mat, N) == 0)
        stampamatrice(mat, N);
    else
        printf("\nnon trovato\n");
    return 0;
}