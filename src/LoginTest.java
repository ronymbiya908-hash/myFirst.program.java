import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {
    Login login = new Login();

    @Test
    public void testCheckUserNameCorrect() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrect() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordComplexityCorrect() {
        assertTrue(login.checkPasswordComplexity("Password123!"));
    }

    @Test
    public void testPasswordComplexityIncorrect() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellphoneCorrect() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellphoneIncorrect() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
