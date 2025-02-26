package Nivel3;

import java.util.PriorityQueue;

public class MedianaStream {
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public void agregarNumero(int numero) {
        if (maxHeap.isEmpty() || numero <= maxHeap.peek()) {
            maxHeap.offer(numero);
        } else {
            minHeap.offer(numero);
        }
        
        balancearHeaps();
    }

    private void balancearHeaps() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double obtenerMediana() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianaStream medianaStream = new MedianaStream();
        int[] numeros = {1, 3, 2, 4, 5};
        
        for (int num : numeros) {
            medianaStream.agregarNumero(num);
            System.out.println("Mediana actual: " + medianaStream.obtenerMediana());
        }
    }
}

