public class MatriceSparsa {
    private class Elem {
        int i;
        int j;
        int x;
        Elem next;

        Elem(int i, int j, int x, Elem next) {
            this.i = i;
            this.j = j;
            this.x = x;
            this.next = next;
        }
    }

    private int m;
    private int n;
    private Elem head;

    public MatriceSparsa(int m, int n) {
        this.m = m;
        this.n = n;
        this.head = null;
    }

    public int getNumRow() {
        return m;
    }

    public int getNumCol() {
        return n;
    }

    public void set(int i, int j, int x) {
        if (i < 0 || i >= m || j < 0 || j >= n)
            return;

        Elem prev = null;
        Elem curr = head;

        while (curr != null && (curr.i < i || (curr.i == i && curr.j < j))) {
            prev = curr;
            curr = curr.next;
        }

        if (curr != null && curr.i == i && curr.j == j) {
            if (x == 0) {
                if (prev == null)
                    head = curr.next;
                else
                    prev.next = curr.next;
            } else {
                curr.x = x;
            }
        } else if (x != 0) {
            Elem newElem = new Elem(i, j, x, curr);
            if (prev == null)
                head = newElem;
            else
                prev.next = newElem;
        }
    }

    public int get(int i, int j) {
        Elem curr = head;
        while (curr != null) {
            if (curr.i == i && curr.j == j)
                return curr.x;
            curr = curr.next;
        }
        return 0;
    }

    public MatriceSparsa add(MatriceSparsa mat1, MatriceSparsa mat2) {
        if (mat1.getNumRow() != mat2.getNumRow() || mat1.getNumCol() != mat2.getNumCol()) {
            return null;
        }
        MatriceSparsa matAdd = new MatriceSparsa(mat1.getNumRow(), mat1.getNumCol());
        for (int i = 0; i < mat1.getNumRow(); i++) {
            for (int j = 0; j < mat1.getNumCol(); j++) {
                int sum = mat1.get(i, j) + mat2.get(i, j);
                matAdd.set(i, j, sum);
            }
        }
        return matAdd;
    }

    public MatriceSparsa tra(MatriceSparsa mat) {
        MatriceSparsa matTra = new MatriceSparsa(mat.getNumCol(), mat.getNumRow());
        for (int i = 0; i < mat.getNumRow(); i++) {
            for (int j = 0; j < mat.getNumCol(); j++) {
                int val = mat.get(i, j);
                if (val != 0) {
                    matTra.set(j, i, val);
                }
            }
        }
        return matTra;
    }

    public MatriceSparsa mul(MatriceSparsa mat1, MatriceSparsa mat2) {
        if (mat1.getNumCol() != mat2.getNumRow()) {
            return null;
        }
        MatriceSparsa result = new MatriceSparsa(mat1.getNumRow(), mat2.getNumCol());
        for (int i = 0; i < mat1.getNumRow(); i++) {
            for (int j = 0; j < mat2.getNumCol(); j++) {
                int sum = 0;
                for (int k = 0; k < mat1.getNumCol(); k++) {
                    sum += mat1.get(i, k) * mat2.get(k, j);
                }
                if (sum != 0) {
                    result.set(i, j, sum);
                }
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(get(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}