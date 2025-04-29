import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            // Set security manager if needed
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            // Look up the remote object
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Hello stub = (Hello) registry.lookup("Hello");
            
            // Call the remote method
            String response = stub.sayHello();
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}