public class SparseOut {
    public static void main(String[] args) {
        int[][] out = { {1, 0, 0, 0, 0}, {0, 2, 6, 7, 0}, {0, 6, 3, 0, 0}, {0, 7, 0, 4, 8}, {0, 0, 0, 8, 5} };
         SparseMatrix sparseMatrix = new SparseMatrix(out);
         sparseMatrix.packaging();
         sparseMatrix.setSize(5);
         sparseMatrix.unpackaging();
    }
}
