import java.util.Scanner;

public class ShuntingYard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una expresión matemática en notación infija:");
        String expresionInfija = scanner.nextLine();
        scanner.close();

        System.out.println(convertirAPosfija(expresionInfija));
    }

    public static String convertirAPosfija(String expresionInfija) {
        String[] pila = new String[100];
        String[] salida = new String[100];
        int topeIndice = -1;
        int salidaIndice = 0;

        String[] tokens = expresionInfija.split("\\s*");

        for (String token : tokens) {
            if (token.matches("\\d+(\\.\\d+)?")) {
                salida[salidaIndice++] = token;
            } else if (esOperador(token)) {
                while (topeIndice >= 0 && esOperador(pila[topeIndice]) &&
                        obtenerPrecedencia(pila[topeIndice]) >= obtenerPrecedencia(token)) {
                    salida[salidaIndice++] = pila[topeIndice--];
                }
                pila[++topeIndice] = token;
            } else if (token.equals("(")) {
                pila[++topeIndice] = token;
            } else if (token.equals(")")) {
                while (topeIndice >= 0 && !pila[topeIndice].equals("(")) {
                    salida[salidaIndice++] = pila[topeIndice--];
                }
                if (topeIndice >= 0 && pila[topeIndice].equals("(")) {
                    topeIndice--;
                }
            }
        }

        while (topeIndice >= 0) {
            salida[salidaIndice++] = pila[topeIndice--];
        }

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < salidaIndice; i++) {
            if (i > 0) resultado.append(" ");
            resultado.append(salida[i]);
        }
        
        return resultado.toString();
    }

    private static boolean esOperador(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || 
               token.equals("/") || token.equals("^");
    }

    private static int obtenerPrecedencia(String operador) {
        switch (operador) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
            case "^": return 3;
            default: return 0;
        }
    }
}
