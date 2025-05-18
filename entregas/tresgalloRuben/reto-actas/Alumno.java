public class Alumno {
    String nombre;
    double examenParcial;
    double examenFinal;
    double evaluacionContinua;

    public Alumno(String nombre, double examenParcial, double examenFinal, double evaluacionContinua) {
        this.nombre = nombre;
        this.examenParcial = examenParcial;
        this.examenFinal = examenFinal;
        this.evaluacionContinua = evaluacionContinua;
    }

    public double promedio() {
        return (examenParcial + examenFinal + evaluacionContinua) / 3.0;
    }

    @Override
    public String toString() {
        return nombre + "," + examenParcial + "," + examenFinal + "," + evaluacionContinua;
    }
}
