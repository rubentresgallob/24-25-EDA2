package entregas.tresgalloRuben;
import java.util.HashMap;
import java.util.Map;

class Pixel {
    int x, y;
    char color;

    public Pixel(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public char getColor() {
        return this.color;
    }
}

class Frame {
    int altura, anchura;
    Pixel[][] pixeles;

    public Frame(int altura, int anchura) {
        this.altura = altura;
        this.anchura = anchura;
        this.pixeles = new Pixel[altura][anchura];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                this.pixeles[i][j] = new Pixel(i, j, '.'); 
            }
        }
    }

    public void setPixel(int x, int y, char color) {
        if (x >= 0 && x < altura && y >= 0 && y < anchura) {
            pixeles[x][y].setColor(color);
        }
    }

    public Pixel getPixel(int x, int y) {
        return pixeles[x][y];
    }

    public void renderizar() {
        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < anchura; j++) {
                System.out.print(pixeles[i][j].getColor() + " ");
            }
            System.out.println();
        }
    }
}

class Superposicion {
    Map<Character, Map<Character, Character>> tablaColores;

    public Superposicion() {
        this.tablaColores = new HashMap<>();

        // Ejemplo de combinación de colores
        agregarCombinacion('N', 'B', 'G'); 
        agregarCombinacion('C', 'M', 'P'); 
    }

    public void agregarCombinacion(char color1, char color2, char resultado) {
        tablaColores.putIfAbsent(color1, new HashMap<>());
        tablaColores.get(color1).put(color2, resultado);

        tablaColores.putIfAbsent(color2, new HashMap<>());
        tablaColores.get(color2).put(color1, resultado);
    }

    public char combinarColores(char color1, char color2) {
        return tablaColores.getOrDefault(color1, new HashMap<>()).getOrDefault(color2, color1);
    }
}

class Interfaz {
    Frame frame1, frame2;
    int frameActual;
    Superposicion superposicion;

    public Interfaz(Frame frame1, Frame frame2, int frameActual, Superposicion superposicion) {
        this.frame1 = frame1;
        this.frame2 = frame2;
        this.frameActual = frameActual;
        this.superposicion = superposicion;
    }

    public void cambiaFrame() {
        frameActual = (frameActual == 1) ? 2 : 1;
    }

    public void actualizarPantalla() {
        if (frameActual == 1) {
            frame1.renderizar();
        } else {
            frame2.renderizar();
        }
    }

    public char[][] combinarFrames() {
        int nuevaAnchura = frame1.anchura * 2;
        char[][] resultado = new char[frame1.altura][nuevaAnchura];

        for (int i = 0; i < frame1.altura; i++) {
            for (int j = 0; j < frame1.anchura; j++) {
                char color1 = frame1.getPixel(i, j).getColor();
                char color2 = frame2.getPixel(i, j).getColor();
                resultado[i][j * 2] = color1;
                resultado[i][j * 2 + 1] = color2;
            }
        }
        return resultado;
    }
}

public class Main {
    public static void main(String[] args) {
        Frame frame1 = new Frame(7, 3);
        Frame frame2 = new Frame(7, 3);
        Superposicion superposicion = new Superposicion();

        frame1.setPixel(0, 0, '#');
        frame1.setPixel(0, 1, '#');

        frame2.setPixel(0, 1, '#');
        frame2.setPixel(0, 2, '#');

        Interfaz interfaz = new Interfaz(frame1, frame2, 1, superposicion);

        System.out.println("Frame 1:");
        frame1.renderizar();
        System.out.println("\nFrame 2:");
        frame2.renderizar();

        System.out.println("\nResultado Combinado:");
        char[][] combinado = interfaz.combinarFrames();
        for (char[] fila : combinado) {
            for (char c : fila) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
