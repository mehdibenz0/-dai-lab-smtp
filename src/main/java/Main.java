import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Command-line arguments and configuration files
        String victimsFile = "/Users/ewanb/OneDrive/Bureau/HEIG/S3/DAI/SMTP/victims.txt";
        String messagesFile = "/Users/ewanb/OneDrive/Bureau/HEIG/S3/DAI/SMTP/messages.txt";
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
                PrankGenerator prankGenerator = new PrankGenerator(configManager, smtpClient, numberOfGroups);
                prankGenerator.generateAndSendPranks();
                System.out.println("Pranks sent successfully!");
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
        if (victimEmailAddresses.size() < numberOfGroups * 2) {
            System.err.println("Not enough email addresses to form the specified number of groups.");
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
        return emailAddress.contains("@") && emailAddress.contains(".") && !emailAddress.contains(" ");
    }
}
