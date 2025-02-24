package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PizzarraImpl extends UnicastRemoteObject
    implements Pizzarra {
    
    List<String> lista = new ArrayList<String>();
    
    public PizzarraImpl() throws RemoteException {
        super();
    }

    @Override
    public String registrarPintor(String codigoPintor) throws RemoteException {

        if(lista.contains(codigoPintor)){
            System.out.println("El pintor "+ codigoPintor+ " ya existe.");
            return "YA_REGISTRADO";            
        }else{
            lista.add(codigoPintor);
            System.out.println("Pintor "+ codigoPintor+ " Registrado.");
            return "OK";
        }
        
    }
    
    public List<String> verPintores() throws RemoteException {
        return lista;
    }
    
    public Integer cantidadPintores() throws RemoteException {
        if(lista != null) {
        	return lista.size();
        }else{
        	return 0; 
        }
    }
}
