package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IV5Enhanced_Parallel_Block {

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
		enhanced_Parallel_Block(A, B, Result, N, P, M); // Pasar argumentos al método
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
	
	public static void enhanced_Parallel_Block(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {

	    final int bsize = N; // Tamaño del bloque

	    // Separar la matriz en dos bloques
	    int halfSize = N / 2;

	    // Ejecutar en paralelo la multiplicación de cada bloque
	    ExecutorService executor = Executors.newFixedThreadPool(2);

	    try {
	        executor.submit(() -> {
	            for (int i1 = 0; i1 < halfSize; i1 += bsize) {
	                for (int j1 = 0; j1 < M; j1 += bsize) {
	                    for (int k1 = 0; k1 < P; k1 += bsize) {
	                        for (int i = i1; i < i1 + bsize && i < halfSize; i++) {
	                            for (int j = j1; j < j1 + bsize && j < M; j++) {
	                                for (int k = k1; k < k1 + bsize && k < P; k++) {
	                                    Result[i][k] += A[i][j] * B[j][k];
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        });

	        executor.submit(() -> {
	            for (int i1 = halfSize; i1 < N; i1 += bsize) {
	                for (int j1 = 0; j1 < M; j1 += bsize) {
	                    for (int k1 = 0; k1 < P; k1 += bsize) {
	                        for (int i = i1; i < i1 + bsize && i < N; i++) {
	                            for (int j = j1; j < j1 + bsize && j < M; j++) {
	                                for (int k = k1; k < k1 + bsize && k < P; k++) {
	                                    Result[i][k] += A[i][j] * B[j][k];
	                                }
	                            }
	                        }
	                    }
	                }
	            }
	        });
	    } catch (Exception e) {
	        e.printStackTrace(); // Manejar excepciones adecuadamente
	    } finally {
	        executor.shutdown(); // No aceptar nuevos trabajos
	        try {
	            executor.awaitTermination(10, TimeUnit.SECONDS); // Esperar a que terminen los trabajos en curso
	        } catch (InterruptedException e) {
	            e.printStackTrace(); // Manejar interrupciones adecuadamente
	        }
	    }
	}

}
