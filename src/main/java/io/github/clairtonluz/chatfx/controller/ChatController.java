package io.github.clairtonluz.chatfx.controller;

import io.github.clairtonluz.chatfx.cliente.ChatCliente;
import io.github.clairtonluz.chatfx.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.*;


public class ChatController {

    @FXML
    public TextFlow conversa;
    @FXML
    public TextField tfMensagem;
    private ChatCliente cliente;
    private Thread TaskMonitorarMensage;

    public void initialize() {
        cliente = new ChatCliente(Main.usuarioLogado, conversa);
    }

    @FXML
    void enviar(ActionEvent event) {
        cliente.enviar(tfMensagem.getText());
        tfMensagem.clear();
    }

}
