// Source code is decompiled from a .class file using FernFlower decompiler.
public class Soluzione {
   public Soluzione() {
   }

   public static void stampaArray(char[] var0) {
      System.out.print("[");

      for(int var1 = 0; var1 < var0.length - 1; ++var1) {
         System.out.print(var0[var1] + ", ");
      }

      System.out.println(var0[var0.length - 1] + "]");
   }

   public static void permuta_negativi_positivi(float[] var0) {
      int var1 = 0;
      int var2 = var0.length - 1;

      while(var1 < var2) {
         float var3;
         if (var0[var1] < 0.0F) {
            if (var0[var2] > 0.0F) {
               var3 = var0[var1];
               var0[var1] = var0[var2];
               var0[var2] = var3;
               --var2;
            }

            ++var1;
         } else if (var0[var1] > 0.0F) {
            if (var0[var2] <= 0.0F) {
               var3 = var0[var1];
               var0[var1] = var0[var2];
               var0[var2] = var3;
            }

            if (var0[var2] <= 0.0F) {
               ++var1;
            }

            --var2;
         } else {
            if (var0[var2] < 0.0F) {
               var3 = var0[var1];
               var0[var1] = var0[var2];
               var0[var2] = var3;
               ++var1;
            }

            if (var0[var2] > 0.0F) {
               --var2;
            }

            if (var0[var2] == 0.0F) {
               int var5 = Math.round((float)(var2 + var1 / 2));
               if (var1 < var5 && var5 < var2) {
                  float var4;
                  if (var0[var5] >= 0.0F) {
                     var4 = var0[var5];
                     var0[var5] = var0[var2];
                     var0[var2] = var4;
                     --var2;
                  } else {
                     var4 = var0[var5];
                     var0[var5] = var0[var1];
                     var0[var1] = var4;
                     ++var1;
                  }
               }
            }
         }
      }

   }

   public static void bandiera(char[] var0) {
      int var1 = 0;
      int var2 = 0;
      int var3 = var0.length - 1;
      boolean var4 = true;
      int var5 = 0;

      while(true) {
         while(true) {
            while(var3 >= var5) {
               char var6;
               if (var0[var5] != 'r' && var0[var5] != 'R') {
                  if (var0[var5] != 'b' && var0[var5] != 'B') {
                     var6 = var0[var1];
                     var0[var1] = var0[var5];
                     var0[var5] = var6;
                     ++var1;
                     ++var2;
                     ++var5;
                  } else {
                     ++var2;
                     ++var5;
                  }
               } else {
                  var6 = var0[var3];
                  var0[var3] = var0[var5];
                  var0[var5] = var6;
                  --var3;
               }
            }

            return;
         }
      }
   }

   private static boolean[] resBoolArray(int var0) {
      boolean[] var1 = new boolean[var0];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = false;
      }

      return var1;
   }

   public static boolean isQuadratoLatino(int[][] var0) {
      int var1;
      boolean[] var2;
      int var3;
      for(var1 = 0; var1 < var0.length; ++var1) {
         var2 = resBoolArray(var0.length);

         for(var3 = 0; var3 < var0[0].length; ++var3) {
            if (var0[var1][var3] < 1 || var0[var1][var3] > var0.length) {
               return false;
            }

            if (var2[var0[var1][var3] - 1]) {
               return false;
            }

            var2[var0[var1][var3] - 1] = true;
         }
      }

      for(var1 = 0; var1 < var0[0].length; ++var1) {
         var2 = resBoolArray(var0.length);

         for(var3 = 0; var3 < var0.length; ++var3) {
            if (var0[var3][var1] < 1 || var0[var3][var1] > var0.length) {
               return false;
            }

            if (var2[var0[var3][var1] - 1]) {
               return false;
            }

            var2[var0[var3][var1] - 1] = true;
         }
      }

      return true;
   }

   private static void minimo(float[] var0) {
      float var1 = Float.MIN_VALUE;

      for(int var2 = 0; var2 < var0.length; ++var2) {
         if (var0[var2] < var1) {
            var1 = var0[var2];
         }
      }

      System.out.print("Il tuo risultato è: " + var1);
   }

   private static void auxPrimiKMin(float[] var0, int var1) {
      float[] var2 = new float[var1];

      int var3;
      for(var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = Float.MAX_VALUE;
      }

      if (var0.length > 2) {
         if (var0[0] < var0[1]) {
            var2[0] = var0[0];
            var2[1] = var0[1];
         } else {
            var2[0] = var0[1];
            var2[1] = var0[0];
         }

         for(var3 = 2; var3 < var0.length; ++var3) {
            float var4 = var0[var3];
            int var5 = 0;
            boolean var6 = true;

            do {
               if (var4 < var2[var5]) {
                  float var7 = var2[var5];
                  var2[var5] = var4;
                  var4 = var7;
               }

               ++var5;
            } while(var5 < var2.length);
         }
      }

      System.out.print("Il risultato è: ");

      for(var3 = 0; var3 < var2.length; ++var3) {
         System.out.print(var2[var3] + "\t");
      }

   }

   public static void primiKMin(float[] var0, int var1) {
      if (var1 < var0.length) {
         switch (var1) {
            case 0:
               System.out.println("valore k=0 non ammesso");
               break;
            case 1:
               minimo(var0);
               break;
            default:
               auxPrimiKMin(var0, var1);
         }

      } else {
         System.out.println("Valore di k >= n... tutti gli elementi sono minimi");

         for(int var2 = 0; var2 < var0.length; ++var2) {
            System.out.print(var0[var2] + "\t");
         }

      }
   }
}
