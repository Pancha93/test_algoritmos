package test;

public class NaivLoopUnrollingTwo {

	public static void main(String[] args) {

		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		naiveLoopUnrollingTwo(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void naiveLoopUnrollingTwo(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
		int i, j, k;
		int aux;
		if (P % 2 == 0) {
			for (i = 0; i < N; i++) {
				for (j = 0; j < M; j++) {
					aux = 0;
					for (k = 0; k < P; k += 2) {
						aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j];
					}
					Result[i][j] = aux;
				}
			}
		} else {
			int PP = P - 1;
			for (i = 0; i < N; i++) {
				for (j = 0; j < M; j++) {
					aux = 0;
					for (k = 0; k < PP; k += 2) {
						aux += A[i][k] * B[k][j] + A[i][k + 1] * B[k + 1][j];
					}
					Result[i][j] = aux + A[i][PP] * B[PP][j];
				}
			}
		}
	}
}
