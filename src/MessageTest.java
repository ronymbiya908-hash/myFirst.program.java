import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Message ready to send.", msg.checkMessage());
    }

    @Test
    public void testMessageLengthFailure() {
        String longText = "a".repeat(300);
        Message msg = new Message("+27718693002", longText, 0);
        assertEquals("Message exceeds 250 characters by 50, please reduce number and try again.", msg.checkMessage());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("08575975889", "Hi Keegan, did you receive the payment?", 1);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight?", 0);
        msg.messageID = "0012345678"; // pour forcer 00 au début comme dans l'exemple du prof
        String hash = msg.createMessageHash();
        assertEquals("00:0:HITONIGHT", hash);
    }

    @Test
    public void testMessageIDGenerated() {
        Message msg = new Message("+27718693002", "Test", 0);
        assertNotNull(msg.messageID);
        assertEquals(10, msg.messageID.length());
        System.out.println("Message ID generated: " + msg.messageID);
    }

    @Test
    public void testSentMessageSend() {
        Message msg = new Message("+27718693002", "Hello", 0);
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message("+27718693002", "Hello", 0);
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        Message msg = new Message("+27718693002", "Hello", 0);
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    @Test
    public void testTotalMessages() {
        Message.totalMessagesSent = 2;
        Message msg = new Message("+27718693002", "Hello", 2);
        assertEquals(2, Message.returnTotalMessages());
    }
}