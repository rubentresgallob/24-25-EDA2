package Nivel3;

import java.util.PriorityQueue;

public class CasiOrdenado {
    public static void ordenarCasiOrdenado(int[] numeros, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i <= k && i < numeros.length; i++) {
            minHeap.offer(numeros[i]);
        }

        int index = 0;
        for (int i = k + 1; i < numeros.length; i++) {
            numeros[index++] = minHeap.poll();
            minHeap.offer(numeros[i]);
        }

        while (!minHeap.isEmpty()) {
            numeros[index++] = minHeap.poll();
        }
    }

    public static void main(String[] args) {
        int[] numeros = {2, 1, 3, 5, 4};
        int k = 2;
        ordenarCasiOrdenado(numeros, k);
        for (int num : numeros) {
            System.out.print(num + " ");
        }
    }
}
