package user;

import customexceptions.ExceededAttemptsException;
import org.junit.jupiter.api.Test;

import userinterface.UI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseUserTest {
    @Test
    public void sampleTest() throws ExceededAttemptsException {
        UI ui = new UI();
        BaseUser user = new BaseUser("Bob", ui);
        Authentication auth = user.getAuthentication();
        assertTrue(auth.checkPassword("Bob", "password"));
    }
}
