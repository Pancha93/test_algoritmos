package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StrassenWinograd {

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
		
		long startTime = System.nanoTime();  // tiempo inicial
		StrassenWinograd(A, B, Result, N, P, M); // Pasar argumentos al método
		long endTime = System.nanoTime(); // Captura el tiempo de finalizaci�n
		long executionTimeInNanoseconds = endTime - startTime; // Calcula la diferencia de tiempo en nanosegundos

		double executionTimeInMicroseconds = (double) executionTimeInNanoseconds / 1000; // Convierte a microsegundos
		double executionTimeInMilliseconds = executionTimeInMicroseconds / 1000; // Convierte a milisegundos
		System.out.println("Tiempo de ejecuci�n: " + executionTimeInMicroseconds + " microsegundos (" + String.format("%.5f", executionTimeInMilliseconds) + " milisegundos)");
		System.out.println("---------------------------------");


		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void StrassenWinograd(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
		// Comprobar compatibilidad
		if (N != P || P != M) {
			throw new IllegalArgumentException("Las dimensiones de la matriz deben ser iguales..");
		}

		// Manejar matrices pequeñas de manera eficiente
		if (N == 2) {
			naiveMultiplication(A, B, Result); // Use a traditional 2x2 matrix multiplication
			return;
		}

		// Calcular valores intermedios
		int MaxSize = Math.max(N, Math.max(P, M));
		if (MaxSize < 16) {
			MaxSize = 16;
		}
		int k = (int) Math.floor(Math.log(MaxSize) / Math.log(2)) - 4;
		int m = (int) Math.floor(MaxSize * Math.pow(2, -k)) + 1;
		int NewSize = m * (int) Math.pow(2, k);

		// Crear submatrices usando matrices rellenas
		int[][] NewA = padMatrix(A, NewSize);
		int[][] NewB = padMatrix(B, NewSize);
		int[][] AuxResult = new int[NewSize][NewSize];

		// Llamadas recursivas a StrassenWinogradStep
		StrassenWinogradStep(NewA, NewB, AuxResult, NewSize, m);

		// Extract result from padded matrix
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Result[i][j] = AuxResult[i][j];
			}
		}
	}

	// Traditional 2x2 matrix multiplication for small matrices
	private static void naiveMultiplication(int[][] A, int[][] B, int[][] Result) {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				Result[i][j] = 0;
				for (int k = 0; k < 2; k++) {
					Result[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}

	// Creates a padded matrix by adding rows and columns of zeros
	private static int[][] padMatrix(int[][] matrix, int newSize) {
		int originalN = matrix.length;
		int originalM = matrix[0].length;

		int[][] paddedMatrix = new int[newSize][newSize];
		for (int i = 0; i < originalN; i++) {
			for (int j = 0; j < originalM; j++) {
				paddedMatrix[i][j] = matrix[i][j];
			}
		}
		return paddedMatrix;
	}

	private static void StrassenWinogradStep(int[][] A, int[][] B, int[][] Result, int NewSize, int m) {
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
			int[][] M8 = new int[m][m];
			int[][] M9 = new int[m][m];
			int[][] M10 = new int[m][m];
			int[][] M11 = new int[m][m];
			int[][] M12 = new int[m][m];
			int[][] M13 = new int[m][m];
			int[][] M14 = new int[m][m];
			int[][] M15 = new int[m][m];

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					M1[i][j] = A[i][j];
					M2[i][j] = A[i][j + m];
					M3[i][j] = A[i + m][j];
					M4[i][j] = A[i + m][j + m];
					M5[i][j] = B[i][j];
					M6[i][j] = B[i][j + m];
					M7[i][j] = B[i + m][j];
					M8[i][j] = B[i + m][j + m];
				}
			}

			// Calcular submatrices
			int[][] C11 = new int[m][m];
			int[][] C12 = new int[m][m];
			int[][] C21 = new int[m][m];
			int[][] C22 = new int[m][m];
			int[][] C31 = new int[m][m];
			int[][] C32 = new int[m][m];
			int[][] C41 = new int[m][m];
			int[][] C42 = new int[m][m];
			int[][] C51 = new int[m][m];
			int[][] C52 = new int[m][m];
			int[][] C61 = new int[m][m];
			int[][] C62 = new int[m][m];
			int[][] C71 = new int[m][m];
			int[][] C72 = new int[m][m];
			int[][] C81 = new int[m][m];
			int[][] C82 = new int[m][m];

			StrassenWinogradStep(M1, M4, C11, m, m);
			StrassenWinogradStep(M3, M5, C12, m, m);
			StrassenWinogradStep(M2, M4, C21, m, m);
			StrassenWinogradStep(M1, M2, C22, m, m);
			StrassenWinogradStep(M3, M6, C22, m, m);
			StrassenWinogradStep(M7, M10, C31, m, m);
			StrassenWinogradStep(M11, M14, C31, m, m);
			StrassenWinogradStep(M13, M15, C32, m, m);
			StrassenWinogradStep(M11, M12, C41, m, m);
			StrassenWinogradStep(M8, M10, C42, m, m);
			StrassenWinogradStep(M9, M10, C51, m, m);
			StrassenWinogradStep(M13, M14, C51, m, m);
			StrassenWinogradStep(M8, M11, C52, m, m);
			StrassenWinogradStep(M12, M15, C52, m, m);
			StrassenWinogradStep(M11, M13, C61, m, m);
			StrassenWinogradStep(M8, M9, C62, m, m);
			StrassenWinogradStep(M10, M12, C62, m, m);
			StrassenWinogradStep(M1, M3, C71, m, m);
			StrassenWinogradStep(M5, M7, C71, m, m);
			StrassenWinogradStep(M2, M3, C72, m, m);
			StrassenWinogradStep(M9, M10, C81, m, m);
			StrassenWinogradStep(M13, M15, C82, m, m);

			// Combinar resultados
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					Result[i][j] = C11[i][j] + C12[i][j];
					Result[i][j + m] = C21[i][j] + C22[i][j];
					Result[i + m][j] = C31[i][j] + C32[i][j];
					Result[i + m][j + m] = C41[i][j] + C42[i][j];
				}
			}

			for (int i = 0; i < m; i++) {
				for (int j = 0; j < m; j++) {
					Result[i][j] += C51[i][j] - C52[i][j];
					Result[i + m][j] -= C61[i][j] + C62[i][j];
					Result[i][j + m] += C71[i][j] - C72[i][j];
					Result[i + m][j + m] -= C81[i][j] - C82[i][j];
				}
			}
		}
	}
}
