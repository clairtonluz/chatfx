package io.github.clairtonluz.controller;

import io.github.clairtonluz.Main;
import io.github.clairtonluz.cliente.ChatCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
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
