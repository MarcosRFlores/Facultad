package clientermi;


import servidor.Pizzarra;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class ClienteRMI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        	String input = "";
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            
            Pizzarra impl = (Pizzarra) myRegistry.lookup("miPizarra");

            boolean seguir = true;
            while(seguir) {
            	clearConsole();
            	System.out.println("**************************************************************");
            	System.out.println("Opciones:  1-registrar pintor   2-listar pintores   3-consultar cantidad ");
            	System.out.print("Elija una opcion > ");
                
                input = br.readLine();            	
            	

            	if(input.trim().compareTo("1") ==0 ) {
            		String pintor = random();
            		String resultado = impl.registrarPintor(pintor);
            		System.out.println("Registrar pintor " + pintor + ": " + resultado);
            		
            	}else if(input.trim().compareTo("2") ==0 ) {
            		System.out.println("Lista de Pintores");
                    List<String> verPintores = impl.verPintores();
                    for (Iterator<String> iterator = verPintores.iterator(); iterator.hasNext();) {
                        String next = iterator.next();
                        System.out.println("Pintor: " + next );   
                    }            		
            		
            	}else if(input.trim().compareTo("3") ==0 ) {
            		System.out.println("La cantidad de pintores registrados es: " + impl.cantidadPintores());
            		
            		
            	}else{
            		seguir = false;
            	}
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static String random() {
    	// class variable
    	final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    	final java.util.Random rand = new java.util.Random();


    	StringBuilder builder = new StringBuilder();
    	while(builder.toString().length() == 0) {
    		int length = rand.nextInt(5)+5;
    		for(int i = 0; i < length; i++) {
    				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
    		}
    	}
    	return builder.toString();
    }

    public final static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            //  Handle any exceptions.
        }
    }

}
