package test;

import java.util.Arrays;

public class WinogradScaled {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int N = 2;
        int P = 2;
        int M = 2;

        double[][] A = {{1, 2}, {3, 4}};
        double[][] B = {{5, 6}, {7, 8}};
        double[][] Result = new double[N][M];

        WinogradScaled scaled = new WinogradScaled();
        scaled.calculateScaledResult(A, B, Result, N, P, M);

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(Result[i]));
        }
    }

    private void calculateScaledResult(double[][] A, double[][] B, double[][] Result, int N, int P, int M) {
        double a = normInf(A, N, P);
        double b = normInf(B, P, M);
        double lambda = Math.floor(0.5 + Math.log(b / a) / Math.log(4));

        double[][] scaledA = multiplyWithScalar(A, N, P, Math.pow(2, lambda));
        double[][] scaledB = multiplyWithScalar(B, P, M, Math.pow(2, -lambda));

        WinogradOriginal.WinogradOriginal(scaledA, scaledB, Result, N, P, M);
    }

    private static double normInf(double[][] matrix, int rows, int cols) {
        double maxNorm = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < rows; i++) {
            double rowSum = 0.0;
            for (int j = 0; j < cols; j++) {
                rowSum += Math.abs(matrix[i][j]);
            }

            maxNorm = Math.max(maxNorm, rowSum);
        }

        return maxNorm;
    }

    private static double[][] multiplyWithScalar(double[][] matrix, int rows, int cols, double scalar) {
        double[][] scaledMatrix = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                scaledMatrix[i][j] = matrix[i][j] * scalar;
            }
        }

        return scaledMatrix;
    }
}