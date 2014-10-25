package io.github.clairtonluz.chatfx.cliente;

import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by clairton on 10/25/14.
 */
public class ChatCliente {

    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 8080;
    private static final String MENSAGEM_DO_SISTEMA = "[SYSTEM]";

    private BufferedReader input = null;
    private PrintWriter output = null;

    public ChatCliente(String username, TextFlow receptorDaMensagem) {
        if(username != null) {
            conectar();
            enviar(username);
            aguardarMensagens(receptorDaMensagem);
        } else {
            throw new IllegalArgumentException("Username não pode ser null");
        }
    }

    private void conectar() {
        try {
            Socket socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.printf("Conectado ao servidor %s:%s%n", SERVER_HOSTNAME, SERVER_PORT);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, String.format("Não foi possível se conectar a  " + SERVER_HOSTNAME + ":" + SERVER_PORT));
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private void aguardarMensagens(TextFlow receptorDaMensagem){
        new Thread(() -> {
            try {
                // Ler mensagem do servidor e imprime
                String mensagem;
                while ((mensagem = input.readLine()) != null) {
                    mensagem += System.getProperty("line.separator");
                    final Text msg = new Text();
                    if(mensagem.contains(MENSAGEM_DO_SISTEMA)) {
                        mensagem = mensagem.substring(MENSAGEM_DO_SISTEMA.length());
                        msg.setFill(Color.RED);
                    }
                    msg.setText(mensagem);
                    Platform.runLater(() -> {
                        receptorDaMensagem.getChildren().add(msg);
                    });
                }
            } catch (IOException e) {
                System.err.println("Connection to server broken.");
                e.printStackTrace();
            }
        }).start();
    }

    public void enviar(String mensagem) {
        if(!mensagem.isEmpty()) {
            output.println(mensagem);
            output.flush();
        }
    }
}
