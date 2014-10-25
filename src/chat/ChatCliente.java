package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ChatCliente {

    private String username = "Clairton";
    private int port;
    private String hostname;
    private Socket clientSocket;
    private DateTimeFormatter formatter;

    public ChatCliente() {
        this("localhost", 9000);
    }

    public ChatCliente(String hostname, int port) {
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.hostname = hostname;
        this.port = port;
    }

    public void enviar(String mensagem) {
        try {
            this.clientSocket = new Socket(hostname, port);
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            PrintStream out = new PrintStream(clientSocket.getOutputStream());
            DataInputStream userInput = new DataInputStream(System.in);

            if ((mensagem != null) || !mensagem.isEmpty()) {
                out.println(String.format("%s [%s]: %s",this.username, LocalDateTime.now().format(formatter), mensagem));
            }

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
