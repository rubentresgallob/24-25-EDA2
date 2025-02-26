package Nivel4;
import java.util.ArrayList;
import java.util.List;

public class TriangulosPosibles {
    public static List<int[]> encontrarTodosTriangulos(int[] numeros) {
        List<int[]> triangulos = new ArrayList<>();
        int n = numeros.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (numeros[i] + numeros[j] > numeros[k] && numeros[i] + numeros[k] > numeros[j] && numeros[j] + numeros[k] > numeros[i]) {
                        triangulos.add(new int[] {numeros[i], numeros[j], numeros[k]});
                    }
                }
            }
        }
        return triangulos;
    }

    public static void main(String[] args) {
        int[] numeros = {3, 4, 5, 6, 7};
        List<int[]> triangulos = encontrarTodosTriangulos(numeros);
        for (int[] triangulo : triangulos) {
            System.out.println("Triángulo: " + triangulo[0] + ", " + triangulo[1] + ", " + triangulo[2]);
        }
    }
}
