package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class IV5Enhanced_Parallel_Block {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		enhanced_Parallel_Block(A, B, Result, N, P, M); // Pasar argumentos al método

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
