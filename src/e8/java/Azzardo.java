import java.util.Arrays;

public class Azzardo {

    public static class GiocataVincente {
        int start; // Indice di partenza della giocata vincente
        int end; // Indice di fine della giocata vincente
        int maxWin; // Vincita massima della giocata vincente
        int[] subPlay; // Sequenza della giocata vincente

        public GiocataVincente(int start, int end, int maxWin, int[] subPlay) {
            this.start = start;
            this.end = end;
            this.maxWin = maxWin;
            this.subPlay = subPlay;
        }
    }

    public static GiocataVincente bruteForce(int[] listaPartite){
        if (listaPartite == null || listaPartite.length == 0) {
            return new GiocataVincente(-1, -1, 0, new int[]{});
        }

        int maxWin = Integer.MIN_VALUE;
        int start = 0, end = 0;

        for (int i = 0; i < listaPartite.length; i++) {
            for (int j = i; j < listaPartite.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += listaPartite[k];
                }
                if (sum > maxWin) {
                    maxWin = sum;
                    start = i;
                    end = j;
                }
            }
        }

        int[] subPlay = Arrays.copyOfRange(listaPartite, start, end + 1);
        return new GiocataVincente(start, end, maxWin, subPlay);
    }
    


    public static GiocataVincente faster(int[] listaPartite){
        if (listaPartite == null || listaPartite.length == 0) {
            return new GiocataVincente(-1, -1, 0, new int[]{});
        }

        int maxWin = Integer.MIN_VALUE;
        int start = 0, end = 0;

        for (int i = 0; i < listaPartite.length; i++) {
            int sum = 0;
            for (int j = i; j < listaPartite.length; j++) {
                sum += listaPartite[j];
                if (sum > maxWin) {
                    maxWin = sum;
                    start = i;
                    end = j;
                }
            }
        }

        int[] subPlay = Arrays.copyOfRange(listaPartite, start, end + 1);
        return new GiocataVincente(start, end, maxWin, subPlay);
    }



    public static GiocataVincente bolt(int[] listaPartite){
        if (listaPartite == null || listaPartite.length == 0) {
            return new GiocataVincente(-1, -1, 0, new int[]{});
        }
        return maxsubPlayHelper(listaPartite, 0, listaPartite.length - 1);
    }
    private static GiocataVincente maxsubPlayHelper(int[] listaPartite, int left, int right) {
        if (left == right) {
            return new GiocataVincente(left, right, listaPartite[left], new int[]{listaPartite[left]});
        }

        int mid = (left + right) / 2;

        GiocataVincente leftResult = maxsubPlayHelper(listaPartite, left, mid);
        GiocataVincente rightResult = maxsubPlayHelper(listaPartite, mid + 1, right);
        GiocataVincente crossResult = maxCrossingsubPlay(listaPartite, left, mid, right);

        if (leftResult.maxWin >= rightResult.maxWin && leftResult.maxWin >= crossResult.maxWin)
            return leftResult;
        else if (rightResult.maxWin >= leftResult.maxWin && rightResult.maxWin >= crossResult.maxWin)
            return rightResult;
        else
            return crossResult;
    }

    private static GiocataVincente maxCrossingsubPlay(int[] listaPartite, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;

        for (int i = mid; i >= left; i--) {
            sum += listaPartite[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;

        for (int j = mid + 1; j <= right; j++) {
            sum += listaPartite[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        int totalSum = leftSum + rightSum;
        int[] subPlay = Arrays.copyOfRange(listaPartite, maxLeft, maxRight + 1);
        return new GiocataVincente(maxLeft, maxRight, totalSum, subPlay);
    }

    public static GiocataVincente goldenSolution(int[] listaPartite) {
        if (listaPartite == null || listaPartite.length == 0) {
            return new GiocataVincente(-1, -1, 0, new int[]{});
        }

        int maxSoFar = listaPartite[0];
        int maxEndingHere = listaPartite[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < listaPartite.length; i++) {
            if (maxEndingHere < 0) {
                maxEndingHere = listaPartite[i];
                tempStart = i;
            } else {
                maxEndingHere += listaPartite[i];
            }

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        int[] subPlay = Arrays.copyOfRange(listaPartite, start, end + 1);
        return new GiocataVincente(start, end, maxSoFar, subPlay);
    }
}
