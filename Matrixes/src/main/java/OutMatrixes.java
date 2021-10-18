public class OutMatrixes {
    public static void main(String[] args){

        int[][] matrix1 = GeneratorMatrix.generateMatrix(3,2);
        PrinterWord.printMatrix(matrix1);
        System.out.println();
        int[][] matrix2 = GeneratorMatrix.generateMatrix(2, 3);
        PrinterWord.printMatrix(matrix2);
        System.out.println();

        int[][] matrix3 = Matrixes.multiplyVinogradImprove(matrix1, matrix2);
        PrinterWord.printMatrix(matrix3);
        System.out.println();
//        int[][] matrix1 = GeneratorMatrix.generateMatrix(3,2);
//        PrinterWord.printMatrix(matrix1);
//        System.out.println();
//        int[][] matrix2 = GeneratorMatrix.generateMatrix(2, 3);
//        PrinterWord.printMatrix(matrix2);
//        System.out.println();

//        int[][] matrix3 = Matrixes.multiplyMatrixes(matrix1, matrix2);
//        PrinterWord.printMatrix(matrix3);
//        System.out.println();

    }

}
