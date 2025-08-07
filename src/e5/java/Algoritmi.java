import java.util.Arrays;

public class Algoritmi {
    
     
    public static void permuta_negativi_positivi(float [] a){
        int j = 0;
        for (int i=0;i<a.length;i++){
            if (a[i] < 0){
                float temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j++;
            }
        }
        return;
    }
    
    public static void bandiera(char[] a){
        int j = 0;
        for (int i=0; i<a.length;i++){
            if (a[i] == 'v'){
                char temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j++;
            }
        }
        for (int i=0; i<a.length;i++){
            if (a[i] == 'b'){
                char temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j++;
            }
        }
        return;
    }
    
       
    public static boolean isQuadratoLatino(int[][] m){
        int[] a = new int[m.length];
        boolean ret = false;
        for (int i=0;i<m.length;i++) {
            int k = 0;
            for (int j=0;j<m.length;j++){
                a[k] = m[i][j];
                k++;
            }
            if (is_duplicate(a)){
                return false;
            }
        }
        for (int i=0;i<m.length;i++) {
            int k = 0;
            for (int j=0;j<m.length;j++){
                a[k] = m[j][i];
                k++;
            }
            if (is_duplicate(a)){
                return false;
            }
        }
        return true;
    }

    public static boolean is_duplicate(int[] a){
        for (int i=0;i<a.length;i++){
            int k = 0;
            while(k<a.length){
                if (a[i]==a[k] && i!=k)
                    return true;
                k++;
            }
        }
        return false;
    }
    
        
    public static void primiKMin(float[] a, int k){
        Arrays.sort(a);
        int i=0;
        while (i<k){
            System.out.print(a[i] + "  ");
            i++;
        }
        System.out.println();
        return;
    }
}