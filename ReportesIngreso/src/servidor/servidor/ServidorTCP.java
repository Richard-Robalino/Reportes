package servidor.servidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        int puerto = 5000;
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                String nombre = dis.readUTF();
                String ingreso = dis.readUTF();
                String salidaAlmuerzo = dis.readUTF();
                String regresoAlmuerzo = dis.readUTF();
                String salidaTrabajo = dis.readUTF();

                String registro = String.format("%s;%s;%s;%s;%s%n",
                        nombre, ingreso, salidaAlmuerzo, regresoAlmuerzo, salidaTrabajo);

                // 📄 Guardar en archivo
                String rutaEscritorio = System.getProperty("user.home") + "/Desktop/ReporteEmpleado.dat";
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaEscritorio, true))) {
                    bw.write(registro);
                }

                // 👀 Mostrar en consola del servidor
                System.out.println("📥 Registro recibido:");
                System.out.println("Nombre: " + nombre);
                System.out.println("Ingreso: " + ingreso);
                System.out.println("Salida a Almuerzo: " + salidaAlmuerzo);
                System.out.println("Regreso de Almuerzo: " + regresoAlmuerzo);
                System.out.println("Salida del Trabajo: " + salidaTrabajo);
                System.out.println("-----------------------------");

                dis.close();
                socket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
