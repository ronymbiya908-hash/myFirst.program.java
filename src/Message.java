import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Message {
    String messageID;
    int numSent;
    String recipient;
    String messageText;
    String messageHash;
    static int totalMessages = 0;
    static ArrayList<Message> sentMessages = new ArrayList<>();

    public Message(String recipient, String messageText, int numSent) {
        this.recipient = recipient;
        this.messageText = messageText;
        this.numSent = numSent;
        this.messageID = generateID();
        this.messageHash = createMessageHash();
    }

    public String generateID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.length() <= 12 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessage() {
        if (messageText.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageText.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split("\\s+");
        String first = words[0].replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        String last = words[words.length - 1].replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        String idStart = messageID.substring(0, 2).toUpperCase();
        String hash = idStart + ":" + numSent + ":" + first + last;
        messageHash = hash.toUpperCase();
        return hash.toUpperCase();

    }

    public String SentMessage(int option) {
        if (option == 1) return "Message successfully sent.";
        if (option == 2) return "Press 0 to delete the message.";
        if (option == 3) return "Message successfully stored.";
        return "Invalid option.";
    }

    public String printMessages() {
        return "MessageID: " + messageID +
                "\nMessageHash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + messageText;
    }

    public int returnTotalMessages() {
        return Message.totalMessages;
    }

    public void storeMessage() {
        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(printMessages() + "\n---\n");
        } catch (IOException e) {
            System.out.println("Error storing message");
        }
    }
}