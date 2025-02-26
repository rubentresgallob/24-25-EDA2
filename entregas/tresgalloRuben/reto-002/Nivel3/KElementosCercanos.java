package Nivel3;

import java.util.PriorityQueue;
import java.util.Comparator;

public class KElementosCercanos {
    static class Elemento {
        int valor;
        double distancia;

        Elemento(int valor, double distancia) {
            this.valor = valor;
            this.distancia = distancia;
        }
    }

    public static int[] encontrarKCercanos(int[] numeros, int k, double target) {
        PriorityQueue<Elemento> maxHeap = new PriorityQueue<>(k, Comparator.comparingDouble(e -> -e.distancia));
        
        for (int num : numeros) {
            double distancia = Math.abs(num - target);
            if (maxHeap.size() < k) {
                maxHeap.offer(new Elemento(num, distancia));
            } else if (distancia < maxHeap.peek().distancia) {
                maxHeap.poll();
                maxHeap.offer(new Elemento(num, distancia));
            }
        }
        
        int[] resultado = new int[k];
        int index = 0;
        while (!maxHeap.isEmpty()) {
            resultado[index++] = maxHeap.poll().valor;
        }

        return resultado;
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5};
        int k = 2;
        double target = 3.7;
        int[] resultado = encontrarKCercanos(numeros, k, target);
        for (int num : resultado) {
            System.out.print(num + " ");
        }
    }
}

