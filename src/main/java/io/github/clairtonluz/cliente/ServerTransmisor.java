package io.github.clairtonluz.cliente;

import java.net.Socket;
import java.util.Vector;

/**
 * Created by clairton on 10/25/14.
 */
public class ServerTransmisor extends Thread {
    private Vector filaDeMensagens = new Vector();
    private Vector listClientes = new Vector();

    public synchronized void addCliente(ClienteInfo clienteInfo) {
        listClientes.add(clienteInfo);
    }

    public synchronized void deleteClient(ClienteInfo aClientInfo) {
        int clientIndex = listClientes.indexOf(aClientInfo);
        if (clientIndex != -1) {
            listClientes.removeElementAt(clientIndex);
        }
    }

    public synchronized void transmitirMensagem(ClienteInfo clienteInfo, String mensagem) {
        Socket socket = clienteInfo.getSocket();
        String hostOrigem = socket.getInetAddress().getHostAddress();
        String portaOrigem = "" + socket.getPort();
        mensagem = hostOrigem + ":" + portaOrigem + " : " + mensagem;
        filaDeMensagens.add(mensagem);
        notify();
    }

    private synchronized String getProximaMensagemDaFila() throws InterruptedException {
        while (filaDeMensagens.size()==0) {
            wait();
        }
        String message = (String) filaDeMensagens.get(0);
        filaDeMensagens.removeElementAt(0);
        return message;
    }

    private synchronized void enviarMensagemParaTodosClientes(String mensagem) {
        for (int i=0; i< listClientes.size(); i++) {
            ClienteInfo clienteInfo = (ClienteInfo) listClientes.get(i);
            clienteInfo.getClienteEnviador().enviarMensagem(mensagem);
        }
    }

    /**
     * Infinitamente ler as mensagens da fila e envia para todos
     * os clientes conectados
     */
    public void run() {
        try {
            while (true) {
                String message = getProximaMensagemDaFila();
                enviarMensagemParaTodosClientes(message);
            }
        } catch (InterruptedException ie) {
            // Thread interrupted. Stop its execution
        }
    }
}