// Brock Williams and Rayan Amir

public class MatrixEntry {
    private int row;
    private int col;
    private int data;
    private MatrixEntry nextRow;
    private MatrixEntry nextCol;

    public MatrixEntry(int row1, int col1, int data1) { // constructor for our MatrixEntry "node"
        this.row = row1;
        this.col = col1;
        this.data = data1;

    }

    public int getColumn() { // accessor for the columns
        return this.col;

    }

    public void setColumn(int col) { // mutator for the columns
        this.col = col;

    }

    public int getRow() { // accessor for the rows
        return this.row;

    }

    public void setRow(int row) { // mutator for the rows
        this.row = row;

    }

    public int getData() { // accessor for the data
        return this.data;

    }

    public void setData(int data) { // mutator for the data
        this.data = data;

    }

    public MatrixEntry getNextRow() { // accessor for the next row
        return nextRow;
    }

    public void setNextRow(MatrixEntry el) { // mutator for the next row, links our node with the next row
        nextRow = el;
    }

    public MatrixEntry getNextCol() { // accessor for the next column
        return nextCol;
    }

    public void setNextCol(MatrixEntry el) { // mutator for the next column, links our node with the next column
        nextCol = el;
    }
}
