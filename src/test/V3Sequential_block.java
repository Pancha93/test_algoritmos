package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class V3Sequential_block {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("D:\\workspace\\proyecto-final\\src\\test\\prueba.txt"));
		
		int N = 2; 
		int P = 2;
		int M = 2;
		
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
		sequentialBlockMultiplication(A, B, Result, N, P, M); // Pasar argumentos al método
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
//ESTA MULTIPLICACION ES LA TRANSPUESTA DE A POR la transpuesta de B Y EL RESULTADO ES LA TRANSPUESTA

