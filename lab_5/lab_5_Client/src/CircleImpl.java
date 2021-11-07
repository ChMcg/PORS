import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CircleImpl extends UnicastRemoteObject implements CircleInterface {
    private int radius;
    private String name;

    public CircleImpl(String name) throws RemoteException {
        super();
        this.name = name;
    }

    @Override
    public void setRadius(int r) throws RemoteException {
        radius = r;
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
        return Math.PI * Math.pow(radius, 2);
    }
    
}
