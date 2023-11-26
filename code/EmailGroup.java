import java.util.ArrayList;
import java.util.List;

public class EmailGroup {
    private String sender;
    private List<String> recipients;
    private String emailMessage;

    public EmailGroup() {
        this.recipients = new ArrayList<>();
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void addRecipient(String recipient) {
        this.recipients.add(recipient);
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage = emailMessage;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getRecipients() {
        return new ArrayList<>(recipients); // Returning a copy to prevent external modification
    }

    public String getEmailMessage() {
        return emailMessage;
    }


    @Override
    public String toString() {
        return "EmailGroup{" +
                "sender='" + sender + '\'' +
                ", recipients=" + recipients +
                ", emailMessage='" + emailMessage + '\'' +
                '}';
    }
}
