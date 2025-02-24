package py.una.server.tcp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            // viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);

        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser;
        System.out.println("\nOpciones: \n » login\n » logout\n » conectados\n » historial\n » Bye\n » Terminar todo");
        while ((fromServer = in.readLine()) != null) {
            if (fromServer.equals("Bye")) {
                break;
            }
            System.out.println("\n*Servidor: " + fromServer);
            System.out.print("\nSeleccione una Opcion: ");
            fromUser = stdIn.readLine();//lee del usuario
            if (fromUser != null) {
                if (fromUser.equals("login")) {
                    out.println("login");//envia al servidor
                    fromServer = in.readLine();//lee del servidor
                    if (fromServer.equals("login")) {
                        System.out.print("Nombre: ");
                        fromUser = stdIn.readLine();//lee del usuario
                    }
                }
                System.out.println("\n*Cliente: " + fromUser);
                // escribimos al servidor
                out.println(fromUser);

            }
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
}