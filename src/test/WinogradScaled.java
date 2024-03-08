package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class WinogradScaled {

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
		
		
        // Instancia del algoritmo
        WinogradScaled scaled = new WinogradScaled();

        long startTime = System.nanoTime();  // tiempo inicial
        // Cálculo del resultado escalado
        scaled.calculateScaledResult(A, B, Result, N, P, M);
		long endTime = System.nanoTime(); // Captura el tiempo de finalizaci�n
		long executionTimeInNanoseconds = endTime - startTime; // Calcula la diferencia de tiempo en nanosegundos

		double executionTimeInMicroseconds = (double) executionTimeInNanoseconds / 1000; // Convierte a microsegundos
		double executionTimeInMilliseconds = executionTimeInMicroseconds / 1000; // Convierte a milisegundos
		System.out.println("Tiempo de ejecuci�n: " + executionTimeInMicroseconds + " microsegundos (" + String.format("%.5f", executionTimeInMilliseconds) + " milisegundos)");
		System.out.println("---------------------------------");

       

        // Impresión de la matriz resultante
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(Result[i]));
        }
    }

    private void calculateScaledResult(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
        // Cálculo de las normas infinito
        long a = normInf(A, N, P);
        long b = normInf(B, P, M);

        // Cálculo de lambda
        int lambda = (int) Math.round(Math.log(b / a) / Math.log(2));

        // Escalado de las matrices
        int[][] scaledA = multiplyWithScalar(A, N, P, (int) Math.pow(2, lambda / 2));
        int[][] scaledB = multiplyWithScalar(B, P, M, (int) Math.pow(2, -lambda / 2));

        // Multiplicación de matrices escaladas usando Winograd original
        WinogradOriginal.WinogradOriginal(scaledA, scaledB, Result, N, P, M);
    }

    private static long normInf(int[][] matrix, int rows, int cols) {
        long maxNorm = 0;

        // Cálculo de la norma infinito por fila
        for (int i = 0; i < rows; i++) {
            long rowSum = 0L;
            for (int j = 0; j < cols; j++) {
                rowSum += Math.abs(matrix[i][j]);
            }

            maxNorm = Math.max(maxNorm, rowSum);
        }

        return maxNorm;
    }

    private static int[][] multiplyWithScalar(int[][] matrix, int rows, int cols, int scalar) {
        int[][] scaledMatrix = new int[rows][cols];

        // Multiplicación de cada elemento por el escalar
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                scaledMatrix[i][j] = (int) Math.round(matrix[i][j] * scalar);
            }
        }

        return scaledMatrix;
    }
}