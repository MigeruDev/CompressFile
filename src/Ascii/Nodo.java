
package Ascii;

public class Nodo {
    private Nodo ramaIzq;
    private Nodo ramaDer;
    private String clave;
    private Integer frecuencia;

    public Nodo(String clave) {
        this.clave = clave;
        this.frecuencia = 1;
        this.ramaDer = null;
        this.ramaIzq =null;
    }
    
    public Nodo(Nodo izq, Nodo der){
        this.clave = null;
        this.frecuencia = izq.getFrecuencia() + der.getFrecuencia();
        this.ramaDer = der;
        this.ramaIzq = izq;
    }
    
    public Nodo incrementarFrecuencia(){
        frecuencia += 1;
        return this;
    }
    
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public Nodo getRamaIzq() {
        return ramaIzq;
    }

    public Nodo getRamaDer() {
        return ramaDer;
    }
    
    
}
