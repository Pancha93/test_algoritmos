package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class V4Parallel_Block {

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

		Parallel_BlockMultiplication(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void Parallel_BlockMultiplication(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {
        int bsize = 1; // Tamaño del bloque

        for (int i1 = 0; i1 < N; i1 += bsize) {
            for (int j1 = 0; j1 < M; j1 += bsize) {
                for (int k1 = 0; k1 < P; k1 += bsize) {
                    for (int i = i1; i < Math.min(i1 + bsize, N); i++) {
                        for (int j = j1; j < Math.min(j1 + bsize, M); j++) {
                            for (int k = k1; k < Math.min(k1 + bsize, P); k++) {
                                Result[k][i] += B[k][j] * A[j][i]; // Nota el orden de los índices
                            }
                        }
                    }
                }
            }
        }
    }
}
//MULTIPLICA LA TRANSPUESTA DE A Y LA TRANSPUESTA DE B Y EL RESULTADO ES LA TRANSPUESTA
