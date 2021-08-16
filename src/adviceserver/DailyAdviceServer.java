package adviceserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DailyAdviceServer {

    String[] adviceList = {
            "Morda pedaços menores",
            "Use o jeans apertado. Não, ele NÃO faz você parecer gorda.",
            "Só vou dizer uma palavra: inapropriado",
            "Pelo menos hoje, seja honesta. Dia a seu chefe o que realmente pensa.",
            "Reconsidere esse corte de cabelo."
    };

    public void go() {
        try {
            ServerSocket serverSocket = new ServerSocket(4242);

            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                String advice = getAdvice();
                writer.println(advice);
                writer.close();
                System.out.println(advice);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private String getAdvice() {
        int random = (int) (Math.random() * adviceList.length);
        return adviceList[random];
    }

    public static void main(String[] args) {
        DailyAdviceServer server = new DailyAdviceServer();
        server.go();
    }
}
