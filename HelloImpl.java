import java.rmi.RemoteException;
import java.rmi.MarshalledObject;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.rmi.activation.ActivationGroup;
import java.rmi.activation.ActivationGroupDesc;
import java.rmi.activation.ActivationGroupDesc.CommandEnvironment;
import java.rmi.activation.ActivationGroupID;
import java.rmi.activation.ActivationDesc;
import java.util.Properties;

public class HelloImpl extends Activatable implements Hello {
    public HelloImpl(ActivationID id, MarshalledObject<?> data) throws RemoteException {
        super(id, 0);
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            ActivationGroupDesc groupDesc = new ActivationGroupDesc(props, null);
            ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);
            ActivationDesc desc = new ActivationDesc(groupID, "HelloImpl", null, null);
            HelloImpl obj = (HelloImpl) Activatable.register(desc);
            System.out.println("Object registered: " + obj.sayHello());
        } catch (Exception e) {
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
