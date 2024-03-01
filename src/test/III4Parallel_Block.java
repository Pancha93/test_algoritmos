package test;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class III4Parallel_Block {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

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

		parallelBlockMultiplication(A, B, Result, N, M, P);

		// Imprimir el resultado
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void parallelBlockMultiplication(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {

		final int bsize = N; // Tamaño del bloque
		// Calcular el tamaño del grupo de subprocesos dinámicamente
		int threadPoolSize = calculateThreadPoolSize(A);

		ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

		try {
			for (int i1 = 0; i1 < N; i1 += bsize) {
				for (int j1 = 0; j1 < M; j1 += bsize) {
					for (int k1 = 0; k1 < P; k1 += bsize) {
						final int finalI1 = i1;
						final int finalJ1 = j1;
						final int finalK1 = k1;

						executor.submit(new Runnable() {
							@Override
							public void run() {
								for (int i = finalI1; i < Math.min(finalI1 + bsize, N); i++) {
									for (int j = finalJ1; j < Math.min(finalJ1 + bsize, M); j++) {
										for (int k = finalK1; k < Math.min(finalK1 + bsize, P); k++) {
											Result[i][j] += A[i][k] * B[k][j];
										}
									}
								}
							}
						});
					}
				}
			}
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

	private static int calculateThreadPoolSize(int[][] A) {
		int numCores = Runtime.getRuntime().availableProcessors();
		double loadAverage = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
		int size = A.length;

		// Fórmula para calcular el tamaño del grupo de subprocesos
		int threadPoolSize = (int) Math.min(numCores, Math.sqrt(size) * loadAverage);

		// Ajustar el valor según necesidades específicas

		// Limitar el tamaño del grupo a un valor máximo
		threadPoolSize = Math.min(threadPoolSize, 8);

		// Asegurar que el tamaño del grupo sea positivo
		threadPoolSize = Math.max(threadPoolSize, 1);

		return threadPoolSize;
	}
}
