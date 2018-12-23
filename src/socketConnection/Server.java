package socketConnection;

import java.net.ServerSocket;
import java.net.Socket;
import model.User;

public class Server
{
    public Socket createServer(int port, User u1) throws Exception
    {
        System.out.println(u1.getUsername());
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server ceka i slusa...");
        Socket socket = server.accept();
        System.out.println("Primljena je konekcija");
        return socket; 
    }
}