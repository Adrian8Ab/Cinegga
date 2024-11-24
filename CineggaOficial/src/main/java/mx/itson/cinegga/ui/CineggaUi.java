package mx.itson.cinegga.ui;
/**
 *
 * 
 * @author PC AGRA
 */
import mx.itson.cinegga.entity.Cinegga;
import mx.itson.cinegga.entity.Funcion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CineggaUi extends JFrame {
    private Cinegga cine; // El sistema de cine que administra las funciones y boletos.
    private JTextArea displayArea; // Área de texto para mostrar información.

    public CineggaUi() {
        // Inicializar el cine con funciones
        cine = new Cinegga();
        cine.agregarFuncion(new Funcion("Venom el último baile", "2:00 PM", 100.0));
        cine.agregarFuncion(new Funcion("Sonríe 2", "5:00 PM", 120.0));
        cine.agregarFuncion(new Funcion("Gladiador (ReEstreno)", "8:00 PM", 150.0));

        // Configuración básica del JFrame
        setTitle("Somos Cinefilos de Corazón");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel superior: Título
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("---CINEFILOS---");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Panel central: Área de texto para mostrar información
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Panel inferior: Botones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5, 10, 10));
        JButton showFunctionsButton = new JButton("Mostrar Funciones");
        JButton sellTicketButton = new JButton("Vender Boleto");
        JButton generalReportButton = new JButton("Reporte General");
        JButton functionReportButton = new JButton("Reporte por Función");
        JButton exitButton = new JButton("Salir");

        // Agregar botones al panel inferior
        buttonPanel.add(showFunctionsButton);
        buttonPanel.add(sellTicketButton);
        buttonPanel.add(generalReportButton);
        buttonPanel.add(functionReportButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Acciones para cada botón
        // Botón: Mostrar funciones
        showFunctionsButton.addActionListener(e -> mostrarFunciones());

        // Botón: Vender boleto
        sellTicketButton.addActionListener(e -> venderBoleto());

        // Botón: Reporte general
        generalReportButton.addActionListener(e -> reporteGeneral());

        // Botón: Reporte por función
        functionReportButton.addActionListener(e -> reportePorFuncion());

        // Botón: Salir
        exitButton.addActionListener(e -> System.exit(0));
    }

    // Métodos para manejar las acciones
    private void mostrarFunciones() {
        StringBuilder output = new StringBuilder("--- Funciones Disponibles ---\n");
        int index = 1;
        for (Funcion funcion : cine.getFunciones()) {
            output.append(index++).append(". ").append(funcion.getNombre())
                    .append(" | Horario: ").append(funcion.getHorario())
                    .append(" | Precio: $").append(funcion.getPrecio()).append("\n");
        }
        displayArea.setText(output.toString());
    }

    private void venderBoleto() {
        if (cine.getFunciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay funciones disponibles.");
            return;
        }

        String funcionIndex = JOptionPane.showInputDialog(this, "Ingrese el número de la función:");
        try {
            int index = Integer.parseInt(funcionIndex) - 1;
            Funcion funcion = cine.seleccionarFuncion(index + 1);

            StringBuilder asientoOutput = new StringBuilder("--- Asientos Disponibles ---\n");
            for (int i = 0; i < funcion.getAsientos().size(); i++) {
                asientoOutput.append(funcion.getAsientos().get(i));
                if ((i + 1) % 4 == 0) asientoOutput.append("\n");
            }
            String asientoIndex = JOptionPane.showInputDialog(this, asientoOutput.toString() + "\nSeleccione un asiento (1-16):");
            int asientoNumero = Integer.parseInt(asientoIndex);

            String cliente = JOptionPane.showInputDialog(this, "Ingrese su nombre (opcional):");
            funcion.venderBoleto(cliente == null ? "" : cliente, asientoNumero);

            JOptionPane.showMessageDialog(this, "Boleto vendido exitosamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error: " + ex.getMessage());
        }
    }

    private void reporteGeneral() {
        StringBuilder output = new StringBuilder("--- Reporte General ---\n");
        double totalGanancias = 0;
        int totalBoletos = 0;

        for (Funcion funcion : cine.getFunciones()) {
            totalGanancias += funcion.getTotalGanancia();
            totalBoletos += funcion.getBoletosVendidos();
        }

        output.append("Total de boletos vendidos: ").append(totalBoletos).append("\n");
        output.append("Ganancias totales: $").append(totalGanancias).append("\n");
        displayArea.setText(output.toString());
    }

    private void reportePorFuncion() {
        StringBuilder output = new StringBuilder("--- Reporte por Función ---\n");
        for (Funcion funcion : cine.getFunciones()) {
            output.append("Película: ").append(funcion.getNombre()).append("\n")
                    .append("Boletos vendidos: ").append(funcion.getBoletosVendidos()).append("\n")
                    .append("Ganancia total: $").append(funcion.getTotalGanancia()).append("\n\n");
        }
        displayArea.setText(output.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CineggaUi ui = new CineggaUi();
            ui.setVisible(true);
        });
    }
}

