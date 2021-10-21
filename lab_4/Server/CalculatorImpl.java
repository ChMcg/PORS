import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class CalculatorImpl 
    extends  UnicastRemoteObject 
    implements CalculatorInterface {

    public CalculatorImpl()  throws RemoteException {
        super();
    }

    public int add(int x, int y) throws RemoteException {
        return x + y;        
    }

    public int subtract(int x, int y) throws RemoteException {
        return x - y;
    }

    public int multiply(int x, int y) throws RemoteException {
        return x * y;
    }

    public int divide(int x, int y) throws RemoteException {
        if (y == 0) throw new ArithmeticException();
        return x / y;
    }
}