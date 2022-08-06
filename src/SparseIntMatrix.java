// Rayan Amir and Brock Williams

import java.io.File;
import java.util.Scanner;

public class SparseIntMatrix {
    private int row; // private data members used within our SparseIntMatrix class
    private int col;
    private int data;
    private MatrixEntry[] colHeaders; // the "head" nodes for our columns
    private MatrixEntry[] rowHeaders; // the "head" nodes for our rows
    private int numEntry;

    public SparseIntMatrix(int numRows, int numCols) { // default constructor
        this.row = numRows;
        this.col = numCols;
        colHeaders = new MatrixEntry[numCols];
        rowHeaders = new MatrixEntry[numRows];
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) { // constructor for file input
        this.row = numRows;
        this.col = numCols;
        colHeaders = new MatrixEntry[numCols];
        rowHeaders = new MatrixEntry[numRows];
        File text = new File(inputFile); // create file object to pass our file name into

        try {
            Scanner s = new Scanner(text);
            while (s.hasNextLine()) { // while loop to iterate through every line in file
                String line = s.nextLine();
                String[] word = line.split(",");
                int row = Integer.parseInt(word[0]); // parse row input to an integer
                int col = Integer.parseInt(word[1]); // parse col input to an integer
                int data = Integer.parseInt(word[2]); // parse data input to an integer
                this.setElement(row,col,data); // set our row, col, and data that we parsed to create our matrix
            }
        } catch (Exception e) { // Catches any exception, most common is FileNotFoundError so we print "File not found"
            System.out.println("File not found.");
        } // use scanner to read file input


    }

    public int getElement(int row, int col) { // iterates to specific row/col, returns the data there
        int ele = 0;
        if (isEmpty() || row < 0 || row >= rowHeaders.length || col < 0 || col >= colHeaders.length) {
            return 0; // check to see if empty row/col, and if it is in-bounds
        } else {
            MatrixEntry head = colHeaders[col]; // only need to iterate through 1 header to get the data

            if (head == null || head.getColumn() > col) { // that column is empty, there will be no nodes present
                return 0;
            }

            while (head != null) { // iterate through our specific column

                if (head.getRow() == row && head.getColumn() == col) {
                    ele = head.getData(); // // when we are at our specified spot, get the data and return
                    return ele;
                }

                if (head.getRow() > row) {
                    return 0;
                }

                if (head.getRow() == row && head.getColumn() > col) {
                    return 0;
                }

                head = head.getNextRow(); // updating our head to traverse through linked list
            }
        }
        return ele;
    }

    public int getNumRows() { // accessor for the number of rows
        return this.row;
    }

    public int getNumCols() { // accessor for the number of columns
        return this.col;
    }

    public boolean isEmpty() { // checking to see of list is empty
        if (numEntry == 0) {
            return true;
        }
        return false;
    }

    public boolean setElement(int row, int col, int data) { // sets a node at specified row/col with a specified data
        if (row < 0 || row >= rowHeaders.length || col < 0 || col >= colHeaders.length) {
            return false; // checking to see if in-bounds
        } else {
            MatrixEntry node = new MatrixEntry(row, col, data); // our node we are inserting
            MatrixEntry prevVal = null;
            MatrixEntry currVal = rowHeaders[row]; // start at the row headers
            if (currVal == null) { // nothing is in ths row, set our node to the head
                rowHeaders[row] = node;
            }
            else if (currVal.getColumn() > col) {
                node.setNextCol(currVal);
                rowHeaders[row] = node;
            }
            prevVal = rowHeaders[row];
            currVal = prevVal.getNextCol();
            while (currVal != null && currVal.getColumn() < col) { // traverse through our row
                prevVal = currVal;
                currVal = currVal.getNextCol();
            }

            prevVal.setNextCol(node); // set node in between the prevVal and the CurrVal
            node.setNextCol(currVal);
        }
        if (row < 0 || row > rowHeaders.length || col < 0 || col > colHeaders.length) {
            return false; // checking if in-bounds again
        } else {
            MatrixEntry node = new MatrixEntry(row, col, data); // our node we are adding
            MatrixEntry prevVal = null;
            MatrixEntry currVal = colHeaders[col];

            if (currVal == null) { // if there is nothing in this column, we set the head to our node
                colHeaders[col] = node;
            }
            else if (currVal.getRow() > row) {
                node.setNextRow(currVal);
                colHeaders[col] = node;
            }

            prevVal = colHeaders[col];
            currVal = prevVal.getNextRow();

            while (currVal != null && currVal.getRow() < row) { // traverse through our columns until we get to our spot
                prevVal = currVal;
                currVal = currVal.getNextRow();
            }

            if (prevVal.getColumn() == node.getColumn() && prevVal.getRow() == node.getRow()) {
                prevVal.setData(data); // checks to see if we are in correct location
            }
            prevVal.setNextRow(node); // set the node in-between the prevVal and the currVal
            node.setNextRow(currVal);
            numEntry++; // we have 1 entry, update numEntry
            return true;
        }
    }

