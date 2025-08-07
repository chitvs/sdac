public class BST<V> {
    
    private class Node {
        private int key;
        private V value;
        private Node left;
        private Node right;
        
        public Node(int key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    
    private Node root;
    
    public BST(int key, V value) {
        this.root = new Node(key, value);
    }
    
    public void insert(int k, V v) {
        root = insertRec(root, k, v);
    }
    
    private Node insertRec(Node node, int k, V v) {
        if (node == null) {
            return new Node(k, v);
        }
        
        if (k < node.key) {
            node.left = insertRec(node.left, k, v);
        } else if (k > node.key) {
            node.right = insertRec(node.right, k, v);
        } else {
            // Chiave già esistente, sovrascrive il valore
            node.value = v;
        }
        
        return node;
    }
    
    public V find(int k) {
        Node result = findRec(root, k);
        return result != null ? result.value : null;
    }
    
    private Node findRec(Node node, int k) {
        if (node == null || node.key == k) {
            return node;
        }
        
        if (k < node.key) {
            return findRec(node.left, k);
        } else {
            return findRec(node.right, k);
        }
    }
    
    public int findMin() {
        if (root == null) {
            throw new RuntimeException("BST vuoto");
        }
        return findMinRec(root).key;
    }
    
    private Node findMinRec(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public void removeMin() {
        if (root == null) {
            return;
        }
        root = removeMinRec(root);
    }
    
    private Node removeMinRec(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMinRec(node.left);
        return node;
    }
    
    public void remove(int k) {
        root = removeRec(root, k);
    }
    
    private Node removeRec(Node node, int k) {
        if (node == null) {
            return null;
        }
        
        if (k < node.key) {
            node.left = removeRec(node.left, k);
        } else if (k > node.key) {
            node.right = removeRec(node.right, k);
        } else {
            // Nodo trovato, da rimuovere
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // Nodo con due figli
                Node successor = findMinRec(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = removeMinRec(node.right);
            }
        }
        
        return node;
    }
    
    public void print() {
        System.out.println("BST structure:");
        printRec(root, "", true);
        System.out.println();
    }
    
    private void printRec(Node node, String prefix, boolean isLast) {
        if (node != null) {
            System.out.println(prefix + (isLast ? "└── " : "├── ") + node.key + ":" + node.value);
            
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    printRec(node.left, prefix + (isLast ? "    " : "│   "), node.right == null);
                }
                if (node.right != null) {
                    printRec(node.right, prefix + (isLast ? "    " : "│   "), true);
                }
            }
        }
    }
    
    public int predecessor(int k) {
        Node pred = predecessorRec(root, k, null);
        if (pred == null) {
            throw new RuntimeException("Nessun predecessore trovato per la chiave " + k);
        }
        return pred.key;
    }
    
    private Node predecessorRec(Node node, int k, Node predecessor) {
        if (node == null) {
            return predecessor;
        }
        
        if (k <= node.key) {
            // k è minore o uguale alla chiave del nodo corrente
            // il predecessore è nel sottoalbero sinistro
            return predecessorRec(node.left, k, predecessor);
        } else {
            // k è maggiore della chiave del nodo corrente
            // il nodo corrente potrebbe essere il predecessore
            // ma potrebbe esserci uno migliore nel sottoalbero destro
            return predecessorRec(node.right, k, node);
        }
    }
}