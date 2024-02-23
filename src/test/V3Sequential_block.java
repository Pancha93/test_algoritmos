package test;

public class V3Sequential_block {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		sequentialBlockMultiplication(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public static void sequentialBlockMultiplication(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {

	    final int bsize = N; // Tamaño del bloque
	    int size = A.length;

	    // Transponer la matriz A
	    transposeMatrix(A, N, P);

	    for (int i1 = 0; i1 < size; i1 += bsize) {
	        for (int j1 = 0; j1 < size; j1 += bsize) {
	            for (int k1 = 0; k1 < size; k1 += bsize) {
	                for (int i = i1; i < i1 + bsize && i < size; i++) {
	                    for (int j = j1; j < j1 + bsize && j < size; j++) {
	                        for (int k = k1; k < k1 + bsize && k < size; k++) {
	                            Result[i][k] += A[k][j] * B[j][i];
	                        }
	                    }
	                }
	            }
	        }
	    }

	    // Transponer la matriz A de nuevo para volver a su estado original
	    transposeMatrix(A, N, P);
	}

	private static void transposeMatrix(int[][] matrix, int rows, int cols) {
	    for (int i = 0; i < rows; i++) {
	        for (int j = i + 1; j < cols; j++) {
	            int temp = matrix[i][j];
	            matrix[i][j] = matrix[j][i];
	            matrix[j][i] = temp;
	        }
	    }
	}
}
//ESTA MULTIPLICACION ES LA TRANSPUESTA DE A POR B Y EL RESULTADO ES LA TRANSPUESTA