    public int getNumEntry() { // accessor for number of entries
        return this.numEntry;
    }

    public String toString() { // visual representation of our matrix, null nodes = 0
        if (isEmpty()) {
            return "Empty Matrix";
        }

        String a = ""; // our string we'll be adding to

        for (int i = 0; i < this.getNumRows(); i++) { // iterate through number of rows
            for (int j = 0; j < this.getNumCols(); j++) { // iterate through number of columns
                    int ele = this.getElement(i,j);
                        a += ele + " ";
                // use our getElement at each node to grab the data, and add it to our string
                }
            a += "\n"; // after we go through a row, do a new line to move down a column
            }
        return a;
    }


    public boolean removeElement(int row, int col, int data) { // method to remove an element
        if (row < 0 || row > rowHeaders.length || col < 0 || col > colHeaders.length) {
            return false;
        } else {
            MatrixEntry prevNode = null;
            MatrixEntry headRow = rowHeaders[row];
            MatrixEntry headCol = colHeaders[col];


            // loop through parameters of row/col
            //set prevNode to node AFTER our removed node

            if (headRow == null || headCol == null) { // if either head is null, the node doesn't exist
                return false;
            }


            while (headRow != null && headRow.getColumn() < col) { // iterates through row headers
                prevNode = headRow;
                headRow = headRow.getNextCol();

            }

            if (headRow.getData() != data) {
                return false; // headRow is what we're removing, if the data isn't equal to the parameter... dont delete
            }

            if (prevNode == null && headRow != null) { // at beginning of list
                rowHeaders[row] = rowHeaders[row].getNextCol(); // set the header equal to the next row header
            }


            if (headRow != null && prevNode != null) { // middle of list
                MatrixEntry afterRemovedNode = headRow.getNextCol(); // headRow is what we remove, get column after
                prevNode.setNextCol(afterRemovedNode);
                // set our previous node next column to the column after our removed node, "deleting" the node
            }

            prevNode = null; // reset prevNode to null so we can re-iterate
            while (headCol != null && headCol.getRow() < row) { // iterates through column headers
                prevNode = headCol;
                headCol = headCol.getNextRow();
            }

            if (prevNode == null && headCol != null) {// at beginning of list, set it equal to the next row to remove
                colHeaders[col] = colHeaders[col].getNextRow();
            }

            if (headCol != null && prevNode != null) { // in the middle of list, grab row after removed node
                MatrixEntry afterRemovedNode = headCol.getNextRow();
                prevNode.setNextRow(afterRemovedNode); // set our previous' next row to the one after removed node

            }

        }
        return true;
    }

    public boolean plus(SparseIntMatrix otherMatrix) { // adding two matrix together
        int ele = 0;
        if (this.getNumRows() != otherMatrix.getNumRows() || this.getNumCols() != otherMatrix.getNumCols()) {
            return false; // checking to see dimensions, must be equal in order to add
        }

        for (int i = 0; i < otherMatrix.getNumRows(); i++) { // iterate through otherMatrix rows
            for (int j = 0; j < otherMatrix.getNumCols(); j++) { // iterate through otherMatrix columns
                ele = otherMatrix.getElement(i,j); // use our getElement at the specific node of the otherMatrix

                if (ele != 0) { // no reason to add 0, so we check if its not 0
                    int ourData = this.getElement(i,j); // the data of OUR matrix at the same spot
                    this.setElement(i,j,ele+ourData);
                    // updating OUR matrix by adding ourData plus the otherMatrix data
                }
            }
        }
        return true;
}

    public boolean minus(SparseIntMatrix otherMatrix) { // subtracting two matrix
        int ele = 0;
        if (this.getNumRows() != otherMatrix.getNumRows() || this.getNumCols() != otherMatrix.getNumCols()) {
            return false; // checking to see dimensions, must be equal in order to subtract
        }

        for (int i = 0; i < otherMatrix.getNumRows(); i++) { // iterate through otherMatrix rows
            for (int j = 0; j < otherMatrix.getNumCols(); j++) { // iterate through otherMatrix columns
                ele = otherMatrix.getElement(i,j); // get the data at that specific node

                if (ele != 0) { // no reason to add 0, only want to add nodes that exist
                    int ourData = this.getElement(i,j); // grab our data at the same spot
                    this.setElement(i,j,ourData-ele);
                } // updating OUR matrix by setting element at specific node to ourData MINUS otherMatrix data
            }
        }
        return true;
    }
}
