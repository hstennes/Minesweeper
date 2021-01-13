package com.mines.solver;

public class MatrixTest {

	public static void testMatrix() {
		/*
		double[][] a = new double[8][16];
		//					 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 
		a[0] = new double[] {1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
		a[1] = new double[] {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		a[2] = new double[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0};
		a[3] = new double[] {0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0};
		a[4] = new double[] {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0};
		a[5] = new double[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 0, 0};
		a[6] = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0};
		a[7] = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 1};
		double[] b = new double[] {1, 1, 1, 1, 1, 1, 1, 1};
		
		
		double[][] a = new double[4][14];
		//		             1  2  3  4  5  6  7  8  9  10 11 12 13 14 
		a[0] = new double[] {1, 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0};
		a[1] = new double[] {0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0};
		a[2] = new double[] {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0};
		a[3] = new double[] {0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1};
		
		double[] b = new double[] {2, 1, 3, 2};
		*/
		
		double[][] a = new double[2][3];
		a[0] = new double[] {1, 2, 3};
		a[1] = new double[] {4, 9, 6};
		
		double[] b = new double[] {20, 2};
		Matrix m = new Matrix(a, b);
		m.printMatrix();
		m.eliminate(false);
		m.printMatrix(); 
		/*
		MatrixReader reader = new MatrixReader();
		reader.setMatrix(m);
		int[] conclusions = reader.getConclusions();
		for(int i = 0; i < conclusions.length; i++) {
			System.out.print(conclusions[i] + " ");
		}
		*/
	}
	
}
