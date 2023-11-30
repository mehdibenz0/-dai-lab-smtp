import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PrankGenerator {
    private ConfigurationManager configManager;
    private SMTPClient smtpClient;
    private int numberOfGroups;
    String subject;
    String content;

    public PrankGenerator(ConfigurationManager configManager, SMTPClient smtpClient, int numberOfGroups) {
        this.configManager = configManager;
        this.smtpClient = smtpClient;
        this.numberOfGroups = numberOfGroups;
    }

    public void generateAndSendPranks() throws IOException {
        ArrayList<String> victimEmails = configManager.readVictimEmailAddresses();
        ArrayList<String> emailMessages = configManager.readEmailMessages();

        // Shuffle the list for randomness
        Collections.shuffle(victimEmails);

        
        Random random = new Random();
        int groupSize = random.nextInt(victimEmails.size()) + 1;
        for (int i = 0; i < numberOfGroups; i++) {
            EmailGroup group = new EmailGroup();
            // Assign sender
            group.setSender(victimEmails.remove(0));
            
            // Assign recipients
            for (int j = 0; j < groupSize - 1; j++) {
                group.addRecipient(victimEmails.get(j));
            }

            // Assign a random email message to the group
            String message = emailMessages.get(random.nextInt(emailMessages.size()));
            group.setEmailMessage(message);

            sendEmail(group);
        }
    }

    private void sendEmail(EmailGroup group) throws IOException {
        smtpClient.connect();

        smtpClient.sendEHLO(group.getSender());
        smtpClient.sendMAILFROM(group.getSender());

        for (String recipient : group.getRecipients()) {
            smtpClient.sendRCPTTO(recipient);
        }

        subject = group.getEmailMessage();

        //get the text content without the "body:_" part
        content = group.getEmailMessage().substring(group.getEmailMessage().indexOf("Body") + 6 , group.getEmailMessage().length());

        smtpClient.sendData(group.getSender(), group.getRecipients(), subject,content);
        smtpClient.sendQUIT();

        smtpClient.close();
    }
}
