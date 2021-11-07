import java.rmi.RemoteException;

public interface RectangleInterface extends FigureInterface {
    public void setSides(int a, int b) throws RemoteException;
}
