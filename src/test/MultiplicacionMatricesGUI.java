package test;

import javax.swing.*;

import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiplicacionMatricesGUI extends JFrame {

    private JTextField filasMatrizA;
    private JTextArea matrizResultado, matrizA, matrizB;
    private JButton multiplicar, limpiar;

    public MultiplicacionMatricesGUI() throws NumberFormatException {
        super("Multiplicación de matrices");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new FlowLayout());
        
        JLabel lblMatrizA = new JLabel("La matriz A es:");
        matrizA = new JTextArea(5, 20);
        JLabel lblMatrizB = new JLabel("La matriz B es:");
        matrizB = new JTextArea(5, 20);
        
        JLabel lblFilasMatrizA = new JLabel("Ingrese el valor de n:");
        filasMatrizA = new JTextField(10); // Campo de texto para el tamaño de la matriz
        JLabel lblMatrizResultado = new JLabel("El resultado es:");
        matrizResultado = new JTextArea(5, 20); // Área de texto para el resultado
        multiplicar = new JButton("Multiplicar");
        limpiar = new JButton("Limpiar");

        // ... (agregar action listeners como antes)

        // Agregar componentes a la ventana
        
        add(lblFilasMatrizA);
        add(filasMatrizA);
        add(lblMatrizResultado);
        add(matrizResultado);
        add(lblMatrizA);
        add(matrizA);
        add(lblMatrizB);
        add(matrizB);
        add(multiplicar);
        add(limpiar);

        setVisible(true);
        
       
        multiplicar.addActionListener(e -> {
            try {
                int n = Integer.parseInt(filasMatrizA.getText());

                int[][] A = leerMatrizArchivo("D:\\workspace\\proyecto-final\\src\\test\\matriz4.txt", n);

                int[][] C = new NaivOnArray().NaivOnArray(A, A, new int[n][n], n, n, n);
                
                mostrarMatriz2(matrizA, A);
                mostrarMatriz2(matrizB, A);

                mostrarMatriz(matrizResultado, C);
            } catch (FileNotFoundException | IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        });
        
       
        limpiar.addActionListener(e -> {
            filasMatrizA.setText("");
            matrizResultado.setText("");
            matrizA.setText("");
            matrizB.setText("");
        });

        setVisible(true);
    }

    private static int[][] leerMatrizArchivo(String archivo, int n) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(archivo));
        int[][] matriz = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matriz[i][j] = scanner.nextInt();
            }
        }

        scanner.close();
        return matriz;
    }

    private static void mostrarMatriz(JTextArea area, int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                area.append(matriz[i][j] + " ");
            }
            area.append("\n");
        }
    }
    
    private void mostrarMatriz2(JTextArea area, int[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                area.append(matriz[i][j] + " ");
            }
            area.append("\n");
        }
    }
    

    public static void main(String[] args) {
        new MultiplicacionMatricesGUI();
    }
}