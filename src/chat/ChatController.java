package chat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    private TextArea ta_conversa;

    @FXML
    private TextField tf_mensagem;

    @FXML
    void enviar(ActionEvent event) {
        System.out.println(tf_mensagem.getText());
        ta_conversa.setText(tf_mensagem.getText());

    }

}
