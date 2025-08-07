public class Tree {
    private int key;
    private Tree left;
    private Tree right;
    
    public Tree(int key) {
        this.key = key;
    }
    
    public static Tree build_1() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);
        Tree n7 = new Tree(7);
        Tree n15 = new Tree(15);
        n6.left = n3;
        n6.right = n12;
        n3.left = n1;
        n3.right = n5;
        n12.left = n7;
        n12.right = n15;
        return n6;
    }
    
    public static Tree build_2() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);
        Tree n7 = new Tree(7);
        Tree n15 = new Tree(15);
        n6.right = n3;  // Struttura non-BST: 3 a destra di 6
        n6.left = n12;  // Struttura non-BST: 12 a sinistra di 6
        n3.left = n1;
        n3.right = n5;
        n12.left = n7;
        n12.right = n15;
        return n6;
    }
    
    public static Tree build_3() {
        Tree n6 = new Tree(6);
        Tree n3 = new Tree(3);
        Tree n12 = new Tree(12);
        Tree n1 = new Tree(1);
        Tree n5 = new Tree(5);
        n6.left = n3;
        n3.left = n1;
        n3.right = n5;
        n5.right = n12;  // Questo rende l'albero sbilanciato
        return n6;
    }
    
    public boolean isBST() {
        return isBSTRec(this, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    private boolean isBSTRec(Tree node, int min, int max) {
        if (node == null) {
            return true;
        }
        
        if (node.key <= min || node.key >= max) {
            return false;
        }
        
        return isBSTRec(node.left, min, node.key) && 
               isBSTRec(node.right, node.key, max);
    }
    
    public boolean isBalanced() {
        return checkBalanced(this) != -1;
    }
    
    private int checkBalanced(Tree node) {
        if (node == null) {
            return 0;
        }
        
        int leftHeight = checkBalanced(node.left);
        if (leftHeight == -1) {
            return -1; // Sottoalbero sinistro non bilanciato
        }
        
        int rightHeight = checkBalanced(node.right);
        if (rightHeight == -1) {
            return -1; // Sottoalbero destro non bilanciato
        }
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Nodo corrente non bilanciato
        }
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    public boolean isAVL() {
        return isAVLRec(this).isAVL;
    }
    
    private class AVLResult {
        boolean isAVL;
        boolean isBST;
        int height;
        int min;
        int max;
        
        public AVLResult(boolean isAVL, boolean isBST, int height, int min, int max) {
            this.isAVL = isAVL;
            this.isBST = isBST;
            this.height = height;
            this.min = min;
            this.max = max;
        }
    }
    
    private AVLResult isAVLRec(Tree node) {
        if (node == null) {
            return new AVLResult(true, true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        
        AVLResult leftResult = isAVLRec(node.left);
        AVLResult rightResult = isAVLRec(node.right);
        
        // Verifica se è un BST
        boolean isBST = leftResult.isBST && rightResult.isBST &&
                       (node.left == null || leftResult.max < node.key) &&
                       (node.right == null || rightResult.min > node.key);
        
        // Verifica se è bilanciato
        boolean isBalanced = leftResult.isAVL && rightResult.isAVL &&
                            Math.abs(leftResult.height - rightResult.height) <= 1;
        
        // Calcola altezza
        int height = Math.max(leftResult.height, rightResult.height) + 1;
        
        // Calcola min e max
        int min = (node.left == null) ? node.key : leftResult.min;
        int max = (node.right == null) ? node.key : rightResult.max;
        
        boolean isAVL = isBST && isBalanced;
        
        return new AVLResult(isAVL, isBST, height, min, max);
    }
}