import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FigureInterface extends Remote {
    public void setName(String name) throws RemoteException;
    public String getName() throws RemoteException;
    abstract double calculateArea() throws RemoteException;
}
