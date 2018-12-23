package socketConnection;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{

    private final Socket socket;
    private final BufferedReader input;
    private final PrintWriter output;
    

    public Client(Socket socket, InputListener listener) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(),true);
        Thread listenerThread = new Thread(new InputListenerThread(input, listener));
        listenerThread.start();
        
    }
    
    public void sendMessage(String message){
        output.println(message);
    }
    
    public void onReceivedMove(ActionListener al){
        
    }

    public BufferedReader getInput() {
        return input;
    }
    
    
}