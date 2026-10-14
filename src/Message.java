import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    public String messageID;
    public String recipient;
    public String message;
    public String messageHash;
    public int numMessagesSent;

    public static List<JSONObject> messagesList = new ArrayList<>();
    public static int totalMessagesSent = 0;

    public Message(String recipient, String message, int numMessagesSent) {
        this.recipient = recipient;
        this.message = message;
        this.numMessagesSent = numMessagesSent;
        this.messageID = String.format("%010d", new Random().nextInt(1000000000));
        this.messageHash = createMessageHash();
    }

    public boolean checkMessageID() { return messageID.length() <= 10; }

    public String checkRecipientCell() {
        if (recipient == null ||!recipient.startsWith("+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        if (!recipient.substring(1).matches("\\d+")) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        if (recipient.length() > 13) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
        return "Cell phone number successfully captured.";
    }

    public String checkMessage() {
        if (message.length() > 250) {
            return "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce size.";
        }
        return "Message ready to send.";
    }
    public String checkMessageLength() { return checkMessage(); }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        String first = words[0];
        String last = words.length > 1? words[words.length - 1] : first;
        return (messageID.substring(0,2) + ":" + numMessagesSent + ":" + first + last).toUpperCase();
    }

    public String SentMessage(int choice) {
        if (choice == 1) return "Message successfully sent.";
        if (choice == 2) return "Press 0 to delete the message.";
        if (choice == 3) return "Message successfully stored.";
        return "Invalid";
    }

    public static String printMessages() { return messagesList.toString(); }
    public static int returnTotalMessages() { return totalMessagesSent; }

    public void storeMessage() {
        try {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", messageID);
            obj.put("MessageHash", messageHash);
            obj.put("Recipient", recipient);
            obj.put("Message", message);
            messagesList.add(obj);
            totalMessagesSent++;
            FileWriter file = new FileWriter("src/messages.json");
            file.write(new JSONArray(messagesList).toString(4));
            file.close();
        } catch (Exception e) { System.out.println(e.getMessage()); }
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
}