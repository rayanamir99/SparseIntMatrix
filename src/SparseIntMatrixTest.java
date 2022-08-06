// Brock Williams and Rayan Amir

public class SparseIntMatrixTest {
    public static void main(String[] args) {
        SparseIntMatrix mat = new SparseIntMatrix(1000, 1000, "src\\matrix1_data.txt");
        MatrixViewer.show(mat);

        SparseIntMatrix data2 = new SparseIntMatrix(1000,1000, "src\\matrix2_data.txt");
        SparseIntMatrix data2_noise = new SparseIntMatrix(1000,1000, "src\\matrix2_noise.txt");
        data2.minus(data2_noise);
        MatrixViewer.show(data2);
    }
}
