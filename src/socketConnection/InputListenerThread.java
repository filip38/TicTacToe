/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketConnection;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author Filip
 */
public class InputListenerThread implements Runnable{
    private BufferedReader input;
    private InputListener listener;

    public InputListenerThread(BufferedReader input, InputListener listener) {
        this.input = input;
        this.listener = listener;
    }
    
    
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            while(true){
                try {
                    String data = input.readLine();
                    System.out.println("Data "+data);
                    listener.onReceivedData(data);
                    // listener(data);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
