package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

    private int port;
    private String ipServer;

    public Cliente() {
        this.("localhost", 1024)
    }

    public Cliente(String ipServer, int port) {
        this.ipServer = ipServer;
        this.port = port;
    }

    public void conectar(){
        try {
            Socket cliente = new Socket(ipServer, port)
            DataInputStream in = new DataInputStream(cliente.getInputStream());
            PrintStream out = new PrintStream(cliente.getOutputStream());
            DataInputStream userInput = new DataInputStream(System.in);
            String line;

            while (true) {
                System.out.print(">");
                line = userInput.readLine();
                if ((line == null) || line.equals("exit"))
                    break;
                out.println(line);
                line = in.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
