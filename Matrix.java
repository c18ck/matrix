package matrixCKK;

/**
 * Matrix!
 * 
 * This class can:
 * - print a matrix
 * - copy a matrix
 * - add two matrices
 * - multiply matrices
 * - alter/switch the rows of a matrix
 * - reduce a matrix (following reduced-row echelon form)
 * - invert a matrix
 * 
 * @author Claire Keenan-Kurgan
 *
 */

public class Matrix {
	int rows; //number of rows in the matrix
	int columns; //number of columns in the matrix
	double [][] matrix; //2D double array that stores the matrix's entries

	/**
	 * Constructor
	 * Creates a 2D array with the given number of rows and columns 
	 * @param rows		number of rows
	 * @param columns	number of columns
	 * 
	 */
	public Matrix(int rows, int columns){
		this.rows = rows;
		this.columns = columns;		
		this.matrix = new double [rows][columns];
	}
	
	/**
	 * Sets specific spot in the matrix to a given value 
	 * 
	 * @param row 		which row to put entry in
	 * @param column 	which column to put entry in
	 * @param value		desired value for the spot in the matrix
	 */
	public void setEntry(int row, int column, double value){
		matrix[row][column] = value;
	}

	/**
	 * Gets the value at a specific spot in the matrix
	 * 
	 * @param row		row of the spot you are getting the value for
	 * @param column	column of the spot you are getting the value for
	 * @return 			the value of the matrix at the given spot
	 */
	public double getEntry(int row, int column){
		return matrix[row][column];
	}

