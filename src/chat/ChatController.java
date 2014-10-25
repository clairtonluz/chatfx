package chat;

import chat.cliente.ChatCliente;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ChatController {

    @FXML
    public TextField tf_mensagem;
    @FXML
    private volatile TextArea ta_conversa;
    private ChatCliente cliente;
    private Thread TaskMonitorarMensage;

    public void initialize() {
        cliente = new ChatCliente(ta_conversa);
    }

    @FXML
    void enviar(ActionEvent event) {
        cliente.enviar(tf_mensagem.getText());
        tf_mensagem.clear();

    }

}
