package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NaivOnArray {

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
		

		NaivOnArray(A, B, Result, N, P, M); // Pasar argumentos al método
		

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(Result[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void NaivOnArray(int[][] A, int[][] B, int[][] Result, int N, int P, int M) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				Result[i][j] = 0;
				for (int k = 0; k < P; k++) {
					Result[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}
}
