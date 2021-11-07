import java.rmi.RemoteException;

public interface TriangleInterface extends FigureInterface {
    public void setSides(int a, int b, int c) throws RemoteException;
}
