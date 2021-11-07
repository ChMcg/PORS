import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FigureFactoryImpl 
        extends UnicastRemoteObject
        implements FigureFactoryInterface {
    private Registry registry;

    public FigureFactoryImpl(Registry registry) throws RemoteException {
        super();
        this.registry = registry;
    }

    @Override
    public FigureInterface getObject(FigureFactoryInterface.FigureType type, 
                                    String name) 
                                    throws RemoteException {
        switch (type)
        {
            case Circle:    return createCircle(name);
            case Rectangle: return createRectangle(name);
            case Triangle:  return createTriangle(name);
            default:        return null;
        }
    }

    private void bindToRegistry(String name, Remote obj) {
        try {
            registry.bind(name, obj);
        } catch (AlreadyBoundException|RemoteException e) {
            if (e instanceof RemoteException) {
                System.out.println("Не удалось создать объект " + name);
            } else if (e instanceof AlreadyBoundException) {
                System.out.println("Уже создан объект " + name);
            }
        }
    }

    private CircleInterface createCircle(String name) throws RemoteException {
        CircleInterface circle = (CircleInterface) new CircleImpl(name);
        bindToRegistry(name, circle);
        return circle;
    }

    private TriangleInterface createTriangle(String name) throws RemoteException {
        TriangleInterface triangle = (TriangleInterface) new TriangleImpl(name);
        bindToRegistry(name, triangle);
        return triangle;
    }

    private RectangleInterface createRectangle(String name) throws RemoteException {
        RectangleInterface rectangle = (RectangleInterface) new RectangleImpl(name);
        bindToRegistry(name, rectangle);
        return rectangle;
    }

}
