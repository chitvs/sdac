#include "mat_sparsa_lista.h"
#include <stdlib.h>
#include <stdio.h>

typedef struct elem {
    int i;
    int j;
    int x;
    struct elem* next;
} elem;

struct matrice_sparsa {
    int m;
    int n;
    elem* head;
};

matrice_sparsa* matrice_sparsa_new(int m, int n) {
    matrice_sparsa* mat = (matrice_sparsa*)malloc(sizeof(matrice_sparsa));
    mat->m = m;
    mat->n = n;
    mat->head = NULL;
    return mat;
}

void matrice_sparsa_delete(matrice_sparsa* mat) {
    elem* curr = mat->head;
    while (curr != NULL) {
        elem* temp = curr;
        curr = curr->next;
        free(temp);
    }
    free(mat);
}

int get_num_row(matrice_sparsa* mat) {
    return mat->m;
}

int get_num_col(matrice_sparsa* mat) {
    return mat->n;
}

void mat_set(matrice_sparsa* mat, int i, int j, int x) {
    if (i < 0 || i >= mat->m || j < 0 || j >= mat->n)
        return;

    elem *prev = NULL;
    elem *curr = mat->head;

    while (curr != NULL && (curr->i < i || (curr->i == i && curr->j < j))) {
        prev = curr;
        curr = curr->next;
    }

    if (curr != NULL && curr->i == i && curr->j == j) {
        if (x == 0) {
            if (prev == NULL)
                mat->head = curr->next;
            else
                prev->next = curr->next;
            free(curr);
        } else {
            curr->x = x;
        }
    } else if (x != 0) {
        elem* new_elem = (elem*)malloc(sizeof(elem));
        new_elem->i = i;
        new_elem->j = j;
        new_elem->x = x;
        new_elem->next = curr;

        if (prev == NULL)
            mat->head = new_elem;
        else
            prev->next = new_elem;
    }
}

int mat_get(matrice_sparsa* mat, int i, int j) {
    elem* curr = mat->head;
    while (curr != NULL) {
        if (curr->i == i && curr->j == j)
            return curr->x;
        curr = curr->next;
    }
    return 0;
}

void mat_print(matrice_sparsa* mat) {
    for (int i = 0; i < mat->m; i++) {
        for (int j = 0; j < mat->n; j++) {
            printf("%d ", mat_get(mat, i, j));
        }
        printf("\n");
    }
}
