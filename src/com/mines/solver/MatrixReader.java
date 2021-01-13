package com.mines.solver;

public class MatrixReader {

	private Matrix matrix;
	
	public MatrixReader() { }
	
	public MatrixReader(Matrix matrix) {
		this.matrix = matrix;
	}
	
	/**
	 * Uses the current matrix to make all possible conclusions about which cells have to be mines and which cells have to be free
	 * @return An int[] where each element represents the found state of each mine in the matrix. 1 -> mine, 0 -> free, -1 -> unknown / cannot be proven
	 * either way from matrix 
	 */
	public int[] getConclusions() {
		double[][] a = matrix.getA();
		double[] b = matrix.getB();
		//1 -> mine, 0 -> free, -1 -> unknown
		int[] cellList = new int[a[0].length];
		for(int i = 0; i < cellList.length; i++) cellList[i] = -1;
		while(true) {
			int[] oldCellList = cellList.clone();
			for(int i = 0; i < a.length; i++) {
				double[] row = a[i];
				double[] maxMin = computeMaxMin(row);
				if(maxMin[0] == b[i]) {
					for(int x = 0; x < row.length; x++) {
						if(row[x] < 0) {
							cellList[x] = 0;
							Matrix m = simplifyMatrix(a, b, false, x, i);
							a = m.getA();
							b = m.getB();
						}
						else if(row[x] > 0) {
							cellList[x] = 1;
							Matrix m = simplifyMatrix(a, b, true, x, i);
							a = m.getA();
							b = m.getB();
						}
					}
				}
				else if(maxMin[1] == b[i]) {
					for(int x = 0; x < row.length; x++) {
						if(row[x] < 0) {
							cellList[x] = 1;
							Matrix m = simplifyMatrix(a, b, true, x, i);
							a = m.getA();
							b = m.getB();
						}
						else if(row[x] > 0) {
							cellList[x] = 0;
							Matrix m = simplifyMatrix(a, b, false, x, i);
							a = m.getA();
							b = m.getB();
						}
					}
				}
			}
			if(compareArray(oldCellList, cellList)) break;
		} 
		return cellList;
	}
	
	/**
	 * Edits the given matrix by writing new equations that account for the given conclusion made by the min/max algorithm.  This allows the algorithm
	 * to make additional conclusions based on variable values that have been found.
	 * @param a The coefficient portion of the matrix
	 * @param b The augmented column of the matrix
	 * @param conclusion True if a cell was found to be a mine, false if it was found to be free
	 * @param index The column number representing the cell that was proven to be a mine or a free square
	 * @param skipRow The row that the conclusion was made in, which should be skipped in the simplification
	 * @return
	 */
	public Matrix simplifyMatrix(double[][] a, double[] b, boolean conclusion, int index, int skipRow) {
		for(int i = 0; i < a.length; i++) {
			if(i != skipRow) {
				if(conclusion) b[i] -= a[i][index];
				a[i][index] = 0;
			}
		}
		return new Matrix(a, b);
	}
	
	/**
	 * @param row The row to find the maximum and minimum sums for
	 * @return A double[] with 2 elements where the first value is the maximum sum and the second is the minimum sum         
	 */
	private double[] computeMaxMin(double[] row) {
		double[] maxMin = new double[2];
		maxMin[0] = 0;
		maxMin[1] = 0;
		for(int i = 0; i < row.length; i++) {
			if(row[i] > 0) maxMin[0] += row[i];
			else if(row[i] < 0) maxMin[1] += row[i];
		}
		return maxMin;
	}
	
	private boolean compareArray(int[] a, int[] b) {
		for(int i = 0; i < a.length; i++) {
			if(a[i] != b[i]) return false;
		}
		return true;
	}
	
	public void printArray(int[] a){
		for(int i = 0; i < a.length; i++) {
			if(i != a.length - 1) System.out.print(a[i] + ", ");
			else System.out.println(a[i]);
		}
	}
	
	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}
}
