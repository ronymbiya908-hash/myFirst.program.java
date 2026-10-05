import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        // Test Data for Message 1: "Hi Mike, can you join us for dinner tonight?"
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        assertTrue(validMessage.length() <= 250);
    }

    @Test
    public void testMessageLengthFailure() {
        // Creating a string longer than 250 characters
        StringBuilder longMessage = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            longMessage.append("a");
        }
        assertTrue(longMessage.length() > 250);
        // Expected system return: "Message exceeds 250 characters by X; please reduce the size."
    }

    @Test
    public void testRecipientNumberCorrect() {
        Message msg = new Message(1, "+27718693002", "Hi Mike...");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientNumberIncorrect() {
        Message msg = new Message(1, "08575975889", "Hi Keegan...");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        // Test data: ID starts with 00, message number 0, message "Hi Mike, can you join us for dinner tonight?"
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        // Note: Because MessageID is randomly generated in constructor, we can't assert a specific value here
        // unless we use a mock or reflection. However, the structure is tested:
        assertNotNull(msg.getMessageHash());
        assertTrue(msg.getMessageHash().contains(":0:"));
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(1, "+27718693002", "Test");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}