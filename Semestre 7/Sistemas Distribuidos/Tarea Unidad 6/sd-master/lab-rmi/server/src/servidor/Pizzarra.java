package servidor;

import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.List;

public interface Pizzarra extends Remote { 
    String registrarPintor(String codigoPintor) throws RemoteException; 
    List<String> verPintores() throws RemoteException;
    Integer cantidadPintores() throws RemoteException;
}
