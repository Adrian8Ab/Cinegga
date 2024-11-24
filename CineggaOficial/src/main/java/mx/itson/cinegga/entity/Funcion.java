package mx.itson.cinegga.entity;
/**
 *
 * 
 * @author alang
 */
import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private String nombre;
    private String horario;
    private List<Asiento> asientos;
    private double precio;
    private int boletosVendidos;
    private double totalGanancia;

    public Funcion(String nombre, String horario, double precio) {
        this.nombre = nombre;
        this.horario = horario;
        this.precio = precio;
        this.asientos = new ArrayList<>();
        this.boletosVendidos = 0;
        this.totalGanancia = 0.0;

        // Inicializar los 16 asientos numerados
        for (int i = 1; i <= 16; i++) {
            asientos.add(new Asiento(i));
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getHorario() {
        return horario;
    }

    public List<Asiento> getAsientos() {
        return asientos;
    }

    public double getPrecio() {
        return precio;
    }

    public double getTotalGanancia() {
        return totalGanancia;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }

    public void venderBoleto(String cliente, int numeroAsiento) {
        Asiento asiento = asientos.get(numeroAsiento - 1);

        if (!asiento.isOcupado()) {
            asiento.setOcupado(true);
            boletosVendidos++;
            totalGanancia += precio;
            System.out.println("Boleto vendido a: " + (cliente.isEmpty() ? "Cliente Anónimo" : cliente));
            System.out.println("Asiento: " + numeroAsiento + " | Película: " + nombre);
        } else {
            System.out.println("El asiento " + numeroAsiento + " ya está ocupado.");
        }
    }

    public void mostrarAsientos() {
        for (int i = 0; i < asientos.size(); i++) {
            System.out.print(asientos.get(i) + " ");
            if ((i + 1) % 4 == 0) System.out.println();
        }
    }
}
