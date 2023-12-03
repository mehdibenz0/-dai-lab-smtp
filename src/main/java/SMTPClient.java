import java.io.*;
import java.net.Socket;
import java.util.List;

public class SMTPClient {
    private String serverHost;
    private int serverPort;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public SMTPClient(String serverHost, int serverPort) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socket = new Socket(serverHost, serverPort);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        readResponse();
    }

    private void sendCommand(String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
        readResponse();
    }

    private void readResponse() throws IOException {
        String response = reader.readLine();
    }

    public void sendEHLO(String sender) throws IOException {
        sendCommand("EHLO " + serverHost);
    }

    public void sendMAILFROM(String from) throws IOException {
        sendCommand("MAIL FROM: <" + from + ">");
    }

    public void sendRCPTTO(String to) throws IOException {
        sendCommand("RCPT TO: <" + to + ">");
    }

    public void sendData(String sender, List<String> recipients, String subject, String body) throws IOException {
        sendCommand("DATA");
        writer.write("Content-Type: text/html; charset=utf-8\r\n");
        writer.write("From: " + sender + "\r\n");
        for (String recipient : recipients) {
            writer.write("To: " + recipient + "\r\n");
        }
        writer.write("Subject: " + subject);
        writer.write("\r\n");
        writer.write(body);
        writer.write("\r\n.\r\n");
        writer.flush();
        readResponse();
    }

    public void sendQUIT() throws IOException {
        sendCommand("QUIT");
    }

    public void close() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
