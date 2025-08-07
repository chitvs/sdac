#include <stdlib.h>
#include <stdio.h>
#include <limits.h>
#include "tree.h"

struct tree {
    int key;
    void * value;
    struct tree * left;
    struct tree * right;
};

tree * build_tree_1() {
    tree * n6 = (tree*)calloc(sizeof(tree), 1);
    n6->key = 6;
    tree * n3 = (tree*)calloc(sizeof(tree), 1);
    n3->key = 3;
    tree * n12 = (tree*)calloc(sizeof(tree), 1);
    n12->key = 12;
    tree * n1 = (tree*)calloc(sizeof(tree), 1);
    n1->key = 1;
    tree * n5 = (tree*)calloc(sizeof(tree), 1);
    n5->key = 5;
    tree * n7 = (tree*)calloc(sizeof(tree), 1);
    n7->key = 7;
    tree * n15 = (tree*)calloc(sizeof(tree), 1);
    n15->key = 15;
    n6->left = n3;
    n6->right = n12;
    n3->left = n1;
    n3->right = n5;
    n12->left = n7;
    n12->right = n15;
    return n6;
}

tree * build_tree_2() {
    tree * n6 = (tree*)calloc(sizeof(tree), 1);
    n6->key = 6;
    tree * n3 = (tree*)calloc(sizeof(tree), 1);
    n3->key = 3;
    tree * n12 = (tree*)calloc(sizeof(tree), 1);
    n12->key = 12;
    tree * n1 = (tree*)calloc(sizeof(tree), 1);
    n1->key = 1;
    tree * n5 = (tree*)calloc(sizeof(tree), 1);
    n5->key = 5;
    tree * n7 = (tree*)calloc(sizeof(tree), 1);
    n7->key = 7;
    tree * n15 = (tree*)calloc(sizeof(tree), 1);
    n15->key = 15;
    n6->right = n3;
    n6->left = n12;
    n3->left = n1;
    n3->right = n5;
    n12->left = n7;
    n12->right = n15;
    return n6;
}

tree * build_tree_3() {
    tree * n6 = (tree*)calloc(sizeof(tree), 1);
    n6->key = 6;
    tree * n3 = (tree*)calloc(sizeof(tree), 1);
    n3->key = 3;
    tree * n12 = (tree*)calloc(sizeof(tree), 1);
    n12->key = 12;
    tree * n1 = (tree*)calloc(sizeof(tree), 1);
    n1->key = 1;
    tree * n5 = (tree*)calloc(sizeof(tree), 1);
    n5->key = 5;
    n6->left = n3;
    
    n3->left = n1;
    n3->right = n5;
    n5->right = n12;
    return n6;
}

void tree_delete(tree * tt) {
    tree * t = tt;
    if (t == NULL)
        return;
    tree_delete(t->left);
    tree_delete(t->right);
    free(t);
}

static int tree_is_bst_helper(tree * node, int has_min, int min_val, int has_max, int max_val) {
    if (node == NULL) {
        return 1; 
    }
    
    if ((has_min && node->key <= min_val) || 
        (has_max && node->key >= max_val)) {
        return 0;
    }
    
    return tree_is_bst_helper(node->left, has_min, min_val, 1, node->key) && 
           tree_is_bst_helper(node->right, 1, node->key, has_max, max_val);
}

int tree_is_bst(tree * tt) {
    return tree_is_bst_helper(tt, 0, 0, 0, 0);
}

static int tree_height(tree * node) {
    if (node == NULL) {
        return -1; 
    }
    
    int left_height = tree_height(node->left);
    int right_height = tree_height(node->right);
    
    return 1 + (left_height > right_height ? left_height : right_height);
}

static int abs_val(int x) {
    return x < 0 ? -x : x;
}

static int tree_is_balanced_helper(tree * node) {
    if (node == NULL) {
        return 1;
    }
    
    int left_height = tree_height(node->left);
    int right_height = tree_height(node->right);
    
    return abs_val(left_height - right_height) <= 1 && 
           tree_is_balanced_helper(node->left) && 
           tree_is_balanced_helper(node->right);
}

int tree_is_balanced(tree * tt) {
    return tree_is_balanced_helper(tt);
}

int tree_is_avl(tree * tt) {
    return tree_is_bst(tt) && tree_is_balanced(tt);
}