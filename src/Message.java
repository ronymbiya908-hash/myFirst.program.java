import java.util.Random;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    public Message(int messageNumber, String recipient, String messageText) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Generates a random 10-digit number
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long) (rand.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    // Checks message ID is not more than 10 characters
    public boolean checkMessageID() {
        return this.messageID.length() <= 10;
    }

    // Checks recipient cell number (max 12 chars, starts with code)
    public String checkRecipientCell() {
        if (recipient.length() <= 12 && recipient.startsWith("+")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Creates the Message Hash
    public String createMessageHash() {
        if (messageText == null || messageText.isEmpty()) return "";
        String[] words = messageText.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z]", "");
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "");

        String firstTwoID = messageID.substring(0, 2);

        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Allows user to choose send, store, or disregard
    public String sentMessage(int choice) {
        switch (choice) {
            case 1: return "Message successfully sent.";
            case 2: return "Press 0 to delete the message.";
            case 3: return "Message successfully stored.";
            default: return "Invalid option.";
        }
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageText() { return messageText; }
    public int getMessageNumber() { return messageNumber; }
}