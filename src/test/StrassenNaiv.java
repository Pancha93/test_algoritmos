package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StrassenNaiv {

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
		StrassenNaiv(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void StrassenNaiv(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
		int MaxSize = Math.max(N, Math.max(P, M));
		if (MaxSize < 16) {
			MaxSize = 16;
		}
		int k = (int) Math.floor(Math.log(MaxSize) / Math.log(2)) - 4;
		int m = (int) Math.floor(MaxSize * Math.pow(2, -k)) + 1;
		int NewSize = m * (int) Math.pow(2, k);

		int[][] NewA = new int[NewSize][NewSize];
		int[][] NewB = new int[NewSize][NewSize];
		int[][] AuxResult = new int[NewSize][NewSize];

		// Inicializar NewA y NewB con ceros
		for (int i = 0; i < NewSize; i++) {
			for (int j = 0; j < NewSize; j++) {
				NewA[i][j] = 0;
				NewB[i][j] = 0;
			}
		}

		// Copiar submatrices de A y B a NewA y NewB
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < P; j++) {
				NewA[i][j] = A[i][j];
			}
		}
		for (int i = 0; i < P; i++) {
			for (int j = 0; j < M; j++) {
				NewB[i][j] = B[i][j];
			}
		}

		StrassenNaivStep(NewA, NewB, AuxResult, NewSize, m);

		// Extraer resultado de AuxResult
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Result[i][j] = AuxResult[i][j];
			}
		}
	}

	private static void StrassenNaivStep(int[][] A, int[][] B, int[][] Result, int NewSize, int m) {
		if (NewSize <= m) {
			// Multiplicación de matrices tradicional
			for (int i = 0; i < NewSize; i++) {
				for (int j = 0; j < NewSize; j++) {
					Result[i][j] = 0;
					for (int k = 0; k < NewSize; k++) {
						Result[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		} else {
			// Dividir la matriz en submatrices
			int[][] M1 = new int[m][m];
			int[][] M2 = new int[m][m];
			int[][] M3 = new int[m][m];
			int[][] M4 = new int[m][m];
			int[][] M5 = new int[m][m];
			int[][] M6 = new int[m][m];
			int[][] M7 = new int[m][m];

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					M1[i][j] = A[i][j];
					M2[i][j] = A[i][j + m];
					M3[i][j] = A[i + m][j];
					M4[i][j] = A[i + m][j + m];
					M5[i][j] = B[i][j];
					M6[i][j] = B[i][j + m];
					M7[i][j] = B[i + m][j + m];
				}
			}

			// Calcular submatrices
			int[][] C11 = new int[m][m];
			int[][] C12 = new int[m][m];
			int[][] C21 = new int[m][m];
			int[][] C22 = new int[m][m];

			StrassenNaivStep(M1, M4, C11, m, m);
			StrassenNaivStep(M3, M5, C12, m, m);
			StrassenNaivStep(M2, M4, C21, m, m);
			StrassenNaivStep(M2, M3, C22, m, m);

			// Combinar resultados
			for (int i = 0; i < m; i++) {
				for (int j = m; j < 2 * m; j++) {
					Result[i][j - m] = C12[i][j - m]; // Actualizar índice j
				}
				for (int j = 0; j < m; j++) {
					Result[i + m][j] = C21[i][j] + C22[i][j];
				}
			}
		}
	}

}
