package user;

import customexceptions.ExceededAttemptsException;
import org.junit.jupiter.api.Test;

import storage.Storage;
import userinterface.UI;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseUserTest {
    @Test
    public void sampleTest() throws ExceededAttemptsException {
        UI ui = new UI();
        Storage storage = new Storage("./data");
        BaseUser user = new BaseUser("Bob", "password", ui, storage);
        Authentication auth = user.getAuthentication();
        assertTrue(auth.checkPassword("Bob", "password"));
    }
}
