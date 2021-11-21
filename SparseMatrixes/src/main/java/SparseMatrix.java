import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SparseMatrix {

    public static void packaging(int[][]sMatrix){
        List<Integer> an = new ArrayList<>();
        int[] d = new int[sMatrix.length];
        int notNullEl = 0;
        int n = sMatrix.length;
       // int m = sMatrix[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <=i; j++) {
                if(i==j){
                   an.add(sMatrix[i][j]);
                   d[i] = an.indexOf(sMatrix[i][j]);
                   notNullEl++;
                }else{
                    if(sMatrix[i][j]>0){
                        an.add(sMatrix[i][j]);
                        notNullEl++;
                    }
//                    else{
////                        if(sMatrix[i][j-1]>0&&sMatrix[i][j+1]>0){
////                            an.add(sMatrix[i][j]);
////                            notNullEl++;
////                        }
//                    }
                }
            }
        }
        an.stream().forEach(x-> System.out.printf(x+ ""));
        System.out.println();
        Arrays.asList(d).stream().forEach(x-> System.out.printf(x+ ""));
        System.out.println();
        System.out.println(notNullEl);

    }
}
