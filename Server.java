import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Server {
    public static void main(String[] args) {
        try {
            // Set security manager if needed
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            // Setup properties similar to activation group
            var props = new Properties();
            props.put("java.security.policy", "policy.all");
            System.setProperties(props);
            
            // Create the implementation
            var location = "file:" + System.getProperty("user.dir") + "/";
            System.setProperty("java.rmi.server.codebase", location);
            
            // Create and export the remote object
            var obj = new HelloImpl();
            Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
            
            // Create and populate the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", stub);
            
            System.out.println("Server ready");
            
            // Keep the server running
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}