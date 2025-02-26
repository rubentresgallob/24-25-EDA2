package Nivel3;

import java.util.PriorityQueue;

public class CombinacionArrays {
    static class Elemento implements Comparable<Elemento> {
        int valor;
        int arrayIndex;
        int elementIndex;

        Elemento(int valor, int arrayIndex, int elementIndex) {
            this.valor = valor;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }

        public int compareTo(Elemento otro) {
            return this.valor - otro.valor;
        }
    }

    public static int[] combinarArrays(int[][] arrays) {
        PriorityQueue<Elemento> minHeap = new PriorityQueue<>();
        int totalElementos = 0;

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Elemento(arrays[i][0], i, 0));
                totalElementos += arrays[i].length;
            }
        }

        int[] resultado = new int[totalElementos];
        int index = 0;

        while (!minHeap.isEmpty()) {
            Elemento elementoActual = minHeap.poll();
            resultado[index++] = elementoActual.valor;

            int siguienteIndex = elementoActual.elementIndex + 1;
            if (siguienteIndex < arrays[elementoActual.arrayIndex].length) {
                minHeap.offer(new Elemento(arrays[elementoActual.arrayIndex][siguienteIndex], elementoActual.arrayIndex, siguienteIndex));
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        int[][] arrays = {{1, 4, 7}, {2, 5, 8}, {3, 6, 9}};
        int[] resultado = combinarArrays(arrays);
        for (int num : resultado) {
            System.out.print(num + " ");
        }
    }
}

