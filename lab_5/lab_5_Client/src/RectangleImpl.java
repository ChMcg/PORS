import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RectangleImpl 
        extends UnicastRemoteObject 
        implements RectangleInterface {
    
    private String name;
    private int a;
    private int b;

    public RectangleImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public double calculateArea() throws RemoteException {
        return a * b;
    }

    @Override
    public void setSides(int a, int b) throws RemoteException {
        this.a = a;
        this.b = b;
    }
    
}
