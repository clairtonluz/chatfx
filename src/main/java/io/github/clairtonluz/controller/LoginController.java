package io.github.clairtonluz.controller;

/**
 * Created by clairton on 10/25/14.
 */

import io.github.clairtonluz.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button btEntrar;

    @FXML
    private TextField tfUsername;

    @FXML
    void login(ActionEvent event) throws IOException {
        String usuario = tfUsername.getText();
        if(usuario != null && !usuario.trim().isEmpty()) {
            Main.usuarioLogado = usuario;
            Main.irPara("chat.fxml");
        } else {
            JOptionPane.showMessageDialog(null, "Entre com seu nome");
        }
    }

}
