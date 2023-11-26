import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Command-line arguments and configuration files
        String victimsFile = "/Users/mehdibenzekri/Desktop/SMTP/victims.txt";
        String messagesFile = "/Users/mehdibenzekri/Desktop/SMTP/messages.txt";
        String serverHost = "localhost";
        int serverPort = 1025;
        // Here to specify the number of groups for email pranks
        int numberOfGroups = 2;

        try {
            ConfigurationManager configManager = new ConfigurationManager(victimsFile, messagesFile);

            List<String> victimEmailAddresses = configManager.readVictimEmailAddresses();
            List<String> emailMessages = configManager.readEmailMessages();

            if (validateConfiguration(numberOfGroups, victimEmailAddresses, emailMessages)) {
                SMTPClient smtpClient = new SMTPClient(serverHost, serverPort);
                smtpClient.connect();
                distributeEmailPranks(smtpClient, victimEmailAddresses, emailMessages, numberOfGroups);
                smtpClient.close();
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static boolean validateConfiguration(int numberOfGroups, List<String> victimEmailAddresses, List<String> emailMessages) {
        if (numberOfGroups <= 0) {
            System.err.println("Number of groups must be greater than zero.");
            return false;
        }
    
        for (String address : victimEmailAddresses) {
            if (!isValidEmailAddress(address)) {
                System.err.println("Invalid email address: " + address);
                return false;
            }
        }
    
        if (emailMessages.isEmpty()) {
            System.err.println("No email messages available.");
            return false;
        }
        return true;
    }
    
    private static boolean isValidEmailAddress(String emailAddress) {
        return !emailAddress.isEmpty() && emailAddress.contains("@") && emailAddress.contains(".") && !emailAddress.contains(" ");
    }
    
    private static void distributeEmailPranks(SMTPClient smtpClient, List<String> victimEmailAddresses, List<String> emailMessages, int numberOfGroups) {
        if (victimEmailAddresses.isEmpty()) {
            System.err.println("No email addresses available to form groups.");
            return;
        }

        Collections.shuffle(victimEmailAddresses);
        
        if (victimEmailAddresses.size() < numberOfGroups * 2) {
            System.err.println("Not enough email addresses to form the specified number of groups.");
            return;
        }
    
        for (int i = 0; i < numberOfGroups; i++) {
            List<String> group = new ArrayList<>();
            // Remove the sender
            String sender = victimEmailAddresses.remove(0);
            group.add(sender);
            // Select 2-5 receivers from the remaining addresses
            int maxReceivers = Math.min(4, victimEmailAddresses.size());
            int receiversCount = Math.max(1, 1 + (int) (Math.random() * maxReceivers));
            for (int j = 0; j < receiversCount; j++) {
                group.add(victimEmailAddresses.remove(0));
            }
            // Select a random email message
            String message = emailMessages.get((int) (Math.random() * emailMessages.size()));
            // Send the email prank for the current group
            System.out.println("Sending email prank to group: " + group);
            System.out.println("Sender is: " + sender);
            sendEmailPrank(smtpClient,sender, group, message);
        }
    }
    
    private static void sendEmailPrank(SMTPClient smtpClient, String sender, List<String> receivers, String message) {
        try {
            // Send the email using the generic sendEmail method
            smtpClient.sendEmail(message.toString());
        } catch (IOException e) {
            System.err.println("Error sending email prank: " + e.getMessage());
        }
    }    
}
