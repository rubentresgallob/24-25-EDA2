import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

class Acta {
    List<Alumno> alumnos;
    String hashActa;

    public Acta(List<Alumno> alumnos) {
        this.alumnos = alumnos;
        this.hashActa = generarHash();
    }

    private String generarHash() {
        StringBuilder data = new StringBuilder();
        for (Alumno a : alumnos) {
            data.append(a.toString()).append(";");
        }
        return hashSHA256(data.toString());
    }

    public boolean verificarIntegridad() {
        return generarHash().equals(hashActa);
    }

    private String hashSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(data.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no soportado");
        }
    }

    public void imprimirActa() {
        System.out.println("=== ACTA UNIVERSITARIA ===");
        for (Alumno a : alumnos) {
            System.out.printf("%s -> Parcial: %.2f | Final: %.2f | Continua: %.2f | Promedio: %.2f\n",
                    a.nombre, a.examenParcial, a.examenFinal, a.evaluacionContinua, a.promedio());
        }
        System.out.println("Hash del Acta: " + hashActa);
        System.out.println("Integridad: " + (verificarIntegridad() ? "VÁLIDA" : "ALTERADA"));
    }
}

public class App {
    public static void main(String[] args) {
        List<Alumno> alumnos = Arrays.asList(
                new Alumno("Carlos Pérez", 8.0, 9.0, 8.5),
                new Alumno("Lucía Gómez", 7.5, 8.0, 7.0),
                new Alumno("Juan Martínez", 6.0, 7.0, 6.5),
                new Alumno("Ana Torres", 9.0, 9.5, 9.0),
                new Alumno("Pedro Sánchez", 5.5, 6.0, 5.0)
        );

        Acta acta = new Acta(alumnos);
        acta.imprimirActa();

        System.out.println("\nVerificación tras posible modificación:");
        System.out.println("Integridad: " + (acta.verificarIntegridad() ? "VÁLIDA" : "ALTERADA"));
    }
}
