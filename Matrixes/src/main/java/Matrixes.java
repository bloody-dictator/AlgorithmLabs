import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

public class Matrixes {
    public static int[][] multiplyMatrixes(int[][] matrix1, int[][] matrix2){
        int[][] matrix3;
        if(matrix1[0].length == matrix2.length ){
            matrix3 = new int[matrix1.length][matrix2[0].length];
            ThreadMXBean tm = ManagementFactory.getThreadMXBean();
            for(int i = 0; i< matrix1.length; i++){
                for (int j = 0; j< matrix2[0].length; j++){
                    for (int k =0; k< matrix2.length; k++){
                        matrix3[i][j]+=matrix1[i][k]*matrix2[k][j];
                    }
                }
            }
            System.out.println(tm.getCurrentThreadUserTime());
            return matrix3;

        }else {
            System.out.println("Умножение невозможно!");
            return new int[0][0];}
    }

    public static int[][] multiplyVinograd(int[][] matrix1, int[][] matrix2){
        int [][] matrix3;
        if(matrix1[0].length == matrix2.length ) {
            matrix3 = new int[matrix1.length][matrix2[0].length];
            int[] mulH = new int[matrix1.length];
            for(int i =0; i< matrix1.length; i++){
                for (int k = 0; k<(matrix1[0].length)/2; k++){
                   mulH[i]+=matrix1[i][2*k]*matrix1[i][2*k+1];
                }
            }
            int[] mulj = new int[matrix2[0].length];
            for(int j =0; j< matrix2[0].length; j++){//each column
                for (int k = 0; k<(matrix2.length)/2; k++){
                    mulj[j]+=matrix2[2*k][j]*matrix2[2*k+1][j];
                }
            }
            //заполнение матрицы 3

            for(int i = 0; i<matrix1.length; i++){
                for (int j = 0; j<matrix2[0].length; j++){
                    matrix3[i][j] = -mulH[i]-mulj[j];
                    if(matrix1[0].length%2 ==1){
                        matrix3[i][j] +=  matrix1[i][matrix1[0].length-1] *
                                matrix2[matrix1[0].length-1][j];
                    }
                    for (int k = 0; k< matrix1[0].length/2; k++){
                        matrix3[i][j]+=(matrix1[i][2*k]+matrix2[2*k+1][j])*(matrix1[i][2*k+1]+matrix2[2*k][j]);
                    }

                }
            }
            return matrix3;
        }
        return new int[0][0];
    }

    public static int[][] multiplyVinogradImprove(int[][] matrix1, int[][] matrix2){
        int [][] matrix3;
        if(matrix1[0].length == matrix2.length ) {
            matrix3 = new int[matrix1.length][matrix2[0].length];
            int[] mulH = new int[matrix1.length];
            for(int i =0; i< matrix1.length; i++){
                for (int k = 1; k<matrix1[0].length; k+=2){
                    mulH[i]-=matrix1[i][k-1]*matrix1[i][k];
                }
            }
            int[] mulj = new int[matrix2[0].length];
            for(int j =0; j< matrix2[0].length; j++){
                for (int k = 1; k<matrix2[0].length; k+=2){
                    mulj[j]-=matrix2[k-1][j]*matrix2[k][j];
                }
            }
            //заполнение матрицы 3

            for(int i = 0; i<matrix1.length; i++){
                for (int j = 0; j<matrix2[0].length; j++){
                    matrix3[i][j] = mulH[i]+mulj[j];
                    if(matrix1[0].length%2 ==1){
                        matrix3[i][j] +=  matrix1[i][matrix1[0].length-1] *
                                matrix2[matrix1[0].length-1][j];
                    }
                    for (int k = 1; k< matrix2.length; k+=2){
                        matrix3[i][j]+=(matrix1[i][k-1]+matrix2[k][j])*(matrix1[i][k]+matrix2[k-1][j]);
                    }

                }
            }
            return matrix3;
        }
        return new int[0][0];
    }

}