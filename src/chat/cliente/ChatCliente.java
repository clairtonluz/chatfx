package chat.cliente;

import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

/**
 * Created by clairton on 10/25/14.
 */
public class ChatCliente {

    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 8080;

    public BufferedReader getInput() {
        return input;
    }

    private BufferedReader input = null;
    private PrintWriter output = null;
    public static final StringBuilder CONVERSA = new StringBuilder();

    public ChatCliente(TextArea textArea) {
        try {
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.printf("Conectado ao servidor %s:%s", SERVER_HOSTNAME, SERVER_PORT);
        } catch (IOException e) {
            System.err.println("Can not establish connection to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
            e.printStackTrace();
            System.exit(-1);
        }
    }

//    public static void main(String[] args) {
//        BufferedReader in = null;
//        PrintWriter out = null;
//        try {
//            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
//            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
//            System.out.printf("Conectado ao servidor %s:%s", SERVER_HOSTNAME, SERVER_PORT);
//        } catch (IOException e) {
//            System.err.println("Can not establish connection to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
//            e.printStackTrace();
//            System.exit(-1);
//        }
//
//        Enviador enviador = new Enviador(out);
//        enviador.setDaemon(true);
//        enviador.start();
//
//        try {
//            // Ler mensagem do servidor e imprime
//            String message;
//            while ((message = in.readLine()) != null) {
//                System.out.println(message);
//            }
//        } catch (IOException e) {
//            System.err.println("Connection to server broken.");
//            e.printStackTrace();
//        }
//    }

    public void enviar(String mensagem) {
        output.println(mensagem);
        output.flush();
    }
}
