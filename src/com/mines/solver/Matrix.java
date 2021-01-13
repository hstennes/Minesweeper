package com.mines.solver;

public class Matrix {

	private double[][] a;
	private double[] b;
	
	public Matrix(double[][] a, double[] b) {
		this.a = a;
		this.b = b;
	}
	
	public void eliminate(boolean round) {
		int rows = a.length;
		for(int row = 0; row < rows; row++) {
			int pivotIndex = locateFirstNonzero(a[row]);
			int bestRow = row;
			for(int i = row; i < rows; i++) {
				int rowFirst = locateFirstNonzero(a[i]);
				if(rowFirst < pivotIndex && rowFirst >= row) {
					pivotIndex = rowFirst;
					bestRow = i;
				}
			}
			if(bestRow != row) swap(bestRow, row);
			
			if(a[row][pivotIndex] != 0 && a[row][pivotIndex] != 1) {
				double factor = 1 / a[row][pivotIndex];
				b[row] *= factor;
				a[row] = multiply(a[row], factor);	
			}
	
			if(a[row][pivotIndex] != 0) {
				for(int i = 0; i < rows; i++) {		
					if(i != row && a[i][pivotIndex] != 0) {
						double factor = a[i][pivotIndex] / a[row][pivotIndex];
						a[i] = subtract(a[i], a[row], factor);
						b[i] = b[i] - factor * b[row];
					}
				}
			}
			fixNegZero();
		}
		if(round) roundAllToWhole();
	}
	
	public void roundAllToWhole() {
		for(int row = 0; row < a.length; row++) {
			for(int col = 0; col < a[0].length; col++) { 
				a[row][col] = Math.round(a[row][col]);
				b[row] = Math.round(b[row]);
			}
		}
	}
	
	public void fixNegZero() {
		for(int row = 0; row < a.length; row++) {
			for(int col = 0; col < a[0].length; col++) { 
				a[row][col] += 0.0;
			}
		}
	}
	
	private int locateFirstNonzero(double[] row) {
		for(int i = 0; i < row.length; i++) {
			if(row[i] != 0) return i;
		}
		return row.length - 1;
	}
	
	private void swap(int row1, int row2) {
		double[] temp = a[row1];
		a[row1] = a[row2];
		a[row2] = temp;
		double temp2 = b[row1];
		b[row1] = b[row2];
		b[row2] = temp2;
	}
	
	private double[] multiply(double[] row, double factor) {
		for(int i = 0; i < row.length; i++) {
			row[i] *= factor;
		}
		return row;
	}
	
	private double[] subtract(double[] row1, double[] row2, double factor) {
		double[] newRow = new double[row1.length];
		for(int i = 0; i < newRow.length; i++) {
			newRow[i] = row1[i] - row2[i] * factor;
		}
		return newRow;
	}
	
    public void printMatrix() {
    	int rows = a.length;
    	int cols = a[0].length;
        for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) System.out.printf("%.0f ", a[i][j]);
           System.out.printf("| %.3f\n", b[i]);
        }
        System.out.println();
    }
    
    public double[][] getA(){
    	return a;
    }
    
    public void setA(double[][] a) {
    	this.a = a;
    }
    
    public double[] getB() {
    	return b;
    }
    
    public void setB(double[] b) {
    	this.b = b;
    }
}
