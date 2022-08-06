Brock Williams (will7159)
Rayan Amir (amir0045)

(Part V: Analysis is on the bottom)


Contributions:

Rayan: setElement, getElement, SparseIntMatrixTest, File input constructor
Brock: MatrixEntry (accessor/mutator), MatrixEntryTest class, removeElement, plus/minus


How To Compile:

We're using the IntelliJ Java IDE, we go to one of our main method files (MatrixEntryTest.java, SparseIntMatrixTest.java), we can compile 
them by selecting the green arrow (line 4 for both fields) to run the file.


Assumptions:

We assume our user is only using the int primitive type


Additional Features:

None


Known Bugs/Defencts:

None that we know of


Outside Sources:

No outside sources. Just TA's and Prof. Myers


Part V: Analysis

a.) The amount of memory that is required for a SparseIntMatrix implementation is 5 units of memory 
per non-zero node. Every node that exists, 5 units of memory is required. Each non-zero node doesn't use 
memory as it doesn't exist in a SparseIntMatrix. In a 2D array on the other hand, even zeroes are counted 
in the units of memory required.

For a standard 2D implementation, all elements are worth 1 unit of memory, even zeroes. A 4x4 2D array would have 
16 units of memory regardless of data stored in each element. 


b.) Square Matrix of N = 100,000 and M = 1,000,000. So our Square Matrix is 100,000 x 100,000 (10 billion total) 
with 1 million non-zero elements. The space taken up by a 2D array is 10 billion because each element is 1 memory unit.
The space taken up by the SparseIntMatrix is 5 million units of memory because every non-zero element gets 5 units of 
memory to track (row label, col label, matrix element data, link to next row, link to next column). The SparseIntMatrix
is more efficient by 9,995,000,000 units of memory.

2D array becomes more space efficient when M = 2,000,000,000 (2 billion). The reason for this is because at this point 
it would take 10 billion units of memory to store the SparseInt Matrix because 2 billion times 5 is equal to 10 billion
so a 2D array would be more space efficient for any value greater than 2 billion. 


I certify that the information contained in this README file is complete and accurate. 
I have both read and followed the course policies in the ‘Academic Integrity - Course 
Policy’ section of the course syllabus.

-Brock Williams
-Rayan Amir
