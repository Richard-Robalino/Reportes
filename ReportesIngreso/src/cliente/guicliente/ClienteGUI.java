package cliente.guicliente;

import cliente.cliente.ClienteTCP;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ClienteGUI extends JFrame {
    private JTextField tfNombre;
    private String ingreso = "", salidaAlmuerzo = "", regresoAlmuerzo = "", salidaTrabajo = "";

    public ClienteGUI() {
        setTitle("Registro de Empleado");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
        tfNombre = new JTextField();

        JButton btnIngreso = new JButton("Registrar Ingreso");
        JButton btnSalidaAlmuerzo = new JButton("Registrar Salida a Almuerzo");
        JButton btnRegresoAlmuerzo = new JButton("Registrar Regreso de Almuerzo");
        JButton btnSalida = new JButton("Registrar Salida del Trabajo");
        JButton btnEnviar = new JButton("Enviar Registro");

        btnIngreso.addActionListener(e -> ingreso = getHoraActual());
        btnSalidaAlmuerzo.addActionListener(e -> salidaAlmuerzo = getHoraActual());
        btnRegresoAlmuerzo.addActionListener(e -> regresoAlmuerzo = getHoraActual());
        btnSalida.addActionListener(e -> salidaTrabajo = getHoraActual());

        btnEnviar.addActionListener(e -> {
            String nombre = tfNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del empleado");
                return;
            }
            try {
                ClienteTCP.enviarRegistro(nombre, ingreso, salidaAlmuerzo, regresoAlmuerzo, salidaTrabajo);
                JOptionPane.showMessageDialog(this, "Registro enviado correctamente.");
                limpiar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al enviar datos: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Nombre del Empleado:"));
        panel.add(tfNombre);
        panel.add(btnIngreso);
        panel.add(btnSalidaAlmuerzo);
        panel.add(btnRegresoAlmuerzo);
        panel.add(btnSalida);
        panel.add(btnEnviar);

        add(panel);
    }

    private String getHoraActual() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private void limpiar() {
        tfNombre.setText("");
        ingreso = salidaAlmuerzo = regresoAlmuerzo = salidaTrabajo = "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClienteGUI().setVisible(true));
    }
}