	/**
	 * Fills a matrix to make it a copy of this class's Matrix
	 * @param ralph		the new matrix that will become identical to this class's Matrix
	 */
	public void copy(Matrix ralph){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ralph.setEntry(i, j, matrix[i][j]);
			}
		}
	}

	/**
	 * Prints a rounded version of the matrix
	 */
	public void print (){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				System.out.print(Math.round(matrix[i][j]*10.0) / 10.0); //prints a rounded version, to the first decimal place
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	/**
	 * Adds this class's Matrix and another matrix (must be the same dimensions)
	 * @param alice 	the second Matrix that will be added to this matrix
	 * @return ralph	a new matrix that is the sum of this matrix and alice
	 */
	public Matrix plus(Matrix alice){
		Matrix ralph = new Matrix(rows,columns);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				//sets entry to the sum of an entry in this matrix and the corresponding entry in alice
				ralph.setEntry(i, j, matrix[i][j]+alice.getEntry(i, j));
			}
		}
		return ralph;
	}


	/**
	 * Multiplies this class's Mqtrix by another matrix
	 * @param alice 	the second matrix
	 * @return ralph	a new matrix that is the product of this Matrix and alice
	 */
	public Matrix times(Matrix alice){
		double val;
		Matrix ralph = new Matrix(this.rows, alice.columns);
		for (int i = 0; i < ralph.rows; i++) {
			for (int j = 0; j < ralph.columns; j++) {
				val = 0;
				for (int k = 0; k < this.columns; k++) {
					//entry (i,j) is the sum of all entries in row i of this matrix multiplied by corresponding entries in column j of alice
					val+=matrix[i][k]*alice.getEntry(k, j);
				}
				ralph.setEntry(i, j, val);
			}
		}
	
		return ralph;
	}

	/**
	 * Multiplies a row of this class's matrix by a scalar
	 * @param scalar			the scalar (number) that each entry in the row will be multiplied by
	 * @param rownumber			the row being multiplied
	 * @return Matrix ralph		a new matrix with the specific row altered
	 */
	public Matrix scalarTimesRow(double scalar, int rownumber){		
		Matrix ralph = new Matrix(rows, columns);
		//
		copy(ralph);
		for (int i = 0; i < columns; i++) {
			//sets ralph's entry to be a scalar multiple times the corresponding entry of this matrix
			ralph.setEntry(rownumber, i, matrix[rownumber][i]*scalar); 
		}
		return ralph;
	}

	/**
	 * Switches two rows of this class's matrix
	 * @param firstrow		will move to the row number secondrow
	 * @param secondrow		will move to the row number firstrow
	 * @return ralph		a new matrix with the rows switched
	 */
	public Matrix switchRows(int firstrow, int secondrow){
		Matrix ralph = new Matrix(rows, columns);
		copy(ralph);

		double [] temp = new double [columns];		
		for (int i = 0; i < columns; i++) {
			temp[i] = ralph.getEntry(firstrow, i); //stores firstrow
			ralph.setEntry(firstrow, i, ralph.getEntry(secondrow, i));  //firstrow becomes secondrow
			ralph.setEntry(secondrow, i, temp[i]); //secondrow becomes firstrow
		}
		return ralph;
	}

	/**
	 * Adds a scalar multiple of each entry of the first row to each corresponding entry in the second row
	 * @param scalar		multiple of firstrow to be added to secondrow
	 * @param firstrow		the row being added to another row (this row does not change)
	 * @param secondrow		the row being altered (scalar multiple of the first row added to it)
	 * @return ralph		a new matrix with a scalar*firstrow added to secondrow
	 */
	public Matrix linearCombRows(double scalar, int firstrow, int secondrow){
		Matrix ralph = new Matrix (rows,columns);
		copy(ralph);
		for (int i = 0; i < columns; i++) {
			//sets the entry to be the sum of a scalar multiple of the entry in firstrow and the corresponding entry in secondrow
			ralph.setEntry(secondrow, i, (ralph.getEntry(secondrow, i) + scalar*(ralph.getEntry(firstrow, i))));
		}

		return ralph;
	}

	/**
	 * Reduces a matrix
	 * https://en.wikipedia.org/wiki/Row_echelon_form
	 * 
	 * Rules: 
	 * - the leading coefficient must be a 1
	 * - if there is a 1 in the column, everything else must be a zero
	 */
	public Matrix rowreduce(){
		Matrix ralph = new Matrix(rows, columns);
		copy(ralph);

		int i = 0;
		int spot = 0; //which row you want the 1 to be in
		boolean one = false;
		
		while(true){
			
			//i = column
			//j = row
			one = false; //whether or not it finds a non-zero entry
			for(int j = spot; j < ralph.rows; j++){
				
				if(ralph.getEntry(j, i) != 0){  //if there's a non-zero entry
					one = true;
			
					//switches the row up
					ralph = ralph.switchRows(j, spot);
					
					//divide row to make it 1
					ralph = ralph.scalarTimesRow(1/ralph.getEntry(spot, i), spot);
					
					//subtract away entries in that column in other rows to make them all zero
					for (int j2 =0; j2 < ralph.rows; j2++) {
						if(j2!=spot){
							ralph = ralph.linearCombRows(-1*ralph.getEntry(j2, i), spot, j2);
						}
					}
				}
			}

			i++; //next column
				
			if(one == true){ //if it found a non-zero integer, the next 1 should be a row down
				spot++;
				one = false;
			}

			if(i>columns-1){ //if it finishes the whole matrix
				break;
			}
		}
		
		return ralph;
	}

	/**
	 * Inverts a matrix
	 * - only can invert square matrices
	 * 
	 */
	public Matrix invert(){
		
		//builds the identity matrix
		Matrix identity = new Matrix(rows,columns);
		for (int i = 0; i < identity.rows; i++) {
			for (int j = 0; j < identity.columns; j++) {
				if(i == j) {
					identity.setEntry(i, j, 1.0);
				}
				else {
					identity.setEntry(i, j, 0.0);				
				}
			}
		}
		
		Matrix augmented = new Matrix(rows, columns*2); //augmented matrix
		
		//fill the first half of the array with the original matrix
		for (int i = 0; i < augmented.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				augmented.setEntry(i, j, matrix[i][j]);
			}
		}
		
		//fill the second half of the array with the identity matrix
		for (int i = 0; i < identity.rows; i++) {
			int j2 = 0; 
			for (int j = augmented.columns/2; j < augmented.columns; j++) {
				augmented.setEntry(i, j, identity.getEntry(i, j2));
				j2++;
			}
		}
		
		//reduce the combined matrix
		augmented = augmented.rowreduce();
		
		//break off the last three rows to form the inverted matrix ralph
		Matrix ralph = new Matrix(identity.rows, identity.columns); //inverted
		for (int i = 0; i < ralph.rows; i++) {
			int j2 = augmented.columns/2;
			for (int j = 0; j < ralph.columns; j++) {
				ralph.setEntry(i, j, augmented.getEntry(i, j2));
				j2++;
			}
		}
		
		return ralph;
	}


}
