package chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.net.ConnectException;

public class ChatController {

    @FXML
    private TextArea ta_conversa;

    @FXML
    private TextField tf_mensagem;

    private ChatCliente cliente;

    public void initialize(){
        cliente = new ChatCliente();
    }

    @FXML
    void enviar(ActionEvent event) {
        cliente.enviar(tf_mensagem.getText());
    }

}
