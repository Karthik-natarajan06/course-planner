package com.karthik.course_planner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class AuthControllerTest {

    @Autowired
    private AuthController authController;

    @Test
    public void testPasswordIsHashedNotStoredAsPlainText() {
        User user = new User();
        user.setUsername("karthik");
        user.setPassword("test123");

        User saved = authController.register(user);

        assertNotEquals("test123", saved.getPassword());
    }

    @Test
    public void testLoginSucceedsWithCorrectPassword() {
        User user = new User();
        user.setUsername("alice");
        user.setPassword("mypassword");
        authController.register(user);

        User loginAttempt = new User();
        loginAttempt.setUsername("alice");
        loginAttempt.setPassword("mypassword");

        String token = authController.login(loginAttempt);

        assertNotNull(token);
        assertTrue(token.split("\\.").length == 3);
    }

    @Test
    public void testLoginFailsWithWrongPassword() {
        User user = new User();
        user.setUsername("bob");
        user.setPassword("correctpassword");
        authController.register(user);

        User loginAttempt = new User();
        loginAttempt.setUsername("bob");
        loginAttempt.setPassword("wrongpassword");

        assertThrows(UnauthorizedException.class, () -> authController.login(loginAttempt));
    }

}
