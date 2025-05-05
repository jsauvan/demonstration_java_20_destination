

```java
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationDesc;
import java.rmi.activation.Activatable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Server {
    public static void main(String[] args) {
        try {
            // Set security manager if needed
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            // Setup activation group
            var props = new Properties();
            props.put("java.security.policy", "policy.all");
            var groupDesc = new ActivationGroupDesc(props, null);
            var groupID = ActivationGroup.getSystem().registerGroup(groupDesc);
            
            // Create activation descriptor
            var location = "file:" + System.getProperty("user.dir") + "/";
            var desc = new ActivationDesc(groupID, "HelloImpl", location, null);
            
            // Register the activatable object
            var obj = (Hello) Activatable.register(desc);
            
            // Create and populate the registry
            var registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", obj);
            
            System.out.println("Server ready");
            
            // Keep the server running
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
```