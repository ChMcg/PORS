import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class TriangleImpl 
        extends UnicastRemoteObject 
        implements TriangleInterface {

    private String name;
    private int a;
    private int b;
    private int c;

    public TriangleImpl(String name) throws RemoteException {
        super();
        setName(name);
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
        int p = a + b + c;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    @Override
    public void setSides(int a, int b, int c) throws RemoteException {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    
    
}
