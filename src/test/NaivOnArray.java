package test;

public class NaivOnArray {

	public static void main(String[] args) {
		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		NaivOnArray(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void NaivOnArray(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Result[i][j] = 0;
				for (int k = 0; k < P; k++) {
					Result[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}
}
