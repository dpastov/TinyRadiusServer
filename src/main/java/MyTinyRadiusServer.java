import java.net.InetAddress;
import java.net.InetSocketAddress;

import org.tinyradius.packet.AccessRequest;
import org.tinyradius.packet.RadiusPacket;
import org.tinyradius.util.RadiusServer;

public class MyTinyRadiusServer extends RadiusServer {

	@Override
	public String getSharedSecret(InetSocketAddress client) {
    	System.out.println("-- getSharedSecret2 --");
        return "sharedSecret123";
	}

	@Override
	public String getUserPassword(String userName) {
    	System.out.println("-- getUserPassword --");
		if (userName.equals("John")) {
			return "p123";
		}
		
		return null;
	}

    public RadiusPacket accessRequestReceived(AccessRequest request, InetAddress client) {
    	System.out.println("-- accessRequestReceived --");
        String username = request.getUserName();
        String password = request.getUserPassword();
        
        if (username.equals("John") && password.equals("token123")) {
        	RadiusPacket response = new RadiusPacket(RadiusPacket.ACCESS_ACCEPT);
        	return response;
        }
        
        return null;
    }
}
