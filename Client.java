import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Look up the remote object
            var registry = LocateRegistry.getRegistry("localhost", 1099);
            var stub = (Hello) registry.lookup("Hello");
            
            // Call the remote method
            var response = stub.sayHello();
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}