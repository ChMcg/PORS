import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FigureFactoryInterface extends Remote {
    public enum FigureType {
        Circle,
        Rectangle,
        Triangle
    };

    public FigureInterface getObject(FigureType type, String name) throws RemoteException;
        
}
