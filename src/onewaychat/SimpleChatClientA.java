package onewaychat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClientA {
    JTextField outgoing;
    PrintWriter writer;
    Socket sock;

//        cria a gui e registra um ouvinte no botão send
//        chama o método setUpNetworking()
    public void go() {

        JFrame frame = new JFrame("Ludicrously Simple Chat Client");
        JPanel mainPanel = new JPanel();
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        setUpNetworking();
        frame.setSize(400,500);
        frame.setVisible(true);
    }

//        cria um objeto Socket e, em seguida, um objeto PrintWriter
//        atribui o objeto PrintWriter à variável de instância writer
    public void setUpNetworking() {
        try {

            sock = new Socket("127.0.0.1",5000);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("networking established");

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public class SendButtonListener implements ActionListener {

//            captura o texto no campo de texto e
//            o envia para o servidor usando o objeto de gravação (obj PrintWriter)
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }

    public static void main(String[] args) {
        new SimpleChatClientA().go();
    }
}
