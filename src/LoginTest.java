import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LoginTest {
    Login login = new Login();
    @Test
    public void testCheckUserNameCorrect() {
        assertTrue(login.checkUserName("Kyl_1"));
    }
    @Test
    public void testCheckUserNameIncorrect() {
        assertFalse(login.checkUserName("Kyle!!!!!!!!"));
    }
    @Test
    public void testPasswordComplexityIncorrect() {
        assertFalse(login.checkPasswordComplexity("password"));
    }
    @Test
    public void testCellPhoneNumberCorrect() {
}
@Test
public void testCellphoneIncorrect() {
    assertFalse(login.checkCellPhoneNumber(""));
}
    }
