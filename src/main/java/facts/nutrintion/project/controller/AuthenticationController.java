package facts.nutrintion.project.controller;

import facts.nutrintion.project.dto.request.UserRequest;
import facts.nutrintion.project.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    UserService userService;
    AuthenticationManager authenticationManager;

    @PostMapping("/sign-up")
    public ResponseEntity<String> signUp(@RequestBody UserRequest request) {
        try {
            userService.registerUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to register user: " + e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest request, HttpSession session) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return ResponseEntity.ok("User logged in successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        SecurityContextHolder.clearContext();
        session.invalidate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String responseBody = "Logout successfully";
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

}
