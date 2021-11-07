// import java.rmi.Naming;
// import java.rmi.RemoteException;


// public class FigureFactory implements FigureFactoryInterface {
//     private String url;

//     public FigureFactory(String url) {
//         this.url = url;
//     }

//     @Override
//     public FigureInterface getObject(FigureFactoryInterface.FigureType type, String name) throws RemoteException {
//         try {
//             return (FigureInterface) Naming.lookup(url + typeToString(type));
//         } catch (Exception e) {
//             System.out.println(e);
//             // return new FigureInterface(name);
//             return null;
//         }
//     }

//     private String typeToString(FigureFactoryInterface.FigureType type){
//         switch (type)
//         {
//             case Circle:    return "Circle";
//             case Rectangle: return "Rectangle";
//             case Triangle:  return "Triangle";
//             default:        return "malformed";
//         }
//     }
    
// }
