package cliente.cliente;

import java.io.DataOutputStream;
import java.net.Socket;

public class ClienteTCP {
    public static void enviarRegistro(String nombre, String hIngreso, String hSalidaAlmuerzo,
                                      String hRegresoAlmuerzo, String hSalidaTrabajo) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        dos.writeUTF(nombre);
        dos.writeUTF(hIngreso);
        dos.writeUTF(hSalidaAlmuerzo);
        dos.writeUTF(hRegresoAlmuerzo);
        dos.writeUTF(hSalidaTrabajo);

        dos.close();
        socket.close();
    }
}
