package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class III4Parallel_Block {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int N = 2;
		int P = 2;
		int M = 2;

		int[][] A = { { 1, 2 }, { 3, 4 } };
		int[][] B = { { 5, 6 }, { 7, 8 } };
		int[][] Result = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Result[i][j] = 0; // Inicializar a ceros
			}
		}

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
