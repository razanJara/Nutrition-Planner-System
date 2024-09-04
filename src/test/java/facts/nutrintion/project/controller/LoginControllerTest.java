package facts.nutrintion.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        loginController = new LoginController();
    }

    @Test
    void testShowAccessDenied() {
        // Act
        ResponseEntity<String> response = loginController.showAccessDenied();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Access Denied. You do not have the necessary permissions to access this resource.", response.getBody());
    }

    @Test
    void testHome() {
        // Act
        ResponseEntity<String> response = loginController.home();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Welcome to the home page!", response.getBody());
    }
}
