package chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatCliente {

    private int port;
    private String ipServer;

    public ChatCliente() {
        this.ipServer = "localhost";
        this.port = 1024;
    }

    public ChatCliente(String ipServer, int port) {
        this.ipServer = ipServer;
        this.port = port;
    }


    public void enviar(String mensagem) {

        while (true) {
            try (Socket cliente = new Socket(hostname, port)) {
                DataInputStream in = new DataInputStream(cliente.getInputStream());
                PrintStream out = new PrintStream(cliente.getOutputStream());
                DataInputStream userInput = new DataInputStream(System.in);

                if ((mensagem == null) || mensagem.isEmpty()) {
                    break;
                }
                out.println(mensagem);

                while ((mensagem = in.readLine()) != null) {
                    System.out.println(mensagem);
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
//    public String enviar(String mensagem) {
//        System.out.println("ENVIAR " + mensagem);
//        try (Socket cliente = new Socket(ipServer, port)) {
//            PrintStream out = new PrintStream(cliente.getOutputStream());
//            if (mensagem == null || mensagem.isEmpty()) {
//                out.println(mensagem);
//                return mensagem;
//            }
//        } catch (UnknownHostException e) {
//            return "Host desconhecido";
//        } catch (IOException e) {
//            return "Host desconhecido";
//        }
//        return null;
//    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }
}
