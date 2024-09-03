package facts.nutrintion.project.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {
    @GetMapping("/access-denied")
    public ResponseEntity<String> showAccessDenied() {
        return ResponseEntity.ok("Access Denied. You do not have the necessary permissions to access this resource.");
    }

    @GetMapping("/")
    public  ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the home page!");
    }
}
