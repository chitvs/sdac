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
        n6.right = n3;
        n6.left = n12;
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
        n5.right = n12;
        return n6;
    }
    
    private boolean isBSTHelper(Tree node, Integer minVal, Integer maxVal) {
        if (node == null) {
            return true;
        }
        
        if ((minVal != null && node.key <= minVal) || 
            (maxVal != null && node.key >= maxVal)) {
            return false;
        }
        
        return isBSTHelper(node.left, minVal, node.key) && 
               isBSTHelper(node.right, node.key, maxVal);
    }
    
    public boolean isBST() {
        return isBSTHelper(this, null, null);
    }
    
    private int height(Tree node) {
        if (node == null) {
            return -1; 
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }
    
    private boolean isBalancedHelper(Tree node) {
        if (node == null) {
            return true;
        }
        
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        
        return Math.abs(leftHeight - rightHeight) <= 1 && 
               isBalancedHelper(node.left) && 
               isBalancedHelper(node.right);
    }
    
    public boolean isBalanced() {
        return isBalancedHelper(this);
    }
    
    private BalanceInfo checkBalanceEfficient(Tree node) {
        if (node == null) {
            return new BalanceInfo(true, -1);
        }
        
        BalanceInfo leftInfo = checkBalanceEfficient(node.left);
        BalanceInfo rightInfo = checkBalanceEfficient(node.right);
        
        boolean isBalanced = leftInfo.isBalanced && rightInfo.isBalanced && 
                           Math.abs(leftInfo.height - rightInfo.height) <= 1;
        
        int height = 1 + Math.max(leftInfo.height, rightInfo.height);
        
        return new BalanceInfo(isBalanced, height);
    }
    
    public boolean isAVL() {
        return isBST() && isBalanced();
    }
    
    private static class BalanceInfo {
        boolean isBalanced;
        int height;
        
        BalanceInfo(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }
}