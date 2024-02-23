package test;

public class III3Sequential_block {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		strassen(A, B, Result, N);

		// Imprimir el resultado
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void strassen(int[][] A, int[][] B, int[][] Result, int size) {
		// Cálculo de bsize
		int bsize = 1;
		while (bsize < size) {
			bsize *= 2;
		}

		// Bloques secuenciales
		for (int i1 = 0; i1 < size; i1 += bsize) {
			for (int j1 = 0; j1 < size; j1 += bsize) {
				for (int k1 = 0; k1 < size; k1 += bsize) {
					for (int i = i1; i < i1 + bsize && i < size; i++) {
						for (int j = j1; j < j1 + bsize && j < size; j++) {
							for (int k = k1; k < k1 + bsize && k < size; k++) {
								Result[i][j] += A[i][k] * B[k][j];
							}
						}
					}
				}
			}
		}
	}

}
