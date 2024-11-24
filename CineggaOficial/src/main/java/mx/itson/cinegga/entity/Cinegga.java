package mx.itson.cinegga.entity;
/**
 *
 * 
 * @author alang
 */
import java.util.ArrayList;
import java.util.List;

public class Cinegga {
    private List<Funcion> funciones;

    public Cinegga() {
        this.funciones = new ArrayList<>();
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public void mostrarFunciones() {
        for (int i = 0; i < funciones.size(); i++) {
            Funcion funcion = funciones.get(i);
            System.out.println((i + 1) + ". " + funcion.getNombre() + " | Horario: " + funcion.getHorario());
        }
    }

    public Funcion seleccionarFuncion(int indice) {
        return funciones.get(indice - 1);
    }

    public void reporteGeneral() {
        System.out.println("Reporte General:");
        double totalGanancias = 0;
        int totalBoletos = 0;

        for (Funcion funcion : funciones) {
            totalGanancias += funcion.getTotalGanancia();
            totalBoletos += funcion.getBoletosVendidos();
        }

        System.out.println("Total de boletos vendidos: " + totalBoletos);
        System.out.println("Ganancias totales: $" + totalGanancias);
    }

    public void reportePorFuncion() {
        System.out.println("Reporte por Función:");
        for (Funcion funcion : funciones) {
            System.out.println("Película: " + funcion.getNombre());
            System.out.println("Boletos vendidos: " + funcion.getBoletosVendidos());
            System.out.println("Ganancia total: $" + funcion.getTotalGanancia());
            System.out.println();
        }
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }
}
