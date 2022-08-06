// Rayan Amir and Brock Williams

public class MatrixEntryTest {
    public static void main(String[] args) {
        MatrixEntry a = new MatrixEntry(5, 5, 10);
        // write if statement to check if correct
        if(a.getColumn() == 5 && a.getRow() == 5 && a.getData() == 10) {
            System.out.println("Column is: " + a.getColumn());
            System.out.println("Row is: " + a.getRow());
            System.out.println("Data is: " + a.getData());
            System.out.println("Correctly set.\n");
        }

        MatrixEntry b = new MatrixEntry(3, 3, 3);
        a.setRow(2);
        a.setColumn(2);
        a.setData(2);
        // write if statement to check if correctly updated
        if(a.getColumn() == 2 && a.getRow() == 2 && a.getData() == 2) {
            System.out.println("Column is: " + a.getColumn());
            System.out.println("Row is: " + a.getRow());
            System.out.println("Data is: " + a.getData());
            System.out.println("Correctly set. \n");
        }
        a.setNextCol(b);
        a.getNextCol();
        if (a.getNextCol().equals(b)) {

            System.out.println("A's next column does equal b. Correctly set. \n");
        }

        a.setNextRow(b);
        a.getNextRow();
        if (a.getNextRow().equals(b)) {
            System.out.println("A's next row does equal b. Correctly set. \n");
        }

    }
}
