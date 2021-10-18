import java.util.Random;

public class GeneratorMatrix {
    public static int[][] generateMatrix(int size1, int size2){
        int[][] matrix = new int[size1][size2];
        Random rn = new Random();
        for(int i=0; i< matrix.length; i++){
            for(int j = 0; j<matrix[i].length; j++){
                matrix[i][j] = rn.nextInt(10);
            }
        }
        return matrix;
    }

    public  static int[][] initCash(int n, int m){
        int[][] cash = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                cash[i][j] = -1;
            }
        }
        return cash;
    }
}
