package py.una.server.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

public class TCPServerHilo extends Thread {

    private Socket socket = null;

    TCPMultiServer servidor;
    
    public TCPServerHilo(Socket socket, TCPMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            out.println("Bienvenido!");
            String inputLine, outputLine;
            String user = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Mensaje recibido: " + inputLine);
                
                //to-do: utilizar json
                if (inputLine.equals("Bye")) {
                    outputLine = "Usted apago el hilo";
                    if (user != null) {
                        servidor.conectados.remove(user);
                    }
                    System.out.println(outputLine);
                    break;
                    
                } else if (inputLine.equals("Terminar todo")) {
                    servidor.listening = false;
                    if (user != null) {
                        servidor.conectados.remove(user);
                    }
                    outputLine = "Usted apago todo";
                    System.out.println(outputLine);
                    break;

                } else if (inputLine.equals("login")) {
                    out.println("login");//envia al cliente
                    String nombre = in.readLine();//lee del cliente
                    if (false == servidor.conectados.contains(nombre)) {//pregunta si es que existe otro user
                        servidor.conectados.add(nombre);
                        user = nombre;
                        outputLine = "Ha Iniciado Sesion";
                        if (false == servidor.usuarios.contains(nombre)){
                            servidor.usuarios.add(nombre);
                        }

                    }else{
                        outputLine = "El Usuario ya Existe";
                    }

                } else if (inputLine.equals("logout")) {
                    servidor.conectados.remove(user);
                    user = null;
                    outputLine = "Se Ha Cerrado la Sesion";

                } else if (inputLine.equals("conectados")) {
                	outputLine = "Lista de usuarios: " ; 	
                	Iterator<String> iter = servidor.conectados.iterator();
                    while (iter.hasNext()) { 
                    	outputLine = outputLine + " » " + iter.next();
                    } 

                } else if (inputLine.equals("historial")) {
                	outputLine = "Historial de Usuarios: " ; 	
                	Iterator<String> iter = servidor.usuarios.iterator();
                    while (iter.hasNext()) { 
                    	outputLine = outputLine + " » " + iter.next();
                    } 

                }else{
                    outputLine = "Comando Desconocido";
                }
                out.println(outputLine);
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
