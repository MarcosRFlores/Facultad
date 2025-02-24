package py.una.server.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class TCPMultiServer {
    // variables compartidas
    boolean listening = true;//falta una condicion de carrera, para evitar que desde el cliente se use "Terminar todo" pero el servidor siga ejecutandose por una vez mas
    List<TCPServerHilo> hilosClientes; // almacenar los hilos (no se utiliza en el ejemplo, se deja para que el alumno lo utilice)
    List<String> usuarios; // almacenar una lista de usuarios (no se utiliza, se deja para que el alumno lo utilice)
    List<String> conectados;

    public void ejecutar() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(4444);

        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 4444.");
            System.exit(1);
        }
        System.out.println("Puerto abierto: 4444.");
        while (listening) {
            if (listening) {
                TCPServerHilo hilo = new TCPServerHilo(serverSocket.accept(), this);
                hilosClientes.add(hilo);
                hilo.start();                
            }
        }
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        RedisClient redisClient = RedisClient.create("redis://localhost:6379");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        TCPMultiServer tms = new TCPMultiServer();
        tms.hilosClientes = new ArrayList<>(); // Inicializar la lista de hilos

        try {
            RedisFuture<Long> existsFuture = asyncCommands.exists("usuarios");
            Long existsCount = existsFuture.get();

            if (existsCount > 0) {
                tms.usuarios = asyncCommands.lrange("usuarios", 0, -1).get();
            } else {
                tms.usuarios = new ArrayList<>();
            }
            tms.conectados = new ArrayList<>();
            tms.ejecutar();
            if (tms.usuarios.isEmpty() == false) {
                asyncCommands.lpush("usuarios", tms.usuarios.toArray(new String[0])).get();
            }
            
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de excepciones

        } finally {
            redisClient.shutdown(); // Asegurarse de cerrar la conexión a Redis
        }
    }
}