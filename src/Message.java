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
            return "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce number and try again.";
        }
        return "Message ready to send.";
    }
    public String checkMessageLength() { return checkMessage(); }

    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        String first = words[0];
        String last = words.length > 1? words[words.length - 1] : first;
        last = last.replace("?", "").replace("!", "").replace(".", "");
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
    // ===== PART 3 =====
    private static String[] messageIDsArr = new String[100];
    private static String[] messageHashesArr = new String[100];
    private static String[] recipientsArr = new String[100];
    private static String[] messagesArr = new String[100];
    private static int[] messageNumbersArr = new int[100];
    private static int part3Count = 0;

    public static void populateMessage() {
        part3Count = 0;
        addToPart3("+27838884567", "Hi Mike, can you join us for dinner tonight?");
        addToPart3("+27838884567", "Hi Keegan, did you receive the payment?");
        addToPart3("+27838884567", "Hi Mike, can you join us for dinner tonight?");
        addToPart3("0838884567", "Hi Keegan, did you receive the payment?");
        addToPart3("+27838884567", "Hi Mike, can you join us for dinner tonight?");
    }

    private static void addToPart3(String recipient, String text) {
        Message m = new Message(recipient, text, part3Count + 1);
        messageIDsArr[part3Count] = m.getMessageID();
        messageHashesArr[part3Count] = m.getMessageHash();
        recipientsArr[part3Count] = recipient;
        messagesArr[part3Count] = text;
        messageNumbersArr[part3Count] = part3Count + 1;
        part3Count++;
    }

    public static String displayLongestMessage() {
        if (part3Count == 0) return "No messages.";
        int longest = 0;
        for (int i = 1; i < part3Count; i++) {
            if (messagesArr[i].length() > messagesArr[longest].length()) {
                longest = i;
            }
        }
        return "Longest Message:\nRecipient: " + recipientsArr[longest] +
                "\nMessage: " + messagesArr[longest] +
                "\nHash: " + messageHashesArr[longest];
    }

    public static String searchMessagesByRecipient(String recipient) {
        String result = "";
        for (int i = 0; i < part3Count; i++) {
            if (recipientsArr[i].equals(recipient)) {
                result += "Message " + messageNumbersArr[i] + ": " + messagesArr[i] + "\n";
            }
        }
        if (result.isEmpty()) return "No messages found for " + recipient;
        return result;
    }

    public static String deleteMessageByHash(String hash) {
        for (int i = 0; i < part3Count; i++) {
            if (messageHashesArr[i].equals(hash)) {
                for (int j = i; j < part3Count - 1; j++) {
                    messageIDsArr[j] = messageIDsArr[j + 1];
                    messageHashesArr[j] = messageHashesArr[j + 1];
                    recipientsArr[j] = recipientsArr[j + 1];
                    messagesArr[j] = messagesArr[j + 1];
                    messageNumbersArr[j] = messageNumbersArr[j + 1];
                }
                part3Count--;
                return "Message successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public static String displayReport() {
        if (part3Count == 0) return "No messages to display.";
        String report = "=== MESSAGE REPORT ===\n";
        for (int i = 0; i < part3Count; i++) {
            report += "MessageID: " + messageIDsArr[i] +
                    "\nHash: " + messageHashesArr[i] +
                    "\nRecipient: " + recipientsArr[i] +
                    "\nMessage: " + messagesArr[i] + "\n---\n";
        }
        return report;
    }

    public static int getPart3Count() { return part3Count; }
    public static String[] getHashesArr() { return messageHashesArr; }
}