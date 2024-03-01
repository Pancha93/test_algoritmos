package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class III3Sequential_block  {

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

		strassen(A, B, Result, N);

		// Imprimir el resultado
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void strassen(int[][] A, int[][] B, int[][] Result, int size) {
		// Cálculo de bsize
		int bsize = 1;
		while (bsize < size) {
			bsize *= 2;
		}

		// Bloques secuenciales
		for (int i1 = 0; i1 < size; i1 += bsize) {
			for (int j1 = 0; j1 < size; j1 += bsize) {
				for (int k1 = 0; k1 < size; k1 += bsize) {
					for (int i = i1; i < i1 + bsize && i < size; i++) {
						for (int j = j1; j < j1 + bsize && j < size; j++) {
							for (int k = k1; k < k1 + bsize && k < size; k++) {
								Result[i][j] += A[i][k] * B[k][j];
							}
						}
					}
				}
			}
		}
	}

}
