package mx.itson.cinegga.entity;
/**
 *
 * 
 * @author alang
 */
public class Asiento {

    // Número del asiento
    private int numero;

    // Estado de ocupación del asiento: true si está ocupado, false si está libre
    private boolean ocupado;

    /**
     * Constructor de la clase Asiento.
     * Inicializa un asiento con un número específico y lo marca como libre.
     * 
     * @param numero El número asignado al asiento.
     */
    public Asiento(int numero) {
        this.numero = numero; // Asigna el número al asiento
        this.ocupado = false; // Inicialmente, el asiento está libre
    }

    /**
     * Obtiene el número del asiento.
     * 
     * @return El número del asiento.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Verifica si el asiento está ocupado.
     * 
     * @return true si el asiento está ocupado, false si está libre.
     */
    public boolean isOcupado() {
        return ocupado;
    }

    /**
     * Establece el estado de ocupación del asiento.
     * 
     * @param ocupado true para marcar el asiento como ocupado, false para libre.
     */
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    /**
     * Devuelve una representación en texto del asiento.
     * Si el asiento está ocupado, muestra "[X]". Si está libre, muestra el número del asiento entre corchetes.
     * 
     * @return Una cadena que representa el estado del asiento.
     */
    @Override
    public String toString() {
        // Operador ternario para decidir la representación del asiento
        return ocupado ? "[X]" : "[" + numero + "]";
    }
}
