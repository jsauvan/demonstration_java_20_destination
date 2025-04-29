

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
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            var props = new Properties();
            props.put("java.security.policy", "policy.all");
            ActivationGroupDesc groupDesc = new ActivationGroupDesc(props, null);
            ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);
            
            var location = "file:" + System.getProperty("user.dir") + "/";
            ActivationDesc desc = new ActivationDesc(groupID, "HelloImpl", location, null);
            
            var obj = (Hello) Activatable.register(desc);
            
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Hello", obj);
            
            System.out.println("Server ready");
            
            Thread.sleep(Long.MAX_VALUE);
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
```