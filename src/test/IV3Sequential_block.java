package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class IV3Sequential_block {

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

		Sequential_block(A, B, Result, N, P, M); // Pasar argumentos al método

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void Sequential_block(int[][] A, int[][] B, int[][] Result, int N, int M, int P) {

	    final int bsize = N; // Tamaño del bloque

	    for (int i1 = 0; i1 < N; i1 += bsize) {
	        for (int j1 = 0; j1 < M; j1 += bsize) {
	            for (int k1 = 0; k1 < P; k1 += bsize) {
	                for (int i = i1; i < i1 + bsize && i < N; i++) {
	                    for (int j = j1; j < j1 + bsize && j < M; j++) {
	                        for (int k = k1; k < k1 + bsize && k < P; k++) {
	                            Result[i][j] += A[i][k] * B[k][j];
	                        }
	                    }
	                }
	            }
	        }
	    }
	}
}
