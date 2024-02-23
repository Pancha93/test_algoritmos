package test;

public class IV3Sequential_block {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		Sequential_block(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void Sequential_block(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {

	    final int bsize = N; // Tamaño del bloque

	    for (int i1 = 0; i1 < N; i1 += bsize) {
	        for (int j1 = 0; j1 < M; j1 += bsize) {
	            for (int k1 = 0; k1 < P; k1 += bsize) {
	                for (int i = i1; i < i1 + bsize && i < N; i++) {
	                    for (int j = j1; j < j1 + bsize && j < M; j++) {
	                        for (int k = k1; k < k1 + bsize && k < P; k++) {
	                            Result[i][j] += A[i][k] * B[k][j];
	                        }
	                    }
	                }
	            }
	        }
	    }
	}
}
