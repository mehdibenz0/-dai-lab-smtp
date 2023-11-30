import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigurationManager {
    private String victimsFile;
    private String messagesFile;

    public ConfigurationManager(String victimsFile, String messagesFile) {
        this.victimsFile = victimsFile;
        this.messagesFile = messagesFile;
    }


    public ArrayList<String> readVictimEmailAddresses() throws IOException {
        ArrayList<String> addresses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(victimsFile))) {
            String line;
            StringBuilder addresse = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if(line.contains("@")){
                    addresse.append(line);
                    addresses.add(addresse.toString());
                    addresse.setLength(0);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading victim email addresses", e);
        }
        return addresses;
    }

    public ArrayList<String> readEmailMessages() throws IOException {
        ArrayList<String> messages = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(messagesFile))) {
            String line;
            StringBuilder message = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Subject:")) {
                    message.append(line).append("\n");
                } else if (line.startsWith("Body:")) {
                    message.append(line).append("\n");
                    messages.add(message.toString());
                    message.setLength(0);
                } else {
                    message.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading email messages", e);
        }
        return messages;
    }
}
