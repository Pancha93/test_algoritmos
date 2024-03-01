package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WinogradOriginal {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("D:\\workspace\\proyecto-final\\src\\test\\matriz4.txt"));
		
		int N = 4; 
		int P = 4;
		int M = 4;
		
		int[][] A = new int[N][P];
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < P; j++) {
		        A[i][j] = scanner.nextInt();
		    }
		}
		scanner.close(); // Cierra el archivo
		
		int[][] B = A;
		int[][] Result = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(A[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("----------------------");
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(B[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println("----------------------");

		WinogradOriginal(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void WinogradOriginal(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
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
