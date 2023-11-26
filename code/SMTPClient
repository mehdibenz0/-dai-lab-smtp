import java.io.*;
import java.net.Socket;

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

        readResponse();  // Read server's initial response
    }

    private void sendCommand(String command) throws IOException {
        writer.write(command + "\r\n");
        writer.flush();
        readResponse();  // Read server's response to the command
    }

    private void readResponse() throws IOException {
        String response = reader.readLine();
    }

    public void sendEHLO() throws IOException {
        sendCommand("EHLO " + serverHost);
    }

    public void sendMAILFROM(String from) throws IOException {
        sendCommand("MAIL FROM: <" + from + ">");
    }

    public void sendRCPTTO(String to) throws IOException {
        sendCommand("RCPT TO: <" + to + ">");
    }

    public void sendData(String subject, String body) throws IOException {
        sendCommand("DATA");
        writer.write("Subject: " + subject + "\r\n");
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
