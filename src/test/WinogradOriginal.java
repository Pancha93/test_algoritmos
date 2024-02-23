package test;

public class WinogradOriginal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 2;
		int P = 2;
		int M = 2;

		double[][] A = { { 1, 2 }, { 3, 4 } };
		double[][] B = { { 5, 6 }, { 7, 8 } };
		double[][] Result = new double[N][M];

		WinogradOriginal(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void WinogradOriginal(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
		int i, j, k;
		int aux;
		int upsilon = P % 2;
		int gamma = P - upsilon;
		int[] y = new int[M];
		int[] z = new int[N];
		for (i = 0; i < M; i++) {
			aux = 0;
			for (j = 0; j < gamma; j += 2) {
				aux += A[i][j] * A[i][j + 1];
			}
			y[i] = aux;
		}
		for (i = 0; i < N; i++) {
			aux = 0;
			for (j = 0; j < gamma; j += 2) {
				aux += B[j][i] * B[j + 1][i];
			}
			z[i] = aux;
		}
		if (upsilon == 1) {
			/*
			 * P is odd The value A[i][P]*B[P][k] is missing in all auxiliary sums.
			 */
			int PP = P - 1;
			for (i = 0; i < M; i++) {
				for (k = 0; k < N; k++) {
					aux = 0;
					for (j = 0; j < gamma; j += 2) {
						aux += (A[i][j] + B[j + 1][k]) * (A[i][j + 1] + B[j][k]);
					}
					Result[i][k] = aux - y[i] - z[k] + A[i][PP] * B[PP][k];
				}
			}
		} else {
			/*
			 * P is even The result can be computed with the auxiliary sums.
			 */
			for (i = 0; i < M; i++) {
				for (k = 0; k < N; k++) {
					aux = 0;
					for (j = 0; j < gamma; j += 2) {
						aux += (A[i][j] + B[j + 1][k]) * (A[i][j + 1] + B[j][k]);
					}
					Result[i][k] = aux - y[i] - z[k];
				}
			}
		}

	}
}
