package chat;

import chat.cliente.ChatCliente;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatController {

    @FXML
    public TextField tf_mensagem;
    @FXML
    private volatile TextArea ta_conversa;
    private ChatCliente cliente;

    public void initialize() {
        cliente = new ChatCliente(ta_conversa);
        aguardarMensagens(ta_conversa);
    }

    @FXML
    void enviar(ActionEvent event) {
        cliente.enviar(tf_mensagem.getText());
        tf_mensagem.clear();
    }

    private void aguardarMensagens(final TextArea receptorDaMensagem) {
        new Thread(() -> {
            try {
                // Ler mensagem do servidor e imprime
                String mensagem;
                final StringBuilder sb = new StringBuilder();
                while ((mensagem = cliente.getInput().readLine()) != null) {
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

}
