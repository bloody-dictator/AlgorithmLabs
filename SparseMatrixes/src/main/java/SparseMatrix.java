import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Getter @Setter
public class SparseMatrix {
    List<Integer> an;
    int[] d;
    int notNullEl;
    int size;
    int[][]sMatrix;
    int[][] unpakagingMatrix;

    public SparseMatrix(int[][]sMatrix){
        this.sMatrix = sMatrix;
    }

    public void packaging(){
        an = new ArrayList<>();
        d = new int[sMatrix.length];
        notNullEl = 0;
        size = sMatrix.length;
        for (int i = 0; i < size; i++) {
            int flag = 0;
            for (int j = 0; j <=i; j++) {
                if(i==j){
                   an.add(sMatrix[i][j]);
                   d[i] = an.indexOf(sMatrix[i][j]);
                   notNullEl++;
                }else{
                    if(sMatrix[i][j]>0){
                        flag=1;
                        an.add(sMatrix[i][j]);
                        notNullEl++;
                    }else if(flag==1){
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
        an.forEach(x-> System.out.print(x+ " "));
        System.out.println();
        Stream.of(d).forEach(x-> System.out.print(Arrays.toString(x) + ""));
        System.out.println();
        System.out.println(notNullEl);

    }


    public void unpackaging(){
        unpakagingMatrix = new int[size][size];
        unpakagingMatrix[0][0] = an.get(0);
        for (int i = 1; i < size; i++) {
               int j = d[i];
               int k = i;
               while (j> d[i-1]){
                   unpakagingMatrix[i][k] = unpakagingMatrix[k][i] = an.get(j);
                   j--;
                   k--;
               }
        }
        PrinterWord.printMatrix(unpakagingMatrix);
    }
}
