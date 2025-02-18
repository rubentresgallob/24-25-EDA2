package Ejercicio02_17;

abstract class InstrumentoMusical {
    protected String nombre;
    
    public InstrumentoMusical(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract void tocar();
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        InstrumentoMusical instrumento = (InstrumentoMusical) obj;
        return nombre.equals(instrumento.nombre);
    }
}
