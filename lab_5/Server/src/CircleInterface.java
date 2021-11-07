import java.rmi.RemoteException;

public interface CircleInterface extends FigureInterface {
    public void setRadius(int r) throws RemoteException;
}
