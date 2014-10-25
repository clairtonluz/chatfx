package chat.cliente;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

/**
 * Created by clairton on 10/25/14.
 */
public class ChatCliente {

    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 8080;

    private BufferedReader input = null;
    private PrintWriter output = null;
    public static final StringBuilder CONVERSA = new StringBuilder();

    public ChatCliente(TextArea receptorDaMensagem) {
        conectar();

        aguardarMensagens(receptorDaMensagem);
    }

    private void conectar() {
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

    private void aguardarMensagens(TextArea receptorDaMensagem){
        new Thread(() -> {
            try {
                // Ler mensagem do servidor e imprime
                String mensagem;
                final StringBuilder sb = new StringBuilder();
                while ((mensagem = input.readLine()) != null) {
                    System.out.println(mensagem);
                    sb.append(mensagem).append(System.getProperty("line.separator"));
                    Platform.runLater(() -> {
                        receptorDaMensagem.setText(sb.toString());
                    });
                }
            } catch (IOException e) {
                System.err.println("Connection to server broken.");
                e.printStackTrace();
            }
        }).start();
    }

    public void enviar(String mensagem) {
        output.println(mensagem);
        output.flush();
    }
}
