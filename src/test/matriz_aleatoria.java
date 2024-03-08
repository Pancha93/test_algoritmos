package test;

import java.sql.SQLException;
import java.util.Random;


public class matriz_aleatoria {
	 public static void main(String[] args) throws SQLException {
	        int N = 4;
	        int P = 4;

	        // Generar la matriz aleatoria
	        int[][] A = generateRandomMatrix(N, P);

	        // Imprimir la matriz (opcional)
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < P; j++) {
	                System.out.print(A[i][j] + " ");
	            }
	            System.out.println();
	        }

	        // Crear un controlador y utilizar el método insertarMatriz
//	        controlador controlador = new controlador();
//	        controlador.getConexionMySQL().establecerConexion();
//	        controlador.insertarMatriz(A);
//
//	        System.out.println("Matriz insertada en la base de datos.");
	    }
	
	private static int[][] generateRandomMatrix(int rows, int cols) {
		int[][] matrix = new int[rows][cols];
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Generar un número aleatorio de 8 dígitos (entre 100000 y 999999)
                matrix[i][j] = 100000 + random.nextInt(900000);
            }
        }

        return matrix;
	}

}
