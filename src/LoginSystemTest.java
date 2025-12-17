import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class LoginSystemTest {

    private final InputStream systemIn = System.in;

    @AfterEach
    void restoreSystemInput() {
        System.setIn(systemIn);
    }

    @Test
    void loginShouldReturnTrueForCorrectCredentials() {
        String input = "admin\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        boolean result = LoginSystem.login();

        assertTrue(result);
    }

    @Test
    void loginShouldReturnFalseForWrongUsername() {
        String input = "wrong\n1234\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        boolean result = LoginSystem.login();

        assertFalse(result);
    }

    @Test
    void loginShouldReturnFalseForWrongPassword() {
        String input = "admin\n0000\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        boolean result = LoginSystem.login();

        assertFalse(result);
    }

    @Test
    void loginShouldReturnFalseForWrongCredentials() {
        String input = "user\npass\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        boolean result = LoginSystem.login();

        assertFalse(result);
    }
}



