package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NaivLoopUnrollingTwo {

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
		naiveLoopUnrollingTwo(A, B, Result, N, P, M); // Pasar argumentos al método
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
